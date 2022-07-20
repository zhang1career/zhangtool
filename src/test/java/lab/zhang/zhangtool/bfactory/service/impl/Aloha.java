package lab.zhang.zhangtool.bfactory.service.impl;

import lab.zhang.zhangtool.bfactory.service.Greetable;
import org.springframework.stereotype.Service;

@Service
public class Aloha implements Greetable {
    @Override
    public String getId() {
        return "alo";
    }

    @Override
    public String hi() {
        return "aloha";
    }
}
