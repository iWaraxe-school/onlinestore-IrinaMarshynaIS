package by.issoft.store;

import by.issoft.store.XML.SortingTypes.SortCategory;
import by.issoft.store.XML.SortingTypes.SortType;
import by.issoft.store.XML.XMLParser;
import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.XML.Comparator.ProductComparator;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

public class Store {
    private static final Store instance = new Store();

    public Store() {

    }
    public static Store getInstance() {
        return instance;
    }


    private List<Category> categoryList = new ArrayList<>();

    public void addCategoryToStore(Category category) {  //новый метод
        categoryList.add(category);
    }

    public void sortStore() {
        Comparator<Product> productComparator = new ProductComparator(XMLParser.configMap());

        productStream().sorted(productComparator)
                .forEach(System.out::println);
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

    public List<Product> addProductToList() {
        List<Product> allProducts = new ArrayList<>();
        for (Category category : categoryList) {
            allProducts.addAll(category.getProductList());
        }
        return allProducts;
    }

    public Product getRandomProductFromStore() {
        Random random = new Random();
        List<Product> allProducts = addProductToList();
        return allProducts.get(random.nextInt(allProducts.size()));
    }

    private Stream<Product> productStream() {
        return categoryList.stream()
                .map(Category::getProductList)
                .flatMap(Collection::stream);
    }
}
