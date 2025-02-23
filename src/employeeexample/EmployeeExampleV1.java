package employeeexample;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeExampleV1 {

    public static void main(String[] args) {
        List<Employee> employeeList = EmployeeLoadData.loadEmployee();

        // Exercise 1 : How many male and female employees are there in the organization?
        howManyMaleAndFemaleInTheOrganization(employeeList);

        // Exercise 2 : Print the name of all departments in the organization.
        printNameOfAllDepartmentsInTheOrganization(employeeList);

        // Exercise 3 : What is the average age of male and female employees?
        averageAgeOfAllMaleAndFemaleInTheOrganization(employeeList);

        // Exercise 4 : Get the details of highest paid employee in the organization
        getDetailsOfHighestReleasedEmployee(employeeList);

        // Exercise 5 : Get the names of all employees who have joined after 2015
        getNamesOfEmployeesWhoHaveJoinedAfter2015(employeeList);

        // Exercise 6 : Count the number of employees in each department
        countNumberEmployeeInEachDepartment(employeeList);

        // Exercise 7 : What is the average salary of each department?
        averageSalaryOfEachDepartment(employeeList);

        // Exercise 8 : Who has the most working experience in the organization?
        whoHasMostWorkingExperience(employeeList);

        // Exercise 9 : Get the details of youngest male employee in the each department.
        getDetailsOfYoungestMaleInEachDepartment(employeeList);

    }

    // Ex:1
    private static void howManyMaleAndFemaleInTheOrganization(List<Employee> employeeList) {
        Map<String, Long> maleAndFemaleEmployeesCount = employeeList.stream().collect(Collectors.groupingBy(e -> e.getEmp_gender(), Collectors.counting()));

        System.out.println("--> howManyMaleAndFemaleInTheOrganization:");
        maleAndFemaleEmployeesCount.entrySet().forEach(e -> System.out.println(e.getKey() + ", " + e.getValue()));
    }

    // Ex:2
    private static void printNameOfAllDepartmentsInTheOrganization(List<Employee> employeeList) {
        Set<String> allDepts = employeeList.stream().map(e -> e.getEmp_dept()).collect(Collectors.toSet());

        System.out.println("--> printNameOfAllDepartmentsInTheOrganization: \n" + allDepts);

    }

    // Ex:3
    private static void averageAgeOfAllMaleAndFemaleInTheOrganization(List<Employee> employeeList) {
        Map<String, Double> avgAgeOfMFEmployees = employeeList.stream().collect(Collectors.groupingBy(e -> e.getEmp_gender(), Collectors.averagingInt(e -> e.getEmp_age())));

        System.out.println("--> averageAgeOfAllMaleAndFemaleInTheOrganization:");
        avgAgeOfMFEmployees.entrySet().forEach(e -> System.out.println(e.getKey() + ", " + e.getValue()));
    }

    // Ex:4
    private static void getDetailsOfHighestReleasedEmployee(List<Employee> employeeList) {
        Optional<Employee> highPaidEmployee =  employeeList.stream().collect(Collectors.maxBy(Comparator.comparing(e -> e.getEmp_salary())));

        System.out.println("--> getDetailsOfHighestReleasedEmployee: \n" + highPaidEmployee);
    }

    // Ex: 5
    private static void getNamesOfEmployeesWhoHaveJoinedAfter2015(List<Employee> employeeList) {
        List<Employee> employees = employeeList.stream().filter(e -> e.getEmp_doj() > 2015).collect(Collectors.toList());

        System.out.println("--> getNamesOfEmployeesWhoHaveJoinedAfter2015: \n" + employees);
    }

    // Ex: 6
    private static void countNumberEmployeeInEachDepartment(List<Employee> employeeList) {
        Map<String, Long> employeeInDept = employeeList.stream().collect(Collectors.groupingBy(Employee::getEmp_dept, Collectors.counting()));

        System.out.println("--> countNumberEmployeeInEachDepartment: \n" + employeeInDept);
    }

    // Ex: 7
    private static void averageSalaryOfEachDepartment(List<Employee> employeeList) {
        Map<String, Double> avgSalByDept = employeeList.stream().collect(Collectors.groupingBy(Employee::getEmp_dept, Collectors.averagingDouble(Employee::getEmp_salary)));

        System.out.println("--> averageSalaryOfEachDepartment: \n" + avgSalByDept);
    }

    // Ex: 8
    private static void whoHasMostWorkingExperience(List<Employee> employeeList) {
        Optional<Employee> employee = employeeList.stream().min(Comparator.comparing(Employee::getEmp_doj));

        System.out.println("--> whoHasMostWorkingExperience: \n" + employee);
    }

    // Ex: 9
    private static void getDetailsOfYoungestMaleInEachDepartment(List<Employee> employeeList) {
        Map<String, Optional<Employee>> map = employeeList.stream().filter(e -> e.getEmp_gender().equals("Male")).collect(Collectors.groupingBy(Employee::getEmp_dept, Collectors.minBy(Comparator.comparing(Employee::getEmp_age))));

        System.out.println("--> getDetailsOfYoungestMaleInEachDepartment: \n" + map);
    }
}
