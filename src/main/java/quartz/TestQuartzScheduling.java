package quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Created by wenxiangzhou214164 on 2017/8/2.
 */
public class TestQuartzScheduling {

    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        JobDetail jobDetail = JobBuilder.newJob(TestQuartzJob.class).withIdentity("testQuartzJob").build();

        SimpleTrigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity("simpleTrigger").withSchedule(SimpleScheduleBuilder.simpleSchedule().repeatForever().withIntervalInSeconds(10)).startAt(new Date()).build();

        scheduler.scheduleJob(jobDetail, simpleTrigger);
        scheduler.start();
    }

}
