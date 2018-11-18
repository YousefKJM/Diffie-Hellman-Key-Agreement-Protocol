import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


public class DiffieHellman {
	
	private static final BigInteger TWO = BigInteger.valueOf(2);	
	static Boolean isVpk = false;
	static BigInteger k1 = null;
	static BigInteger k2 = null;
	static BigInteger k1_C = null;
	static BigInteger k2_C = null;
	static BigInteger A = null;
	static BigInteger B = null;
	
	
	/*public static void main(String[] args) throws NumberFormatException, IOException {
		Scanner scan = new Scanner(System.in);
			System.out.println("Enter:	  - 1 to compute the shared key");
			System.out.println("	  - 2 to crack the shared key");
			System.out.println("	  - 3 any other key to exist");
			

			try{
			int i =scan.nextInt();
			
			if(i==1)
				runComputeSK();
			else if(i==2)
				runCrackSK();
			else if(i==3)
				System.exit(1);
			}
			catch (Exception e) 
			{
				System.out.println("wrong input");	
			     System.exit(1);
			}
			
		}*/

	//*****************************
public static void runComputeSK() throws NumberFormatException, IOException{
	BigInteger p = null;
	BigInteger a = null;
	BigInteger sk1 = null;
	BigInteger sk2 = null;
	Boolean validRun =true;

	System.out.println("You choose finding the Shared Key operation: "); 

		boolean isPrime =false;
		while(!isPrime && !DiffieHellmanUI.primeTextField.getText().isEmpty()){
			System.out.print("Enter a Prime 'p' Number: ");
			
			try{ /// check if the input is valid or not
				p = BigInteger.valueOf(Long.parseLong(DiffieHellmanUI.primeTextField.getText()));
				if(isPrime(p)) {
					isPrime = true;
				}
			
				else{
					DiffieHellmanUI.errMsgPrime(); // show error message
					validRun=false;
					DiffieHellmanUI.primeTextField.setText("");
					System.out.println("Invalid Number, this is not a PRIME Number .. Please Re-Enter a valid one.");
				}
			}catch (NumberFormatException ex) {
				DiffieHellmanUI.errMsg();
				DiffieHellmanUI.primeTextField.setText("");
				DiffieHellmanUI.PRtextField.setText("");
				validRun=false;
			}catch (NullPointerException ex) {
				DiffieHellmanUI.errMsg();
				DiffieHellmanUI.primeTextField.setText("");
				DiffieHellmanUI.PRtextField.setText("");
				validRun=false;
			}
				
		}
		
		boolean isPrimitive =false;
		while(!isPrimitive && !DiffieHellmanUI.PRtextField.getText().isEmpty() && validRun){
			System.out.print("Enter a Primitive root 'a' : ");
			try{
				a = BigInteger.valueOf(Long.parseLong(DiffieHellmanUI.PRtextField.getText()));
				if(isPrimitiveRoot(a,p))
					isPrimitive = true;
				else{
					DiffieHellmanUI.errMsgPrimitiveRoot();
					DiffieHellmanUI.PRtextField.setText("");
					System.out.println("Invalid Number, this is not a Primitive of 'p' .. Please Re-Enter a valid one.");
					validRun=false;
				}
			}catch (NumberFormatException ex) {
				DiffieHellmanUI.errMsg();
				DiffieHellmanUI.primeTextField.setText("");
				DiffieHellmanUI.PRtextField.setText("");
				validRun=false;
			}catch (NullPointerException ex) {
				DiffieHellmanUI.errMsg();
				DiffieHellmanUI.primeTextField.setText("");
				DiffieHellmanUI.PRtextField.setText("");
				validRun=false;
			}
		}
		
		
		
		if(validRun){
				System.out.println();
		      k1 = randomBigInteger(BigInteger.ONE, p);
		      k2 = randomBigInteger(BigInteger.ONE, p); 
		      
		       System.out.println("Private Key 'k1' is: " + k1);
		       System.out.println("Private Key 'k2' is: " + k2);
		       
		       A = a.modPow(k1, p); // a.pow(k1).mod(p)
		       B = a.modPow(k2, p); // a.pow(k2).mod(p)
		       
		       System.out.println("Public Key 'A' is: " + A);
		       System.out.println("Public Key 'B' is: " + B);
		       
		       isVpk = true;
		       
		       sk1 = B.modPow(k1, p); // B.pow(k1).mod(p)
		       sk2 = A.modPow(k2, p); // A.pow(k2).mod(p)
		       
		       
		       if (sk1.equals(sk2)) {
		      	 System.out.println("\nThe Shared Secret key is:  " + sk1);
		      	DiffieHellmanUI.sharedKeyTF.setText(""+sk1);
		       }
		       else 
		      	 System.out.println("Failed ...");
		}
       
}

public static void showPrivateKeys() {
		if(isVpk){
			DiffieHellmanUI.txtPrivateKeys1.setText("Private Key 'k1' is: " + k1);
			DiffieHellmanUI.txtPrivateKeys2.setText("Private Key 'k2' is: " + k2);
		}		
}

public static void showPublicKeys() {
	if(isVpk){
		DiffieHellmanUI.txtPublicKeys1.setText("Public Key 'A' is: " + A);
		DiffieHellmanUI.txtPublicKeys2.setText("Public Key 'B' is: " + B);
	}		
}
	//***************************************************************************************************************//
public static  void runCrackSK() throws NumberFormatException, IOException{
	BigInteger p = null;
	BigInteger a = null;
	BigInteger A = null;
	BigInteger B = null;
	BigInteger sk1 = null;
	BigInteger sk2 = null;
	Boolean validRun =true;

	

     System.out.println("-------------------------------------------------------------------------------------------------"); 

    System.out.println("\nYou choose cracking the Shared Key operation:"); 
    
    boolean isPrime = false;
 	while(!isPrime && !DiffieHellmanUI.txtCskprime.getText().isEmpty()){
		System.out.print("Enter a Prime 'p' Number: ");
		try{
			p = BigInteger.valueOf(Long.parseLong(DiffieHellmanUI.txtCskprime.getText()));
			if(isPrime(p)&& validRun) {
				isPrime = true;
			}	else{
				System.out.println("Invalid Number, this is not a PRIME Number .. Please Re-Enter a valid one.");
				DiffieHellmanUI.errMsgPrime();
				DiffieHellmanUI.txtCskprime.setText("");
				validRun=false;
			}
		}catch (NumberFormatException ex) {
			DiffieHellmanUI.errMsg();
			DiffieHellmanUI.txtCskprime.setText("");
			DiffieHellmanUI.tfCSK_PR.setText("");
			DiffieHellmanUI.textFieldA.setText("");
			DiffieHellmanUI.textFieldB.setText("");
			validRun=false;
		}catch (NullPointerException ex) {
			DiffieHellmanUI.errMsg();
			DiffieHellmanUI.txtCskprime.setText("");
			DiffieHellmanUI.tfCSK_PR.setText("");
			DiffieHellmanUI.textFieldA.setText("");
			DiffieHellmanUI.textFieldB.setText("");
			validRun=false;
		}
	
	}
 	
 	boolean isPrimitive = false;
 	while(!isPrimitive && !DiffieHellmanUI.tfCSK_PR.getText().isEmpty() && validRun ){
		System.out.print("Enter a Primitive root 'a': ");
		try{
		a = BigInteger.valueOf(Long.parseLong(DiffieHellmanUI.tfCSK_PR.getText()));
		if(isPrimitiveRoot(a,p))
			isPrimitive = true;
		else{
			System.out.println("Invalid Number, this is not a Primitive of 'p' .. Please Re-Enter a valid one.");
			validRun=false;	
			DiffieHellmanUI.errMsgPrimitiveRoot();
			DiffieHellmanUI.tfCSK_PR.setText("");
		}
		}catch (NumberFormatException ex) {
			DiffieHellmanUI.errMsg();
			DiffieHellmanUI.txtCskprime.setText("");
			DiffieHellmanUI.tfCSK_PR.setText("");
			DiffieHellmanUI.textFieldA.setText("");
			DiffieHellmanUI.textFieldB.setText("");
			validRun=false;
		}catch (NullPointerException ex) {
			DiffieHellmanUI.errMsg();
			DiffieHellmanUI.txtCskprime.setText("");
			DiffieHellmanUI.tfCSK_PR.setText("");
			DiffieHellmanUI.textFieldA.setText("");
			DiffieHellmanUI.textFieldB.setText("");
			validRun=false;
		}
	}
		if(validRun)
 		try {
		System.out.println();
		System.out.print("Enter Public Key 'A': ");
		 A = BigInteger.valueOf(Long.parseLong(DiffieHellmanUI.textFieldA.getText()));
		 
		 System.out.print("Enter Public Key 'B': ");
		 B = BigInteger.valueOf(Long.parseLong(DiffieHellmanUI.textFieldB.getText()));
 		}
		 catch (NumberFormatException ex) {
				DiffieHellmanUI.errMsg();
				DiffieHellmanUI.txtCskprime.setText("");
				DiffieHellmanUI.tfCSK_PR.setText("");
				DiffieHellmanUI.textFieldA.setText("");
				DiffieHellmanUI.textFieldB.setText("");
				validRun=false;
			}catch (NullPointerException ex) {
				DiffieHellmanUI.errMsg();
				DiffieHellmanUI.txtCskprime.setText("");
				DiffieHellmanUI.tfCSK_PR.setText("");
				DiffieHellmanUI.textFieldA.setText("");
				DiffieHellmanUI.textFieldB.setText("");
				validRun=false;
			}
 		
 		if(validRun){
 			try{
 			k1_C = findDiscreteLogarithm(a,A,p);
 			k2_C = findDiscreteLogarithm(a,B,p);
		
			 
		     System.out.println("Private Key 'k1' is: " + k1_C);
		     System.out.println("Private Key 'k2' is: " + k2_C);
		     
		     isVpk = true;
		     
		     sk1 = B.modPow(k1_C, p);
		     sk2 = A.modPow(k2_C, p);
		
		     
		
		     if (sk1.equals(sk2)) {
		    	 System.out.println("\nThe Shared Secret key (Cracked) is: " + sk1);
		    	 DiffieHellmanUI.sharedKeyTFC.setText(""+sk1);
		     }
		     else 
		    	 System.out.println("Failed ...");  	
 			}catch (ArithmeticException ex) {
				DiffieHellmanUI.errMsg();
				DiffieHellmanUI.txtCskprime.setText("");
				DiffieHellmanUI.tfCSK_PR.setText("");
				DiffieHellmanUI.textFieldA.setText("");
				DiffieHellmanUI.textFieldB.setText("");
				validRun=false;
			}
 	}
}

public static void showPrivateKeysC() {
	if(isVpk){
		DiffieHellmanUI.txtPrivateKeysC1.setText("Private Key 'k1' is: " + k1_C);
		DiffieHellmanUI.txtPrivateKeysC2.setText("Private Key 'k2' is: " + k2_C);
	}		
}
	//***********************************************************************************************************************//

/*This mehtod is for checking if the number is prime or not*/
	public static boolean isPrime(BigInteger number) {
	    //check via BigInteger.isProbablePrime(certainty)
	    if (!number.isProbablePrime(5))
	        return false;
	    if ( ( !TWO.equals(number) && (BigInteger.ZERO.equals(number.mod(TWO))) ) || BigInteger.ONE.equals(number))
	       return false; 
	  //start from 3, 5, etc. the odd number, and look for a divisor if any
	    for (BigInteger i = BigInteger.valueOf(3); i.multiply(i).compareTo(number) < 1; i = i.add(TWO))  
	        if (BigInteger.ZERO.equals(number.mod(i)) ) //check if 'i' is divisor of 'number'
	            return false;
	    return true;
	}
	
	
	//***************************************************************************************************************//
	/*This mehtod is for checking if the number is a primitive root of the prime or not*/
	public static boolean isPrimitiveRoot(BigInteger a, BigInteger p) {
		BigInteger n = p.subtract(BigInteger.ONE);
		LinkedList<BigInteger> factors = findAllPrimeFactors(n);
		// Run a^(n / "each factor) mod p
		// If this ^^ = 1 mod p then 'a' is not a primitive root
		Iterator<BigInteger> i = factors.iterator();
		while (i.hasNext()) {
			if ((a.modPow(n.divide(i.next()), p)).equals(BigInteger.ONE)) 
				return false;
		}
		return true;
	}
	
