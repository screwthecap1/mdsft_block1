import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankAccount {
    private String ownerName;
    private int balanceAcc;
    private LocalDateTime openDate;
    private boolean gonnaBlocked;

    public BankAccount(String ownerName) {
        this.ownerName = ownerName;
        this.balanceAcc = 0;
        this.openDate = LocalDateTime.now();
        this.gonnaBlocked = false;
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
    public int getBalanceAcc() {
        return balanceAcc;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOpenDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return openDate.format(formatter);
    }

    public boolean isGonnaBlocked(boolean block) {
        return gonnaBlocked;
    }

    // Setter for Blocking Account out of class
    public void setGonnaBlocked(boolean gonnaBlocked) {
        this.gonnaBlocked = gonnaBlocked;
    }

    public static void main(String[] args) {
        BankAccount ba1 = new BankAccount("Klimas");
        BankAccount ba2 = new BankAccount("Penek");

        System.out.println(ba1.getOwnerName());
        System.out.println(ba1.getOpenDate());
        System.out.println(ba1.isGonnaBlocked(false));
        System.out.println("Klimas zakinul sebe 2 kosars: " + ba1.deposit(2000));
        System.out.println("Teper mozhno s kentamy v kalyanku, cause balance: " + ba1.getBalanceAcc()+ "\n");

        System.out.println("Klimas snyal kosar na pokupku tachki: " + ba1.withdraw(1000));
        System.out.println("Poetomu Klimas pereshel na vodu i khleb, balance till month ending: " + ba1.getBalanceAcc()+ "\n");

        System.out.println("Penek zanyal u Klimasa pyatihat na remont chetirki: " + ba1.transfer(ba2, 500));
        System.out.println("Penek balance: " + ba2.getBalanceAcc());
        System.out.println("Klimas balance: " + ba1.getBalanceAcc() + "\n");

        System.out.println("Penek poprosil eshe dokinut kosar: " + ba1.transfer(ba2, 1000));
        System.out.println("Penek balance: " + ba2.getBalanceAcc());
        System.out.println("Klimas balance: " + ba1.getBalanceAcc());
        System.out.println("Sorry brat sam na meli\n");

        ba2.setGonnaBlocked(true);
        System.out.println("*Scheta Penka has banned*");
        System.out.println("Teper vozmozhnost namilit chetirku: " + ba2.deposit(1000));
    }
}