package collections;

import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 * Created by wenxiangzhou on 2017/5/26.
 */
public class Map {
    public static void main(String[] args) {
        //初始化
        java.util.Map<Integer, String> map = new HashMap<Integer, String>(){
            {
                put(2, "1");
                put(3, "2");
            }
        };
        map.put(4, "4");
        System.out.println(map);
        //map 遍历操作 JDK8
        map.forEach(new BiConsumer<Integer, String>() {
            @Override
            public void accept(Integer integer, String s) {
                if (integer == Integer.parseInt(s)) {
                    System.out.println(integer);
                }
            }
        });
    }
}
