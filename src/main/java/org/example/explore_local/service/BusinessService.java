package org.example.explore_local.service;

import org.example.explore_local.model.dtos.BusinessRegisterBindingModel;
import org.example.explore_local.model.entity.Business;
import org.example.explore_local.model.entity.Category;
import org.example.explore_local.model.entity.City;
import org.example.explore_local.model.entity.User;
import org.example.explore_local.model.enums.CategoryName;
import org.example.explore_local.model.enums.RoleName;
import org.example.explore_local.model.view.BusinessProfileViewModel;
import org.example.explore_local.repository.BusinessRepository;
import org.example.explore_local.repository.CategoryRepository;
import org.example.explore_local.repository.CityRepository;
import org.example.explore_local.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessService {

    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final CategoryService categoryService;
    private final UserService userService;
    private final CategoryRepository categoryRepository;


    private final ModelMapper modelMapper;

    public BusinessService(BusinessRepository businessRepository, UserRepository userRepository, CityRepository cityRepository, CategoryService categoryService, @Lazy UserService userService, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.categoryRepository = categoryRepository;


        this.modelMapper = modelMapper;
    }

    @Transactional
    public long addBusiness(BusinessRegisterBindingModel businessRegisterBindingModel, UserDetails businessOwner) {

        Business business = this.modelMapper.map(businessRegisterBindingModel, Business.class);

        User user = userRepository.findByUsername(businessOwner.getUsername()).orElseThrow(() ->
                new IllegalArgumentException("User with username " + businessOwner.getUsername() + " not found!"));

        if (!isUniqueEmail(business.getEmail())) {
            throw new RuntimeException("Email is taken");
        } else if (!isUniqueName(business.getBusinessName())) {
            throw new RuntimeException("Business Name is taken");
        }

        String name = businessRegisterBindingModel.getCategoryName();

        CategoryName categoryName = categoryService.getCategoryByDisplayName(name);
        Category category = categoryRepository.getCategoryByCategoryName(categoryName);

        business.setCategory(category);

        City city = cityRepository.getCityByName(business.getCity().getName());
        business.setCity(city);

        user.getRoles().add(RoleName.BUSINESS_OWNER);
        user.getOwnedBusinesses().add(business);
        business.setOwner(user);

        cityRepository.save(business.getCity());
        userRepository.save(user);
        businessRepository.save(business);
        city.addBusiness(business);
        category.addBusiness(business);
        categoryRepository.save(category);

        return business.getId();
    }

    public boolean isUniqueName(String name) {
        return this.businessRepository.findByBusinessName(name).isEmpty();
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

    public long getAllBusinessCount() {
        return businessRepository.findAll().size();
    }

    public long getBusinessCountByCategoryName(Category name) {

        return businessRepository.getAllBusinessesByCategory(name).size();
    }

    public List<BusinessProfileViewModel> getAllBusinessProfileViewModel() {
        return businessRepository.findAll()
                .stream()
                .map(BusinessService::mapToBusinessProfileViewModel)
                .toList();
    }
    private static BusinessProfileViewModel mapToBusinessProfileViewModel(Business business) {
        return
                new BusinessProfileViewModel(
                        business.getId(),
                        business.getBusinessName(),
                        business.getCategory(),
                        business.getEmail(),
                        business.getPhoneNumber(),
                        business.getAddress(),
                        business.getAbout());


    }


    public void deleteBusinessById(long id) {
        businessRepository.deleteById(id);
    }

    public void deleteBusiness(Business business) {
        businessRepository.delete(business);
    }

    public BusinessProfileViewModel getBusinessProfileViewById(long id) {

        Optional<Business> business = businessRepository.findById(id);

        if (business.isPresent()) {
            return modelMapper.map(business, BusinessProfileViewModel.class);
        } else {
            throw new IllegalArgumentException("Business not exist!");
        }

    }


    public CategoryName getByCategoryName(String name) {
        String name1 = "";
        if (name.equals("Shopping")) {

            name1 = "SHOPPING";

        } else if (name.equals("Restaurants")) {
            name1 = "RESTAURANTS";


        } else if (name.equals("Nightlife")) {
            name1 = "NIGHTLIFE";


        } else if (name.equals("Local Farms")) {
            name1 = "LOCAL_FARMS";


        } else if (name.equals("Beauty And Spa")) {
            name1 = "BEAUTY_SPA";


        } else if (name.equals("Automotive")) {
            name1 = "AUTOMOTIVE";


        } else if (name.equals("Home Services")) {
            name1 = "HOME_SERVICES";

        }
        return getByCategoryName(name1);
    }

    public Business getById(long id) {

        Optional<Business> byId = businessRepository.findById(id);

        return byId.orElse(null);
    }

    public String getAboutForAllBusinesses() {
        return "Discover the best spots around you, " +
                "from cozy restaurants offering diverse cuisines to vibrant " +
                "nightlife venues where you can unwind after dark. Explore local " +
                "shopping options, from trendy boutiques to essential stores, and find " +
                "trusted professionals for all your home services needs. Pamper yourself at " +
                "nearby beauty and spa centers, and keep your vehicle in top shape with local " +
                "automotive experts. Whatever you're looking for, it's all just around the corner.";
    }


    public List<Business> getAllBusinesses() {
        return businessRepository.findAll();
    }
}