	//***************************************************************************************************************//


	public static LinkedList<BigInteger> findAllPrimeFactors(BigInteger n) {
        LinkedList<BigInteger> factors = new LinkedList<BigInteger>();
        for (BigInteger i = TWO; i.compareTo(n) <= 0; i = i.add(BigInteger.ONE)) {
            while (n.mod(i).equals(BigInteger.ZERO)) {
				factors.add(i);
				n = n.divide(i);
            }
        }
        return factors;
    }
	
	//***************************************************************************************************************//

	public static BigInteger randomBigInteger(BigInteger min, BigInteger max) {
		BigInteger n;
		do {
			n = randomBigInteger(min.bitLength(), max.bitLength());
		} while (n.compareTo(min) <= 0 || n.compareTo(max) >= 0);
		return n;
	}
	
	public static BigInteger randomBigInteger(int minBits, int maxBits) {
		// Choose a random length
        SecureRandom rand = new SecureRandom();
		int bits = rand.nextInt(maxBits - minBits + 1) + minBits;
		BigInteger n = new BigInteger(bits, rand);
		// Make sure we didn't get a random bigint outside range
		while (n.bitLength() <= minBits && n.bitLength() >= maxBits) {
			n = new BigInteger(bits, rand);
		}
		return n;
	}
	
