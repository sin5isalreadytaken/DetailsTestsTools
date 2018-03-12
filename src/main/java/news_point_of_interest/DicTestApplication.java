package news_point_of_interest;

import org.ansj.domain.Term;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wenxiangzhou on 2017/5/26.
 */
@RestController
@SpringBootApplication
public class DicTestApplication {
    @RequestMapping(value = "/testDic")
    @ResponseBody
    public String testDic(@RequestParam String sentence) {
        long time = System.currentTimeMillis();
        System.out.println(sentence);
        List<Term> list = SegmentKit.segmentSentence(sentence);
        System.out.println(list.toString());
        return (System.currentTimeMillis() - time) + "    " + list.toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(DicTestApplication.class, args);
    }
}
