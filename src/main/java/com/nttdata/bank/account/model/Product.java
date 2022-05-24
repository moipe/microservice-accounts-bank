package com.nttdata.bank.account.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	
	private String _id;
	
	private String name;
	
	private Byte type;
	
	private Byte category;
	
	private String description;
	
	private Float commissionMaintenance;
	
	private Byte maxTransactions;
	
	private Float commissionTransaction;

}