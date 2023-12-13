import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;


public class Services {
    private static final String REPORTS_FOLDER = "src/reports";
    private static final String SERVICES_FILE = "src/services/services.txt";
    private static final String BILL = "src/BILL";
    private static final String REPORT_FILE_EXTENSION = ".txt";

    public void createReport() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the report content: ");
        String content = scanner.nextLine();

        String fileName = generateReportFileName();
        String filePath = REPORTS_FOLDER + "/" + fileName;

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            writer.println("Report Date: " + getCurrentDateTime());
            writer.println("Content: " + content);
            System.out.println("Report created successfully!");
        } catch (IOException e) {
            System.out.println("Error creating report.");
            e.printStackTrace();
        }
    }
    public String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
    public String generateReportFileName() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        return "Report_" + dateFormat.format(new Date()) + REPORT_FILE_EXTENSION;
    }
    public void displayReportContent(File reportFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(reportFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading report content: " + reportFile.getName());
            e.printStackTrace();
        }
    }
    public void viewAllReports() {
        File reportsFolder = new File(REPORTS_FOLDER);

        if (reportsFolder.exists() && reportsFolder.isDirectory()) {
            File[] reportFiles = reportsFolder.listFiles();

            if (reportFiles != null && reportFiles.length > 0) {
                System.out.println("\nAll Reports:");
                for (File reportFile : reportFiles) {
                    System.out.println("Report: " + reportFile.getName());
                    displayReportContent(reportFile);
                    System.out.println("------------------------------");
                }
            } else {
                System.out.println("No reports found.");
            }
        } else {
            System.out.println("Reports folder does not exist.");
        }
    }
   public void addService() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter service name: ");
        String name = scanner.nextLine();

        System.out.print("Enter service description: ");
        String description = scanner.nextLine();

        System.out.print("Enter service price: ");
        double price = scanner.nextDouble();

        try (PrintWriter writer = new PrintWriter(new FileWriter(SERVICES_FILE, true))) {
            writer.println(name + "," + description + "," + price);
            System.out.println("Service added successfully!");
        } catch (IOException e) {
            System.out.println("Error adding service.");
            e.printStackTrace();
        }
    }

    public void updateService() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of the service to update: ");
        String serviceNameToUpdate = scanner.nextLine();

        List<String> services = readServicesFromFile();
        List<String> updatedServices = new ArrayList<>();

        for (String service : services) {
            String[] parts = service.split(",");
            String serviceName = parts[0].trim();

            if (serviceName.equals(serviceNameToUpdate)) {
                System.out.print("Enter new service description: ");
                String newDescription = scanner.nextLine();

                System.out.print("Enter new service price: ");
                double newPrice = scanner.nextDouble();

                updatedServices.add(serviceName + "," + newDescription + "," + newPrice);
                System.out.println("Service updated successfully!");
            } else {
                updatedServices.add(service);
            }
        }

        writeServicesToFile(updatedServices);
    }

    public void deleteService() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of the service to delete: ");
        String serviceNameToDelete = scanner.nextLine();

        List<String> services = readServicesFromFile();
        List<String> updatedServices = new ArrayList<>();

        for (String service : services) {
            String[] parts = service.split(",");
            String serviceName = parts[0].trim();

            if (!serviceName.equals(serviceNameToDelete)) {
                updatedServices.add(service);
            } else {
                System.out.println("Service deleted successfully!");
            }
        }

        writeServicesToFile(updatedServices);
    }

    public void displayServices() {
        List<String> services = readServicesFromFile();

        if (!services.isEmpty()) {
            System.out.println("\nAll Services:");
            for (String service : services) {
                String[] parts = service.split(",");
                String name = parts[0].trim();
                String description = parts[1].trim();
                double price = Double.parseDouble(parts[2].trim());

                System.out.println("Service Name: " + name);
                System.out.println("Description: " + description);
                System.out.println("Price: $" + price);
                System.out.println("------------------------------");
            }
        } else {
            System.out.println("No services found.");
        }
    }

    public List<String> readServicesFromFile() {
        List<String> services = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(SERVICES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                services.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading services data from file: " + SERVICES_FILE);
            e.printStackTrace();
        }

        return services;
    }

    public void writeServicesToFile(List<String> services) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(SERVICES_FILE))) {
            for (String service : services) {
                writer.println(service);
            }
        } catch (IOException e) {
            System.out.println("Error writing to services file.");
            e.printStackTrace();
        }
    }


    public void viewServicesAndSelect() {
        List<String> services = readServicesFromFile();

        if (!services.isEmpty()) {
            System.out.println("Available Services:");
            displayServices();

            Scanner scanner = new Scanner(System.in);
            System.out.print("\nEnter the name of the service you want to use: ");
            String selectedService = scanner.nextLine();
            System.out.println(selectedService + services.toString());
            if (services.contains(selectedService)) {
                System.out.println("You have selected the service: " + selectedService);

//                Customer customer = new Customer();
//                customer.addService(selectedService);
//
//                double totalBill = customer.calculateBill();

                System.out.println("Service added to your account. Your total bill is: $" /*+ totalBill*/);
            } else {
                System.out.println("Invalid service selection. Please try again.");
            }
        } else {
            System.out.println("No services available.");
        }
    }
    public static void updateCustomerBill(int customerId, double totalBill) {
        String billFilePath = BILL + customerId + "_bill.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(billFilePath))) {
            writer.println(totalBill);
        } catch (IOException e) {
            System.out.println("Error updating customer bill file: " + e.getMessage());
            e.printStackTrace();
        }
    }
public static double getServicePrice(String serviceName, List<String> availableServices) {
    for (String service : availableServices) {
        String[] parts = service.split(",");
        if (parts.length == 3) {
            String name = parts[0].trim();
            double price = Double.parseDouble(parts[2].trim());

            if (name.equalsIgnoreCase(serviceName)) {
                return price;
            }
        }
    }
    return 0.0; // Service not found or has no cost
}
}
