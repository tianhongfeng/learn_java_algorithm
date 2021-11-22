package dynamicProxy;

public class SvmServiceImpl implements SvmService{
    @Override
    public String send(String message) {
        System.out.println("send message:" + message);
        return null;
    }
}
