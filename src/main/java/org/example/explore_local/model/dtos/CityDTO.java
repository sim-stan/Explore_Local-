package org.example.explore_local.model.dtos;

import jakarta.validation.constraints.NotBlank;

public class CityDTO {

    @NotBlank
    private String name;

    @NotBlank
    private String about;

    public CityDTO() {
    }

    public String getName() {
        return name;
    }

    public CityDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAbout() {
        return about;
    }

    public CityDTO setAbout(String about) {
        this.about = about;
        return this;
    }
}
