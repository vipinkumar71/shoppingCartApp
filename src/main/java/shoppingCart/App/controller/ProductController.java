package shoppingCart.App.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingCart.App.dto.AppConstants;
import shoppingCart.App.dto.ProductDto;
import shoppingCart.App.dto.ProductResponse;
import shoppingCart.App.entity.Product;
import shoppingCart.App.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("/create/{categoryId}")
    @ResponseBody
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product, @PathVariable int categoryId) {
        ProductDto createProduct = productService.createProduct(product, categoryId);
        return new  ResponseEntity<ProductDto> (createProduct, HttpStatus.CREATED);
    }

    @GetMapping("/getAllProducts")
    public ProductResponse getAllProducts(
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER_STRING, required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE_STRING, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY_STRING, required = false) String sortBy,
            @RequestParam(value ="sortDir", defaultValue = AppConstants.SORT_DIR_STRING, required = false) String sortDir) {
       ProductResponse response = productService.getAllProduct(pageNumber,pageSize,sortBy,sortDir);
       return response;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable int productId){
        ProductDto productById = productService.getProductById(productId);
        return new ResponseEntity<ProductDto>(productById,HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable int productId){
        productService.deleteProductById(productId);
        return new ResponseEntity<String>("Product Deleted", HttpStatus.OK);
    }


    // Update Product by productId
    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<ProductDto> updateProductById(@PathVariable int productId, @RequestBody ProductDto updatedProduct){
       ProductDto updateProduct = productService.updateProductById(productId,updatedProduct);
       return new ResponseEntity<ProductDto>(updatedProduct,HttpStatus.ACCEPTED);
    }

    // Find Product By Category Wise
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDto>> getProductByCategory(@PathVariable int categoryId){
      List<ProductDto> findProductByCategory =  this.productService.findProductByCategory(categoryId);
        return new ResponseEntity<List<ProductDto>>(findProductByCategory, HttpStatus.ACCEPTED);
    }
}
