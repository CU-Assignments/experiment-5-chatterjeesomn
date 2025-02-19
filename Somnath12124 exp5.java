/**
QUESTION 1
*/

import java.util.Scanner;

public class SumOfIntegers {

    // Method to calculate the sum of integers from an array of strings
    public static int calculateSum(String[] stringNumbers) {
        int sum = 0;
        for (String str : stringNumbers) {
            sum += Integer.parseInt(str); // Parse string to int and add to sum
        }
        return sum;
    }

    public static void main(String[] args) {
        // Create a Scanner object to get input from the user
        Scanner scanner = new Scanner(System.in);

        // Ask the user to enter numbers separated by spaces
        System.out.println("Enter numbers separated by spaces:");

        // Read the entire line of input as a string
        String input = scanner.nextLine();

        // Split the input string into an array of strings using spaces as the delimiter
        String[] stringNumbers = input.split(" ");

        // Calculate the sum of the integers
        int sum = calculateSum(stringNumbers);

        // Print the sum
        System.out.println("The sum of the integers is: " + sum);

        // Close the scanner to avoid resource leaks
        scanner.close();
    }
}

/**
QUESTION 2
*/
import java.io.*;
import java.util.Scanner;

class Student implements Serializable {
    private int id;
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', gpa=" + gpa + "}";
    }
}

public class StudentSerialization {

    // Serialize the Student object
    public static void serializeStudent(Student student, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(student);
            System.out.println("Student object serialized and saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e.getMessage());
        }
    }

    // Deserialize the Student object
    public static Student deserializeStudent(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (Student) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error during deserialization: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User input for student details
        System.out.print("Enter student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student GPA: ");
        double gpa = scanner.nextDouble();

        // Create a Student object with user input
        Student student = new Student(id, name, gpa);

        // File to save the serialized object
        String filename = "student.ser";

        // Serialize the student object
        serializeStudent(student, filename);

        // Deserialize the student object and print details
        Student deserializedStudent = deserializeStudent(filename);
        if (deserializedStudent != null) {
            System.out.println("Deserialized Student: " + deserializedStudent);
        } else {
            System.out.println("Failed to deserialize the student.");
        }

        scanner.close();
    }
}

/**
QUESTION 3
  */
import java.io.*;
import java.util.*;

class Employee implements Serializable {
    private String name;
    private int id;
    private String designation;
    private double salary;

    // Constructor
    public Employee(String name, int id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary;
    }
}

public class EmployeeManagement {

    // Method to add an employee
    public static void addEmployee(String filename) {
        Scanner scanner = new Scanner(System.in);

        // Get employee details from user
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Designation: ");
        String designation = scanner.nextLine();
        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();

        // Create Employee object
        Employee employee = new Employee(name, id, designation, salary);

        // Save employee to file
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename, true))) {
            out.writeObject(employee); // Append the employee to the file
            System.out.println("Employee added successfully!");
        } catch (IOException e) {
            System.out.println("Error saving employee: " + e.getMessage());
        }
    }

    // Method to display all employees
    public static void displayEmployees(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            System.out.println("\nEmployee Details:");
            while (true) {
                Employee employee = (Employee) in.readObject();
                System.out.println(employee);
            }
        } catch (EOFException e) {
            // Reached end of file
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading employee data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename = "employees.ser";

        // Menu loop
        int option;
        do {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    addEmployee(filename);
                    break;
                case 2:
                    displayEmployees(filename);
                    break;
                case 3:
                    System.out.println("Exiting the application...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } while (option != 3);

        scanner.close();
    }
}

