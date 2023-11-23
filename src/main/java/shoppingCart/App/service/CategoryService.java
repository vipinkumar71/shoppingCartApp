package shoppingCart.App.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingCart.App.dto.CategoryDto;
import shoppingCart.App.entity.Category;
import shoppingCart.App.exceptionHandler.ResourceNotFountException;
import shoppingCart.App.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper mapper;
    public CategoryDto create(CategoryDto dto){
        // CategoryDto to Category
       Category category = this.mapper.map(dto, Category.class);
       Category saveCategory = this.categoryRepository.save(category);

       //Category to CategoryDto
       return this.mapper.map(saveCategory,CategoryDto.class);
    }

    public CategoryDto updateCategory(int categoryId,CategoryDto newCategory){
      Category oldCategory = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFountException("Category not found from this id =" + " " + categoryId));
        oldCategory.setTitle(newCategory.getTitle());
        Category saveCategory = this.categoryRepository.save(oldCategory);
        return  this.mapper.map(saveCategory, CategoryDto.class);
    }

    public void deleteCategory(int categoryId){
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFountException("Category not found from this id =" + " " + categoryId));
        this.categoryRepository.delete(category);
    }

    public CategoryDto getCategoryById(int categoryId){
        Category getCategory = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFountException("Category not found from this id =" + " " + categoryId));
        return this.mapper.map(getCategory, CategoryDto.class);
    }

    public List<CategoryDto> getAllCategories(){
        List<Category> allCategories = this.categoryRepository.findAll();
        List<CategoryDto> allDto =  allCategories.stream()
                .map( category -> this.mapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
        return allDto;
    }
}
