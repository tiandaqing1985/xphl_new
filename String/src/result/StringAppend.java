package result;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: String->StringAppend
 * @author: gakki
 * @create: 2019-07-30 15:13
 **/
public class StringAppend {
    public static void main(String[] args) {
        List<DemoString> list = new ArrayList<>();
        list.add(new DemoString("张三", "已审"));
        list.add(new DemoString("李四", "未审"));
        StringBuffer all = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            StringBuffer sin = new StringBuffer();
            if (i==0){
                all.append(list.get(i).getName()+"(" +list.get(i).getStatus()+")");
            }else {
                all.append("->"+list.get(i).getName()+"(" +list.get(i).getStatus()+")");
            }
        }
        System.out.println(all.toString());

    }
}
