package com.nttdata.bank.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.nttdata.bank.account.client.ProductClientRest;
import com.nttdata.bank.account.client.TransactionClientRest;
import com.nttdata.bank.account.dto.AccountDTO;
import com.nttdata.bank.account.model.Account;
import com.nttdata.bank.account.repository.AccountRepository;
import com.nttdata.bank.account.service.AccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionClientRest transactionClientRest;
	
	@Autowired
	private ProductClientRest productClientRest;
	
	@Autowired
	private JsonMapper jsonMapper;

	@Override
	public Flux<AccountDTO> findByCustomerId(String customerId) {
		//return accountRepository.findAccountByCustomerId(customerId);
		
		Flux<AccountDTO> accountDTO = accountRepository.findAccountByCustomerId(customerId).map(a -> convertirAAccountDTO(a));
		
		return accountDTO.flatMap( account -> 
										Mono.just(account)
										.zipWith(transactionClientRest.findByAccountId(account.get_id())
													.collectList(),
													(a, t) -> {
														a.setTransactions(t);
														return a;
													}
												)
										.zipWith(productClientRest.findById(account.getProductId())
													,(a, p) -> {
														a.setProduct(p);
														return a;
													}
												)
								);
		
	}

	@Override
	public Mono<Account> save(Account account) {
		return accountRepository.save(account);
	}
	
	private AccountDTO convertirAAccountDTO(Account account) {
		return jsonMapper.convertValue(account, AccountDTO.class);
	}

}
