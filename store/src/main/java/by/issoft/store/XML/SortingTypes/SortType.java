package by.issoft.store.XML.SortingTypes;

public enum SortType {
    ASCENDING("asc"),
    DESCENDING("desc");

    private final String value;

    SortType(String value) {
        this.value = value;
    }

    public static SortType getCategory(String value) throws Exception {
        for (SortType type : SortType.values()) {
            if(type.value.equals(value.toLowerCase())){
                return type;
            }
        }
        throw new Exception();
    }
}
