/**
 * 
 */
package io.prospace.galaxymerchant.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import io.prospace.galaxymerchant.utils.RomanNumeral;
import io.prospace.galaxymerchant.utils.exception.InvalidNumericException;
import io.prospace.galaxymerchant.utils.exception.NullMaterialException;
import io.prospace.galaxymerchant.utils.exception.UnrecognizedStringException;

/**
 * @author Azzuarezh
 *
 */
public class IntergalacticNumeral {
	public static final String TEXT_GLOB = "glob";
	public static final String TEXT_PROK = "prok";
	public static final String TEXT_PISH = "pish";
	public static final String TEXT_TEGJ = "tegj";
	
	public static final char GLOB = RomanNumeral.CHAR_I;
	public static final char PROK = RomanNumeral.CHAR_V;
	public static final char PISH = RomanNumeral.CHAR_X;
	public static final char TEGJ = RomanNumeral.CHAR_L;
	
	public static final String TYPE_CONVERT ="convert";
	public static final String TYPE_CALCULATE ="calculate";
	
	
	
	//	need to define more for hundred, five hundred and thousand
	//	public static final String ANOTHER_HUNDRED_SYMBOL = RomanNumeral.TEXT_HUNDRED;
	//	public static final String ANOTHER_FIVE_HUNDERD_SYMBOL = RomanNumeral.TEXT_FIVE_HUNDRED;
	//	public static final String ANOTHER_THOUSAND_SYMBOL = RomanNumeral.TEXT_THOUSAND;
	
	//defines all the words need to be parsed
	public static String[] materialWords 			= {"Gold", "Silver","Iron" };
	public static String[] questionWords 			= {"how much is ", "how many credits is "};
	public static String[] intergalacticWords		= {"glob","prok","pish","tegj"};
	

	
	/**
	 * @param query
	 * @return
	 * @throws UnrecognizedStringException
	 * @throws InvalidNumericException
	 * @throws NullMaterialException 
	 */
	public static Map<String,Object> checkQuery(String query) throws UnrecognizedStringException, InvalidNumericException, NullMaterialException{
		//variable which will be a response to front end
		Map<String,Object> resultMap = new HashMap<>();
		String explanation="";
		Double result= (double) 0;
		String romanText="";
		String type="";
		String material="";
		int numericValue=0;
		
		String newQuery ="";
		/* for how much. this is not required the material word */
		if(query.toLowerCase().contains(questionWords[0])) {
			/* get the value words */
			System.out.println("the sentence contain " + questionWords[0]);
			newQuery = query.replaceAll(questionWords[0], "");
			System.out.println("remove the "+questionWords[0]+" word, now the sentence is :" + newQuery);
			String[] words = newQuery.split(" ");
			
			//make sure that the sentence does not contain any invalid words
			for (String word : words) {
				System.out.println("current word :" + word);
				System.out.println("-----------------------");
				boolean isGalacticWord= false;
				for (String galacticWord : intergalacticWords) {
					System.out.println("comparing with " + galacticWord);
					if((word.equalsIgnoreCase(galacticWord) || word.equalsIgnoreCase("?"))) {
						isGalacticWord = true;
						System.out.println(word +" are equals with " + galacticWord);
					}
				}
				System.out.println("is galacticWord :" + isGalacticWord);
				if(!isGalacticWord) {
					throw new UnrecognizedStringException("I have no Idea what you are talking about");
				}
				System.out.println("-----------------------");
			}
			/* this only convert intergalactic to roman and get the value */
			result = Calculate(newQuery.replace("?", ""));
			explanation = Explain(newQuery.replace("?", ""));
			romanText = ConvertIntergatalacticToRoman(newQuery.replace("?", ""));
			type = TYPE_CONVERT;
			numericValue = RomanNumeral.convertRomanTextToInt(romanText);
			
		}
		/* for how many. this is required the material word and we will calculate the value*/
		else if(query.contains(questionWords[1])){
			/* get the value words */
			System.out.println("the sentence contain " + questionWords[1]);
			newQuery = query.replaceAll(questionWords[1], "");
			String[] words = newQuery.split(" ");
			System.out.println("remove the "+questionWords[1]+" word, now the sentence is :" + newQuery);
			//make sure that the sentence does not contain any invalid words
			for (String word : words) {
				System.out.println("current word :" + word);
				System.out.println("-----------------------");
				boolean isGalacticWord= false;
				boolean isMaterialWord=false;
				for (String galacticWord : intergalacticWords) {
					System.out.println("comparing with " + galacticWord);
					if((word.equalsIgnoreCase(galacticWord) || word.equalsIgnoreCase("?"))) {
						System.out.println(word +" are equals with " + galacticWord);
						isGalacticWord = true;
					}
				}
				System.out.println("is galacticWord :" + isGalacticWord);
				
				if(!isGalacticWord) {
					/* check if it is a material words */
					for (String materialWord : materialWords) {
						System.out.println("comparing with " + materialWord);
						if((word.equalsIgnoreCase(materialWord))) {
							isMaterialWord = true;
						}
						
					}
					System.out.println("is materialWord :" + isMaterialWord);
					if(!isMaterialWord) throw new UnrecognizedStringException("I have no Idea what you are talking about");
					
				}
				
				System.out.println("-----------------------");
			}
			 String materialName = words[words.length -2];  //get last word before question mark			
			 result =Calculate(newQuery,materialName);
			 romanText = ConvertIntergatalacticToRoman(newQuery.replace("?", ""));
			 
			 explanation = Explain(newQuery, materialName);
			 type = TYPE_CALCULATE;
			 material = materialName;
			 numericValue = RomanNumeral.convertRomanTextToInt(romanText);
			
		}
		//we don't know the pattern so we will assume that the input is not recognized
		else {
			throw new UnrecognizedStringException("I have no Idea what you are talking about");
		}
		resultMap.put("value", result);
		resultMap.put("explanation", explanation);
		resultMap.put("romanValue", romanText);
		resultMap.put("type", type);
		resultMap.put("numericValue", numericValue);		
		if(!material.isEmpty()) {
			Map<String,Object> materialMap = new HashMap<>();
			
			materialMap.put("silver", 17.0);
			materialMap.put("gold", 14450.0);
			materialMap.put("iron", 195.5);
			
			resultMap.put("material", material);
			resultMap.put("price", materialMap.get(material));
		};
		return resultMap;
	}

	
	/**
	 * @param romanQty
	 * @param material
	 * @return
	 * @throws InvalidNumericException
	 * @throws NullMaterialException 
	 */
	public static Double Calculate(String romanQty, String material) throws InvalidNumericException, NullMaterialException {
		System.out.println("romanQty :" + romanQty +", material:" + material);
		
		Map<String,Object> materialMap = new HashMap<>();
		
		materialMap.put("silver", 17.0);
		materialMap.put("gold", 14450.0);
		materialMap.put("iron", 195.5);
		
		double qty = new Double(RomanNumeral.convertRomanTextToInt(IntergalacticNumeral.ConvertIntergatalacticToRoman(romanQty)));
		
		Double price = (Double) materialMap.get(material.toLowerCase());
		if(price == null) throw new NullMaterialException("Material not found!, use \" How much is \" instead");	
		return qty * price;
	}
	
