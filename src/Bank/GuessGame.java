package Bank;

import java.util.Scanner;



public class GuessGame {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int computernumber = (int) (Math.random() * 100); // computer ekhane (1-100) porjonto jekono ekti random number select kore dibe
        int usernumber = 0;
        do {
            System.out.println("Guess the number :");
            usernumber=sc.nextInt();// ekhane usere j number input dibe sheta bujhaice
            if(usernumber == computernumber){
                System.out.println("Yeah ... this is a correct number!!!");
            }
            else if(usernumber > computernumber){
                System.out.println("Its too large");
            }
            else{
                System.out.println("Its too short");
            }
        }while(usernumber > 0);
        System.out.println(" And \nThis is not valid number");
    }
}