import java.util.InputMismatchException;
import java.util.Scanner;

//  Custom Exceptions 

class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super(message);
    }
}

class MissingDataException extends Exception {
    public MissingDataException(String message) {
        super(message);
    }
}

//  Student Class

class Student {
    private String name;
    private String rollNo;
    private int[] marks = new int[3]; // 3 subjects
    private double percentage;
    private String result;

    public void inputDetails(Scanner sc) throws MissingDataException, InvalidMarksException {
        sc.nextLine(); // clear buffer

        System.out.print("Enter Student Name: ");
        name = sc.nextLine();
        if (name == null || name.trim().isEmpty()) {
            throw new MissingDataException("Student name cannot be empty!");
        }

        System.out.print("Enter Roll Number: ");
        rollNo = sc.nextLine();
        if (rollNo == null || rollNo.trim().isEmpty()) {
            throw new MissingDataException("Roll number cannot be empty!");
        }

        System.out.println("Enter marks for 3 subjects (0 - 100):");

        for (int i = 0; i < 3; i++) {
            try {
                System.out.print("Subject " + (i + 1) + ": ");
                marks[i] = sc.nextInt();

                if (marks[i] < 0 || marks[i] > 100) {
                    throw new InvalidMarksException("Marks must be between 0 and 100!");
                }

            } catch (InputMismatchException e) {
                sc.nextLine(); // clear invalid input
                throw new InvalidMarksException("Marks must be a valid integer!");
            }
        }
    }

    public void calculateResult() {
        int total = 0;
        for (int m : marks) {
            total += m;
        }

        percentage = total / 3.0;

        if (percentage >= 40) {
            result = "PASS";
        } else {
            result = "FAIL";
        }
    }

    public void displayDetails() {
        System.out.println("\n--------- Student Result ---------");
        System.out.println("Name       : " + name);
        System.out.println("Roll No    : " + rollNo);
        System.out.println("Marks      : " + marks[0] + ", " + marks[1] + ", " + marks[2]);
        System.out.println("Percentage : " + percentage);
        System.out.println("Result     : " + result);
        System.out.println("----------------------------------\n");
    }
}

//  Main Application 

public class StudentResultManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Student student = new Student();

        try {
            student.inputDetails(sc);
            student.calculateResult();
            student.displayDetails();

        } catch (MissingDataException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (InvalidMarksException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        } finally {
            System.out.println("Program execution completed. Closing resources...");
            sc.close();
        }
    }
}
