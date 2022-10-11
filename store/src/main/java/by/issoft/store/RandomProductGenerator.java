package by.issoft.store;

import com.github.javafaker.Faker;

public class RandomProductGenerator {
    private Faker faker = new Faker();

    public double generatePrice () {
        return faker.number().randomDouble(2,0,2);
    }

    public double generateRate () {
        return faker.number().randomDouble(1,0,5);
    }

    public String generateName (String categoryName) {
        switch (categoryName) {
            case "Books":
                return faker.book().title();
            case "Bikes":
                return faker.animal().name();
            case "Food":
                return faker.food().ingredient();
            case "Phones":
                return faker.space().galaxy();
            default:
                return null;
        }

    }

}
