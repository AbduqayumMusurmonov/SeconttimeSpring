package com.example.seconttimespring.webrest;

import com.example.seconttimespring.entity.Employee;
import com.example.seconttimespring.services.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class EmployeeResource implements Serializable {
    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("employees")
    public ResponseEntity save(@RequestBody Employee employee){
        Employee result = employeeService.save(employee);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("employees")
    public ResponseEntity update(@RequestBody Employee employee){
        if(employee.getId()==null){
            return ResponseEntity.ok("Error");
        }
        Employee result = employeeService.save(employee);
        return ResponseEntity.ok(result);
    }

    @GetMapping("employees/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        Employee result = employeeService.findById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("employees")
    public ResponseEntity getAll(@RequestParam String name){
        List<Employee> result = employeeService.findByQueryParameters(name);
        return ResponseEntity.ok(result);
    }
    @GetMapping("employees/ali")
    public ResponseEntity getAll(){
        List<Employee> result = employeeService.findAll();
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("employees/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.ok("malumot o'chirildi");
    }
}
