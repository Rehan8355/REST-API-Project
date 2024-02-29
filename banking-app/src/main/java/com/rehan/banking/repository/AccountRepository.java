package com.rehan.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rehan.banking.entities.Account;


public interface AccountRepository  extends JpaRepository<Account,Long> {

}
