package springStaticAutowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springStaticAutowired.staticAutowired.StaticTest;

/**
 * Created by wenxiangzhou214164 on 2017/9/29
 */
@SpringBootApplication
@ComponentScan(value = "springStaticAutowired")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(springtask.test.Application.class, args);
    }
}
