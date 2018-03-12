package http;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by byzuse
 * datetime: 2017/2/27 11:36.
 * HttpClient 连接池工具类
 */
public class HttpUtil {

    static CloseableHttpClient httpClient = HttpClients.createDefault();

    public static String getDate(String cid) {

        HttpGet get = new HttpGet("http://api.k.sohu.com/api/user/queryClient.go?cid=" + cid);
        try {
            CloseableHttpResponse response = httpClient.execute(get);
            String responseStr = IOUtils.toString(response.getEntity().getContent(), "utf-8");
            response.close();
            get.releaseConnection();
            Matcher matcher = PATTERN.matcher(responseStr);
            if (matcher.find()) {
                String match = matcher.group().replaceAll("Active Time: ", "").replaceAll("<", "");
                return match;
            }
        } catch (IOException e) {
        }
        return null;
    }

    static final Pattern PATTERN = Pattern.compile("Active Time: .*?<");

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\workspace\\sohu\\project\\清华大学科研需求\\cid.list"));
        FileWriter fw = new FileWriter("D:\\workspace\\sohu\\project\\清华大学科研需求\\cid_activeTime.txt");
        String cid = "";
        int count = 0;
        while ((cid = br.readLine()) != null) {
            count++;
            if (StringUtils.isNotBlank(cid)) {
                String at = getDate(cid);
                if (StringUtils.isNotBlank(at)) {
                    fw.write(cid + "\t" + at + "\n");
                    fw.flush();
                    System.out.println(count + "\t" + cid + "\t" + at);
                }
            }
        }
        br.close();
        fw.flush();
        fw.close();
    }

}