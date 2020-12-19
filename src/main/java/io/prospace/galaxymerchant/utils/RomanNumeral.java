package io.prospace.galaxymerchant.utils;
/**
 * @author Azzuarezh
 *
 */
import java.util.ArrayList;
import java.util.List;

import io.prospace.galaxymerchant.utils.exception.InvalidNumericException;

/**
 * @author diasp
 *
 * 2020

 */
/**
 * @author diasp
 *
 * 2020

 */
public class RomanNumeral {
	public static final int ONE 			= 1;
	public static final int FIVE 			= 5;
	public static final int TEN 			= 10;
	public static final int FIFTY 			= 50;
	public static final int HUNDRED 		= 1_00;
	public static final int FIVE_HUNDRED 	= 5_00;
	public static final int THOUSAND 		= 1_000;
	
	public static final char CHAR_I 			= 'I';
	public static final char CHAR_V 			= 'V';
	public static final char CHAR_X 			= 'X';
	public static final char CHAR_L 			= 'L';
	public static final char CHAR_C 			= 'C';
	public static final char CHAR_D 			= 'D';
	public static final char CHAR_M 			= 'M';
	
	
	/** Get Numeral value one character based on Roman character
	 * @param RomanSymbol <code>String</code> Roman character defines above (I,V,X,L,C,D)
	 * @return <code>Integer</code> the value of Roman character
	 */
	public static int getNumeralValue(char romanSymbol) {
		int val = 0;
		switch (romanSymbol) {
		case CHAR_I:
			val = ONE;
			break;
		case CHAR_V:
			val = FIVE;
			break;
		case CHAR_X:
			val = TEN;
			break;
		case CHAR_L:
			val = FIFTY;
			break;
		case CHAR_C:
			val = HUNDRED;
			break;
		case CHAR_D:
			val = FIVE_HUNDRED;
			break;
		case CHAR_M:
			val = THOUSAND;
			break;
		default:
			break;
		}
		return val;
	}
	
