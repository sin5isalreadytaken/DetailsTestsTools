package tool;

import java.io.*;

/**
 * Created by wenxiangzhou214164 on 2017/7/19.
 */
public class StringCount {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\workspace\\idea\\media-content-storage\\logs\\news-top-server.log"));
        String line = null;
        int stringCount = 0;
        while ((line = br.readLine()) != null) {
            if (line.contains("Caused by: java.net.SocketTimeoutException: Read timed out.")) {
                stringCount++;
            }
        }
        br.close();
        System.out.println(stringCount);
    }
}
