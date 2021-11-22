package Obsever_pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

//观察者接口
interface Observers {
    void update();
    void update(Subject s);
}

//怪物
class Monster implements Observers {

    @Override
    public void update() {
        if(inRange()){
            System.out.println("怪物 对主角攻击！");
        }
    }

    @Override
    public void update(Subject s) {
        s.move();
    }

    private boolean inRange(){
        //判断主角是否在自己的影响范围内，这里忽略细节，直接返回true
        return true;
    }
}

//陷阱
class Trap implements Observers {

    @Override
    public void update() {
        if(inRange()){
            System.out.println("陷阱 困住主角！");
        }
    }

    @Override
    public void update(Subject s) {

    }

    private boolean inRange(){
        //判断主角是否在自己的影响范围内，这里忽略细节，直接返回true
        return true;
    }
}

//宝物
class Treasure implements Observers {

    @Override
    public void update() {
        if(inRange()){
            System.out.println("宝物 为主角加血！");
        }
    }

    @Override
    public void update(Subject s) {

    }

    private boolean inRange(){
        //判断主角是否在自己的影响范围内，这里忽略细节，直接返回true
        return true;
    }
}

//抽象观察对象
abstract class Subject {

    private List<Observers> observerList = new ArrayList<>();

    public void attachObserver(Observers observer) {
        observerList.add(observer);
    }

    public void detachObserver(Observers observer){
        observerList.remove(observer);
    }

    public void notifyObservers(){
        for (Observers observer: observerList){
            observer.update(this);
        }
    }

    abstract void move();
}

// 具体观察对象
class Observer_subject extends Subject{

    @Override
    void move() {
        System.out.println("主角向前移动");
        notifyObservers();
    }
}

public class Main{
    public static void main(String[] args) {
        Observer_subject observer_subject = new Observer_subject();
        // 注册观察者
        observer_subject.attachObserver(new Monster());
//        observer_subject.attachObserver(new Trap());
//        observer_subject.attachObserver(new Treasure());
        observer_subject.move();
    }
}


