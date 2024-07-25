package org.example.explore_local.service;

import org.example.explore_local.model.dtos.BusinessRegisterBindingModel;
import org.example.explore_local.model.entity.Business;
import org.example.explore_local.model.entity.User;
import org.example.explore_local.model.enums.RoleName;
import org.example.explore_local.model.view.BusinessProfileViewModel;
import org.example.explore_local.repository.BusinessRepository;
import org.example.explore_local.repository.CityRepository;
import org.example.explore_local.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BusinessService {

    private final BusinessRepository businessRepository;
    private final UserRepository userRepository;
    private final CityRepository cityRepository;
    private final RoleService roleService;

    private final ModelMapper modelMapper;

    public BusinessService(BusinessRepository businessRepository, UserRepository userRepository, CityRepository cityRepository, RoleService roleService, ModelMapper modelMapper) {
        this.businessRepository = businessRepository;
        this.userRepository = userRepository;
        this.cityRepository = cityRepository;
        this.roleService = roleService;

        this.modelMapper = modelMapper;
    }


    public long addBusiness(BusinessRegisterBindingModel businessRegisterBindingModel, UserDetails businessOwner) {

        Business business = this.modelMapper.map(businessRegisterBindingModel, Business.class);

        User user = userRepository.findByUsername(businessOwner.getUsername()).orElseThrow(() ->
                new IllegalArgumentException("User with username " + businessOwner.getUsername() + " not found!"));

        if (!isUniqueEmail(business.getEmail())) {
            throw new RuntimeException("Email is taken");
        } else if (!isUniqueName(business.getName())) {
            throw new RuntimeException("Business Name is taken");
        }
        user.getRoles().add(roleService.findByName(RoleName.BUSINESS_OWNER));
        business.setOwner(user);

        cityRepository.save(business.getCity());
        userRepository.save(user);
        businessRepository.save(business);

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
            ;

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

    public BusinessProfileViewModel getProfileView(long id) {

        Optional<Business> business = businessRepository.findById(id);

        if (business.isPresent()) {
            return modelMapper.map(business, BusinessProfileViewModel.class);
        } else {
            throw new IllegalArgumentException("Business not exist!");
        }

    }
}
