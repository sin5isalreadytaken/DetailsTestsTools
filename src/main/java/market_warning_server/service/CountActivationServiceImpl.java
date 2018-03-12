package market_warning_server.service;

import com.sohu.mrd.framework.redis.client.client.CodisLocalClient;
import market_warning_server.util.CodisClientUtil;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by wenxiangzhou on 2017/6/20.
 */
@Service
public class CountActivationServiceImpl{
    private static final CodisLocalClient codisLocalClient = CodisClientUtil.getClient();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
    private static final Map<String, String> acToStoreMap = new HashMap<String, String>(){
        {
            put("1815", "1");//360
            put("3343", "1");
            put("3344", "1");
            put("3345", "1");
            put("3055", "1");
            put("3336", "1");
            put("3339", "1");
            put("3314", "1");
            put("2915", "1");
            put("2916", "1");
            put("2917", "1");
            put("2918", "1");
            put("2919", "1");
            put("1811", "11");//oppo
            put("3044", "11");
            put("3320", "11");
            put("3304", "4");//vivo
            put("3318", "4");
            put("3218", "4");
            put("1809", "4");
            put("3021", "10");//华为
            put("3310", "10");
            put("3145", "10");
            put("3040", "2");//小米
            put("1803", "2");
            put("3312", "2");
            put("1814", "9");//百度
            put("3337", "9");
            put("3340", "9");
            put("3311", "9");
            put("3327", "9");
            put("3058", "9");
            put("1816", "3");//应用宝
            put("1818", "3");
            put("2537", "3");
            put("3335", "3");
            put("3338", "3");
            put("2562", "3");
            put("2575", "3");
            put("2316", "3");
            put("2337", "3");
            put("3313", "3");
            put("3806", "3");
            put("2700", "5");//金立
            put("3047", "5");
            put("3316", "5");
            put("1805", "5");
            put("1805", "5");
            put("3022", "6");//酷派
            put("3317", "6");
            put("3216", "6");
//            put("3042", "豌豆荚");
//            put("3807", "豌豆荚");
//            put("3057", "豌豆荚");
//            put("3321", "豌豆荚");
//            put("3805", "豌豆荚");
            put("1812", "8");//魅族
            put("3349", "8");
            put("3020", "8");
            put("3323", "8");
            put("1804", "8");
            put("1812", "8");
            put("3349", "8");
            put("3020", "8");
            put("3323", "8");
            put("1804", "8");
            put("1813", "7");//联想
            put("3346", "7");
            put("3347", "7");
            put("3348", "7");
            put("1802", "7");
            put("2327", "7");
            put("2328", "7");
            put("2329", "7");
            put("3164", "7");
            put("2330", "7");
            put("2331", "7");
            put("3033", "7");
            put("3322", "7");
            put("2379", "7");
            put("3341", "7");
            put("3350", "7");
            put("1331", "7");
            put("1813", "7");
            put("3346", "7");
            put("3347", "7");
            put("3348", "7");
            put("1802", "7");
            put("2327", "7");
            put("2328", "7");
            put("2329", "7");
            put("3164", "7");
            put("2330", "7");
            put("2331", "7");
            put("3033", "7");
            put("3322", "7");
            put("2379", "7");
            put("3341", "7");
            put("3350", "7");
            put("1331", "7");
//            put("1810", "阿里云应用中心");
//            put("3019", "阿里云应用中心");
//            put("3315", "阿里云应用中心");
//            put("6478", "阿里云应用中心");
//            put("1810", "阿里云应用中心");
//            put("3019", "阿里云应用中心");
//            put("3315", "阿里云应用中心");
//            put("6478", "阿里云应用中心");
        }
    };

    public Map<String, Integer> getActivationCountMapBy1Hour(Date date) {
        return getActivationCountMapByDuration(date, 3600);
    }

    /**
     * 获取截至date的durationSeconds秒内的激活总数
     * @param date
     * @param durationSeconds
     * @return
     */
    public Map<String, Integer> getActivationCountMapByDuration(Date date, int durationSeconds) {
        List<String> records = new ArrayList<>();
        for (int i = 0; i < durationSeconds / 3600; i++) {
            records.addAll(getSetFromRedisByKey(sdf.format(new Date(date.getTime() - (i + 1) * 60 * 60 * 1000))));
        }
        durationSeconds %= 3600;
        for (int i = 0; i < durationSeconds; i++) {
            records.addAll(getSetFromRedisByKey(getSecondTimestamp(new Date(date.getTime() - i * 1000))));
        }
        return getCountMapFromSet(records);
    }

    public Map<String, Integer> getActivationCountMapBy10Min(Date date) {
        return getActivationCountMapByDuration(date, 600);
    }

    private String getSecondTimestamp(Date date) {
        return String.valueOf(date.getTime() / 1000);
    }

    private Set<String> getSetFromRedisByKey(String key) {
        return codisLocalClient.smembers(codisLocalClient.mkKey(key, "dr_user_active"));
    }

    /**
     * 从数据set中统计出map
     * @param records
     * @return
     */
    private Map<String, Integer> getCountMapFromSet(List<String> records) {
        Map<String, Integer> activationCountMap = new HashMap<>();
        if (records == null) {
            return null;
        }
        for (String record:
                records) {
            String[] items = record.split("_");
            int count = 1;
            if (activationCountMap.containsKey(acToStoreMap.get(items[2]))) {
                count += activationCountMap.get(acToStoreMap.get(items[2]));
            }
            activationCountMap.put(acToStoreMap.get(items[2]), count);
        }
        return activationCountMap;
    }

    public static void main(String[] args) {
        CountActivationServiceImpl countActivationService = new CountActivationServiceImpl();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 4);
        cal.set(Calendar.HOUR_OF_DAY, 16);
        cal.set(Calendar.MINUTE, 20);
        cal.set(Calendar.SECOND, 0);
        System.out.println(cal.getTime());

        while (true) {
            System.out.println(new Date() + "\t16:20:00\t" + countActivationService.getActivationCountMapByDuration(cal.getTime(), 1));
            System.out.println(new Date() + "\t16:10-16:20\t" + countActivationService.getActivationCountMapBy10Min(cal.getTime()));
            System.out.println(new Date() + "\t近十分钟\t" + countActivationService.getActivationCountMapBy10Min(new Date()));
            System.out.println();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


//        for (int i = 0; i < 30; i++) {
//            System.out.println(countActivationService.getActivationCountMapBy10Min(new Date(new Date().getTime() - i * 10 * 60 * 1000)));
//        }
//
//        for (int i = 0; i < 3600; i++) {
//            System.out.println(countActivationService.getSetFromRedisByKey(countActivationService.getSecondTimestamp(new Date(new Date().getTime() - i * 1000))));
//        }

//        Date date = new Date((new Date().getTime() - 0 * 60 * 60 * 1000) / 60 / 60 / 1000 * 60 * 60 * 1000);
//        System.out.println(date);
//        System.out.println(countActivationService.getActivationCountMapBy1Hour(date));
//        Map<String, Integer> records = new HashMap<>();
//        for (int i = 0; i < 6; i++) {
//            System.out.println(date);
//            Map<String, Integer> map = countActivationService.getActivationCountMapBy10Min(date);
//            for (Map.Entry<String, Integer> entry:
//                    map.entrySet()) {
//                if (records.containsKey(entry.getKey())) {
//                    records.put(entry.getKey(), entry.getValue() + records.get(entry.getKey()));
//                }
//                else {
//                    records.put(entry.getKey(), entry.getValue());
//                }
//            }
//            date = new Date(date.getTime() - 600 * 1000);
//        }
//        System.out.println(records);
    }

}
