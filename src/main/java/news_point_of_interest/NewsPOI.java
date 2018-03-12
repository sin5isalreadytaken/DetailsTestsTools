package news_point_of_interest;

import com.sohu.mrd.framework.hbase.client.HBaseLocalClient;
import org.ansj.domain.Term;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.hbase.TableName;
import org.nlpcn.commons.lang.util.StringUtil;
import util.HbaseUtil;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by wenxiangzhou on 2017/5/26.
 */
public class NewsPOI {

    static String dburl;
    static String dname;
    static String user;
    static String password;
    static ConnectionPool connPool;
    public static HBaseLocalClient client;


    static {
        try {
            client = HbaseUtil.getClient();
            if (!client.getAdmin().tableExists(TableName.valueOf("na_gl_geo_dict"))){
                HbaseUtil.createTable("na_gl_geo_dict", "f");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        dburl = "jdbc:mysql://10.13.82.17:3306/spider";
        dname = "com.mysql.jdbc.Driver";
        user = "recom";
        password = "xsKR6QSufx";

        connPool
                = new ConnectionPool(dname,
                dburl
                , user, password);

        try {
            connPool.createPool();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        generateAnsjDic();
//        testMemoryDic();
//        ifLineExists("良");
//        statisticSpace();
//        generateGeneralDic();
//        generateAmb();
//        comHandle();
        mySqlToLocalDic();
        localDicToHBase();
    }

    public static void mySqlToLocalDic() throws IOException {
        Connection conn = null;
        try {
            conn = connPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "select name from region_info_new where type like '%村庄级%' or type like '%村组级%'";
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        FileWriter fw = new FileWriter("src/main/resources/library/countryLevel.dic");
        try {
            pst = conn.prepareStatement(sql);
            resultSet = pst.executeQuery();

            int count = 0;
            while (resultSet.next()) {
                String name = resultSet.getString("name").trim();
                System.out.println(count++ + "\t" + name);
                fw.write(name + "\tn\t1000\r\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        fw.close();
    }

    public static void localDicToHBase() {
        Connection conn = null;
        try {
            conn = connPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql = "select * from region_info_new where type like '%村庄级%' or type like '%村组级%'";
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            pst = conn.prepareStatement(sql);
            resultSet = pst.executeQuery();

            int count = 0;
            while (resultSet.next()) {

                String addressinfo = resultSet.getString("addressinfo").trim();

                Map<String, byte[]> map = new HashMap<String, byte[]>();

                map.put("q", "".getBytes());
                String[] geos = addressinfo.split(",");
                for (String geo:geos){
                    if(!geo.equals("[]")&& StringUtils.isNotBlank(geo)){
                        client.put("na_gl_geo_dict", geo.getBytes(), "f", map);
                    }
                }

                String name = resultSet.getString("name").trim();
                client.put("poi_name_dict", name.getBytes(), "f", map);
                System.out.println(count++ + "\t" + name);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void generateAnsjDic() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("F:\\desktopbackup\\workspace\\sohu\\地点12553179"));
        FileWriter fw = new FileWriter("F:\\desktopbackup\\workspace\\sohu\\dic10w.dic");
        String line = null;
        int count = 0;
        while ((line = br.readLine()) != null && count < 100000) {
            System.out.println(++count + "    " + line);
            line += "\tn\t1000";
            fw.write(line + "\r\n");
        }
        br.close();
        fw.close();
    }

    public static void statisticSpace() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("F:\\desktopbackup\\workspace\\sohu\\region_all.dic"));
        String line = null;
        int count = 0;
        while ((line = br.readLine()) != null) {
            if (line.split("\t")[0].length() == 3) {
                System.out.println(count++ + "    " + line);
            }
        }
        br.close();
    }

    public static void ifLineExists(String s) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("F:\\desktopbackup\\workspace\\sohu\\地点12553179"));
        String line = null;
        int count = 0;
        while ((line = br.readLine()) != null) {
            if (line.equals(s)){
                System.out.println(line);
                count++;
            }
        }
        System.out.println(count);
    }

    public static void generateAmb() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("F:\\desktopbackup\\workspace\\sohu\\news_point_of_interest\\double.dic"));
        FileWriter fw = new FileWriter("F:\\desktopbackup\\workspace\\sohu\\news_point_of_interest\\ambresearch\\amb");
        FileWriter fwTarget = new FileWriter("F:\\desktopbackup\\workspace\\sohu\\news_point_of_interest\\ambresearch\\ambtarget");
        FileWriter fwExplain = new FileWriter("F:\\desktopbackup\\workspace\\sohu\\news_point_of_interest\\ambresearch\\ambexplain");
        String line = null;
        HashSet<String> region = new HashSet<String>();
        List<String> general = new ArrayList<String>();
        while ((line = br.readLine()) != null) {
            region.add(line.split("\t")[0]);
        }
        br.close();
        br = new BufferedReader(new FileReader("F:\\desktopbackup\\workspace\\sohu\\news_point_of_interest\\ambresearch\\general"));
        while ((line = br.readLine()) != null) {
            line = line.split("\t")[0];
            if (StringUtil.isNotBlank(line)) {
                general.add(line);
            }
        }
        br.close();

        int count = 0;
        for (String s:
             region) {
            System.out.println(count++ + "\t" + s);
            int len = s.length();
            String explain = "";
            for (int i = 1; i < len; i++) {
                String prefix = s.substring(0, i);
                int j = general.size();
                for (int m = 0; m < j; m++) {
                    String g = general.get(m);
                    if (g.indexOf(prefix) + i == g.length()) {
                        String suffix = s.substring(i, len);
                        for (int n = 0; n < j; n++) {
                            String g2 = general.get(n);
                            if (g2.indexOf(suffix) == 0) {
                                explain = g + g2 + "\t";
                            }
                        }
                    }
                }
            }
            if (!"".equals(explain)) {
                fw.write(s + "\t" + explain + "\r\n");
                fwTarget.write(s + "\r\n");
                String[] explains = explain.split("\t");
                for (String e :
                        explains) {
                    fwExplain.write(e + "\r\n");
                }
            }
        }
        fw.close();
        fwTarget.close();
        fwExplain.close();
    }

    public static void generateGeneralDic() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("E:\\maven\\LocalWareHouse\\org\\ansj\\ansj_seg\\5.1.1\\ansj_seg-5.1.1\\core.dic"));
        FileWriter fw = new FileWriter("F:\\desktopbackup\\workspace\\sohu\\news_point_of_interest\\ambresearch\\general.dic");
        String line = null;
        line = br.readLine();
        while ((line = br.readLine()) != null) {
            line = line.split("\t")[1];
            if (StringUtil.isNotBlank(line)) {
                fw.write(line + "\r\n");
            }
        }
        br.close();
        fw.close();
    }

