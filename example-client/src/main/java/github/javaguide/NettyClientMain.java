package github.javaguide;

import github.javaguide.annotation.RpcScan;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@RpcScan(basePackage = {"github.javaguide"})
public class NettyClientMain {
    public static void main(String[] args) throws InterruptedException {

        /**
         *不是spring那样的，现在不在一个包内，需要通过服务注册发现寻找去使用这个类
         */
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(NettyClientMain.class);
        HelloController helloController = (HelloController) applicationContext.getBean("HelloController");
        helloController.test();
    }
}
