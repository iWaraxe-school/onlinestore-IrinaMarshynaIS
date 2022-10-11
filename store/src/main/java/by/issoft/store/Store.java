package by.issoft.store;

import by.issoft.store.XML.XMLParser;
import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.XML.Comparator.ProductComparator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Store {

    private List<Category> categoryList = new ArrayList<>();

    public void addCategoryToStore(Category category) {  //новый метод
        categoryList.add(category);
    }

    public void sortStore() {
        Comparator<Product> productComparator = new ProductComparator(XMLParser.configMap());
        for (Category category : categoryList) {
            category.getProductList().sort(productComparator);

        }
    }

    public void printAllCategoriesAndProducts() {
        for (Category category : categoryList) {
            category.printAllProduct();
        }
    }
}
