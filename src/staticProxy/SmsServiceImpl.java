package staticProxy;

public class SmsServiceImpl implements SmsService{
    @Override
    public void send(String message) {
        System.out.println("send message:" + message);
    }
}
