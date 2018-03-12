package tool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by wenxiangzhou214164 on 2017/6/28.
 */
public class DistinctLine {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\workspace\\sohu\\MediaMarketPromotion\\temp\\有激活记录的渠道号.txt"));
        FileWriter fw = new FileWriter("D:\\workspace\\sohu\\MediaMarketPromotion\\temp\\有激活记录的渠道号2.txt");
        String line = null;
        Set<String> lineSet = new HashSet<String>();
        while ((line = br.readLine()) != null) {
            lineSet.add(line);
        }
        br.close();
        for (String s:
                lineSet) {
            fw.write(s + "\r\n");
        }
        fw.close();
        System.out.println("done!");
    }
}
