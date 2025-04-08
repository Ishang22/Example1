package Practice2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Description:<br>
 * Date: 08/04/25-1:42 am
 *
 * @author ishangarg
 * @since
 */

final class Address {
    private final String country;

    public Address(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "Address{country='" + country + "'}";
    }
}


final class Employee {
    private final String name;
    private final double salary;
    private final Address address;

    public Employee(String name, double salary, Address address) {
        this.name = name;
        this.salary = salary;
        // Defensive copy to ensure immutability
        this.address = new Address(address.getCountry());
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Address getAddress() {
        // Return a defensive copy to preserve immutability
        return new Address(address.getCountry());
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', salary=" + salary + ", address=" + address + "}";
    }
}

class Main
{
    public static void main(String args[])
    {
        Employee e1 = new Employee("P1",400,new Address("india"));
        Employee e2 = new Employee("P2",500,new Address("india"));
        Employee e3 = new Employee("P3",600,new Address("india"));
        Employee e4 = new Employee("P4",700,new Address("india"));
        Employee e5 = new Employee("P5",800,new Address("india"));

        List<Employee> employees = new ArrayList<>();
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        employees.add(e4);
        employees.add(e5);

        Predicate<Employee> greaterThan40k = employee -> employee.getSalary()>400;
        Predicate<Employee> lessThan80k = employee -> employee.getSalary()<700;

        Predicate<Employee> combination=greaterThan40k.and(lessThan80k);
        //mutable
        List<Employee> resultList =employees.stream().filter(combination).collect(Collectors.toList());
        //unmutable Collections.unmodifiableList()
        List<Employee> resultList1 =employees.stream().filter(combination).toList();

    }
}
