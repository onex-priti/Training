package com.onextel.appraisal;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;

public class AppraisalService {

    public static double calculateAppraisal(Appraisal a) {
        double total = 0.0;

        double avgReview = (a.getSelfReview() + a.getManagerReview() + a.getCoworkerReview()) / 3.0;

        if (avgReview > 8.0) total += 10;
        if (a.getInnovation() > 8.0) total += 5;
        if (a.getPunctuality() > 9.0) total += 5;
        if (a.getTicketsClosed() > a.getTicketsRemaining()) total += 5;
        if (a.getDisciplinaryActions() > 0) total -= 10;

        int experience = Period.between(a.getJoiningDate(), LocalDate.now()).getYears();
        if (experience > 3) total += 5;

        return total;
    }

    public static String generateReport(Appraisal a, double newSalary, double percent) {
        return "\n========================= 📄 Employee Appraisal Report =========================\n"
                + String.format("Employee Name       : %s\n", a.getEmployeeName())
                + String.format("Tickets Closed      : %d\n", a.getTicketsClosed())
                + String.format("Tickets Remaining   : %d\n", a.getTicketsRemaining())
                + String.format("Disciplinary Actions: %d\n", a.getDisciplinaryActions())
                + String.format("Self Review         : %.1f\n", a.getSelfReview())
                + String.format("Manager Review      : %.1f\n", a.getManagerReview())
                + String.format("Coworker Review     : %.1f\n", a.getCoworkerReview())
                + String.format("Innovation Score    : %.1f\n", a.getInnovation())
                + String.format("Punctuality Score   : %.1f\n", a.getPunctuality())
                + String.format("Joining Date        : %s\n", a.getJoiningDate())
                + String.format("Current Salary (LPA): ₹%.2f\n", a.getCurrentSalaryLPA())
                + "--------------------------------------------------------------------------------\n"
                + String.format("Appraisal Percent   : %.2f%%\n", percent)
                + String.format("New Salary (LPA)    : ₹%.2f\n", newSalary)
                + "================================================================================\n";
    }

    public static void saveReport(String filename, String content) {
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write(content);
            System.out.println("✅ Report saved as: " + filename);
        } catch (IOException e) {
            System.err.println("❌ Error saving report: " + e.getMessage());
        }
    }

    public static void viewReport(String employeeName) {
        String filename = "AppraisalReport_" + employeeName.replaceAll("\\s+", "_") + ".txt";
        File file = new File(filename);
        if (file.exists()) {
            System.out.println("\n📄 Appraisal Report:\n");
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.err.println("❌ Error reading file: " + e.getMessage());
            }
        } else {
            System.out.println("⚠️ Report not found for " + employeeName);
        }
    }

    public static void listAllAppraisals() {
        File dir = new File(".");
        File[] reports = dir.listFiles((d, name) -> name.startsWith("AppraisalReport_") && name.endsWith(".txt"));

        System.out.println("\n📂 Existing Appraisal Reports:");
        if (reports != null && reports.length > 0) {
            for (File file : reports) {
                String name = file.getName().replace("AppraisalReport_", "").replace(".txt", "").replaceAll("_", " ");
                System.out.println(" - " + name);
            }
        } else {
            System.out.println("No reports found.");
        }
    }
}

