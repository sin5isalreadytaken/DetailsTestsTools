package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by wenxiangzhou on 2017/5/26.
 */
public class Set {
    public static void main(String[] args) {
        //初始化
        java.util.Set<String> set = new HashSet<String>(){
            {
                add("1");
                add("2");
            }
        };
        set.add("3");
        set.add("1");
        //初始化
        String[] ss = new String[]{"5", "6", "7", "8"};
        java.util.Set<String> set2 = new HashSet<String>(Arrays.asList(ss));
        set2.add("9");
        //初始化
        java.util.List<Integer> list = new ArrayList<Integer>(){{add(1);add(2);add(3);}};
//        java.util.Set<Integer> set3 = new HashSet<String>(list);
        System.out.println(set + " " + set2 + " set3" );
        //contains方法可用
        System.out.println(set.contains("2") + " " + set2.contains("6"));
    }
}
