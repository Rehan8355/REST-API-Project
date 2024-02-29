package com.rehan.banking.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rehan.banking.dto.AccountDto;
import com.rehan.banking.entities.Account;
import com.rehan.banking.mapper.AccountMapper;
import com.rehan.banking.repository.AccountRepository;

@Service
public class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;

   
    public AccountServiceImp(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    //add
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    //get by id
	@Override
	public AccountDto getAccountById(Long id) {
		
	 Account account=	accountRepository
			 .findById(id)
			 .orElseThrow(()->new RuntimeException("Account does not exixst"));
		return AccountMapper.mapToAccountDto(account);
	}

	//deposit
	@Override
	public AccountDto deposit(Long id, double amount) {
		 Account account=	accountRepository
		 .findById(id)
		 .orElseThrow(()->new RuntimeException("Account does not exixst"));
	 double total =account.getBalance()+amount;
	 account.setBalance(total);
	 Account savedAccount = accountRepository.save(account);
		
	return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount) {
	    Account account = accountRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Account does not exist"));

	    // Check if the balance is sufficient for the withdrawal
	    if (account.getBalance() < amount) {
	        throw new RuntimeException("Insufficient balance for withdrawal");
	    }

	    // Subtract the withdrawal amount from the balance
	    double newBalance = account.getBalance() - amount;
	    account.setBalance(newBalance);

	    // Save the updated account with the new balance
	    Account savedAccount = accountRepository.save(account);

	    // Map the updated account to DTO and return it
	    return AccountMapper.mapToAccountDto(savedAccount);
	}

	//get all accounts
	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts=accountRepository.findAll();
		return accounts.stream().map((account)-> AccountMapper.mapToAccountDto(account))
		.collect(Collectors.toList());
		
	}

	
	//delete account
	@Override
	public void deleteAccount(Long id) {
		Account account = accountRepository
				.findById(id)
	            .orElseThrow(() -> new RuntimeException("Account does not exist"));
		
		accountRepository.deleteById(id);

	}

}