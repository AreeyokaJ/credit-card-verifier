import java.util.Scanner; 

public class CreditCardVerifier {
    //fields will be private as it is sensitive information

    //will hold credit card number in String format  
    private String creditCardNumber; 

    /*this will keep track of the sum of even index products of credit card number 
      ex... credit card number: 326392   sumOfEvenIndexProducts = (3*2) + (1 + 2) + (1 + 8)*/
    private int sumOfEvenIndexProducts; 
   
   /*this will keep track of the sum of odd index numbers 
     ex... credit card number: 326392 sumOfOddIndexNumbers = 2 + 6 + 3 + 2  */
    private int sumOfOddIndexNumbers; 

    /* This number will be the number that will be the sum of EvenIndexProducts and sumOfOddIndexNumbers 
        it will be used to determine whether the credit card is valid or not 
     */
    private int validationNumber;


    //default constructor will begin the creditCardVerificationProcess
    CreditCardVerifier() throws InvalidCreditCardInputException{
        creditCardVerification();
    }

    /*
        method will calculate validation number, use validation number to verify validity of credit card, 
        and display results and card type of valid credit cards
     */
    public void creditCardVerification() throws InvalidCreditCardInputException{
        //Method will promptUser for creditCardNumber to be verified
        promptUser();

        //Method will calculate validation number which will be used to determine validity of credit card 
        calculateValidationNumber();

        //Method will display the validity of credit card, along with recognized credit network  of valid credit cards
        displayResults();
    }

    //will prompt user for credit card number that needs to be verified, will throw invalidCreditCardInputException if input does not meet requirements
    public  void promptUser() throws InvalidCreditCardInputException{
        //Create Scanner object to receive user input
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter the credit card number that needs to be verified: "); 

        String userInput = input.nextLine();
        
        //if the user did not input sixteen digits then method will throw InvalidCreditCardInputException
        if((userInput.length() != 16)){
            throw new InvalidCreditCardInputException("Credit card number must have 16 digits. ");
        }
        else if(!isDigitsOnly(userInput)){
            throw new InvalidCreditCardInputException("Credit card number must only contain digits. ");
        }
        
        creditCardNumber = userInput;
    }

      /*will check if string only contains digits returns boolean value of true/false*/
      public static boolean isDigitsOnly(String string){
        for(int i = 0; i < string.length(); i++){
            if (!Character.isDigit(string.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /*This method will calculate the sumOfEvenIndexProducts and the sumOfOddIndexNumbers
        will take in a Scanner object as an argument in order to obtain the creditCardNumber
     */
    public void calculateValidationNumber(){


        //i keeps tracks of every even digit 
        //j keeps track of every other digit
        for(int i = 0, j = 1; i < creditCardNumber.length() || j < creditCardNumber.length(); i += 2, j += 2){
          
            //calculate sumOfEvenIndexProducts 
            
            //stores current digits 
            int currentEvenIndexedDigit = Integer.parseInt(Character.toString(creditCardNumber.charAt(i))); 
            int currentOddIndexedDigit = Integer.parseInt(Character.toString(creditCardNumber.charAt(j)));

            //will store the operation on currentEvenIndexedDigit
            int operation = 2 * currentEvenIndexedDigit;
            
            //method will add the sum of the digits of operation to sumOfEvenIndexProducts 
            operationSum(operation);

            sumOfOddIndexNumbers += currentOddIndexedDigit;
        }

        //Finish calculation of validationNumber
        validationNumber = sumOfOddIndexNumbers + sumOfEvenIndexProducts;
        
    }

    

    /*This method is a helper method for the calculateNewValidationNumber 
            It will be used to help add the digits of each evenIndexedDigit 
            operation to the sumOfEvenIndexedProducts,

            It will take in the current operation as a parameter
    */
    public void operationSum(int operation){
            //if operation is a one digit number we can just add it to the sumOfEvenIndexProducts directly  
            if(operation < 10){
                sumOfEvenIndexProducts += operation; 
            }
            //else we must parse the numbers and add those digits to sumOfEvenIndexProducts
            else{
                String operationString = Integer.toString(operation);
                int firstDigit = Integer.parseInt(Character.toString(operationString.charAt(0)));
                int secondDigit = Integer.parseInt(Character.toString(operationString.charAt(1)));
                int sum = firstDigit + secondDigit;
                sumOfEvenIndexProducts += sum;
            }    
    }


    /* This method returns a string that contains the credit card networking name, ie. Visa, Mastercard, American Express
        it will only be used if the credit card number is valid 
    */
    public String creditCardNetwork(){
        String firstTwoDigits = creditCardNumber.substring(0, 2); 
        String firstDigit = creditCardNumber.substring(0,1); 


        //Check if credit card is Visa 
        if (firstDigit.equals("4")){
            return "VISA";
        }
        //check if credit card is AmericanExpress
        else if (firstTwoDigits.equals("34") || firstTwoDigits.equals("37")){
            return "AMERICAN EXPRESS";
        }

        else if (firstTwoDigits.equals("51") ||
            firstTwoDigits.equals("52") ||         
            firstTwoDigits.equals("53") ||
            firstTwoDigits.equals("54") ||
            firstTwoDigits.equals("55")
         ){
            return "MASTERCARD";
        }
        else{
            return "UNRECOGNIZED NETWORK";
        }


    }

    /*This method verifies if the creditCardNumber is valid by checking if the 
        validation number (number obtained after calculations) is divisible by 10,
        it returns true or false value
     */
    public boolean isValidCreditCard(){
        
        if(validationNumber % 10 == 0){
            return true; 
        }
        else {
            return false;
        }
    }

    /*
        Will display the whether creditCardNumber is valid as long with it's type 
     */
    public void displayResults(){
        if(isValidCreditCard()){
            System.out.println("Credit Card#: " + creditCardNumber + " " + creditCardNetwork() + " is valid.");
        }

        else{
            System.out.println("Credit Card#: " + creditCardNumber + " is not valid.");
        }
    }

}
