package com.adruy.streams;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class StreamOperations {

    public static void main(String[] args) {

        List<Employee> employeeList = new ArrayList<>();
        Employee employeeOne = new Employee(1l,"Sameer","Delhi",new Date());
        Employee employeeTwo = new Employee(1l,"Sameer","Chandigarh",new Date());
        employeeList.add(employeeOne);
        employeeList.add(employeeTwo);
    }

    static List<Employee> findEmployeesLivingIn(List<Employee> employees, String city) {
        return null;
    }

    static String maximumPopulatedCity(List<Employee> employees) {
        return null;
    }

    static List<Employee> sortByName(List<Employee> employees) {
        return null;
    }

    static Map<String, Integer> countOfEmployeesInEachCity(List<Employee> employees) {
        return null;
    }


}

class Employee {

    Long id;
    String name;
    String city;
    Date dob;

    @Override
    public String toString() {

        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", dob=" + dob +
                '}';
    }

    public String getCity() {
        return city;
    }

    public Employee(Long id,String name,String city,Date dob){
        this.id=id;
        this.name=name;
        this.city=city;
        this.dob=dob;
    }
}

class Student {
    Long id;
    String name;
    String city;
    Date dob;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", dob=" + dob +
                '}';
    }

    public String getCity() {
        return city;
    }

    public Student(Long id,String name,String city,Date dob){
        this.id=id;
        this.name=name;
        this.city=city;
        this.dob=dob;
    }
}

class EmployeesHelper {

    static List<Employee> findEmployeesLivingIn(List<Employee> employees, String city) {
        return null;
    }

    static String maximumPopulatedCity(List<Employee> employees) {
        return null;
    }

    static List<Employee> sortByName(List<Employee> employees) {
        return null;
    }

    static Map<String, Integer> countOfEmployeesInEachCity(List<Employee> employees) {
        return null;
    }
}
