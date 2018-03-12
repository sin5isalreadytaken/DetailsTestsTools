package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * datetime: 2017/12/27 11:14.
 *
 * @author wenxiangzhou214164
 */
public class Test {

    public static void main(String[] args) {
        test1();
    }

    public static void test1() {
        ExecutorService es = Executors.newFixedThreadPool(3);
        Thread t = new Thread(() -> {
            while (true) {
                System.out.println("running");
//                for (long i = 0; i < 10000000000L; i++);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        });

        es.execute(t);
        es.execute(t);
        es.execute(t);
        es.execute(t);

        es.shutdown();
        try {
            System.out.println(es.awaitTermination(15, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        es.shutdownNow();
        System.out.println("done");
    }

}
