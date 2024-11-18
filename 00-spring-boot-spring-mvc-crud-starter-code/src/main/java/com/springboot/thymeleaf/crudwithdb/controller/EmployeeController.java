package com.springboot.thymeleaf.crudwithdb.controller;


import com.springboot.thymeleaf.crudwithdb.entity.Employee;
import com.springboot.thymeleaf.crudwithdb.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService){
        this.employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel){

        // get the employees from db
        List<Employee> theEmployees = employeeService.findAll();

        // add to spring model
        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    @GetMapping("/showFormAddEmployee")
    public String addEmployees(Model theModel){

        // create model attributes to bind form data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        System.out.println(theEmployee);
        employeeService.save(theEmployee);

        return "redirect:/employees/list";
    }

    @GetMapping("/showFormUpdateEmployee")
    public String updateEmployees(@RequestParam("employeeId") int theId, Model theModel){

        // create model attributes to bind form data
        Employee theEmployee = employeeService.findById(theId);
        if(theEmployee != null){
            theModel.addAttribute("employee", theEmployee);
        }

        return "employees/employee-form";
    }

    @GetMapping("/delete")
    public String deleteEmployees(@RequestParam("employeeId") int theId){

        // create model attributes to bind form data
        Employee theEmployee = employeeService.findById(theId);
        if(theEmployee != null){
            employeeService.deleteById(theId);
        }

        return "redirect:/employees/list";
    }

}
