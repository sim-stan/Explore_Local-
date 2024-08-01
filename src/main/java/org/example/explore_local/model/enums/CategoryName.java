package org.example.explore_local.model.enums;

public enum CategoryName {

    SHOPPING ("Shopping"),
    RESTAURANTS ("Restaurants"),
    NIGHTLIFE ("Nightlife"),
    LOCAL_FARMS ("Local Farms"),
    BEAUTY_SPA ("Beauty And Spa"),
    AUTOMOTIVE ("Automotive"),
    HOME_SERVICES ("Home Services");
    private String displayName;

    CategoryName (String displayName) {
        this.displayName=displayName;
    }

    public String displayName() {
        return displayName;
    }
    @Override public String toString(){
        return displayName;
    }
}


