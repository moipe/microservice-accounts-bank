package com.nttdata.bank.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movement {
	
	private String _id;
	
	private Byte category;
	
	private String type;
	
	private String description;
	
	private Double amount;
	
	private String createAt;
	
	private String externalAccount;
	
	@JsonIgnore
	private String accountId;

}
