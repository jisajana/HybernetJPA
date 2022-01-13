import com.java.onetomany.Department;
import com.java.onetomany.Employee;
import com.java.onetoone.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;

public class OneToManyTest {
    @Test
    public void insertDeptTest() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("MyJPA");
        System.out.println("Entity Manager Factory : " + entityManagerFactory);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Entity Manager  : " + entityManager);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        System.out.println("Entity transaction : " + entityTransaction);

        entityTransaction.begin();
        System.out.println("Transaction started....");
       /* Department dept1 = new Department();
        dept1.setDepartmentNumber(10);
        dept1.setDepartmentName("IT");
        dept1.setDepartmentLocation("New York");

        entityManager.persist(dept1);*/

        Department dept2 = new Department();
        dept2.setDepartmentNumber(20);
        dept2.setDepartmentName("Test");
        dept2.setDepartmentLocation("New Jersey");

        entityManager.persist(dept2);
        entityTransaction.commit();

    }

    @Test
    public void insertEmployeesToExistingDepartmentTest() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("MyJPA");
        System.out.println("Entity Manager Factory : " + entityManagerFactory);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Entity Manager  : " + entityManager);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        System.out.println("Entity transaction : " + entityTransaction);

        entityTransaction.begin();
        System.out.println("Transaction started....");

        //lets find the dept Java->object [JPA->row] for deptno=10
        Department dept = entityManager.find(Department.class,10);


        Employee employee1 = new Employee(101,"Jack",5000,dept); //<-- are we associating the dept object[ fk ] here? YES
        Employee employee2 = new Employee(102,"Jane",6000,dept);
        Employee employee3 = new Employee(103,"Julie",7000,dept);
        Employee employee4 = new Employee(104,"Julia",8000);
        Employee employee5 = new Employee(105,"Janet",9000);
        employee4.setDept(dept); //setting the FK here
        employee5.setDept(dept); //setting the FK here

        Employee employee6 = new Employee(106,"Robert",8500);

        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.persist(employee3);
        entityManager.persist(employee4);
        entityManager.persist(employee5);
        entityManager.persist(employee6);


        entityTransaction.commit();

    }

    @Test
    public void assignExistingDepartmentToExistingEmployeeTest() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("MyJPA");
        System.out.println("Entity Manager Factory : " + entityManagerFactory);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Entity Manager  : " + entityManager);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        System.out.println("Entity transaction : " + entityTransaction);

        entityTransaction.begin();
        System.out.println("Transaction started....");

        //lets find the dept Java->object [JPA->row] for deptno=10
        Department dept = entityManager.find(Department.class,20);
        Employee emp = entityManager.find(Employee.class,106);

        emp.setDept(dept); //fillup the FK of this emp as of dept
        entityManager.merge(emp);
        entityTransaction.commit();

    }

    @Test
    void insertNewDeptAndNewEmployeeWithoutCascade() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("MyJPA");
        System.out.println("Entity Manager Factory : " + entityManagerFactory);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Entity Manager  : " + entityManager);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        System.out.println("Entity transaction : " + entityTransaction);

        entityTransaction.begin();
        System.out.println("Transaction started....");


        // new dept row
        Department dept = new Department();
        dept.setDepartmentNumber(40);
        dept.setDepartmentName("Sales");
        dept.setDepartmentLocation("New Delhi");

        //new employees for this new dept
        Employee employee1 = new Employee(107,"Seeta",9000,dept); //<-- are we associating the dept object[ fk ] here? YES
        Employee employee2 = new Employee(108,"Geeta",9500,dept);
        Employee employee3 = new Employee(109,"Reeta",8500,dept);


        entityManager.persist(dept);

        entityManager.persist(employee1);
        entityManager.persist(employee2);
        entityManager.persist(employee3);

        entityTransaction.commit();

    }

    @Test
    void insertNewDeptAndNewEmployeeWithCascade() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("MyJPA");
        System.out.println("Entity Manager Factory : " + entityManagerFactory);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Entity Manager  : " + entityManager);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        System.out.println("Entity transaction : " + entityTransaction);

        entityTransaction.begin();
        System.out.println("Transaction started....");


        // new dept row
        Department dept = new Department();
        dept.setDepartmentNumber(50);
        dept.setDepartmentName("Operations");
        dept.setDepartmentLocation("New Mumbai");

        //new employees for this new dept
        Employee employee1 = new Employee(110,"John",7000,dept); //<-- are we associating the dept object[ fk ] here? YES
        Employee employee2 = new Employee(111,"Johny",8500,dept);
        Employee employee3 = new Employee(112,"Janardan",9500,dept);

        //do we have to invoke persist for dept, employee1, employee2, employee3
        //NOW WE NEED NOT CALL persist for four times
        // just the persistence of dept will take of emp persistence
        Set<Employee> staff = new HashSet<Employee>(); //empty set
        staff.add(employee1); //fill up the set
        staff.add(employee2); //fill up the set
        staff.add(employee3); //fill up the set

        dept.setEmpSet(staff); //dept is aware of the staff

        entityManager.persist(dept);

        entityTransaction.commit();

    }

    @Test
    void findEmployeesBasedOnDepartmentTest() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("MyJPA");
        System.out.println("Entity Manager Factory : " + entityManagerFactory);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Entity Manager  : " + entityManager);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        System.out.println("Entity transaction : " + entityTransaction);

        entityTransaction.begin();
        System.out.println("Transaction started....");

        Department dept = entityManager.find(Department.class,10); //search for this row

