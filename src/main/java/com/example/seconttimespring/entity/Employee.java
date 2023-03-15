package com.example.seconttimespring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Set;

@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    @NotNull
    @Size(min = 5,max = 150)
    @Email
    @Column(name = "email",length = 150,unique = true,nullable = false)
    private String email;

    @OneToOne(optional = false)
    @JoinColumn(name = "account_id",unique = true,nullable = false)
    private Account account;

    @OneToMany
    private Set<Item> item;

    @ManyToMany
    @JoinTable(
            name = "dev_employee_project",
            joinColumns = {@JoinColumn(name = "employee",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "project_id",referencedColumnName = "id")}
    )
    private Set<Project> project;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Item> getItem() {
        return item;
    }

    public void setItem(Set<Item> item) {
        this.item = item;
    }

    public Set<Project> getProject() {
        return project;
    }

    public void setProject(Set<Project> project) {
        this.project = project;
    }
}
