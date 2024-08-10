package org.example.explore_local.model.view;

public class CityViewModel {

    private long id;

    private String name;

    private String about;
    private int businessesCount;

    public CityViewModel() {
    }

    public CityViewModel(String name, String about, int businessesCount) {
        this.name = name;
        this.about = about;
        this.businessesCount = businessesCount;
    }

    public CityViewModel(long id, String name, String about, int businessesCount) {
        this.id=id;
        this.name = name;
        this.about = about;
        this.businessesCount = businessesCount;
    }

    public long getId() {
        return id;
    }

    public CityViewModel setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CityViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getAbout() {
        return about;
    }

    public CityViewModel setAbout(String about) {
        this.about = about;
        return this;
    }


    public int getBusinessesCount() {
        return businessesCount;
    }

    public CityViewModel setBusinessesCount(int businessesCount) {
        this.businessesCount = businessesCount;
        return this;
    }



    @Override
    public String toString() {
        return name ;
    }



}
