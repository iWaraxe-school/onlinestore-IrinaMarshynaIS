package by.issoft.store.XML.Comparator;

import by.issoft.domain.Product;
import by.issoft.store.XML.SortingTypes.SortCategory;
import by.issoft.store.XML.SortingTypes.SortType;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ProductComparator implements Comparator<Product> {

    private final Map<SortCategory, SortType> sortMap;
    private Product product1;
    private Product product2;

    public ProductComparator(Map<SortCategory, SortType> sortMap) {
        this.sortMap = sortMap;
    }

    @Override
    public int compare(Product o1, Product o2) {
        product1 = o1;
        product2 = o2;
        Set<Map.Entry<SortCategory, SortType>> entrySet = sortMap.entrySet();
        Iterator<Map.Entry<SortCategory, SortType>> iterator = entrySet.iterator();
        return Sort(iterator);
    }

    private int Sort(Iterator<Map.Entry<SortCategory, SortType>> iterator) {
        int result = 0;
        if (iterator.hasNext()) {
            result = chooseSortMethod(iterator.next());
            if (result == 0) {
                return Sort(iterator);
            } else {
                return result;
            }
        }
        return result;
    }

    private int chooseSortMethod(Map.Entry<SortCategory, SortType> entry) {
        switch (entry.getKey()) {
            case NAME:
                return nameComparator(entry.getValue());
            case PRICE:
                return priceComparator(entry.getValue());
            case RATE:
                return rateComparator(entry.getValue());
            default:
                return 0;
        }
    }

    private int nameComparator(SortType type) {
        int result = product1.getName().compareTo(product2.getName());
        return (type.equals(SortType.ASCENDING) ? result : -result);
    }

    private int priceComparator(SortType type) {
        int result = product1.getPrice().compareTo(product2.getPrice());
        return (type.equals(SortType.ASCENDING) ? result : -result);
    }


    private int rateComparator(SortType type) {
        int result = product1.getRate().compareTo(product2.getRate());
        return (type.equals(SortType.ASCENDING) ? result : -result);
    }


}
