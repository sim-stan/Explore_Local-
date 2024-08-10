package org.example.explore_local.controller;

import org.example.explore_local.model.entity.Category;
import org.example.explore_local.model.enums.CategoryName;
import org.example.explore_local.repository.CategoryRepository;
import org.example.explore_local.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoriesController {


    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    public CategoriesController(CategoryService categoryService, CategoryRepository categoryRepository) {
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/all")
    public String all(){
        return "categories";
    }


    @GetMapping ("/nightlife")
    public String viewNightlife(Model model){

        Category category=categoryRepository.getCategoryByCategoryName(CategoryName.NIGHTLIFE);
        String about=category.getDescription();
        model.addAttribute("about" ,about);

        if (categoryService.getBusinessesByCategory(CategoryName.NIGHTLIFE)!=null){
            model.addAttribute("allNightLife",categoryService.getBusinessesByCategory(CategoryName.NIGHTLIFE));
        }else {
            model.addAttribute("noPlaces",true);
        }

        return "/nightlife";

    }

    @GetMapping ("/shopping")
    public String viewShopping(Model model){


        Category category=categoryRepository.getCategoryByCategoryName(CategoryName.SHOPPING);
        String about=category.getDescription();
        model.addAttribute("about" ,about);

        if (categoryService.getBusinessesByCategory(CategoryName.SHOPPING)!=null){
            model.addAttribute("allShopping",categoryService.getBusinessesByCategory(CategoryName.SHOPPING));
        }

        return "/shopping";

    }

    @GetMapping ("/restaurants")
    public String viewRestaurants(Model model){

        Category category=categoryRepository.getCategoryByCategoryName(CategoryName.RESTAURANTS);
        String about=category.getDescription();
        model.addAttribute("about" ,about);

        if (categoryService.getBusinessesByCategory(CategoryName.RESTAURANTS)!=null){
            model.addAttribute("allRestaurants",categoryService.getBusinessesByCategory(CategoryName.RESTAURANTS));
        }
        return "/restaurants";

    }

    @GetMapping ("/local-farms")
    public String viewLocalFarms(Model model){


        Category category=categoryRepository.getCategoryByCategoryName(CategoryName.LOCAL_FARMS);
        String about=category.getDescription();
        model.addAttribute("about" ,about);

        if (categoryService.getBusinessesByCategory(CategoryName.LOCAL_FARMS)!=null){
            model.addAttribute("allFarms",categoryService.getBusinessesByCategory(CategoryName.LOCAL_FARMS));
        }
        return "/local_farms";

    }

    @GetMapping ("/beauty-and-spa")
    public String viewBeautyAndSpa(Model model){

        Category category=categoryRepository.getCategoryByCategoryName(CategoryName.BEAUTY_SPA);
        String about=category.getDescription();
        model.addAttribute("about" ,about);

        if (categoryService.getBusinessesByCategory(CategoryName.BEAUTY_SPA)!=null){
            model.addAttribute("allBeautyAndSpa",categoryService.getBusinessesByCategory(CategoryName.BEAUTY_SPA));
        }
        return "/beauty_spas";

    }

    @GetMapping ("/automotive")
    public String viewAutomotive(Model model ){

        Category category=categoryRepository.getCategoryByCategoryName(CategoryName.AUTOMOTIVE);
        String about=category.getDescription();
        model.addAttribute("about" ,about);

        if (categoryService.getBusinessesByCategory(CategoryName.AUTOMOTIVE)!=null){
            model.addAttribute("allAutomotive",categoryService.getBusinessesByCategory(CategoryName.AUTOMOTIVE));
        }

        return "/automotive";

    }


    @GetMapping ("/home-services")
    public String viewHomeServices(Model model){

        Category category=categoryRepository.getCategoryByCategoryName(CategoryName.HOME_SERVICES);
        String about=category.getDescription();
        model.addAttribute("about" ,about);

        if (categoryService.getBusinessesByCategory(CategoryName.HOME_SERVICES)!=null){
            model.addAttribute("allHomeServices",categoryService.getBusinessesByCategory(CategoryName.HOME_SERVICES));
        }
        return "/home_services";

    }


}
