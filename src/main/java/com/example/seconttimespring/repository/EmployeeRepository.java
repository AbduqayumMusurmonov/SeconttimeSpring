package com.example.seconttimespring.repository;

import com.example.seconttimespring.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
//    @Query("select e from Employee e where e.name = :name")
//    List<Employee> findAll(@Param("name") String name);

    List<Employee> findAllByNameAndLastName(String name,String lastName);
    @Query(value = "SELECT * from Employee e where e.name = :name",nativeQuery = true)
    List<Employee> findAll(@Param("name") String name);

    List<Employee> findAllByNameLike(String name);
}
