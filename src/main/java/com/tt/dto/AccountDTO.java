package com.tt.dto;


import com.tt.entity.AccountRole;
import lombok.Data;

@Data
public class AccountDTO {
    private Integer id;

    private String username;

    private String fullName;

    private AccountRole role;

    private boolean isActive;

    private String departmentName;
}
