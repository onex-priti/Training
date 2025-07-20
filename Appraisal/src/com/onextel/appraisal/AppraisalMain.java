package com.onextel.appraisal;
import java.time.LocalDate;
import java.util.Scanner;

public class AppraisalMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== 📊 Employee Appraisal System =====");
            System.out.println("1. Generate New Appraisal Report");
            System.out.println("2. View Existing Report");
            System.out.println("3. List All Appraisal Reports");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = -1;

            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    generateNewReport(sc);
                    break;
                case 2:
                    System.out.print("Enter employee name to view report: ");
                    String nameToView = sc.nextLine();
                    AppraisalService.viewReport(nameToView);
                    break;
                case 3:
                    AppraisalService.listAllAppraisals();
                    break;
                case 4:
                    System.out.println("👋 Exiting. Have a good day!");
                    sc.close();
                    return;
                default:
                    System.out.println("⚠️ Invalid choice. Please try again.");
            }
        }
    }

    private static void generateNewReport(Scanner sc) {
        Appraisal a = new Appraisal();

        System.out.println("\n=== 📝 Enter Employee Details ===");
        System.out.print("Name: ");
        a.setEmployeeName(sc.nextLine());

        System.out.print("Tickets Closed: ");
        a.setTicketsClosed(Integer.parseInt(sc.nextLine()));

        System.out.print("Tickets Remaining: ");
        a.setTicketsRemaining(Integer.parseInt(sc.nextLine()));

        System.out.print("Disciplinary Actions: ");
        a.setDisciplinaryActions(Integer.parseInt(sc.nextLine()));

        System.out.print("Self Review (0–10): ");
        a.setSelfReview(Double.parseDouble(sc.nextLine()));

        System.out.print("Manager Review (0–10): ");
        a.setManagerReview(Double.parseDouble(sc.nextLine()));

        System.out.print("Coworker Review (0–10): ");
        a.setCoworkerReview(Double.parseDouble(sc.nextLine()));

        System.out.print("Innovation Score (0–10): ");
        a.setInnovation(Double.parseDouble(sc.nextLine()));

        System.out.print("Punctuality Score (0–10): ");
        a.setPunctuality(Double.parseDouble(sc.nextLine()));

        System.out.print("Current Salary (LPA): ");
        a.setCurrentSalaryLPA(Double.parseDouble(sc.nextLine()));

        System.out.print("Joining Date (yyyy-mm-dd): ");
        a.setJoiningDate(LocalDate.parse(sc.nextLine()));

        double percent = AppraisalService.calculateAppraisal(a);
        double newSalary = a.getCurrentSalaryLPA() + (a.getCurrentSalaryLPA() * percent / 100);

        String report = AppraisalService.generateReport(a, newSalary, percent);
        String filename = "AppraisalReport_" + a.getEmployeeName().replaceAll("\\s+", "_") + ".txt";

        AppraisalService.saveReport(filename, report);
    }
}
