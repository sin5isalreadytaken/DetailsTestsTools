package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wenxiangzhou214164 on 2017/9/15
 */
public class FastJSONTest {

    public static void main(String[] args) {
        test2();
    }

    static class Test implements Serializable{
        private static final long serialVersionUID = -7864593861213919927L;
        int i;
        Test() {}
        Test(int i) {
            this.i = i;
        }

        @Override
        public String toString() {
            return "Test{" +
                    "i=" + i +
                    '}';
        }
    }

    public static void test1() {
        List<Test> tests = new ArrayList<>();
        tests.add(new Test(1));
        tests.add(new Test(2));
        String array = JSON.toJSONString(tests);
        System.out.println(array);
        List<Test> tests1 = JSON.parseArray(array, Test.class);
        System.out.println(tests1);
    }

    public static void test2() {
        String t = "{\n" +
                "    \"keyword\": [\n" +
                "        {\n" +
                "            \"橄榄绿\": 0.8587737703599898\n" +
                "        },\n" +
                "        {\n" +
                "            \"检察干警\": 0.8063611422905119\n" +
                "        },\n" +
                "        {\n" +
                "            \"白加黑\": 0.7660760872037602\n" +
                "        },\n" +
                "        {\n" +
                "            \"军人\": 0.5564924293363566\n" +
                "        },\n" +
                "        {\n" +
                "            \"人民群众\": 0.5512570785011335\n" +
                "        },\n" +
                "        {\n" +
                "            \"司法\": 0.49362697534353545\n" +
                "        },\n" +
                "        {\n" +
                "            \"可喜\": 0.47824623187842386\n" +
                "        },\n" +
                "        {\n" +
                "            \"角色\": 0.38876804445575225\n" +
                "        }\n" +
                "    ],\n" +
                "    \"status\": \"success\"\n" +
                "}";

        JSONObject jo = JSONObject.parseObject(t);
        JSONArray ja = jo.getJSONArray("keyword");
        Map<String, Double> map = new HashMap<>();
        for (Object o : ja) {
            JSONObject item = (JSONObject) o;
            String keywrod = (String) item.keySet().toArray()[0];
            double weight = Double.parseDouble(String.valueOf(item.get(keywrod)));
            map.put(keywrod, weight);
        }
        System.out.println(map);
    }

}
