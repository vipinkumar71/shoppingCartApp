package shoppingCart.App.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shoppingCart.App.dto.CategoryDto;
import shoppingCart.App.dto.ProductDto;
import shoppingCart.App.dto.ProductResponse;
import shoppingCart.App.entity.Category;
import shoppingCart.App.entity.Product;
import shoppingCart.App.exceptionHandler.ResourceNotFountException;
import shoppingCart.App.repository.CategoryRepository;
import shoppingCart.App.repository.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public ProductDto createProduct(ProductDto productDto, int categoryId){
        // Fetch Category is available or not
        Category category  = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new ResourceNotFountException("This category Id not found in Category"));

        // ProductDto to Product
        Product product = toEntity(productDto);
        product.setCategory(category);

        // save product into Database
        Product saveProduct = this.productRepository.save(product);

        // Change Product to ProductDto
         ProductDto dto = toDto(saveProduct);
         return dto;
    }

    public ProductResponse getAllProduct(int pageNumber, int pageSize, String sortBy, String sortDir){
        Sort sort = null;
        if(sortDir.trim().toLowerCase().equals("asc")){
          sort = sort.by(sortBy).ascending();
            System.out.println(sort);
        }else{
           sort = Sort.by(sortBy).descending();
            System.out.println(sort);
        }
       Pageable pageable = PageRequest.of(pageNumber, pageSize,sort);
       Page<Product> page = this.productRepository.findAll(pageable);
       List<Product> pageProduct = page.getContent();

       List<ProductDto> productDto = pageProduct.stream().map(p-> this.toDto(p)).collect(Collectors.toList());
       ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDto);
        productResponse.setPageNumber(page.getNumber());
        productResponse.setPageSize(page.getSize());
        productResponse.setTotalPages(page.getTotalPages());
        productResponse.setLastPage(page.isLast());
        return productResponse;

        // productDto to Product
//       List<Product> findAllProduct = productRepository.findAll();
       // Product to productDto
//      List<ProductDto> allProduct = findAllProduct.stream().map(product -> this.toDto(product)).collect(Collectors.toList());
//       return allProduct;
    }

    public ProductDto getProductById(int productId){
        Product getById = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFountException("Product not found from this id =" + " " + productId));
        ProductDto dto = this.toDto(getById);
                return dto;
    }

    public void deleteProductById(int productId){
        Product productById = productRepository.findById(productId)
                .orElseThrow(()-> new ResourceNotFountException("Product not found from this id =" + " " + productId));
        productRepository.delete(productById);
    }

    public ProductDto updateProductById(int productId, ProductDto updatedProduct){
       Product oldProduct = productRepository.findById(productId)
               .orElseThrow(()-> new ResourceNotFountException("Product not found from this id =" + " " + productId));;
       oldProduct.setProductName(updatedProduct.getProductName());
       oldProduct.setStock(updatedProduct.isStock());
       oldProduct.setProductDescription(updatedProduct.getProductDescription());
       oldProduct.setProductPrice(updatedProduct.getProductPrice());
       oldProduct.setProductQuantity(updatedProduct.getProductQuantity());
       oldProduct.setImageName(updatedProduct.getImageName());
       Product saveProduct = productRepository.save(oldProduct);
       ProductDto Dto = toDto(saveProduct);
       return Dto;
    }

    //ProductDto to product
    public Product toEntity(ProductDto productDto){
        Product prod = new Product();
        prod.setProductId(productDto.getProductId());
        prod.setProductName(productDto.getProductName());
        prod.setProductDescription(productDto.getProductDescription());
        prod.setProductPrice(productDto.getProductPrice());
        prod.setProductQuantity(productDto.getProductQuantity());
        prod.setStock(productDto.isStock());
        prod.setImageName(productDto.getImageName());
        return prod;
    }

    //Product to productDto
    public ProductDto toDto(Product product){
        ProductDto prodDto = new ProductDto();
        prodDto.setProductId(product.getProductId());
        prodDto.setProductName(product.getProductName());
        prodDto.setProductDescription(product.getProductDescription());
        prodDto.setProductPrice(product.getProductPrice());
        prodDto.setProductQuantity(product.getProductQuantity());
        prodDto.setImageName(product.getImageName());
        prodDto.setStock(product.isStock());

        // Change Category to CategoryDto
        CategoryDto catDto = new CategoryDto();
        catDto.setCategoryId(product.getCategory().getCategoryId());
        catDto.setTitle(product.getCategory().getTitle());

        // Then Set CategoryDto in ProductDto
        prodDto.setCategory(catDto);
        return prodDto;
    }
}
