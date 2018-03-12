package springStaticAutowired.staticAutowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springStaticAutowired.service.Test;

import javax.annotation.PostConstruct;

/**
 * @author wenxiangzhou214164
 * @date 2017/11/8
 */
@Component
public class StaticTest {

    @Autowired
    private Test test;
    private static StaticTest staticTest;
    @PostConstruct
    private void init() {
        staticTest = this;
        staticTest.test = this.test;
    }

    public static String test() {
        return staticTest.test.test();
    }

}
