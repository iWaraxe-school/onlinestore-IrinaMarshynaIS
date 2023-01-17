package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomStorePopulatorFactory {
    static Store store;

    public RandomStorePopulatorFactory(Store store) {
        this.store = store;
    }

    public void fillStoreRandomly() {
        RandomProductGenerator generator = new RandomProductGenerator();
        Set<Category> categorySet = createCategorySet(); //вызываем метод
        for (Category category : categorySet) {

            for (int i = 0; i < 7; i++) {
                Product product = new Product(
                        generator.generateName(category.getName()),
                        generator.generatePrice(),
                        generator.generateRate());
                category.addProductToCategory(product);
            }
            store.addCategoryToStore(category);
        }
    }

    private static Set<Category> createCategorySet() {
        Set<Category> categorySet = new HashSet<>(); //задаем множество категорий
        CategoryFactory categoryFactory = new CategoryFactory();
        Reflections reflections = new Reflections("by.issoft.domain.categories"); //создаем обьект reflection, кторый будет смотреть в тот пакет
        Set<Class<? extends Category>> subTypes = reflections.getSubTypesOf(Category.class);  // '?' any class.

        System.out.println(subTypes);
        for (Class<? extends Category> subType : subTypes) {   //для каждого элемента множества классов создаем новый обьект (вызываем конструктор)
            String simpleName = subType.getSimpleName();
            Category categoryToAdd = categoryFactory.getCategory(simpleName);
            store.addCategoryToStore(categoryToAdd);
            categorySet.add(categoryToAdd);
        }
        System.out.println(categorySet);
        return categorySet; //возвращаем
    }
}

