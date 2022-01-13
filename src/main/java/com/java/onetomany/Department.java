package com.java.onetomany;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="DEPT12")
public class Department {

    @Id
    @Column(name="DEPTNO")
    private int departmentNumber;

    @Column(name="DEPT_NAME")
    private String departmentName;

    @Column(name="DEPT_LOC")
    private String departmentLocation;

    @OneToMany(mappedBy = "dept", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<Employee> empSet;

    public Set<Employee> getEmpSet() {
        return empSet;
    }

    public void setEmpSet(Set<Employee> empSet) {
        this.empSet = empSet;
    }

    public int getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(int departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentLocation() {
        return departmentLocation;
    }

    public void setDepartmentLocation(String departmentLocation) {
        this.departmentLocation = departmentLocation;
    }
}

//sometimes tables are already there,
//and based on tables, we should generate our pojos

/*



 */