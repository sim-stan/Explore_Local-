package org.example.explore_local.service;

import org.example.explore_local.model.entity.Category;
import org.example.explore_local.model.enums.CategoryName;
import org.example.explore_local.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


//    public List<String> getAllCategoryNames() {
//      return   categoryRepository.findAll().stream().map(a->a.getCategoryName()).collect(Collectors.toList());
//
//    }


    public Category getCategoryByCategoryName(CategoryName name){
        return categoryRepository.getCategoryByCategoryName(name);
    }



//    public List<String> getCategoryNames() {
//     List<String> categoryNames=new ArrayList<>();
//
//        categoryNames.add("Shopping");
//        categoryNames.add("Restaurants");
//        categoryNames.add("Nightlife");
//        categoryNames.add("Local Farms");
//        categoryNames.add("Beauty And Spa");
//        categoryNames.add("Automotive");
//        categoryNames.add("Home Services");
//
//        return categoryNames;
//    }




    public void seedCategories() {
        if (categoryRepository.count()<=0){


            Category category1=new Category();

            category1.setCategoryName(CategoryName.AUTOMOTIVE);
            category1.setDescription("Find local professionals for all your automotive needs, " +
                    "from routine maintenance to repairs and customization. Whether it's oil changes," +
                    " brake services, bodywork, or upgrades, local automotive experts offer reliable " +
                    "service to keep your vehicle running smoothly and looking its best. Enjoy convenience" +
                    " and expertise for all your car care needs.");
            categoryRepository.save(category1);


            Category category2=new Category();

            category2.setCategoryName(CategoryName.HOME_SERVICES);
            category2.setDescription("Find local professionals for a range of home " +
                    "maintenance and improvement needs, including HVAC repair, painting, " +
                    "carpentry, plumbing, electrical work, and more. Whether you're renovating, " +
                    "repairing, or maintaining your home, local service providers offer expertise and " +
                    "convenience to keep your living space in top condition.");
            categoryRepository.save(category2);

            Category category3=new Category();

            category3.setCategoryName(CategoryName.BEAUTY_SPA);
            category3.setDescription("Indulge in relaxation and rejuvenation with a variety " +
                    "of beauty and spa services. From massages and facials to haircuts and manicures, " +
                    "local beauty and spa professionals offer personalized treatments to enhance your" +
                    " well-being and appearance. Whether you're seeking pampering or therapeutic care, " +
                    "these services provide a tranquil escape for self-care and rejuvenation.");
            categoryRepository.save(category3);


            Category category4=new Category();

            category4.setCategoryName(CategoryName.LOCAL_FARMS);
            category4.setDescription("Find fresh, locally grown fruits and vegetables," +
                    " dairy products, and more at your nearby local farms. " +
                    "Enjoy seasonal produce, farm-fresh eggs, and even U-Pick options for a fun family outing. " +
                    "Support sustainable agriculture and your local economy by purchasing directly from farmers.");
            categoryRepository.save(category4);


            Category category5=new Category();

            category5.setCategoryName(CategoryName.NIGHTLIFE);
            category5.setDescription("Explore a vibrant nightlife scene with options ranging from lively bars and clubs" +
                    " to cozy lounges and entertainment venues. Dance the night away to live music or DJ sets," +
                    " enjoy cocktails at stylish lounges, or discover themed bars for a unique experience. " +
                    "Whether you're into comedy shows, karaoke, or simply relaxing with friends, nightlife venues " +
                    "offer something for everyone to unwind and socialize after dark.");
            categoryRepository.save(category5);

            Category category6=new Category();

            category6.setCategoryName(CategoryName.RESTAURANTS);
            category6.setDescription("Explore a variety of dining experiences from casual eateries " +
                    "to fine dining establishments. Enjoy diverse menus featuring local and international " +
                    "cuisines, served in welcoming atmospheres ranging from cozy cafes to elegant dining rooms. " +
                    "Whether you're craving quick bites or a leisurely meal, restaurants offer delicious options " +
                    "to satisfy every palate and occasion.");
            categoryRepository.save(category6);

            Category category7=new Category();

            category7.setCategoryName(CategoryName.SHOPPING);
            category7.setDescription("Explore a diverse shopping experience with a " +
                    "variety of options from local boutiques to large retail stores. " +
                    "Discover fashion trends, unique gifts, and household essentials." +
                    " Whether you prefer shopping malls, specialty shops, or online platforms, " +
                    "find everything you need and enjoy personalized service and convenience.");
            categoryRepository.save(category7);


        }

    }
}
