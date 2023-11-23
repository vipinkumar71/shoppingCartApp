package shoppingCart.App.dto;

import lombok.Getter;
import shoppingCart.App.entity.Product;

import java.util.List;
@Getter
public class ProductResponse {
    private List<ProductDto> content;
    private int pageNumber;
    private int pageSize;
    private int totalPages;
    private boolean lastPage;


    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }



    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }



    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setContent(List<ProductDto> content) {
        this.content = content;
    }

    public void setLastPage(boolean lastPage) {
        this.lastPage = lastPage;
    }
}
