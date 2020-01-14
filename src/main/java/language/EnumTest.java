package language;

public class EnumTest {
    public static void main(String[] args) {
        FilterType a = FilterType.TIME_FILTER;

        System.out.println(a.ordinal());

        System.out.println(FilterType.values()[1]);

//        System.out.println(a);
    }
}

enum FilterType {
    VALUE_FILTER("VALUE_FILTER"), TIME_FILTER("TIME_FILTER");

    private String name;
    FilterType(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }

}
