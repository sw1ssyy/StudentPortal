package com.example.sescassignment.Model;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepo extends JpaRepository<Account, Long> {

    Boolean existsAccountByUsernameAndPassword(String username, String password);
    Account findAccountByUsername(String username);
    Account findAccountById(long id);
    Boolean existsAccountByUsername(String username);

}
