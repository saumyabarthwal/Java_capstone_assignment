import java.util.Scanner;

// Base Class
class Employee {
    protected int id;
    protected String name;
    protected double salary;

    // Method Overloading
    void setEmployeeDetails(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    void setEmployeeDetails(int id, String name) {
        this.id = id;
        this.name = name;
        this.salary = 0; // default salary
    }

    // Method to display details (will be overridden)
    void displayDetails() {
        System.out.println("Employee ID: " + id);
        System.out.println("Employee Name: " + name);
        System.out.println("Salary: " + salary);
    }

    // Method to calculate bonus (polymorphic)
    double calculateBonus() {
        return salary * 0.05; // generic employees get 5%
    }
}

// Derived Class: Manager
class Manager extends Employee {
    private double teamAllowance;

    Manager(int id, String name, double salary, double teamAllowance) {
        super.id = id;
        super.name = name;
        super.salary = salary;
        this.teamAllowance = teamAllowance;
    }

    // Overriding method
    @Override
    void displayDetails() {
        System.out.println("\n--- Manager Details ---");
        super.displayDetails();
        System.out.println("Team Allowance: " + teamAllowance);
    }

    // Overriding bonus calculation
    @Override
    double calculateBonus() {
        return salary * 0.10 + teamAllowance; // managers get 10% + team allowance
    }
}

// Derived Class: Developer
class Developer extends Employee {
    private String programmingLanguage;

    Developer(int id, String name, double salary, String programmingLanguage) {
        super.id = id;
        super.name = name;
        super.salary = salary;
        this.programmingLanguage = programmingLanguage;
    }

    // Overriding method
    @Override
    void displayDetails() {
        System.out.println("\n--- Developer Details ---");
        super.displayDetails();
        System.out.println("Programming Language: " + programmingLanguage);
    }

    // Overriding bonus calculation
    @Override
    double calculateBonus() {
        return salary * 0.08; // developers get 8%
    }
}

// Main Application Class
public class EmployeeManagementSystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Employee emp = null;

        while (true) {
            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Manager");
            System.out.println("2. Add Developer");
            System.out.println("3. Display Employee Details");
            System.out.println("4. Calculate Bonus");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    System.out.print("Enter Manager ID: ");
                    int mid = sc.nextInt(); sc.nextLine();

                    System.out.print("Enter Manager Name: ");
                    String mname = sc.nextLine();

                    System.out.print("Enter Salary: ");
                    double msalary = sc.nextDouble();

                    System.out.print("Enter Team Allowance: ");
                    double allowance = sc.nextDouble();

                    emp = new Manager(mid, mname, msalary, allowance);
                    System.out.println("Manager added successfully!");
                    break;

                case 2:
                    System.out.print("Enter Developer ID: ");
                    int did = sc.nextInt(); sc.nextLine();

                    System.out.print("Enter Developer Name: ");
                    String dname = sc.nextLine();

                    System.out.print("Enter Salary: ");
                    double dsalary = sc.nextDouble(); sc.nextLine();

                    System.out.print("Enter Programming Language: ");
                    String lang = sc.nextLine();

                    emp = new Developer(did, dname, dsalary, lang);
                    System.out.println("Developer added successfully!");
                    break;

                case 3:
                    if (emp == null) {
                        System.out.println("No employee added yet!");
                    } else {
                        emp.displayDetails();
                    }
                    break;

                case 4:
                    if (emp == null) {
                        System.out.println("No employee added yet!");
                    } else {
                        System.out.println("Bonus: " + emp.calculateBonus());
                    }
                    break;

                case 5:
                    System.out.println("Exiting... Thank you!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
