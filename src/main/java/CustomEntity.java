/*
we cannot create same s3 bucket if the users are different or not

AWS Step Functions is a serverless orchestration service
that lets you integrate with AWS Lambda functions and other AWS services to build business-critical applications

API compositer deisgn pattern

proxy design pattern
*/

import java.util.Set;
import java.util.TreeSet;

class Employee {
    String Name;
    String RollNo;

    Employee(String Name, String RollNo) {
        this.Name = Name;
        this.RollNo = RollNo;
    }

    String getName() {
        return Name;
    }

    String getRollNo() {
        return RollNo;
    }

    @Override
    public boolean equals(Object anotherObject) {
//        CustomEntity c2 = (CustomEntity) anotherObject;
//
//        if (this.getAddress().id == c2.getAddress().getId()) {
//            return true;
//        }
        return false;
    }
}

class Address {
    String id;
    String address;

    Address(String id, String address) {
        this.id = id;
        this.address = address;
    }

    String getId() {
        return id;
    }

    String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object anotherObject) {
//        CustomEntity c2 = (CustomEntity) anotherObject;
//
//        if (this.getAddress().id == c2.getAddress().getId()) {
//            return true;
//        }
        return false;
    }
}

public class CustomEntity {
    Employee employee;
    Address address;

    CustomEntity(Employee employee, Address address) {
        this.employee = employee;
        this.address = address;
    }

    Address getAddress() {
        return address;
    }

    Employee getEmployee() {
        return employee;
    }

    @Override
    public boolean equals(Object anotherObject) {
//        CustomEntity c2 = (CustomEntity) anotherObject;
//
//        if (this.getAddress().id == c2.getAddress().getId()) {
//            return true;
//        }
        return false;
    }

}

class letsTest {
    public static void main(String args[]) {
        /*
        without comparator it will give error
        * */

        Set<CustomEntity> s1 = new TreeSet<>((c1, c2) -> c1.getAddress().getId().length() - c2.getAddress().getId().length());

        Employee e1 = new Employee("ishan", "21");
        Address a1 = new Address("21", "my address");

        CustomEntity c1 = new CustomEntity(e1, a1);
        CustomEntity c2 = new CustomEntity(e1, a1);

        s1.add(c1);
        s1.add(c2);

        System.out.println("======" + s1.size());
    }
}