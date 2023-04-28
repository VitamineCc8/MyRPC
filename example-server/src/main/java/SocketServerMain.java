import github.javaguide.HelloService;
import github.javaguide.config.RpcServiceConfig;
import github.javaguide.remoting.transport.socket.SocketRpcServer;
import github.javaguide.serviceimpl.HelloServiceImpl;

/**
 * @author shuang.kou
 * @createTime 2020年05月10日 07:25:00
 */
public class SocketServerMain {
    public static void main(String[] args) {
        //构建调用类
        HelloService helloService = new HelloServiceImpl();
        //发送消息的主心函数
        SocketRpcServer socketRpcServer = new SocketRpcServer();
        //new一个封装调用类信息的类
        RpcServiceConfig rpcServiceConfig = new RpcServiceConfig();
        //开始封装调用类
        rpcServiceConfig.setService(helloService);
        //将封装后的调用类进行注册
        socketRpcServer.registerService(rpcServiceConfig);
        //开启服务
        socketRpcServer.start();
    }
}
