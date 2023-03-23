package com.example.sescassignment.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account, Long> {

    Boolean existsAccountByUsernameAndPassword(String username, String password);

}
