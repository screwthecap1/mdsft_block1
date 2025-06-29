import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;

public class BankAccount {
    private String ownerName;
    private int balanceAcc;
    private LocalDateTime openDate;
    private boolean gonnaBlocked;
    private String number;

    public BankAccount(String ownerName) {
        this.ownerName = ownerName;
        this.balanceAcc = 0;
        this.number = generateAccNum();
        this.openDate = LocalDateTime.now();
        this.gonnaBlocked = false;
    }

    private String generateAccNum() {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(rnd.nextInt(10));
        }
        return sb.toString();
    }

    public boolean deposit(int amount) {
        if (gonnaBlocked || amount <= 0) return false;
        balanceAcc += amount;
        return true;
    }

    public boolean withdraw(int amount) {
        if (gonnaBlocked || amount <= 0 || amount > balanceAcc) return false;
        balanceAcc -= amount;
        return true;
    }

    public boolean transfer(BankAccount otherAcc, int amount) {
        if (otherAcc == null || otherAcc.gonnaBlocked || gonnaBlocked || amount <= 0 || amount > balanceAcc)
            return false;
        this.balanceAcc -= amount;
        otherAcc.balanceAcc += amount;
        return true;
    }

    // Adding Getters for testing results
    public String getOwnerName() {
        return ownerName;
    }

    public int getBalanceAcc() {
        return balanceAcc;
    }

    public String getNumber() {
        return number;
    }

    public String getOpenDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return openDate.format(formatter);
    }

    public boolean isGonnaBlocked() {
        return gonnaBlocked;
    }

    // Setter for Blocking Account out of class
    public void setGonnaBlocked(boolean gonnaBlocked) {
        this.gonnaBlocked = gonnaBlocked;
    }

    public String toString() {
        return "[YourPersonalBankAccount]" +
                "\nAccount number: " + number +
                "\nOwner: " + ownerName +
                "\nBalance: " + balanceAcc +
                "\nDate of Register: " + getOpenDate() +
                "\nBlocking status: " + gonnaBlocked;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BankAccount)) return false;
        BankAccount oth = (BankAccount) obj;
        return Objects.equals(this.number, oth.number);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(number);
    }

    public static void main(String[] args) {
        BankAccount ba1 = new BankAccount("Klimas");
        BankAccount ba2 = new BankAccount("Penek");

        System.out.println(ba1 + "\n-----");
        System.out.println(ba2);

        System.out.println("\nHashCode ba1: " + ba1.hashCode());
        System.out.println("HashCode ba2: " + ba2.hashCode());

        System.out.println("Are the numbers equal? - " + ba1.equals(ba2));
        if(ba1.equals(ba2)) {
            System.out.println("Are the HashCodes equal? - " + (ba1.hashCode() == ba2.hashCode()));
        } else {
            System.out.println("Numbers are different, also as hashcodes - it's okay");
        }

        System.out.println("Is number equal yourself? - " + ba1.equals(ba1));
    }
}