	//***************************************************************************************************************//
	
	// This is the method for the bonus (Crack the Shared Key)
    public static  BigInteger findDiscreteLogarithm(BigInteger a, BigInteger b, BigInteger p) {
        BigInteger s =  sqrt(p).add(BigInteger.ONE);
        BigInteger b_1 = b.modPow(p.subtract(TWO), p);   
        HashMap<BigInteger, BigInteger> set = new HashMap<BigInteger, BigInteger>();
        // Generating the set
        set.clear();
        for (BigInteger i = BigInteger.ZERO; i.compareTo(s) < 1; i = i.add(BigInteger.ONE)) {
            BigInteger first = (a.modPow(i, p).multiply(b_1)).mod(p);
            if (!set.containsKey(first))
                set.put(first, i);
        }
        
        for (BigInteger i = BigInteger.ZERO; i.compareTo(s) < 1; i = i.add(BigInteger.ONE)) {
			BigInteger el = a.modPow((i.multiply(s)).mod(p), p); 
            el = el.modPow(p.subtract(TWO), p); 

            if (set.containsKey(el))
                return i.multiply(s).add(set.get(el));
        }
        // the method will return a '-1' if the discrete logarithm of a given input cannot be found
		BigInteger muins1=(BigInteger.ZERO).subtract(BigInteger.ONE);
        return muins1;
    }
    
	//***************************************************************************************************************//
	
	public static BigInteger sqrt(BigInteger x) {
	    BigInteger div = BigInteger.ZERO.setBit(x.bitLength()/2);
	    BigInteger div2 = div;
	    // Loop until we hit the same value twice in a row, or wind
	    // up alternating.
	   while(true) { //for(;;) {-------} or same as while (true) {-------}
	        BigInteger y = div.add(x.divide(div)).shiftRight(1);
	        if (y.equals(div) || y.equals(div2))
	            return y;
	        div2 = div;
	        div = y;
	    }
	}

}
