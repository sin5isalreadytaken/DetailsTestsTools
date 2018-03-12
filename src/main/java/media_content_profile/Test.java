package media_content_profile;

import java.util.Random;

/**
 * @author wenxiangzhou214164
 * @date 2017/11/27
 */
public class Test {

    //随机openId
    public static void main(String[] args) {
        String openId = "oumkM0b19CKStgZdkG9ltx2jfgII";
        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            System.out.println(openId);
            int type = random.nextInt(3);
            int cursor = random.nextInt(openId.length());
            char[] array = openId.toCharArray();
            int change = 0;
            switch (type) {
                case 0:
                    change = random.nextInt(10);
                    array[cursor] = (char) ('0' + change);
                    openId = String.valueOf(array);
                    break;
                case 1:
                    change = random.nextInt(26);
                    char c = (char) ('a' + change);
                    array[cursor] = c;
                    openId = String.valueOf(array);
                    break;
                case 2:
                    change = random.nextInt(26);
                    char C = (char) ('A' + change);
                    array[cursor] = C;
                    openId = String.valueOf(array);
                    break;
            }
        }
    }

}
