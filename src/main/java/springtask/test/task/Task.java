package springtask.test.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * Created by wenxiangzhou214164 on 2017/9/18
 */
@Configuration
@EnableScheduling
public class Task {

    @Scheduled(cron = "0 0 * * * ?")
    public void scheduler() {
        Date date = new Date();
        Date ago = new Date((date.getTime() - 60 * 60 * 1000) / 60 / 60 / 1000 * 60 * 60 * 1000);
        System.out.println(date);
        System.out.println(ago);
    }
}
