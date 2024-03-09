package com.tt.service;

import com.tt.entity.Account;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends UserDetailsService {


    List<Account> getAll();

    boolean save(Account account);

    boolean delete(int id);


    Account getById(int id);

    List<Account> search(String data);
}
