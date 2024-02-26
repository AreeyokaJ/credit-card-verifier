import java.util.Scanner;

class CreditCardVerifierTest{
    public static void main(String[] args){
 
       try{
        //create creditCardVerifierObject, this object will ask user for credit card number 
        //and go through credit card verification project  
        CreditCardVerifier creditCardVerifier = new CreditCardVerifier(); 
       }

       //if user inputs an invalid card input, i.e. the input has other characters than digits or did not input exactly 16 digits 
       //method will throw InvalidCreditCardInputException, 
       catch(InvalidCreditCardInputException e){
            //print message explaining the reason for invalidCreditCardInputException
            System.out.println(e.getMessage());
       }
     
    }

    

  
}
