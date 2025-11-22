import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Contact {
    private String name;
    private String phone;
    private String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return name + "," + phone + "," + email;
    }

    public static Contact fromString(String data) {
        String[] temp = data.split(",");
        if (temp.length == 3) {
            return new Contact(temp[0], temp[1], temp[2]);
        }
        return null;
    }
}

public class ContactListApplication {

    private static final String FILE_NAME = "contacts.txt";
    private static HashMap<String, Contact> contactList = new HashMap<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n===== Contact List Application =====");
            System.out.println("1. Add Contact");
            System.out.println("2. View All Contacts");
            System.out.println("3. Search Contact");
            System.out.println("4. Delete Contact");
            System.out.println("5. Save Contacts to File");
            System.out.println("6. Load Contacts from File");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input! Enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addContact(sc);
                    break;

                case 2:
                    viewContacts();
                    break;

                case 3:
                    searchContact(sc);
                    break;

                case 4:
                    deleteContact(sc);
                    break;

                case 5:
                    saveToFile();
                    break;

                case 6:
                    loadFromFile();
                    break;

                case 7:
                    System.out.println("Exiting Application...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    //  Add Contact 
    private static void addContact(Scanner sc) {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Phone Number: ");
        String phone = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        Contact contact = new Contact(name, phone, email);
        contactList.put(name.toLowerCase(), contact);

        System.out.println("Contact added successfully!");
    }

    // View Contacts 
    private static void viewContacts() {
        if (contactList.isEmpty()) {
            System.out.println("No contacts available.");
            return;
        }

        System.out.println("\n----- Contact List -----");
        for (Map.Entry<String, Contact> entry : contactList.entrySet()) {
            Contact c = entry.getValue();
            System.out.println("Name: " + c.getName() +
                    " | Phone: " + c.getPhone() +
                    " | Email: " + c.getEmail());
        }
    }

    // Search Contact 
    private static void searchContact(Scanner sc) {
        System.out.print("Enter name to search: ");
        String name = sc.nextLine().toLowerCase();

        if (contactList.containsKey(name)) {
            Contact c = contactList.get(name);
            System.out.println("Contact Found:");
            System.out.println("Name: " + c.getName());
            System.out.println("Phone: " + c.getPhone());
            System.out.println("Email: " + c.getEmail());
        } else {
            System.out.println("Contact not found.");
        }
    }

    // Delete Contact 
    private static void deleteContact(Scanner sc) {
        System.out.print("Enter name to delete: ");
        String name = sc.nextLine().toLowerCase();

        if (contactList.remove(name) != null) {
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    //  Save Contacts to File 
    private static void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Contact c : contactList.values()) {
                writer.write(c.toString());
                writer.newLine();
            }
            System.out.println("Contacts saved to file successfully.");
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    //  Load Contacts from File
    private static void loadFromFile() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("File does not exist. No data to load.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            contactList.clear();
            String line;

            while ((line = reader.readLine()) != null) {
                Contact c = Contact.fromString(line);
                if (c != null) {
                    contactList.put(c.getName().toLowerCase(), c);
                }
            }
            System.out.println("Contacts loaded successfully.");

        } catch (IOException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }
    }
}
