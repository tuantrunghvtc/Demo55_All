package com.tt.controller;


import com.tt.dto.AccountDTO;
import com.tt.dto.DepartmentDTO;
import com.tt.entity.Account;
import com.tt.entity.AccountRole;
import com.tt.entity.Department;
import com.tt.repository.AccountRepository;

import com.tt.service.AccountService;
import com.tt.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;

//@RestController: Viêt resful Api
//@Controller: Dung giao dien thymleaf
@Controller
@Slf4j
public class AccountController {


    private AccountService accountService;

    private DepartmentService departmentService;

    public AccountController(AccountService accountService, DepartmentService departmentService) {
        this.accountService = accountService;
        this.departmentService = departmentService;
    }

    @GetMapping("account")
    public String getAll(Model model){

        log.info("Get all account");
        log.error("Account id to delete is not found");

        List<Account> accounts = accountService.getAll();

        List<AccountDTO> accountDTOS = new ArrayList<>();

        accounts.forEach(e ->{
            AccountDTO accountDTO = new AccountDTO();

            accountDTO.setId(e.getId());
            accountDTO.setUsername(e.getUsername());
            accountDTO.setFullName(e.getFullName());
            accountDTO.setRole(e.getRole());
            accountDTO.setActive(e.isActive());

            if(e.getDepartment() != null){
                accountDTO.setDepartmentName((e.getDepartment().getDepartmentName()));
            }

            accountDTOS.add(accountDTO);
        });

        model.addAttribute("accountDTOS",accountDTOS);
        return "account-list";
    }

    @GetMapping("account/add")
    public String add(Model model) {
        List<Department> departments = departmentService.getAll();
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();

        departments.forEach(department -> {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(department.getId());
            departmentDTO.setDepartmentName(department.getDepartmentName());

            departmentDTOS.add(departmentDTO);
        });
        model.addAttribute("departmentDTOS", departmentDTOS);

        return "account-add";
    }

    @GetMapping("account-delete/{id}")
    public String delete(@PathVariable int id)
    {
        accountService.delete(id);
        return "redirect:/account";
    }

    @PostMapping("account/save")
    public String save(@RequestParam String username,
    @RequestParam String password,
    @RequestParam String fullName,
    @RequestParam String role,
    @RequestParam Integer departmentId

    ){
        AccountRole accountRole = null;

        if(role.equals("USER") || role.equals((""))){
            accountRole = AccountRole.USER;
        }
        if(role.equals("ADMIN")){
            accountRole = AccountRole.ADMIN;
        }

        password = new BCryptPasswordEncoder().encode(password);

        Account account = new Account(username,password, fullName, accountRole);

        if (departmentId != 0) {
            Department department = departmentService.getById(departmentId);
            account.setDepartment(department);
        }

        accountService.save(account);

        return "redirect:/account";
    }

    @GetMapping("account-edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        log.info("Edit account with id: " + id);

        Account account = accountService.getById(id);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setUsername(account.getUsername());
        accountDTO.setFullName(account.getFullName());
        accountDTO.setRole(account.getRole());
        accountDTO.setActive(account.isActive());
        if (account.getDepartment() != null) {
            accountDTO.setDepartmentName(account.getDepartment().getDepartmentName());
        }

        model.addAttribute("accountDTO", accountDTO);

        List<Department> departments = departmentService.getAll();
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();

        departments.forEach(department -> {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(department.getId());
            departmentDTO.setDepartmentName(department.getDepartmentName());

            departmentDTOS.add(departmentDTO);
        });
        model.addAttribute("departmentDTOS", departmentDTOS);

        return "account-edit";
    }

    @PostMapping("/account/update")
    public String update (@RequestParam Integer id,
                          @RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String fullName,
                          @RequestParam String role,
                          @RequestParam Integer departmentId
                          ){
        Account account = accountService.getById(id);
        account.setUsername(username);
        account.setFullName(fullName);

        AccountRole accountRole = null;

        if(role.equals("USER") || role.equals((""))){
            accountRole = AccountRole.USER;
        }
        if(role.equals("ADMIN")){
            accountRole = AccountRole.ADMIN;
        }

        password = new BCryptPasswordEncoder().encode(password);

        account.setRole(accountRole);
        account.setPassword(password);

        if (departmentId != 0) {
            Department department = departmentService.getById(departmentId);
            account.setDepartment(department);
        }

        accountService.save(account);

        return "redirect:/account";
    }

        @GetMapping("/account-search")public String search(String data, Model model){

        List<Account> account = accountService.search(data);

            List<AccountDTO> accountDTOS = new ArrayList<>();

            account.forEach(e -> {
                AccountDTO accountDTO = new AccountDTO();

                accountDTO.setId(e.getId());
                accountDTO.setUsername(e.getUsername());
                accountDTO.setFullName(e.getFullName());
                accountDTO.setRole(e.getRole());
                accountDTO.setActive(e.isActive());

                if (e.getDepartment() != null) {
                    accountDTO.setDepartmentName((e.getDepartment().getDepartmentName()));
                }

                accountDTOS.add(accountDTO);

            });
            model.addAttribute("accountDTOS",accountDTOS);
            return "account-list";

        }
}
