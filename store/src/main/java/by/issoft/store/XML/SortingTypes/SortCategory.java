package by.issoft.store.XML.SortingTypes;

public enum SortCategory {
    NAME("name"),
    PRICE("price"),
    RATE("rate");

    private final String value;

    SortCategory(String value) {
        this.value = value;
    }

    public static SortCategory getCategory(String value) throws Exception {
        for (SortCategory category : SortCategory.values()) {
            if(category.value.equals(value.toLowerCase())){
                return category;
            }
        }
        throw new Exception();
    }
}
