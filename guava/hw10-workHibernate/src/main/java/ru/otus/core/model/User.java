package ru.otus.core.model;



import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private AddressDataSet street;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="id")
    private Set<PhoneDataSet> phoneDataSets;

    public void setStreet(AddressDataSet street) {
        this.street = street;
    }

    public void setPhoneDataSets(Set<PhoneDataSet> phoneDataSets) {
        this.phoneDataSets = phoneDataSets;
    }

    public AddressDataSet getStreet() {
        return street;
    }

    public Set<PhoneDataSet> getPhoneDataSets() {
        return phoneDataSets;
    }

    public User() {
    }

    public User(long id, String name, AddressDataSet street, Set<PhoneDataSet> phoneDataSets) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.phoneDataSets = phoneDataSets;
    }
    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
