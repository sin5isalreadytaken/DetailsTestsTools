package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wenxiangzhou on 2017/5/26.
 */
public class Test {

    public static void main(String[] args) {
        test4();
    }

    public static void test5() {
        String a = "oqjwo,aowifj,";
        String[] as = a.split(",");
        String[] bs = a.split(",", -1);
        System.out.println(Arrays.toString(as));
        System.out.println(Arrays.toString(bs));
    }

    public static void test4() {
        double decay = 24 * 60 * 60 * 1000 / (3491872983775L - 123412353452L);
        System.out.println(decay);
    }

}
