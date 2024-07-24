package org.example.explore_local.model.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "city",fetch = FetchType.EAGER)
    private List<Business> businesses;


    public City() {
        this.businesses=new ArrayList<>();
    }

    public City(long id, String name, List<Business> businesses) {
        this.id = id;
        this.name = name;
        this.businesses = businesses;
    }


    public long getId() {
        return id;
    }

    public City setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public City setName(String name) {
        this.name = name;
        return this;
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public City setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
        return this;
    }
}
