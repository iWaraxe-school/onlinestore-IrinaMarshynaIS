package by.issoft.store;

import by.issoft.store.XML.SortingTypes.SortCategory;
import by.issoft.store.XML.SortingTypes.SortType;
import by.issoft.store.XML.XMLParser;
import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.XML.Comparator.ProductComparator;

import java.util.*;
import java.util.stream.Stream;

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
    public void getTop5() {
        System.out.println("_______________________________");
        System.out.println("TOP 5");
        System.out.println("_______________________________");

        Map<SortCategory, SortType> sortTypeMap = new HashMap<>();
        sortTypeMap.put(SortCategory.PRICE, SortType.DESCENDING);
        Comparator<Product> productComparator = new ProductComparator(sortTypeMap);

        productStream().sorted(productComparator)
                .limit(5)
                .forEach(System.out::println);
        }

        private Stream<Product> productStream(){
        return categoryList.stream()
                .map(Category::getProductList)
                .flatMap(Collection::stream);
        }
}
