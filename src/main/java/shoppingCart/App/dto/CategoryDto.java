package shoppingCart.App.dto;

import lombok.Getter;

@Getter
public class CategoryDto {
    private int categoryId;
    private String title;



    public CategoryDto() {
        super();
    }

    public CategoryDto(int categoryId, String title) {
        this.categoryId = categoryId;
        this.title = title;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
