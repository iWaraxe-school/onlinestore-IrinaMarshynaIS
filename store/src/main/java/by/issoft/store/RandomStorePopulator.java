package by.issoft.store;

import by.issoft.domain.Product;

public class RandomStorePopulator {
    Store store;

    public RandomStorePopulator(Store store) {
        this.store = store;
    }

    public void fillStoreRandomly () {
        RandomProductGenerator generator = new RandomProductGenerator();
        Product product = new Product(
                generator.generateName("Books"),
                generator.generatePrice(),
                generator.generateRate());
        System.out.println(product);
    }
}
