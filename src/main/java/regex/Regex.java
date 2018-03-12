package regex;

/**
 * @author wenxiangzhou214164
 * @date 2017/11/7
 */
public class Regex {

    public static void main(String[] args) {
        test3();
    }

    public static void test1() {
        //替换所有非数字和小数点的字符
        String costStr = "19,199.00元";
        costStr = costStr.replaceAll("[^0-9|\\.]", "");
        System.out.println(costStr);
    }

    public static void test2() {
        String m = "01";
        m = m.replaceAll("^0", "");//^ 字符串开头锚点， $ 字符串
        System.out.println(m);
    }

    public static void test3() {
        String s = "1202006000030";
        s = s.replaceAll("0+$", "");
        System.out.println(s);
    }

}
