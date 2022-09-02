package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class Store {

    private List<Category> categoryList = new ArrayList<>();

    public void addCategoryToStore(Category category){  //новый метод
        categoryList.add(category);
    }

    public void printAllCategoriesAndProducts() {
        for (Category category : categoryList) {
            category.printAllProduct();
        }
    }
}
