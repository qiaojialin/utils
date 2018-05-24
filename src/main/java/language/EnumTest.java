package language;

public class EnumTest {
    public static void main(String[] args) {
        FilterType a = FilterType.TIME_FILTER;
        System.out.println(a);
    }
}

enum FilterType {
    VALUE_FILTER("value"), TIME_FILTER("time");

    private String name;
    FilterType(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }

}
