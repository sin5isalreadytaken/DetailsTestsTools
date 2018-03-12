package util;

import com.sohu.mrd.framework.hbase.client.HBaseLocalClient;
import com.sohu.mrd.framework.hbase.client.TableCallback;
import com.sohu.mrd.framework.hbase.config.HBaseConfig;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Map;

/**
 * Created by yonghongli on 2016/8/30.
 */
public class HbaseUtil {
    private static HBaseConfig config=new HBaseConfig();

    private  static HBaseLocalClient client=null;

    static {

        config.setWriteBufferSize(100000000);

        config.setPort(2181);

        config.setZookeeperQuorum("10.10.21.86,10.10.21.87,10.10.21.88,10.10.21.89,10.10.21.90");

        config.setWriteBufferSize(1000000);

        //默认true如果设置为false需要达到WriteBufferSize才会提交更改或者手动调用client.flush(tableName)提交更改;

        //config.setAutoFlush(false);

        client=new HBaseLocalClient(config);
    }

    public static  void createTable(String t,String f,int fttl){
        final String tableName=t;

        final String familyName=f;

        final int maxVersion=1;

        final int ttl=fttl;



        client.execute(tableName, new TableCallback<Object>() {

            public Object doInTable(Table table) throws Throwable {

                Admin admin = client.getAdmin();

                HTableDescriptor descripter = new HTableDescriptor(TableName.valueOf(tableName));

                //默认10G





                HColumnDescriptor columnDescriptor = new HColumnDescriptor(familyName);

                //默认1

                columnDescriptor.setMaxVersions(maxVersion);

                //ttl单位秒,默认不过期

                columnDescriptor.setTimeToLive(ttl);

                //默认ROW

                columnDescriptor.setBloomFilterType(BloomType.ROW);

                descripter.addFamily(columnDescriptor);

                if(!admin.tableExists(TableName.valueOf(tableName))){

                    admin.createTable(descripter);

                }

                return null;

            }

        });

    }
    public static  void createTable(String t,String f){
        final String tableName=t;

        final String familyName=f;

        final int maxVersion=1;

        client.execute(tableName, new TableCallback<Object>() {

            public Object doInTable(Table table) throws Throwable {

                Admin admin = client.getAdmin();

                HTableDescriptor descripter = new HTableDescriptor(TableName.valueOf(tableName));

                //默认10G





                HColumnDescriptor columnDescriptor = new HColumnDescriptor(familyName);

                //默认1

                columnDescriptor.setMaxVersions(maxVersion);


                //默认ROW

                columnDescriptor.setBloomFilterType(BloomType.ROW);

                descripter.addFamily(columnDescriptor);

                if(!admin.tableExists(TableName.valueOf(tableName))){

                    admin.createTable(descripter);

                }

                return null;

            }

        });

    }



    public static void getRow(String tableName,String family,String row) throws IOException {

        Map<String, byte[]> stringStringMap = client.get(tableName, row, family);

        System.out.println(Bytes.toString(stringStringMap.get("ja")));
        //System.out.println(Bytes.toString(stringStringMap.get("location")));
        System.out.println(stringStringMap.size());

    }

//    public static void scan() throws IOException {
//        String tableName = Constant.NEWS_LOCATION_TABLE_NAME;
//        Scan scan = new Scan();
//        final String family = Constant.NEWS_LOCATION_FAMILY_NAME;
//        final String qualifier = Constant.NEWS_LOCATION_DOCID;
//        scan.setCaching(10000);
//        RegexStringComparator comp = new RegexStringComparator("^"+110108+".*"); // 以 ** 开头的字符串
//        SingleColumnValueFilter filter = new SingleColumnValueFilter(Bytes.toBytes(family), Bytes.toBytes(Constant.NEWS_LOCATION_CODE), CompareFilter.CompareOp.EQUAL, comp);
//        scan.setFilter(filter);
//        scan.addFamily("f".getBytes());
//
//        // scan.setStartRow("ff3aef48-6b30-4b63-bb49-64c42bc4b74f".getBytes());
//        // scan.setStopRow("ffc47ba6-f021-4a95-ad22-b819e7cad3c0".getBytes());
//        client.find(tableName, scan, new ResultsExtractor<Object>() {
//            @Override
//            public Object extractData(ResultScanner results) throws Exception {
//                for (Result result : results) {
//                    /*for(Cell cell:result.rawCells()){
//                        String row=new String(CellUtil.cloneRow(cell));
//                        String family=new String(CellUtil.cloneFamily(cell));
//                        String qualifier=new String(CellUtil.cloneQualifier(cell));
//                        String value=new String(CellUtil.cloneValue(cell));
//                        //do
//                    }*/
////                    byte[] value = result.getValue(family.getBytes(), qualifier.getBytes());
//                    byte[] value2 = result.getValue(family.getBytes(), Constant.NEWS_LOCATION_CODE.getBytes());
//                 //   byte[] value3 = result.getValue(family.getBytes(), Constant.NEWS_LOCATION_LAT.getBytes());
//                    //do
//                    if(value2!=null){
//                        System.out.println(Bytes.toString(value2));
//                        //  System.out.println(Bytes.toString(value3));
//                        System.out.println(Bytes.toString(result.getRow()));
//                    }
//
//
//
//                }
//                return null;
//            }
//        });
//    }


    public static HBaseLocalClient getClient() {
        return client;
    }

    public static void main(String[] args){
     //  HbaseUtil.createTable("news_user_info", "f",14 * 24 * 60 * 60);
      // HbaseUtil.createTable("sub_news_user_info", "f",7 * 24 * 60 * 60);
     //   HbaseUtil.createTable("spns_user_info_aliyun", "f",14*24*60*60);
     //   HbaseUtil.createTable("sohuvideo", "f", 7 * 24 * 60 * 60);
//

     //   client.delete("sohuvideo", "286e04fa96d73c845d46b875b99d819e8411db1c", "f");
     //   client.delete("sohuvideo", "3f84efaac382faceae74ae09efcd84e6aebaadc0", "f");
        try {
//            client.getAdmin().disableTable(TableName.valueOf(Constant.NEWS_LOCATION_TABLE_NAME));
//            client.getAdmin().deleteTable(TableName.valueOf(Constant.NEWS_LOCATION_TABLE_NAME));
//            HbaseUtil.createTable("gaode_poi", "f");
        //    getRow("gaode_poi","f","wx4erpebkzsw");
         //   getRow("user_location","f","6142197602902781979");

//            scan();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        client.delete("sohuvideo", "e962fcc7c8bd03044874e4d94664135c2c809e4f", "f");
//        client.delete("sohuvideo", "0a8ce1988de3b35baebf1a4321952a82a2404261", "f");
//        client.delete("sohuvideo", "fe2752dd455cc2dea050d26e607b266cdeede8f1", "f");

    }
}
