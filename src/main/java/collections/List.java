package collections;

import org.assertj.core.util.Lists;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by wenxiangzhou on 2017/5/26.
 */
public class List {

    public static void main(String[] args) {
        arrayList();
    }

    public static void arrayList() {
        //初始化
        Integer[] is = new Integer[]{1,2,3,4};
        java.util.List<Integer> listI = new ArrayList<Integer>(Arrays.asList(is));
        //初始化
        String[] ss = new String[]{"1", "2", "3", "4"};
        java.util.List<String> listS = Arrays.asList(ss);
        java.util.Map<String, String> map = new HashMap<>();
        //初始化，集合初始化时尽量指定33
        java.util.List<Double> listD = new ArrayList<Double>() {
            {
                add(1.2);
                add(2.3);
            }
        };
        listD.add(3.4);
        //初始化
        java.util.Set<Integer> set = new HashSet<Integer>(){{add(1);add(2);add(3);}};
        java.util.List<Integer> listI2 = new ArrayList<Integer>(set);
        listI2 = Lists.newArrayList(set);
        System.out.println(listI + " " + listS + " " + listD + " " + listI2);
//        UnsupportedOperationException：Arrays.asList()返回一个受指定数组决定的固定大小的列表，所以不能做 add，remove 等操作
//        listI.add(5);
//        listS.remove("3");
        java.util.List<Integer> subListI = listI.subList(1,listI.size());//同subString，包前不包后
//        在subList场景中，高度注意原集合个数的修改，会导致subList的遍历、增加、删除均产生ConcurrentModificationException
//        listI.add(6);
        System.out.println(subListI);//进行了对subList的遍历操作
        //对subList的所有操作最终会反映到原列表上
        subListI.add(6);
        System.out.println(listI);
//        subList结果不可强转成ArrayList，否则会抛出ClassCastException
//        java.util.List<Integer> listFormSubList = (ArrayList<Integer>) subListI;
        //不在foreach循环里进行元素增删操作。删除元素请用Iterator方式，如果并发方式需要个Iterator对象加锁
        Iterator<Integer> iterator = listI.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == 6) {
                iterator.remove();
            }
        }
        System.out.println(listI);
    }
}
