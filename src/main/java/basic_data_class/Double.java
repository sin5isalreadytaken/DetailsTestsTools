package basic_data_class;

import java.text.DecimalFormat;

/**
 * Created by wenxiangzhou214164 on 2017/9/20
 */
public class Double {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        DecimalFormat df = new DecimalFormat("#.00");
        double d = 0;
        System.out.println(df.format(d));
    }

    public static void test3() {
        java.lang.String s = "5.12322E-2";
        double d = java.lang.Double.parseDouble(s);
        System.out.println(d);
    }

    public static void test4() {
        double decay = (double) 24 * 60 * 60 * 1000 / (3491872983775L - 123412353452L);
        System.out.println(decay);
    }

}
