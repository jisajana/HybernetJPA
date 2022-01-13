package com.java.manytomany;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="subscription12")
public class Subscription {

    @Id
    @GeneratedValue
    @Column(name="sub_id")
    private int subscriptionId;

    @Column(name="sub_type")
    private String subscriptionType;

    @Column(name="sub_duration")
    private String subscriptionDuration;

    @ManyToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(name="CustomerSubscriptionLink12",
            joinColumns={@JoinColumn(name="sid")},
            inverseJoinColumns={@JoinColumn(name="cid")})
    Set<Customer> customers = new HashSet<Customer>();

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public String getSubscriptionDuration() {
        return subscriptionDuration;
    }

    public void setSubscriptionDuration(String subscriptionDuration) {
        this.subscriptionDuration = subscriptionDuration;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
