package lab.zhang.zhangtool.process.impl;

import lab.zhang.zhangtool.process.IProcessDemo;
import lab.zhang.zhangtool.process.Process;
import lab.zhang.zhangtool.process.State;
import org.springframework.stereotype.Service;

@Service
@Process
public class ProcessStep0Impl implements IProcessDemo {

    @State(process = "demo")
    @Override
    public void do_something() throws Exception {
        System.out.println("step 0");
    }
}
