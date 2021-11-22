package map;

public class Manager extends Person{
    /**
     * Constructs an employee with $0 salary.
     *
     * @param name
     */
    public Manager(String name) {
        super(name);
    }

    public String name(){
        return "超级超级英雄";
    }

    public Person hero() {
        return new Person("");
    }
}
