package aop.jdk;

import java.util.ArrayList;
import java.util.List;

public class TestMyAOP {
    public static void main(String[] args) {
        TargetClass tc = new TargetClass();
        AfterHandler ah = new AfterHandlerImpl();
        BeforeHandler bh = new BeforeHandlerImpl();

        List<AbstractHandler> handlerlist = new ArrayList<AbstractHandler>();
        handlerlist.add(ah);
        handlerlist.add(bh);

        TargetInterface proxy = (TargetInterface)ProxyFactory.getProxy(tc, handlerlist);
        // call that method using proxy object we generate
        int calculator = proxy.calculator(100, 10);
        System.out.println("calculator = " + calculator);
    }
}
