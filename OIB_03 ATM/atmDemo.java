/**
 * atmDemo
 */
// importing packages
import java.io.*;
import java.util.HashMap;


class atmDemo {
    public static void main(String[] args)throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        
        // welcome message
        System.out.println("Welcome to XYZ Bank");
        
        // accepting user credentials
        String userName;int passWord;
        System.out.print("Please enter userName: ");
        userName= br.readLine();
        System.out.print("\nPlease enter passWord: ");
        passWord=Integer.parseInt(br.readLine());
        System.out.println();

        // authentication of user
        validation isValidUser=new validation();
        if(isValidUser.checkCredentials(userName,passWord)){
            int choise; Boolean loop=true;
            userDetails user=new userDetails(userName,passWord);

            // action 
            while(loop){
                System.out.println("\nPlease enter your action:");
                System.out.println("1. Transaction History");
                System.out.println("2. Withdrawal");
                System.out.println("3. Deposit");
                System.out.println("4. Transfer");
                System.out.println("5. Quit");
                
                System.out.print("\nEnter: ");
                choise=Integer.parseInt(br.readLine());

                // options
                switch (choise) {
                    case 1:
                        user.transactionHistory();
                        break;
                    case 2:
                        user.Withdrawal(br);
                        break;
                    case 3:
                        user.deposit(br);
                        break;
                    case 4:
                        user.transfer(br);
                        break;
                    case 5:
                        loop=false;
                        System.out.println("Thank you for using our ATM System");
                        break;
                    default:
                        System.out.println("Invaild action");
                        break;
                }
            }

        }
    }
}

class validation{
    private HashMap<String, Integer> credentials= new HashMap<String, Integer>();
    
    //constructor
    public validation(){
        // creating users
        credentials.put("user1", 1234);
        credentials.put("user2", 5678);
        credentials.put("user3",9012);
    } 

    public boolean checkCredentials(String userName, int passWord){
        if(credentials.containsKey(userName)&&credentials.get(userName).equals(passWord)){
            System.out.println("Welcome...");
            return true;
        }
        else{
            System.out.println("Either username or password is incorrect");
            return false;
        }
    }
}

/**
 * userDetails
 */
class userDetails {
    private String userName;
    private int pin;
    private int transaction[];
    private int totalBalance;
    private int index;

    // constructor
    userDetails(String userName, int pin){
        // intialization
        this.userName = userName;
        this.pin = pin;
        transaction=new int[50];
        int intialBalance = 10000;
        transaction[0]=intialBalance;
        totalBalance=intialBalance;
        index=1;
    }

    // function

    // show the previous transaction details
    protected void transactionHistory(){
        System.out.println("Transaction History:");
        for (int i = 0; i < index; i++) {
            System.out.println(i+1+". "+transaction[i]);
        }
        System.out.println("Total Balance: "+totalBalance);
    }

    //to withdraw amount 
    protected void Withdrawal(BufferedReader br)throws IOException{
        System.out.print("Enter amount to be withdrawn: ");
        int amount=Integer.parseInt(br.readLine());
        if(balanceCheck()&&amount>totalBalance){
            System.out.println("Not enough funds to withdraw");
        }
        else{
            totalBalance=totalBalance-amount;
            transaction[index]=amount;
            index++;
            System.out.println("Amount to withdraw Successfully");
        }
    }

    // to deposit amount
    protected void deposit(BufferedReader br)throws IOException{
        System.out.print("Enter the amount to deposit: ");
        int amount= Integer.parseInt(br.readLine());

        totalBalance=totalBalance+amount;
        transaction[index]=amount;
        index++;
        System.out.println("Amount to deposit Successfully");

    }

    // transfer fund to other accounts
    protected void transfer(BufferedReader br)throws IOException{
        System.out.print("enter username of transfer account: ");
        String account=br.readLine();
        System.out.print("enter amount to be send: ");
        int amount=Integer.parseInt(br.readLine());

        if(balanceCheck()&&amount>totalBalance){
            System.out.println("Not enough funds to transfer");
        }
        else if(userName.equals(account)){
            System.out.println("Can't transfer money to same account");
        }
        else{
            totalBalance=totalBalance-amount;
            transaction[index]=amount;
            index++;
            System.out.println("Amount send Successfully to "+account);
        }
    }

    // to check balance of account
    private boolean balanceCheck(){
        if(totalBalance==0){
            return false;
        }
        else{
            return true;
        }
    }
}