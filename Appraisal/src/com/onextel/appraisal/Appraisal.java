package com.onextel.appraisal;
import java.time.LocalDate;

public class Appraisal {
    private String employeeName;
    private int ticketsClosed;
    private int ticketsRemaining;
    private int disciplinaryActions;
    private double selfReview;
    private double managerReview;
    private double coworkerReview;
    private double innovation;
    private double punctuality;
    private double currentSalaryLPA;
    private LocalDate joiningDate;

    // Getters and Setters
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getTicketsClosed() {
        return ticketsClosed;
    }

    public void setTicketsClosed(int ticketsClosed) {
        this.ticketsClosed = ticketsClosed;
    }

    public int getTicketsRemaining() {
        return ticketsRemaining;
    }

    public void setTicketsRemaining(int ticketsRemaining) {
        this.ticketsRemaining = ticketsRemaining;
    }

    public int getDisciplinaryActions() {
        return disciplinaryActions;
    }

    public void setDisciplinaryActions(int disciplinaryActions) {
        this.disciplinaryActions = disciplinaryActions;
    }

    public double getSelfReview() {
        return selfReview;
    }

    public void setSelfReview(double selfReview) {
        this.selfReview = selfReview;
    }

    public double getManagerReview() {
        return managerReview;
    }

    public void setManagerReview(double managerReview) {
        this.managerReview = managerReview;
    }

    public double getCoworkerReview() {
        return coworkerReview;
    }

    public void setCoworkerReview(double coworkerReview) {
        this.coworkerReview = coworkerReview;
    }

    public double getInnovation() {
        return innovation;
    }

    public void setInnovation(double innovation) {
        this.innovation = innovation;
    }

    public double getPunctuality() {
        return punctuality;
    }

    public void setPunctuality(double punctuality) {
        this.punctuality = punctuality;
    }

    public double getCurrentSalaryLPA() {
        return currentSalaryLPA;
    }

    public void setCurrentSalaryLPA(double currentSalaryLPA) {
        this.currentSalaryLPA = currentSalaryLPA;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }
}
