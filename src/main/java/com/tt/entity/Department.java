package com.tt.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name ="tbl_department")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Column(nullable = false ,length = 50)
    private String departmentName;

    @OneToMany (mappedBy = "department")
    private List<Account> accounts;
}
