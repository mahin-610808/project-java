package Bank;

import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;

import static java.lang.System.exit;

// ================= ACCOUNT CLASS =================
class Accunt {
    String phone;
    String pin;
    BigInteger balance;

    public Accunt(String phone, String hashedPin) {
        this.phone = phone;
        this.pin = hashedPin;
        this.balance = BigInteger.ZERO;
    }

    public boolean checkPin(String inputPin) {
        String hashed = hashPin(inputPin);
        return pin.equals(hashed);
    }

    public static String hashPin(String pin) {
        try {
            String salted = "QC_SALT_" + pin.toLowerCase(); // if same pin but different hash
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(salted.getBytes());

            StringBuilder hex = new StringBuilder();
            for (byte b : hash) {
                hex.append(String.format("%02x", b));
            }
            return hex.toString();

        } catch (Exception e) {
            return "";
        }
    }
}

// ================= QUICKCASH SYSTEM =================
class QuickCash {

    ArrayList<Accunt> accounts = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    // ================= MAIN MENU =================
    public void mainMenu() {
        while (true) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. SignUp");
            System.out.println("2. SignIn");
            System.out.println("3. Exit");
            System.out.println("Enter a number:");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> signUp();
                case 2 -> signIn();
                case 3 -> exit(0);
                default -> System.out.println("Invalid!");
            }
        }
    }
    //gp
    public void GP(Accunt user) {
        System.out.print("Enter GP number: ");
        String phone = sc.next();

        if (phone.startsWith("017") && phone.length() == 11) {
            System.out.print("Amount: ");
            BigInteger amount = sc.nextBigInteger();

            if (user.balance.compareTo(amount) >= 0) {
                user.balance = user.balance.subtract(amount);
                saveToFile();
                System.out.println("Recharge Successful!");
            } else {
                System.out.println("Insufficient Balance!");
            }
            return;
        }

        System.out.println("Invalid GP number!");
    }
    // robi
    public void Robi(Accunt user) {
        System.out.print("Enter Robi number: ");
        String phone = sc.next();

        if (phone.startsWith("018") && phone.length() == 11) {
            System.out.print("Amount: ");
            BigInteger amount = sc.nextBigInteger();

            if (user.balance.compareTo(amount) >= 0) {
                user.balance = user.balance.subtract(amount);
                saveToFile();
                System.out.println("Recharge Successful!");
            } else {
                System.out.println("Insufficient Balance!");
            }
            return;
        }

        System.out.println("Invalid Robi number!");
    }
    //airtel
    public void Airtel(Accunt user) {
        System.out.print("Enter Airtel number: ");
        String phone = sc.next();

        if (phone.startsWith("016") && phone.length() == 11) {
            System.out.print("Amount: ");
            BigInteger amount = sc.nextBigInteger();

            if (user.balance.compareTo(amount) >= 0) {
                user.balance = user.balance.subtract(amount);
                saveToFile();
                System.out.println("Recharge Successful!");
            } else {
                System.out.println("Insufficient Balance!");
            }
            return;
        }

        System.out.println("Invalid Airtel number!");
    }

    //teletalk
    public void Teletalk(Accunt user) {
        System.out.print("Enter Teletalk number: ");
        String phone = sc.next();

        if (phone.startsWith("015") && phone.length() == 11) {
            System.out.print("Amount: ");
            BigInteger amount = sc.nextBigInteger();

            if (user.balance.compareTo(amount) >= 0) {
                user.balance = user.balance.subtract(amount);
                saveToFile();
                System.out.println("Recharge Successful!");
            } else {
                System.out.println("Insufficient Balance!");
            }
            return;
        }

        System.out.println("Invalid Teletalk number!");
    }
    //banglalink

    public void Banglalink(Accunt user) {
        System.out.print("Enter Banglalink number: ");
        String phone = sc.next();

        if (phone.startsWith("019") && phone.length() == 11) {
            System.out.print("Amount: ");
            BigInteger amount = sc.nextBigInteger();

            if (user.balance.compareTo(amount) >= 0) {
                user.balance = user.balance.subtract(amount);
                saveToFile();
                System.out.println("Recharge Successful!");
            } else {
                System.out.println("Insufficient Balance!");
            }
            return;
        }

        System.out.println("Invalid Banglalink number!");
    }
    public void mobileRecharge(Accunt user) {

        System.out.println("\n=== Mobile Recharge ===");
        System.out.println("1. GP");
        System.out.println("2. Robi");
        System.out.println("3. Airtel");
        System.out.println("4. Teletalk");
        System.out.println("5. Banglalink");

        int choice = sc.nextInt();

        switch (choice) {
            case 1 -> GP(user);
            case 2 -> Robi(user);
            case 3 -> Airtel(user);
            case 4 -> Teletalk(user);
            case 5 -> Banglalink(user);
            default -> System.out.println("Invalid!");
        }
    }

    // ================= SIGN UP =================
    public void signUp() {
        System.out.print("Enter Phone: ");
        String phone = sc.next();

        // validation
        if (phone.length() != 11 || !phone.startsWith("01")) {
            System.out.println("Invalid phone number!");
            return;
        }

        for (Accunt acc : accounts) {
            if (acc.phone.equals(phone)) {
                System.out.println("Already exists!");
                return;
            }
        }

        System.out.print("Enter 5-digit PIN: ");
        String pin = sc.next();

        if (pin.length() != 5) {
            System.out.println("PIN must be 5 digits!");
            return;
        }

        String hashed = Accunt.hashPin(pin);
        Accunt user = new Accunt(phone, hashed);
        user.balance = new BigInteger("100");

        accounts.add(user);
        saveToFile();

        System.out.println("Account Created!");
    }

    // ================= SIGN IN =================
    public void signIn() {
        System.out.print("Phone: ");
        String phone = sc.next();

        for (Accunt acc : accounts) {
            if (acc.phone.equals(phone)) {

                int chance = 3;
                while (chance-- > 0) {
                    System.out.print("PIN: ");
                    String pin = sc.next();

                    if (acc.checkPin(pin)) {
                        System.out.println("Login Success!");
                        userMenu(acc);
                        return;
                    } else {
                        System.out.println("Wrong PIN!");
                    }
                }
                return;
            }
        }

        System.out.println("Account not found!");
    }

    // ================= SEND MONEY =================
    public void sendMoney(Accunt user) {
        System.out.print("Receiver Phone: ");
        String phone = sc.next();

        if (user.phone.equals(phone)) {
            System.out.println("Cannot send to yourself!");
            return;
        }

        for (Accunt acc : accounts) {
            if (acc.phone.equals(phone)) {

                System.out.print("Amount: ");
                BigInteger amount = sc.nextBigInteger();

                if (amount.compareTo(BigInteger.ZERO) <= 0) {
                    System.out.println("Invalid amount!");
                    return;
                }

                if (user.balance.compareTo(amount) >= 0) {
                    user.balance = user.balance.subtract(amount);
                    acc.balance = acc.balance.add(amount);

                    saveToFile();
                    System.out.println("Money Sent!");
                } else {
                    System.out.println("Insufficient Balance!");
                }
                return;
            }
        }

        System.out.println("User not found!");
    }
    // Cash in
    public void cashIn(Accunt user) {
        System.out.print("Enter Your Account Number: ");
        String bankAcc = sc.next();

        System.out.print("Enter Password: ");
        String pass = sc.next();

        System.out.print("Enter 6-digit OTP: ");
        String otp = sc.next();

        if (otp.length() != 6) {
            System.out.println("Invalid OTP!");
            return;
        }

        System.out.print("Enter Amount: ");
        BigInteger amount = sc.nextBigInteger();

        if (amount.compareTo(BigInteger.ZERO) <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        user.balance = user.balance.add(amount);
        saveToFile();

        System.out.println("Cash In Successful! New Balance: " + user.balance + " TK");
    }

    // Cash out
    public void cashOut(Accunt user) {
        System.out.print("Enter Your Number: ");
        String agent = sc.next();

        System.out.print("Enter Amount: ");
        BigInteger amount = sc.nextBigInteger();

        if (amount.compareTo(BigInteger.ZERO) <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        if (user.balance.compareTo(amount) >= 0) {
            BigInteger charge = amount.divide(BigInteger.valueOf(100));
            user.balance = user.balance.subtract(amount.add(charge));
            // user.balance = user.balance.subtract(amount);
            saveToFile();

            System.out.println("Cash Out Successful!");
            System.out.println("Agent: " + agent);
            System.out.println("Fee: " + charge + " TK");
            System.out.println("Amount: " + amount + " TK");

        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    // ================= CHECK BALANCE =================
    public void checkBalance(Accunt user) {
        System.out.println("Balance: " + user.balance + " TK");
    }

    //payment
    public void payment(Accunt user) {
        System.out.print("Enter Merchant Account: ");
        String merchant = sc.next();

        System.out.print("Enter Amount: ");
        BigInteger amount = sc.nextBigInteger();

        if (amount.compareTo(BigInteger.ZERO) <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        if (user.balance.compareTo(amount) >= 0) {

            //  1% fee calculation
            BigInteger fee = amount.divide(BigInteger.valueOf(100));

            // total deduct = amount + fee
            BigInteger total = amount.add(fee);

            if (user.balance.compareTo(total) < 0) {
                System.out.println("Insufficient Balance for fee!");
                return;
            }

            user.balance = user.balance.subtract(total);

            saveToFile();

            System.out.println("Payment Successful!");
            System.out.println("Merchant: " + merchant);
            System.out.println("Amount: " + amount + " TK");
            System.out.println("VAT: " + fee + " TK");
            System.out.println("Total Deducted: " + total + " TK");

        } else {
            System.out.println("Insufficient Balance!");
        }
    }

    // ================= USER MENU =================
    public void userMenu(Accunt user) {
        while (true) {
            System.out.println("\n=== User Menu ===");
            System.out.println("1. Send Money");
            System.out.println("2. Cash in");
            System.out.println("3. Cash out");
            System.out.println("4. Check Balance");
            System.out.println("5. Mobile Recharge");
            System.out.println("6. Payment");
            System.out.println("7. Logout");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> sendMoney(user);
                case 2 ->cashIn(user);
                case 3 ->cashOut(user);
                case 4 -> checkBalance(user);
                case 5 -> mobileRecharge(user);
                case 6 -> payment(user);
                case 7 -> {
                    return;
                }
                default -> System.out.println("Invalid!");
            }
        }
    }

    // ================= SAVE FILE =================
    public void saveToFile() {
        try (FileWriter fw = new FileWriter("data.txt")) {
            for (Accunt acc : accounts) {
                fw.write(acc.phone + "," + acc.pin + "," + acc.balance + "\n");
            }
        } catch (Exception e) {
            System.out.println("Save Error!");
        }
    }

    // ================= LOAD FILE =================
    public void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                Accunt acc = new Accunt(parts[0], parts[1]);
                acc.balance = new BigInteger(parts[2]);

                accounts.add(acc);
            }

        } catch (Exception e) {
            System.out.println("No previous data found.");
        }
    }
}


public class Banking {
    public static void main(String[] args) {
        QuickCash qc = new QuickCash();
        qc.loadFromFile();
        qc.mainMenu();
    }
}
