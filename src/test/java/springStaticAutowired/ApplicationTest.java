package springStaticAutowired;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import springStaticAutowired.staticAutowired.StaticTest;

import static org.junit.Assert.*;

/**
 * @author wenxiangzhou214164
 * @date 2017/11/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Test
    public void test() {
        System.out.println(StaticTest.test());
    }

}