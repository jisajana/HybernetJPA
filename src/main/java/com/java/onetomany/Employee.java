package com.java.onetomany;

import javax.persistence.*;

@Entity
@Table(name="EMP12")
public class Employee {

    @Id
    @Column(name="EMP_NO")
    private int employeeNumber;

    @Column(name="EMP_NAME")
    private String employeeName;

    @Column(name="EMP_SAL")
    private float employeeSalary;

    public Employee(int employeeNumber, String employeeName, float employeeSalary, Department dept) {
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
        this.dept = dept;
    }

    public Employee(int employeeNumber, String employeeName, float employeeSalary) {
        this.employeeNumber = employeeNumber;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;

    }

    @ManyToOne
    @JoinColumn(name="DNO")//FK
    Department dept;

    public Employee() {
        System.out.println("Employee ");
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public float getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(float employeeSalary) {
        this.employeeSalary = employeeSalary;
    }
}

//sometimes tables are already there,
//and based on tables, we should generate our pojos