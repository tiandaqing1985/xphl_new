import java.util.ArrayList;
import java.util.List;

/**
 * @program: String->ListTest
 * @author: gakki
 * @create: 2019-07-21 17:24
 **/
public class ListTest {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        list1.addAll(list2);
        for (Integer integer : list1) {
            System.out.println(integer);
        }

    }
}
