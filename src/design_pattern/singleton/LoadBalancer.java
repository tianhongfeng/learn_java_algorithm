package design_pattern.singleton;


import sun.security.jca.GetInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoadBalancer {

    public static LoadBalancer ss = null;

    private List<String> serverLsit = null;

    private LoadBalancer(){
        serverLsit = new ArrayList();
    }

    public static LoadBalancer getInstance(){
        if(ss == null){
            ss = new LoadBalancer();
        }
        return ss;
    }

    public void addServer(String server){
        serverLsit.add(server);
    }

    public void removeServer(String server){
        if(serverLsit.size() > 0){
            serverLsit.remove(server);
        }
    }

    public String getServer(){
        Random ra = new Random();
        int i = ra.nextInt(serverLsit.size());
        return serverLsit.get(i);
    }

    public static void main(String[] args) {

        LoadBalancer lb = LoadBalancer.getInstance();
        lb.addServer("server a");
        LoadBalancer lb1 = LoadBalancer.getInstance();
        lb1.addServer("server b");
        LoadBalancer lb2 = LoadBalancer.getInstance();
        lb2.addServer("server c");
        lb2.addServer("server d");

        System.out.println(lb.hashCode());
        System.out.println(lb1.hashCode());
        System.out.println(lb2.hashCode());

        System.out.println(lb.getServer());
        System.out.println(lb1.getServer());
        System.out.println(lb2.getServer());

    }

}
