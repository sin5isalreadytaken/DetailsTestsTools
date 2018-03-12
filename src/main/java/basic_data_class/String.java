package basic_data_class;

import java.util.Arrays;

/**
 * @author wenxiangzhou214164
 * @date 2017/12/1
 */
public class String {

    public static void main(String[] args) {
        System.out.println("1".startsWith(null));
    }

    public static void test4() {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < 2000000; i++) {
            sb.append('0');
        }
        System.out.println(sb.toString());
    }

    public static void test5() {
        java.lang.String a = "oqjwo,aowifj,";
        java.lang.String[] as = a.split(",");
        java.lang.String[] bs = a.split(",", -1);
        System.out.println(Arrays.toString(as));
        System.out.println(Arrays.toString(bs));
    }
}
