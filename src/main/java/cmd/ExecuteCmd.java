package cmd;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wenxiangzhou214164 on 2017/9/25
 */
public class ExecuteCmd {

    public static void main(String[] args) {
        try {
            System.out.println("start");
            Process pr = Runtime.getRuntime().exec("python D:/workspace/python/details-tests-tools/ConstraintedOptimization/scipytest.py [1,2,3,4,5] [1,3,5,7,10] [1,4,7,10,14] [1,2,3,4,5] [1,3.1,4,4.7,5.6] [1,3,7,13,20] 11");
            BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            pr.waitFor();
            if (pr.exitValue() != 0) {
                System.out.println("执行失败");
            }
            int ch;
            while ((ch = br.read()) != -1) {
                System.out.print((char) ch);
            }
            br.close();
            pr.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) throws {
//        List<String> command = new ArrayList<>();
//        command.add("python");
//        command.add("D:/workspace/python/details-tests-tools/ConstraintedOptimization/scipytest.py");
//        command.add("[1,2,3,4,5]");
//        command.add("[1,3,5,7,10]");
//        command.add("[1,4,7,10,14]");
//        command.add("[1,2,3,4,5]");
//        command.add("[1,3.1,4,4.7,5.6]");
//        command.add("[1,3,7,13,20]");
//        command.add("11");
//        ProcessBuilder pb = new ProcessBuilder(command);
//        Map<String, String> environ = pb.environment();
//        System.out.println(environ);
//        final Process pr = pb.start();
//        pr.waitFor();
//        if (pr.exitValue() != 0) {
//            System.out.println("执行失败");
//        }
//        BufferedReader br = new BufferedReader(new InputStreamReader(pr.getInputStream()));
//        String line;
//        while ((line = br.readLine()) != null) {
//            System.out.println(line);
//        }
//    }

}
