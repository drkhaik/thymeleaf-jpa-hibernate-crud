package com.springboot.thymeleaf.crudwithdb.service;

import com.springboot.thymeleaf.crudwithdb.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    Employee save(Employee theEmployee);

    void deleteById(int theId);

}
