import com.google.common.collect.ArrayListMultimap;

public class HelloOtus {
    public static void main(String[] args) {
        ArrayListMultimap<String, String> listMultimap = ArrayListMultimap.<String,String>create();
        listMultimap.put("Синициин", "Воладимир");
        listMultimap.put("Синициин", "Пётр");
        System.out.println(listMultimap);
    }
}
