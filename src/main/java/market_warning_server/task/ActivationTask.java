package market_warning_server.task;

import market_warning_server.service.CountActivationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wenxiangzhou214164 on 2017/8/4.
 */
@Configuration
@EnableScheduling
public class ActivationTask {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private Map<String, Integer>[] tenMaps = new Map[6];

    @Scheduled(fixedRate = 600000)
    public void task() {
        CountActivationServiceImpl countActivationService = new CountActivationServiceImpl();
        Date targetDate = new Date((new Date().getTime() - 10 * 60 * 1000) / 10 / 60 / 1000 * 10 * 60 * 1000);
        if (targetDate.getMinutes() == 0) {
            tenMaps[5] = countActivationService.getActivationCountMapBy10Min(targetDate);
            logger.info("{}前10分钟:{}", targetDate,  tenMaps[5]);
            Map<String, Integer> hourMap = countActivationService.getActivationCountMapBy1Hour(targetDate);
            Map<String, Integer> records = new HashMap<>();
            for (int i = 0; i < 6; i++) {
                Map<String, Integer> map = tenMaps[i];
                for (Map.Entry<String, Integer> entry :
                        map.entrySet()) {
                    if (records.containsKey(entry.getKey())) {
                        records.put(entry.getKey(), entry.getValue() + records.get(entry.getKey()));
                    } else {
                        records.put(entry.getKey(), entry.getValue());
                    }
                }
            }
            logger.info("{}前60分钟:{}", targetDate, records);
            logger.info("{}前1小时:{}", targetDate, hourMap);
        }
        else {
            tenMaps[targetDate.getMinutes() / 10 - 1] = countActivationService.getActivationCountMapBy10Min(targetDate);
            logger.info("{}前10分钟:{}", targetDate, tenMaps[targetDate.getMinutes() / 10 - 1]);
        }
    }
}
