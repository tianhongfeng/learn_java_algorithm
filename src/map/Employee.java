package map;

/**
 * A minimalist employee class for testing purposes.
 */
public class Employee
{
   private String name;
   private double salary;

   /**
    *
    * @param name
    */
   public Employee(String name)
   {
      this.name = name;
      salary = 0;
   }

   public String name() {
      return "超级英雄";
   }

   public Employee hero() {
      return new Employee("");
   }

   public String toString()
   {

      return "[name=" + name + ", salary=" + salary + "]";
   }

   public static void main(String[] args){
      Integer b = new Integer(2);
      Integer c = b;
      System.out.println(b == c);
      Employee P = new Employee("ls");
      P.name();
   }
}
