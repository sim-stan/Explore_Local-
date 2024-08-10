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


    private String about;


    public City() {
        this.businesses=new ArrayList<>();
    }

    public City( String name, List<Business> businesses) {

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

    public int businessesCount(){

                if(getBusinesses().isEmpty()){
                    return 0;
                }else {
                    return getBusinesses().size();
                }
    }

    public String getAbout() {
        return about;
    }

    public City setAbout(String about) {
        this.about = about;
        return this;
    }
    public void addBusiness(Business businesses) {
        this.businesses.add(businesses);
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", businesses=" + businesses +
                ", about='" + about + '\'' +
                '}';
    }
}
