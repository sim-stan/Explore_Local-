package org.example.explore_local.model.view;

import org.example.explore_local.model.entity.Business;

import java.util.List;

public class CategoryViewModel {

    private long id;

    private String name;

    private String about;


    private List<Business> businesses;

    public CategoryViewModel() {
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

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
    }
}
