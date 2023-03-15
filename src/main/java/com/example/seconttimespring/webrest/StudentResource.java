package com.example.seconttimespring.webrest;

import com.example.seconttimespring.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentResource {

//    @GetMapping("/students")
//    public ResponseEntity hello(){
//        return ResponseEntity.ok("Hello world");
//    }

    @PostMapping("/students")
    public ResponseEntity create(@RequestBody Student student){
        return ResponseEntity.ok(student);
    }

    @PutMapping("/students")
    public ResponseEntity update(@RequestBody Student student){
        student.setLastName("test uchun");
        return ResponseEntity.ok(student);
    }
    @PutMapping("/students/{id}")
    public ResponseEntity updateSecond(@PathVariable Long id,@RequestBody Student student){
        student.setLastName("test uchun");
        student.setId(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getOne(@PathVariable Long id){
        Student student = new Student();
        student.setId(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/students")
    public ResponseEntity getList(@RequestParam Long id,
                                  @RequestParam String name,
                                  @RequestParam String lastName,
                                  @RequestParam Integer age){
        List<Student> list = new ArrayList<>();
        list.add(new Student(id,name,lastName,age));
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("students/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return ResponseEntity.ok(id+" malumot o'chirildi");
    }
}
