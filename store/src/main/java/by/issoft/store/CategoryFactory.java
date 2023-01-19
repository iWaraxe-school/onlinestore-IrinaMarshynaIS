package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.categories.BikeCategory;
import by.issoft.domain.categories.BookCategory;
import by.issoft.domain.categories.FoodCategory;
import by.issoft.domain.categories.PhoneCategory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CategoryFactory {
    public Category getCategory(String name) {
        if (name.equals("BikeCategory")) {
            return new BikeCategory();
        }
        if (name.equals("BookCategory")) {
            return new BookCategory();
        }
        if (name.equals("FoodCategory")) {
            return new FoodCategory();
        }
        if (name.equals("PhoneCategory")) {
            return new PhoneCategory();
        }
        return null;
    }
}