// select department0_.DEPTNO as deptno1_0_0_,
// department0_.DEPT_LOC as dept_loc2_0_0_,
// department0_.DEPT_NAME as dept_nam3_0_0_
// from DEPT12 department0_
// where department0_.DEPTNO=?

        Assertions.assertNotNull(dept); // means if dept found
        System.out.println("Dept Number   : "+ dept.getDepartmentNumber());
        System.out.println("Dept Name     : "+ dept.getDepartmentName());
        System.out.println("Dept location : "+ dept.getDepartmentLocation());
        System.out.println("================================");


        Set<Employee> staff =  dept.getEmpSet(); //based on dept, now find its employees
        //note that we are not calling entityManager.find for employee objects

        //Hibernate: select empset0_.DNO as dno4_1_0_,
        // empset0_.EMP_NO as emp_no1_1_0_,
        // empset0_.EMP_NO as emp_no1_1_1_,
        // empset0_.DNO as dno4_1_1_,
        // empset0_.EMP_NAME as emp_name2_1_1_,
        // empset0_.EMP_SAL as emp_sal3_1_1_
        // from EMP12 empset0_
        // where empset0_.DNO=?


        Assertions.assertTrue(staff.size() > 0 ); //if the staff has Employees in it

        for(Employee employee : staff) {
            System.out.println("Employee Number : "+ employee.getEmployeeNumber());
            System.out.println("Employee Name   : "+ employee.getEmployeeName());
            System.out.println("Employee Salary : "+ employee.getEmployeeSalary());
            System.out.println("----------------");
        }

    }

    @Test
    void findEmployeesBasedOnDepartmentWithFetchPolicyTest() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("MyJPA");
        System.out.println("Entity Manager Factory : " + entityManagerFactory);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        System.out.println("Entity Manager  : " + entityManager);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        System.out.println("Entity transaction : " + entityTransaction);

        entityTransaction.begin();
        System.out.println("Transaction started....");

        Department dept = entityManager.find(Department.class,10); //search for this row

        //join query is fired cause of fetchPolicy as EAGER
        //  Hibernate:
        //  select
        //  department0_.DEPTNO as deptno1_2_0_,
        //  department0_.DEPT_LOC as dept_loc2_2_0_,
        //  department0_.DEPT_NAME as dept_nam3_2_0_,

        //  empset1_.DNO as dno4_3_1_,
        //  empset1_.EMP_NO as emp_no1_3_1_,
        //  empset1_.EMP_NO as emp_no1_3_2_,
        //  empset1_.DNO as dno4_3_2_,
        //  empset1_.EMP_NAME as emp_name2_3_2_,
        //  empset1_.EMP_SAL as emp_sal3_3_2_

        //  from DEPT12 department0_
        //  left outer join EMP12 empset1_

        //  on department0_.DEPTNO=empset1_.DNO  -- matching the dept.deptno with emp.deptno

        //  where department0_.DEPTNO=? -- for deptno 10


        Assertions.assertNotNull(dept); // means if dept found
        System.out.println("Dept Number   : "+ dept.getDepartmentNumber());
        System.out.println("Dept Name     : "+ dept.getDepartmentName());
        System.out.println("Dept location : "+ dept.getDepartmentLocation());
        System.out.println("================================");


        Set<Employee> staff =  dept.getEmpSet(); //based on dept, now find its employees
        //note that we are not calling entityManager.find for employee objects

        Assertions.assertTrue(staff.size() > 0 ); //if the staff has Employees in it

        for(Employee employee : staff) {
            System.out.println("Employee Number : "+ employee.getEmployeeNumber());
            System.out.println("Employee Name   : "+ employee.getEmployeeName());
            System.out.println("Employee Salary : "+ employee.getEmployeeSalary());
            System.out.println("----------------");
        }

    }
}
/*

Hibernate:
create table DEPT12
(DEPTNO integer not null,
DEPT_LOC varchar(255),
DEPT_NAME varchar(255),
primary key (DEPTNO))

Hibernate:
create table EMP12
(EMP_NO integer not null,
EMP_NAME varchar(255),
EMP_SAL float,
DNO integer,
primary key (EMP_NO))

Hibernate:
alter table EMP12
add constraint FKkfjgpfa1p8t4umgeg1o4aj7jc
foreign key (DNO) references DEPT12
En


 */


