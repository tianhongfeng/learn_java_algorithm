package abstractClasses;

import innerClass.Outter;

public class SubTestInnerClass extends Outter {

    public static void main(String[] args) {

        SubTestInnerClass sub = new SubTestInnerClass();
        Outter.Inner inner = sub.new Inner();

        Outter.Inner inner1 = sub.getInnerInstance();

    }
}
