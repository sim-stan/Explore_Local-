package org.example.explore_local.model.entity;


import jakarta.persistence.*;
import org.example.explore_local.model.enums.CategoryName;

import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private CategoryName categoryName;
    @OneToMany(mappedBy = "category")
    private List<Business> businesses;

    @Column(columnDefinition = "TEXT")
    private String description;

    public Category() {}



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryName getCategoryName() {
        return categoryName;
    }

    public Category setCategoryName(CategoryName categoryName) {
        this.categoryName = categoryName;
        return this;
    }

    public List<Business> getBusinesses() {
        return businesses;
    }

    public Category setBusinesses(List<Business> businesses) {
        this.businesses = businesses;
        return this;
    }
}
