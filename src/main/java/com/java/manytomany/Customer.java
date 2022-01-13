package com.java.manytomany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="customer12")
public class Customer {

    @Id
    @GeneratedValue
    @Column(name="cust_id")
    private int customerId;

    @Column(name="cust_name")
    private String customerName;

    @Column(name="cust_email")
    private String customerEmailAddress;



    @ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name="CustomerSubscriptionLink12",
            joinColumns={@JoinColumn(name="cid")},
            inverseJoinColumns={@JoinColumn(name="sid")})
    Set<Subscription> subscriptions = new HashSet<Subscription>();

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmailAddress() {
        return customerEmailAddress;
    }

    public void setCustomerEmailAddress(String customerEmailAddress) {
        this.customerEmailAddress = customerEmailAddress;
    }

    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}