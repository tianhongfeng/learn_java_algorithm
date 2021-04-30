/**
 * This program displays a greeting for the reader.
 * @version 1.30 2014-02-27
 * @author Cay Horstmann
 */

import java.io.Console;
import java.util.*;
public class Welcome
{
   public static void main(String[] args)
   {
//      String greeting = "Welcome to Core Java!";
//      int n  = greeting.length();
//      int index = greeting.offsetByCodePoints(0, n-1);
//      int cp = greeting.codePointAt(index);

//      int cpCount = greeting.codePointCount(0, n);
//      System.out.println(cp);
//      System.out.println(greeting);
//      for (int i = 0; i < greeting.length(); i++)
//         System.out.print("=");
//      System.out.println();

//      Scanner in = new Scanner(System.in);
//
//      // get first input
//      System.out.print("What is your name? ");
//      String name = in.nextLine();
//
//      // get second input
//      System.out.print("How old are you? ");
//      int age = in.nextInt();
//
//      // display output on console
//      System.out.println("Hello, " + name + ". Next year, you'll be " + (age + 1));

      Console cons = System.console();
      String  username = cons.readLine("User name: ");
      char[] passwor = cons.readPassword("Password :");

   }
}
