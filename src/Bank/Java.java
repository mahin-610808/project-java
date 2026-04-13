package Bank;


interface Payment {
    void pay(double amount);
}

interface Refundable {
    void refund(double amount);
}
class Accounts {
    String accountHolder;
    double balance;

    void deposit(double amount) {

        balance += amount;
        System.out.println("deposit done :"+ balance);

    }
}
class CreditCard extends Accounts implements Payment,Refundable{
    double Creditlimit=5000;


    public void pay(double amount){
        if(amount<=balance+Creditlimit) {
            balance -= amount;
            System.out.println(" Credit Payment success!!");
        }else{
            System.out.println("Limit cross");
        }
    }
    public void refund(double amount){

        balance += amount;
        System.out.println("refund balance done!!");
    }

}

class DebitCard extends Accounts implements Payment{

    public  void pay(double amount){

        if(balance > amount) {
            balance -= amount;
            System.out.println("Debit payment success,,");
        }else {
            System.out.println("Insufficient balance");
        }
    }
}
class Bkash extends  Accounts implements Payment,Refundable {


    public void pay(double amount) {

        double fee = (amount * 0.015);
        double total = amount + fee;
        if (balance >= total) {
            balance -= total;
            System.out.println("Bkash Payment Success! Fee: " + fee);
        } else {
            System.out.println("Not enough balance!");
        }



    }


    public void refund(double amount) {

        balance += amount;
        System.out.println("refund balance done!!" + balance);
    }
}



public class Java {
    public static void main(String[] args) {
        CreditCard cc=new CreditCard();
        cc.deposit(5000);
        cc.pay(1000);
        cc.refund(500);

        DebitCard dc = new DebitCard();
        dc.deposit(1000);
        dc.pay(500);

        Bkash bk = new Bkash();
        bk.deposit(1000);
        bk.pay(100);

    }

}

