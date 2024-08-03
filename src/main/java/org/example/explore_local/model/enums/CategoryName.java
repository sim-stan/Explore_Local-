package org.example.explore_local.model.enums;

public enum CategoryName {

    SHOPPING("Shopping"),
    RESTAURANTS("Restaurants"),
    NIGHTLIFE("Nightlife"),
    LOCAL_FARMS("Local Farms"),
    BEAUTY_SPA("Beauty And Spa"),
    AUTOMOTIVE("Automotive"),
    HOME_SERVICES("Home Services");
    private String displayName;

    private CategoryName(String displayName) {
        this.displayName = displayName;
    }


    public CategoryName getEnumByString(String name) {
        for (CategoryName e : CategoryName.values()) {
            if (e.displayName.equals(name))
                return e;
        }
        return null;
    }

    public String displayName() {
        return this.displayName;
    }


    @Override
    public String toString() {
        return this.displayName;
    }


}


