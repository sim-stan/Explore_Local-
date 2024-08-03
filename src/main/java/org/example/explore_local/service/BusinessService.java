package org.example.explore_local.service;

import org.example.explore_local.model.dtos.BusinessRegisterBindingModel;
import org.example.explore_local.model.entity.Business;
import org.example.explore_local.model.entity.City;
import org.example.explore_local.model.entity.User;
import org.example.explore_local.model.enums.CategoryName;
import org.example.explore_local.model.enums.RoleName;
import org.example.explore_local.model.view.BusinessProfileViewModel;
import org.example.explore_local.repository.BusinessRepository;
import org.example.explore_local.repository.CityRepository;
import org.example.explore_local.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BusinessService {

    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final CategoryService categoryService;



    private final ModelMapper modelMapper;

    public BusinessService(BusinessRepository businessRepository, UserRepository userRepository, CityRepository cityRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.categoryService = categoryService;



        this.modelMapper = modelMapper;
    }

    @Transactional
    public long addBusiness(BusinessRegisterBindingModel businessRegisterBindingModel, UserDetails businessOwner) {

        Business business = this.modelMapper.map(businessRegisterBindingModel, Business.class);

        User user = userRepository.findByUsername(businessOwner.getUsername()).orElseThrow(() ->
                new IllegalArgumentException("User with username " + businessOwner.getUsername() + " not found!"));

        if (!isUniqueEmail(business.getEmail())) {
            throw new RuntimeException("Email is taken");
        } else if (!isUniqueName(business.getName())) {
            throw new RuntimeException("Business Name is taken");
        }

        System.out.println(business.getCategory().getCategoryName());


        City city=cityRepository.getCityByName(business.getCity().getName());
        business.setCity(city);



//
//        Category category=categoryService.getCategoryByCategoryName(name);
//        business.setCategory(category);

        user.getRoles().add(RoleName.BUSINESS_OWNER);
        user.getOwnedBusinesses().add(business);
        business.setOwner(user);


        cityRepository.save(business.getCity());
        userRepository.save(user);
        businessRepository.save(business);
        city.addBusinesses(business);
        return business.getId();
    }

    public boolean isUniqueName(String name) {
        return this.businessRepository.findByName(name).isEmpty();
    }

    public boolean isUniqueEmail(String email) {
        return this.businessRepository.findByEmail(email).isEmpty();
    }

    public boolean isOwner(long id, String userName) {

        Business business = businessRepository.findById(id).orElse(null);

        if (business != null) {

            User user = userRepository.findById(business.getOwner().getId()).orElseThrow(() -> new IllegalArgumentException("Unknown user..."));

            return user.getUsername().equals(userName);
        } else {
            return false;
        }

    }


    //    @Transactional
    public void deleteBusinessById(long id) {
        businessRepository.deleteById(id);
    }

    public void deleteBusiness(Business business) {
        businessRepository.delete(business);
    }

    public BusinessProfileViewModel getBusinessProfileView(long id) {

        Optional<Business> business = businessRepository.findById(id);

        if (business.isPresent()) {
            return modelMapper.map(business, BusinessProfileViewModel.class);
        } else {
            throw new IllegalArgumentException("Business not exist!");
        }

    }


    public CategoryName getByCategoryName(String name) {
        String name1="";
        if (name.equals("Shopping")){

            name1="SHOPPING";

        } else if (name.equals("Restaurants")) {
            name1="RESTAURANTS";


        } else if (name.equals("Nightlife")) {
            name1="NIGHTLIFE";


        } else if (name.equals("Local Farms")) {
            name1="LOCAL_FARMS";


        } else if (name.equals("Beauty And Spa")) {
            name1="BEAUTY_SPA";


        } else if (name.equals("Automotive")) {
            name1="AUTOMOTIVE";


        } else if (name.equals("Home Services")) {
            name1="HOME_SERVICES";

        }
        return getByCategoryName(name1);
    }
}
