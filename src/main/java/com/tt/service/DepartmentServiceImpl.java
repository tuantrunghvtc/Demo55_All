package com.tt.service;


import com.tt.entity.Department;
import com.tt.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements  DepartmentService{

    private DepartmentRepository departmentRepo;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepo = departmentRepository;
    }

    @Override
    public List<Department> getAll() {
        List<Department> departments = departmentRepo.findAll();

        return  departments;
    }

    @Override
    public boolean save(Department department) {
        departmentRepo.save(department);
        return true;
    }

    @Override
    public boolean delete(int id) {
        departmentRepo.deleteById(id);
        return true;
    }


    @Override
    public Department getById(Integer id) {
        Department department = departmentRepo.findById(id).orElse(null);;
        return department;
    }
}
