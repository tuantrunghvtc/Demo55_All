package com.tt.controller;

import com.tt.dto.AccountDTO;
import com.tt.dto.DepartmentDTO;
import com.tt.entity.Account;
import com.tt.entity.AccountRole;
import com.tt.entity.Department;
import com.tt.repository.DepartmentRepository;
import com.tt.service.DepartmentService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("department")
    public String getAll(Model model){
        List<Department> dp = departmentService.getAll();

        List<DepartmentDTO> departmentDTOS = new ArrayList<>();

        dp.forEach(e->{
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(e.getId());
            departmentDTO.setDepartmentName(e.getDepartmentName());

            departmentDTOS.add(departmentDTO);
        });
        model.addAttribute("departmentDTOS",departmentDTOS);
                return "department-list";
    }
    @GetMapping("department/add")
    public String add(){
        return "department-add";
    }

    @PostMapping("department/save")
    public String save(@RequestParam String departmentName

    ){

        Department department = new Department(departmentName);

        departmentService.save(department);

        return "redirect:/department";
    }

    @GetMapping("department-delete/{id}")
    public String delete(@PathVariable int id)
    {
        departmentService.delete(id);
        return "redirect:/department";
    }

    @GetMapping("department/edit/{id}")
    public String edit(@PathVariable int id, Model model){
        Department dp = departmentService.getById(id);
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(dp.getId());
        departmentDTO.setDepartmentName(dp.getDepartmentName());
        model.addAttribute("departmentDTO",departmentDTO);
        return "department-edit";
    }

    @PostMapping("department/update")
    public String save(@RequestParam int id,@RequestParam String departmentName

    ){
        Department department = new Department();
        department.setId(id);
        department.setDepartmentName(departmentName);
        departmentService.save(department);

        return "redirect:/department";
    }

}
