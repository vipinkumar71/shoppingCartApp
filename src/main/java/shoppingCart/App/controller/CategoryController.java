package shoppingCart.App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingCart.App.dto.ApiResponse;
import shoppingCart.App.dto.CategoryDto;
import shoppingCart.App.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // create new category
    @PostMapping("/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
      CategoryDto createCategory =  this.categoryService.create(categoryDto);
        return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
    }

    // Update category
    @PutMapping("/update/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategoryById(@PathVariable int categoryId, @RequestBody CategoryDto categoryDto){
       CategoryDto updateCategory = this.categoryService.updateCategory(categoryId, categoryDto);
       return new ResponseEntity<CategoryDto>(updateCategory, HttpStatus.OK);
    }

    // delete category
    @DeleteMapping("/delete/{categoryId}")
    public  ResponseEntity<ApiResponse> deleteCategory(@PathVariable int categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully", true), HttpStatus.OK);
    }

    // getCategoryById
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable int categoryId){
       CategoryDto getCategory = this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<CategoryDto>(getCategory, HttpStatus.OK);
    }

    // Get All Categories
    @GetMapping("/getAllCategories")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> allCategories = this.categoryService.getAllCategories();
        return new ResponseEntity<List<CategoryDto>>(allCategories, HttpStatus.ACCEPTED);
    }
}
