package com.java.onetoone;

import javax.persistence.*;

@Entity
@Table(name="person")
public class Person {

    @Id
    @GeneratedValue // automatically increase the value
    @Column(name="PERSON_ID")
    private int personId;

    @Column(name="PERSON_NAME")
    private String personName;

    @Column(name="PERSON_AGE")
    private int personAge;

    //4th field here - connection field
    @OneToOne
    Passport passport; //passport_passport_id

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getPersonAge() {
        return personAge;
    }

    public void setPersonAge(int personAge) {
        this.personAge = personAge;
    }
}
