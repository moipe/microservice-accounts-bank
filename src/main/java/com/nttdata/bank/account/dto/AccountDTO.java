package com.nttdata.bank.account.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nttdata.bank.account.model.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class AccountDTO {
	
	private String _id;
	
	private Double balance;
	
	private String customerId;
	
	private List<Transaction> transactions = new ArrayList<Transaction>();

}
