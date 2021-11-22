package thread;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A bank with a number of bank accounts.
 * @version 1.30 2004-08-01
 * @author Cay Horstmann
 */
public class Bank
{
   private final double[] accounts;

   private Lock lock;

   private Condition sufficientFunds;

   private static final int a;

   static{
      a = 2;
   }

   /**
    * Constructs the bank.
    * @param n the number of accounts
    * @param initialBalance the initial balance for each account
    */
   public Bank(int n, double initialBalance)
   {
      accounts = new double[n];
      Arrays.fill(accounts, initialBalance);
      lock = new ReentrantLock();
      sufficientFunds = lock.newCondition();
   }

   /**
    * Transfers money from one account to another.
    * @param from the account to transfer from
    * @param to the account to transfer to
    * @param amount the amount to transfer
    */
   public void transfer(int from, int to, double amount)
   {
      lock.lock();
      // 加锁
      try {
         if (accounts[from] < amount) {
            sufficientFunds.await();
         }
         System.out.print(Thread.currentThread());
         accounts[from] -= amount;
         System.out.printf(" %10.2f from %d to %d", amount, from, to);
         accounts[to] += amount;
         System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
         sufficientFunds.signalAll();
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         lock.unlock();
      }
   }

   /**
    * Gets the sum of all account balances.
    * @return the total balance
    */
   public double getTotalBalance()
   {
      lock.lock();

      double sum = 0;
      try {
         sum = 0;
         for (double a : accounts)
            sum += a;

      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         lock.unlock();
      }
      return sum;
   }

   /**
    * Gets the number of accounts in the bank.
    * @return the number of accounts
    */
   public int size()
   {
      return accounts.length;
   }

   public double getBalance(int i){
      return accounts[i];
   }
}
