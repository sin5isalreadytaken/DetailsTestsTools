package news_top_server;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by wenxiangzhou214164 on 2017/7/23.
 */
public class Test {

    public static List<RawNewsItem> rawNewsItems = new ArrayList<>();

    public static void init() {
        for (int i = 0; i < 1000000; i++) {
            NewsScoreItem newsScoreItem = new NewsScoreItem();
            newsScoreItem.setCtr(1);
            newsScoreItem.setCtrScore(1);
            newsScoreItem.setCreateTimeScore(1);
            newsScoreItem.setFormalClickNormal(1);
            newsScoreItem.setNormalScore(1);
            newsScoreItem.setRecomClickNormal(1);
            newsScoreItem.setShareClickScore(1);
            newsScoreItem.setTotalShareNormal(1);
            newsScoreItem.setShareNormal(1);
            RawNewsItem rawNewsItem = new RawNewsItem();
            rawNewsItem.setChannel("100000");
            rawNewsItem.setCollect(1);
            rawNewsItem.setComment(1);
            rawNewsItem.setCreateTime(new Date().getTime());
            rawNewsItem.setDataSource("htttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttp");
            rawNewsItem.setDocId("wejfiahewichih3u10u3rhsoe203ur0fhw9ch2983");
            rawNewsItem.setFormalClick(1);
            rawNewsItem.setFormalExpose(1);
            rawNewsItem.setMedia("media");
            rawNewsItem.setOid("1111111111" + i);
            rawNewsItem.setRecomClick(1);
            rawNewsItem.setRecomExpose(1);
            rawNewsItem.setScoreItem(newsScoreItem);
            rawNewsItem.setShare(1);
            rawNewsItem.setSubChannel("100000");
            rawNewsItem.setTitle("wejofofijwoef2r0ashd98429hfhzhkshie2938yr9shih29h9cm92jjfifa9iiajckzyyyhkkh18ihkzgiiwuhebzgebgtafebgawzhengyafeng");
            rawNewsItem.setTotalCollect(1);
            rawNewsItem.setTotalComment(1);
            rawNewsItem.setTotalFormalClick(1);
            rawNewsItem.setTotalFormalExpose(1);
            rawNewsItem.setTotalRecomClick(1);
            rawNewsItem.setTotalRecomExpose(1);
            rawNewsItem.setShare(1);
            rawNewsItems.add(rawNewsItem);
        }
    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        init();
        System.out.println(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
        System.out.println(rawNewsItems.get(1030));
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            System.out.println(rawNewsItems.get(scanner.nextInt()));
        }
    }
}
