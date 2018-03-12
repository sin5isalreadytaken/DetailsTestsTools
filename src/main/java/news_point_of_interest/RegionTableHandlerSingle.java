package news_point_of_interest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wenxiangzhou214164 on 2017/7/14.
 */
public class RegionTableHandlerSingle {
    private static Logger logger = LoggerFactory.getLogger(RegionTableHandlerSingle.class);

    static String dburl;
    static String dname;
    static String user;
    static String password;
    static ConnectionPool connPool;
    static int count = 0, noMatchCount = 0, addressCount = 0, locationCount = 0, addressinfoCount = 0, refreshCount = 0;

    private static int targetDecimal = 4;
    private static int minDecimal = 2;

    static String rinSql = "select name,address,location,addressinfo from region_info_new limit 100";
    static String riSql = "select parentsInfo,location from region_info where level = 6 and location like ?";
    static String rinUpdSql = "update region_info_new set addressinfo_refresh = ? where name = ?";
    static Connection conn = null;

    public static synchronized int addCount() {
        return ++count;
    }
    public static synchronized int addAddressinfoCount() {
        return ++addressinfoCount;
    }
    public static synchronized int addNoMatchCount() {
        return ++noMatchCount;
    }
    public static synchronized int addRefreshCount() {
        return ++refreshCount;
    }

    static {
        dburl = "jdbc:mysql://10.13.82.17:3306/spider";
        dname = "com.mysql.jdbc.Driver";
        user = "recom";
        password = "xsKR6QSufx";

        connPool = new ConnectionPool(dname, dburl, user, password);

        try {
            connPool.createPool();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            conn = connPool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        region_new_info_addressTransform();
    }

    /**
     * 根据region_info_new表的address字段将address
     */
    private static  void region_new_info_addressTransform() {
        if (conn != null) {
            try {
                PreparedStatement rinPst = conn.prepareStatement(rinSql);
                PreparedStatement rinUpdPst = conn.prepareStatement(rinUpdSql);
                PreparedStatement riPst = conn.prepareStatement(riSql);
                ResultSet rinResultSet = rinPst.executeQuery();
                ResultSet riResultSet = null;

                while (rinResultSet.next()) {
                    String address = rinResultSet.getString("address");
                    String name = rinResultSet.getString("name");
                    String locationRin = rinResultSet.getString("location");
                    String addressinfo = rinResultSet.getString("addressinfo");
                    if (address == null || address.endsWith("县")){
//                            || (address.endsWith("区") && !address.endsWith("小区"))) {
                        logger.info(++count + "\t" + name + "\t" + address + "\t" + "address不满足要求" + "\tunqualified address num:" + ++addressCount);
                        rinUpdPst.setString(1, "address");
                        rinUpdPst.setString(2, name);
                        if (!rinUpdPst.execute()) {
//                            logger.info(count + "\t" + name + "\t" + "address update failed");
                        }
                        continue;
                    }
                    if (locationRin == null) {
                        logger.info(++count + "\t" + name + "\t" + locationRin + "\t" + address + "\t" + "location不满足要求" + "\tunqualified location num:" + ++locationCount);
                        continue;
                    }
                    String[] cord = locationRin.split(",");
                    String lon = cord[0];
                    String lat = cord[1];
                    String addressResult = null;
                    for (int d = targetDecimal; addressResult == null && d >= minDecimal; d--) {
                        if ((lat = checkLocation(lat, d)) == null) {
                            logger.info(++count + "\t" + name + "\t'%" + lat + "%" + lon + "%'\t" + "location不满足要求" + "\tunqualified location num:" + ++locationCount);
                            continue;
                        }
                        if ((lon = checkLocation(lon, d)) == null) {
                            logger.info(++count + "\t" + name + "\t'%" + lat + "%" + lon + "%'\t" + "location不满足要求" + "\tunqualified location num:" + ++locationCount);
                            continue;
                        }
                        riPst.setString(1, "%" + lat + "%" + lon + "%");
                        riResultSet = riPst.executeQuery();
                        if (riResultSet == null) {
                            continue;
                        }
                        double dis = Double.MAX_VALUE;
                        while (riResultSet.next()) {
                            String parentsInfo = riResultSet.getString("parentsInfo");
                            if (parentsInfo == null) continue;
                            String location = riResultSet.getString("location");
                            if (location == null) continue;
                            String latlon = location.replaceAll("\"", "")
                                    .replaceAll("\\{lat:", "")
                                    .replaceAll("lng:","")
                                    .replaceAll("}","");
                            String latTemp = latlon.split(",")[0];
                            String lonTemp = latlon.split(",")[1];
                            double disTemp = Math.pow(Double.parseDouble(lat) - Double.parseDouble(lon), 2) + Math.pow(Double.parseDouble(latTemp) - Double.parseDouble(lonTemp), 2);
                            if (disTemp < dis) {
                                dis = disTemp;
                                addressResult = parentsInfo;
                            }
                        }
                    }
                    if (addressResult == null) {
                        logger.info(addCount() + "\t" + name + "\t'%" + lat + "%" + lon + "%'\t" + "无匹配" + "\tno match count:" + addNoMatchCount());
                    }
                    else {
                        String addressinfoRefresh = null;
                        if ((addressinfoRefresh = transformAddressinfo(addressinfo, addressResult)) == null) {
                            logger.info(addCount() + "\t" + name + "\t'%" + lat + "%" + lon + "%'\t" + addressinfo + "\t" + "addressinfo不满足要求" + "\tunqualified addressinfo num:" + addAddressinfoCount());
                            rinUpdPst.setString(1, "addressinfo");
                            rinUpdPst.setString(2, name);
                            if (!rinUpdPst.execute()) {
//                                logger.info(count + "\t" + name + "\t" + "addressinfo update failed");
                            }
                        }
                        else {
                            logger.info(addCount() + "\t" + name +  "\t'%" + lat + "%" + lon + "%'\t" + addressinfo + "\t" + addressinfoRefresh +  "\trefresh count:" + addRefreshCount());
                            rinUpdPst.setString(1, addressinfoRefresh);
                            rinUpdPst.setString(2, name);
                            if (!rinUpdPst.execute()) {
//                                logger.info(count + "\t" + name + "\t" + addressinfo + "\t" + " update failed");
                            }
                        }
                    }
                }
                rinResultSet.close();
                rinPst.close();
                rinUpdPst.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else {
            logger.info("coon == null");
        }
    }

    public static String checkLocation(String l, int targetDecimal) {
        int indexofPoint = l.indexOf('.');
        if (indexofPoint == -1) {
            return null;
        }
        if (indexofPoint + targetDecimal + 1 > l.length()) {
            for (int d = targetDecimal - 1; d >= minDecimal; d--) {
                if (indexofPoint + d + 1 > l.length()) {
                    continue;
                }
                return l.substring(0, indexofPoint + d + 1);
            }
            return null;
        }
        return l.substring(0, indexofPoint + targetDecimal + 1);
    }

    public static String transformAddressinfo(String addressinfo, String r) {
        if (addressinfo == null) {
            return null;
        }
        String[] levels = addressinfo.split(",");
        if (levels.length < 5) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(levels[0]).append(",");
        sb.append(levels[1]).append(",");
        sb.append(levels[2]).append(",");
        sb.append(r).append(",");
        sb.append(levels[levels.length - 1]);
        return  sb.toString();
    }

}
