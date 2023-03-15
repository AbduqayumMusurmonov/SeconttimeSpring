package com.example.seconttimespring.services;

import com.example.seconttimespring.entity.Employee;
import com.example.seconttimespring.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }
    public void delete(Long id){
         employeeRepository.deleteById(id);
    }

    public Employee findById(Long id){
        Employee result = employeeRepository.findById(id).get();
        return result;
    }

    public List<Employee> findAll(String name){
        List<Employee> employees = employeeRepository.findAll(name);
        return employees;
    }

    public List<Employee> findByQueryParameters(String name){
        List<Employee> result = employeeRepository.findAllByNameLike(name);
        return result;
    }
    public List<Employee> findAll(){
        List<Employee> result = employeeRepository.findAll();
        return result;
    }
}