	/** Check whether the Roman symbol is valid (the sequence should match the rules)
	 * @param romanSymbol
	 * @return
	 */
	public static boolean isValidRomanNumeric(String romanText) {
		
		boolean isValid = true;
		char[] romanChar = romanText.toCharArray();
		
		int repeated 		= 0; 						//initialize this for knowing how many times the symbols called, it must not over 3 times
		char repeatedChar 	= '\u0000'; 				// to save last in-memory what the repeated char is
		char previousChar 	= '\u0000'; 
		for (int i = 0; i < romanChar.length; i++) {    
			// lets' make validation for the Roman symbols
			char currentSymbol = romanChar[i];
			
			//we only set values needed on the index 0 as it won't have previous symbol
			if(i == 0) {
				repeated = 1;
				repeatedChar = currentSymbol;
				previousChar = currentSymbol; // set for next loop
			}else {
				
				/* check validation for V symbol */
				if( currentSymbol == CHAR_V) {

					/* check whether I has only repeated 1 times 
					 * because IIV is not valid numeric*/
					if(previousChar == CHAR_I) {
						
						if(repeatedChar == CHAR_I && repeated > 1) {							
							isValid = false;
							break;
						}
					}
					
					/* V can never be repeated so VV is invalid, use X instead */
					if(currentSymbol == repeatedChar || previousChar == currentSymbol) { 
						isValid = false;
						break;
					}
				}
				/* check validation for X symbol */
				else if( currentSymbol == CHAR_X) {
					
					/* check whether I has only repeated 1 times 
					 * because IIX is not valid numeric*/
					if(previousChar == CHAR_I) {
						
						if(repeatedChar == previousChar && repeated > 1) {							
							isValid = false;
							break;
						}						
					}
					
					/* V can never be substracted by X (VX is invalid), why you subtracted 10 by 5? If you can only write 5 instead? */
					if(previousChar == CHAR_V) { 
						isValid = false;
						break;
					}
					
					/* check if X only repeated 3 times */
					if(currentSymbol == repeatedChar && repeated == 3) {
						isValid = false;
						break;
					}
				}
				/* check validation for L symbol */
				else if( currentSymbol == CHAR_L) {
					/* I can never being followed by L (IL/VL is invalid) */
					if(previousChar == CHAR_I || previousChar == CHAR_V) {
						isValid = false;
						break;
					}
					else if(previousChar == CHAR_X) {
						/* only allowed 1 times for X (XL is valid = 40 but XXL is invalid, use XXX instead) */
						if(repeatedChar == previousChar && repeated > 1) {
							isValid = false;
							break;
						}
					}
					
					/* L can never be repeated so LL is invalid, use C instead */
					if(currentSymbol == repeatedChar || previousChar == currentSymbol) { 
						isValid = false;
						break;
					}
				}
				/* check validation for C symbol */
				else if( currentSymbol == CHAR_C) {
					/* I can never being followed by C or V (IC/VC is invalid)  */
					if(previousChar == CHAR_I || previousChar == CHAR_V) {
						isValid = false;
						break;
					}
					else if(previousChar == CHAR_X) {
						/* only allowed 1 times for X (XC is valid = 90 but XXC is invalid, use LXXX instead) */
						if(repeatedChar == previousChar && repeated > 1) {
							isValid = false;
							break;
						}
					}
					/* L can never being followed by C (LC is invalid) */
					else if(previousChar == CHAR_L) {
						isValid = false;
						break;
					}
					
					/* check if C only repeated 3 times CCC invalid, use CD instead*/
					if(currentSymbol == repeatedChar && repeated == 3) {
						isValid = false;
						break;
					}
				}
				/* check validation for D symbol */
				else if( currentSymbol == CHAR_D) {
					/* I,X and L can never being followed by D (ID/VD/XD/LD/DD is invalid) */
					if(previousChar == CHAR_I || previousChar == CHAR_V || previousChar == CHAR_X || previousChar == CHAR_L || previousChar == CHAR_D) {
						isValid = false;
						break;
					}
					else if(previousChar == CHAR_C) {
						/* check whether C has only repeated 1 times 
						 * because CCD is not valid numeric*/
						if(repeatedChar == previousChar && repeated > 1) {
							isValid = false;
							break;
						}
					}
					
					/* check if C only repeated 3 times CCC invalid, use CD instead*/
					if(currentSymbol == repeatedChar && repeated == 3) {
						isValid = false;
						break;
					}					
					
				}
				/* check validation for D symbol */
				else if( currentSymbol == CHAR_M) {
					/* I,X, L and D can never being followed by M (IM/VM/XM/LM/DM is invalid) only C can valid */
					if(previousChar == CHAR_I || previousChar == CHAR_V || previousChar == CHAR_X || previousChar == CHAR_L || previousChar == CHAR_D) {
						isValid = false;
						break;
					}
					/* check if M only repeated 3 times MMM invalid*/
					if(currentSymbol == repeatedChar && repeated == 3) {
						isValid = false;
						break;
					}
				}
				else if(currentSymbol == CHAR_I) {
					if(currentSymbol == repeatedChar && repeated == 3) {
						isValid = false;
						break;
					}
				}
				
				/*
				 * after all validation pass, we will set what we did in this loop to proceed
				 * next loop
				 */
								
				if(currentSymbol == previousChar) {
					repeatedChar = currentSymbol;
					repeated++;
				}
				else {
					repeatedChar = '\u0000';
					repeated= 0;
				}
				previousChar = currentSymbol;
			}
		}
		
		
		return isValid;
	}
	
//	/** Convert Roman text into Numeric value. this will be the function to calculate later
//	 * @param romanText 
//	 * @return
//	 */
	public static int convertRomanTextToInt(String romanText) throws InvalidNumericException{
		int val = 0;
		int nextVal = 0;
		
		if(!(isValidRomanNumeric(romanText))) {
			throw new InvalidNumericException("Romanial Number not valid!");  
		}
		
		List<Integer> listVal = new ArrayList<>();
		/* loop through the text to parse Romanian Symbol to integer value */
		for (char romanChar : romanText.toCharArray()) {
			val = getNumeralValue(romanChar);
			listVal.add(val);
		}
		
		/*
		 * create new list with checking function whether next value is greater than current value
		 * this to determine whether it would be negated/not. for example "IV", it s not 1 + 5 but 5-1 or (-1) + 5
		 */
		List<Integer> resultList = new ArrayList<Integer>();
		
		for (int i = 0; i < listVal.size(); i++) {
			
			int nextIndex = (i + 1 <= listVal.size() -1) ? i+1 :0;
			val = listVal.get(i);
			
			if(nextIndex > 0)nextVal = listVal.get(nextIndex);
			
			if(val < nextVal) {
				resultList.add(val * -1); 
			}else {
				resultList.add(val);
			}
		}
		
		Integer sum = resultList.stream()
				  .mapToInt(Integer::intValue)
				  .sum();
		return sum;
	}
	
	
	
	/**Explain how conversion works 
	 * @param romanText
	 * @return String explanation on How to calculate the conversion
	 * @throws InvalidNumericException
	 */
	public static String explanation(String romanText) throws InvalidNumericException{
		int val = 0;
		int nextVal = 0;
		StringBuilder sb = new StringBuilder();
		if(!(isValidRomanNumeric(romanText))) {
			throw new InvalidNumericException("Romanial Number not valid!");  
		}
		
		List<Integer> listVal = new ArrayList<>();
		/* loop through the text to parse Romanian Symbol to integer value */
		for (char romanChar : romanText.toCharArray()) {
			val = getNumeralValue(romanChar);
			listVal.add(val);
		}
		
		/*
		 * create new list with checking function whether next value is greater than current value
		 * this to determine whether it would be negated/not. for example "IV", it s not 1 + 5 but 5-1 or (-1) + 5
		 */
		List<Integer> resultList = new ArrayList<Integer>();
		
		for (int i = 0; i < listVal.size(); i++) {
			
			int nextIndex = (i + 1 <= listVal.size() -1) ? i+1 :0;
			val = listVal.get(i);
			
			if(nextIndex > 0)nextVal = listVal.get(nextIndex);
			
			if(val < nextVal) {
				resultList.add(val * -1); 
			}else {
				resultList.add(val);
			}
		}
		
		int cnt = 0;
		for (Integer integer : resultList) {
			if(cnt > 0) {
				sb.append("+");
			}
			sb.append("(" +integer + ")");
			cnt = 1;
		}
		
		
		Integer sum = resultList.stream()
				  .mapToInt(Integer::intValue)
				  .sum();
		sb.append(" = " + sum.toString());
		return sb.toString();
	}
}