package map;

public class Person extends Employee{
    /**
     * Constructs an employee with $0 salary.
     *
     * @param name
     */
    public Person(String name) {
        super(name);
    }

    public String name() {
        return "超人";
    }

    public Employee hero() {
        return new Employee("");
    }

    public static void main(String[] args){
        Employee P = new Person("ls");
        System.out.println(P.name());
    }

}
