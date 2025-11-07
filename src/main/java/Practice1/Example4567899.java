package Practice1;


import java.util.*;
import java.util.stream.Collectors;

class Employee {
    int id;
    String name;
    String department;
    double salary;

    Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String toString() {
        return name + "(" + department + "," + salary + ")";
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
}

class calculateEmployeeByEachDepartment{
    public static void main(String[] args) {

      List<Employee> employees = new ArrayList<>();
      Map<String,Integer> departments = new HashMap<>();
      Map<String,List<Employee>> m = employees.stream().collect(Collectors.groupingBy(employee -> employee.getDepartment()));

      for(Map.Entry<String,List<Employee>> entry : m.entrySet()){
          departments.put(entry.getKey(),entry.getValue().size());
      }

    }

}

