package com.java.onetoone;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="passport")
public class Passport {

    @Id
    @GeneratedValue
    @Column(name="PASSPORT_ID")
    private int passportId;

    @Column(name="PASSPORT_ISSUED_BY")
    private String issuedBy;

    @Column(name="PASSPORT_EXPIRY")
    private LocalDate passportExpiry;

    //4th field here - connection field
    @OneToOne
    Person person; //person_person_id

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getPassportId() {
        return passportId;
    }

    public void setPassportId(int passportId) {
        this.passportId = passportId;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public LocalDate getPassportExpiry() {
        return passportExpiry;
    }

    public void setPassportExpiry(LocalDate passportExpiry) {
        this.passportExpiry = passportExpiry;
    }
}
