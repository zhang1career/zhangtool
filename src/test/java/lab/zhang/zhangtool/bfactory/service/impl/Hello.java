package lab.zhang.zhangtool.bfactory.service.impl;

import lab.zhang.zhangtool.bfactory.service.Greetable;
import org.springframework.stereotype.Service;

@Service
public class Hello implements Greetable {
    @Override
    public String getId() {
        return "helo";
    }

    @Override
    public String hi() {
        return "hello";
    }
}
