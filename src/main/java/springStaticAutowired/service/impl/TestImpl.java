package springStaticAutowired.service.impl;

import org.springframework.stereotype.Service;
import springStaticAutowired.service.Test;

/**
 * @author wenxiangzhou214164
 * @date 2017/11/8
 */
@Service
public class TestImpl implements Test {
    @Override
    public String test() {
        return "success";
    }
}
