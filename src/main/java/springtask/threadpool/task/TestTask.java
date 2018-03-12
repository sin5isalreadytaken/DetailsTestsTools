package springtask.threadpool.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author wenxiangzhou214164
 * @date 2017/10/30
 */
@Service
public class TestTask {

    @Async
    @Scheduled(fixedRate = 2 * 60 * 1000)
    public void scheduler() {
        System.out.println("testtask1: 1");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("testtask1: 2");
    }

}
