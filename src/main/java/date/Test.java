package date;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.*;

/**
 * Created by wenxiangzhou214164 on 2017/8/9.
 */
public class Test {

    public static void main(String[] args) {
        test2();
    }


    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static void test1() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.toString());
        String day = sdf.format(localDate);
        System.out.println(day);
    }

    public static void test2() {
        LocalDate ld = LocalDate.now();
        LocalDateTime ldt = LocalDateTime.now();
        ldt = ldt.withHour(0);
        ldt = ldt.withMinute(0);
        ldt = ldt.withSecond(0);
        ldt = ldt.withNano(0);
        Timestamp timestamp = Timestamp.valueOf(ldt);
        timestamp.getTime();

        System.out.println(ldt.getLong(ChronoField.MILLI_OF_SECOND));
    }

}
