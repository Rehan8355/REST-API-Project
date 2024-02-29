package com.rehan.banking.service;

import java.util.List;

import com.rehan.banking.dto.AccountDto;


public interface AccountService {

	//add account
	AccountDto createAccount(AccountDto account);
	
	//get by id
	AccountDto getAccountById(Long id);
	
	
	//deposit method 
	
	AccountDto deposit(Long id, double amount);
	
	//withdraw
	AccountDto withdraw(Long id, double amount);
	
	//get all Accounts
	List<AccountDto> getAllAccounts();
	
	//delete
	
	void deleteAccount(Long id);
	
}
