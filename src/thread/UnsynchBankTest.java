package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This program shows data corruption when multiple threads access a data structure.
 * @version 1.31 2015-06-21
 * @author Cay Horstmann
 */
public class UnsynchBankTest
{
   public static final int NACCOUNTS = 100;
   public static final double INITIAL_BALANCE = 1000;
   public static final double MAX_AMOUNT = 1000;
   public static final int DELAY = 10;
   
   public static void main(String[] args)
   {

      String a = "hello2";
      String q = "hello2";
      final String b = "hello";
      String c = "hello";
      String d = b + 2;
      String e = c + 2;
      System.out.println(a == d);
      System.out.println(a == e);

//      Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
//
//      for (int i = 0; i < NACCOUNTS; i++)
//      {
//         int fromAccount = i;
//         Runnable r = () -> {
//            try
//            {
//               int toAccount = (int) (bank.size() * Math.random());
//               double amount = MAX_AMOUNT * Math.random();
//               bank.transfer(fromAccount, toAccount, amount);
//               TimeUnit.SECONDS.sleep(5);
//            }
//            catch (InterruptedException e)
//            {
//            }
//         };
//         Thread t = new Thread(r);
//         t.start();
//      }
   }
}