    public static void testMemoryDic() throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNext()) {
                String toParse = scanner.next();
                if ("exit".equals(toParse)) {
                    break;
                }
                long start = System.currentTimeMillis();
                List<Term> termList = SegmentKit.segmentSentence(toParse);
                System.out.println((System.currentTimeMillis() - start) + "\t" + termList);
            }
            Thread.sleep(1000);
        }

//        BufferedReader br = new BufferedReader(new FileReader("F:\\desktopbackup\\workspace\\sohu\\dic10w.dic"));
//        String line = null;
//        int count = 0;
//        while ((line = br.readLine()) != null) {
//            count++;
//            line = line.split("n")[0].trim();
//            List<Term> termList = SegmentKit.segmentSentence(line);
//            if (!termList.get(0).getName().equals(line)) {
//                System.out.println(count + "\t" + line + "\t" + termList);
//            }
//        }
//        System.out.println(count);
    }

    public static void comHandle() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("F:\\desktopbackup\\workspace\\sohu\\news_point_of_interest\\wangluokeji3"));
        FileWriter fw = new FileWriter("F:\\desktopbackup\\workspace\\sohu\\news_point_of_interest\\wangluokeji4");
        String line = null;
        HashSet<String> set = new HashSet<String>();
        while ((line = br.readLine()) != null) {
            if (StringUtil.isNotBlank(line)) {
                line = line.replace("有限公司","").
                        replace("股份","").
                        replace("科技","").
                        replace("责任","").
                        replace("集团","").
                        replace("发展","").
                        replace("()","");
                List<Term> termList = SegmentKit.segmentSentence(line);
                for (Term term :
                        termList) {
                    System.out.print(term + "\t");
                    if ("ns".equals(term.getNatureStr())) {
                        line = line.replace(term.getName(), "");
                    }
                    System.out.println();
                }
                if (StringUtil.isNotBlank(line)) {
                    if (line.indexOf('(') != -1) {
                        fw.write(line + "\r\n");
                        line = line.replaceAll("\\(.*?\\)", "");
                    }
                    if (line.length() <= 2) {
                        line = "";
                    }
                    if (StringUtil.isNotBlank(line)) {
                        fw.write(line + "\r\n");
                    }
                }
                set.add(line);
            }
        }
        br.close();
        for (String s:
             set) {
            fw.write(s + "\r\n");
        }
        fw.close();
    }
}
