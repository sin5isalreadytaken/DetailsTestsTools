package random;

import java.util.Random;

/**
 * @author wenxiangzhou214164
 * @date 2017/12/5
 */
public class Test {

    public static void main(String[] args) {
        test3();
    }

    public static void test1() {
        Random random = new Random();
        int startArray[] = {0,1,2,3,4,5,6,7,8,9};//seed array
        int N = 10;//随机数个数
        int resultArray[] = new int [10];//结果存放在里面
        for(int i = 0; i < N; i++)
        {
            int seed = random.nextInt(startArray.length - i);//从剩下的随机数里生成
            resultArray[i] = startArray[seed];//赋值给结果数组
            startArray[seed] = startArray[startArray.length - i - 1];//把随机数产生过的位置替换为未被选中的值。
        }
        for (int i : resultArray) {
            System.out.println(i);
        }
    }

    public static void test2() {
        Random random = new Random(100);
        for (int i = 0; i < 100; i++) {
            System.out.println(random.nextBoolean());
        }
    }

    public static void test3() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(4));
        }
    }

}
