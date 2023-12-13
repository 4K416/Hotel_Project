//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//import java.util.*;
//
//
//public class CustomerFileManager{
//
//    private static final String Customer_FOLDER = "src/users/customer.txt";
//
//    public static void addCustomerByAdmin() {
//        Employee employee = getCustomerInfoFromAdmin();
//        System.out.println(employee.toString());
//        addCustomer(employee);
//    }
//
//    public static void updateCustomerByAdmin() {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter Customer ID to update: ");
//        String id = scanner.nextLine();
//        Employee newEmployee = getCustomerInfoFromAdmin();
//        updateCustomer(id, newEmployee);
//    }
//
//
//    public static void deleteCustomerByAdmin() {
//        Scanner scanner = new Scanner(System.in);
//        displayAllCustomer();
//
//        System.out.print("Enter Customer ID to delete: ");
//        String id = scanner.nextLine();
//        deleteCustomer(id);
//    }
//
//
//    private static void addCustomer(Employee employee) {
//        try (PrintWriter writer = new PrintWriter(new FileWriter(Customer_FOLDER, true))) {
//            writer.println(employee.toString());
//            System.out.println("Registration successful!");
//        } catch (IOException e) {
//            System.out.println("Error writing to user file.");
//            e.printStackTrace();
//        }
//    }
//
//    private static void updateCustomer(String id, Employee newEmployee) {
//        System.out.println(Customer_FOLDER);
//        File employeeFile = new File(Customer_FOLDER);
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(employeeFile))) {
//            List<String> lines = new ArrayList<>();
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                if (line.startsWith(id + ",")) {
//                    lines.add(newEmployee.toString());
//                } else {
//                    lines.add(line);
//                }
//            }
//
//            // Write the updated content back to the file
//            try (PrintWriter writer = new PrintWriter(new FileWriter(employeeFile))) {
//                for (String updatedLine : lines) {
//                    writer.println(updatedLine);
//                }
//            }
//
//            System.out.println("Customer updated successfully!");
//        } catch (IOException e) {
//            System.out.println("Error updating employee.");
//            e.printStackTrace();
//        }
//    }
//
//
//    private static void deleteCustomer(String id) {
//        File employeeFile = new File(Customer_FOLDER);
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(employeeFile))) {
//            List<String> lines = new ArrayList<>();
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                if (!line.startsWith(id + ",")) {
//                    lines.add(line);
//                }
//            }
//
//            // Write the updated content back to the file
//            try (PrintWriter writer = new PrintWriter(new FileWriter(employeeFile))) {
//                for (String updatedLine : lines) {
//                    writer.println(updatedLine);
//                }
//            }
//
//            System.out.println("Customer deleted successfully!");
//        } catch (IOException e) {
//            System.out.println("Error deleting employee.");
//            e.printStackTrace();
//        }
//    }
//
//
//    private static Employee getCustomerInfoFromAdmin() {
//        String email;
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.print("Enter Customer name: ");
//        String name = scanner.nextLine();
//
//        System.out.print("Enter Customer role: ");
//        String role = scanner.nextLine();
//
//        System.out.print("Enter Customer ID: ");
//        String id = scanner.nextLine();
//
//        System.out.print("Enter Customer Password: ");
//        String password = scanner.nextLine();
//
//        do {
//            System.out.print("Enter valid Email: ");
//            email = scanner.nextLine();
//        } while (email != null && !email.contains("@"));
//        Employee employee = new Employee(id, name, password, email, role);
//        return employee;
//    }
//
//    public static void displayAllCustomer() {
//        File employeesFile = new File(Customer_FOLDER);
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(employeesFile))) {
//            System.out.println("Customer List:");
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                Employee employee = createCustomerFromLine(line);
//                System.out.println("ID: " + employee.getId() + ", Name: " + employee.getName() + ", Role: " + employee.getRole());
//            }
//
//        } catch (IOException e) {
//            System.out.println("Error reading employee data from file: " + employeesFile.getName());
//            e.printStackTrace();
//        }
//    }
//
//    private static Employee createCustomerFromLine(String line) {
//        String[] parts = line.split(",");
//
//        if (parts.length == 5) {
//            String id = parts[0];
//            String name = parts[1];
//            String password = parts[2];
//            String email = parts[3];
//            String role = parts[4];
//
//            return new Employee(id, name, password, email, role);
//        } else {
//            return null; // Handle the error appropriately
//        }
//
//
//    }
//}
//}