	/**
	 * @param romanQty
	 * @return
	 * @throws InvalidNumericException
	 */
	public static double Calculate(String romanQty) throws InvalidNumericException {
		System.out.println("romanQty :" + romanQty );
		return new Double(RomanNumeral.convertRomanTextToInt(IntergalacticNumeral.ConvertIntergatalacticToRoman(romanQty)));
	}
	
	
	/**
	 * @param romanQty
	 * @param material
	 * @return
	 * @throws InvalidNumericException
	 * @throws NullMaterialException 
	 */
	public static String Explain(String romanQty, String material) throws InvalidNumericException, NullMaterialException {
		System.out.println("romanQty :" + romanQty );
		
		Map<String,Object> materialMap = new HashMap<>();
		
		materialMap.put("silver", 17.0);
		materialMap.put("gold", 14450.0);
		materialMap.put("iron", 195.5);
		StringBuilder sb = new StringBuilder();
		sb.append(RomanNumeral.explanation(IntergalacticNumeral.ConvertIntergatalacticToRoman(romanQty)));
		sb.append(" * "+ materialMap.get(material));
		sb.append(" = " + Calculate(romanQty, material));
		return sb.toString();
	}
	
	/**
	 * @param romanQty
	 * @return
	 * @throws InvalidNumericException
	 */
	public static String Explain(String romanQty) throws InvalidNumericException {
		System.out.println("romanQty :" + romanQty );
		return RomanNumeral.explanation(IntergalacticNumeral.ConvertIntergatalacticToRoman(romanQty));
	}
	
	
	/** Convert Intergalactic text to Romanian Text
	 * @param intergalacticString the text to be converted to Romanian text
	 * @return Romanian text format
	 */
	public static String ConvertIntergatalacticToRoman(String intergalacticString) {

		/* we will parse the text based on whitespace character */
		String[] interGalacticSymbols = intergalacticString.split(" ");
		
		StringBuilder RomanSymbols = new StringBuilder();
		for (String interGalacticsymbol : interGalacticSymbols) {
			RomanSymbols.append(getRomanSymbolFromIntergalactic(interGalacticsymbol));
		}
		return RomanSymbols.toString();
	}
	
	
	/** Get Roman symbol one character based on Intergalactic symbol
	 * @param IntergalacticSymbol the symbol to be converted to Romanian symbol
	 * @return <code>String</code> the Roman value of Intergalactic character
	 */
	public static char getRomanSymbolFromIntergalactic(String IntergalacticSymbol) {
		char val = '\u0000';
		switch (IntergalacticSymbol) {
		case TEXT_GLOB:
			val = GLOB;
			break;
		case TEXT_PROK:
			val = PROK;
			break;
		case TEXT_PISH:
			val = PISH;
			break;
		case TEXT_TEGJ:
			val = TEGJ;
			break;		
		default:
			break;
		}
		return val;
	}
}
