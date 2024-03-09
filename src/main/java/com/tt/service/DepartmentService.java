package com.tt.service;


import com.tt.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAll();

    boolean save(Department department);

    boolean delete(int id);


    Department getById(Integer id);
}

