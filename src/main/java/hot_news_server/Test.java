package hot_news_server;

import com.sohu.mrd.framework.hbase.client.HBaseClient;
import com.sohu.mrd.framework.hbase.client.ResultsExtractor;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.HbaseUtil;

import java.io.IOException;

/**
 * Created by wenxiangzhou214164 on 2017/7/20.
 */
public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);
    private static HBaseClient client;

    static {
        client = HbaseUtil.getClient();
    }

    public static void main(String[] args) {
        Scan scan = new Scan();

        scan.addFamily("f".getBytes());
        scan.setCaching(10000);
        long current = System.currentTimeMillis();
        try {
            scan.setTimeRange(current - 24 * 60 * 60 * 1000, current);
        } catch (IOException e) {
            logger.error("current:{}", current, e);
        }

        client.find("news", scan, new ResultsExtractor<Object>() {

            @Override
            public Object extractData(ResultScanner results) throws Exception {
                int count = 0;
                if (results == null) {
                    System.out.println("results null");
                }
                if (results.next() == null) {
                    System.out.println("result null");
                }
                for (Result result : results) {
                    System.out.println(++count);
                    System.out.println(result.toString());
                }
                return null;
            }
        });
        System.out.println("done");
    }
}
