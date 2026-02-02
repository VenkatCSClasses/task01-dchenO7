package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * @throws InsufficientFundsException if the balance will become negative after withdraw
     * @throws IllegalArgumentException if the withdraw amount is negative
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount < 0){
            throw new IllegalArgumentException();
        }
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    /**
     * Checks if the amount is valid (non-negative and has two decimal places or less)
     * @return true if valid, false otherwise
     */
    public static boolean isAmountValid(double amount){
        if (amount < 0) {
            return false;
        }
    }

    public static boolean isEmailValid(String email){
        if (email == null || email.isEmpty()) {
            return false;
        }
        if (email.indexOf('@') != email.lastIndexOf('@')) {
            return false; // more than one @
        }
        String[] parts = email.split("@");
        if (parts.length != 2) {
            return false;
        }
        String local = parts[0];
        String domain = parts[1];
        if (local.isEmpty() || domain.isEmpty()) {
            return false;
        }
        // Check local part: starts with letter or number, can have -, but - not at start/end, and after - must be letter/number
        if (!Character.isLetterOrDigit(local.charAt(0))) {
            return false;
        }
        for (int i = 0; i < local.length(); i++) {
            char c = local.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '-' && c != '.') {
                return false;
            }
            if (c == '-' && (i == 0 || i == local.length() - 1 || !Character.isLetterOrDigit(local.charAt(i + 1)))) {
                return false;
            }
        }
        // Domain: must have ., domain name not empty, extension >=2
        int dotIndex = domain.indexOf('.');
        if (dotIndex == -1 || dotIndex == 0 || dotIndex == domain.length() - 1) {
            return false;
        }
        String extension = domain.substring(dotIndex + 1);
        if (extension.length() < 2) {
            return false;
        }
        // Domain name before . must not be empty and valid chars
        String domainName = domain.substring(0, dotIndex);
        if (domainName.isEmpty()) {
            return false;
        }
        for (char c : domainName.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '-') {
                return false;
            }
        }
        return true;
    }
}