
package io.prospace.galaxymerchant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Scanner;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import io.prospace.galaxymerchant.utils.IntergalacticNumeral;
import io.prospace.galaxymerchant.utils.exception.InvalidNumericException;
import io.prospace.galaxymerchant.utils.exception.NullMaterialException;
import io.prospace.galaxymerchant.utils.exception.UnrecognizedStringException;

/**
 * @author Azzuarezh
 *
 * 2020

 */

public class GalaxyMerchantCli {

	public static final String CALCULATE = "calculate";
	public static final String CONVERT = "convert";
	
	/** Main method of cli screen
	 * @param args <code>String</code> Optional, It's okay if null
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.out.println(banner());
		boolean retry = true;
		
		while(retry) {
			System.out.println("Enter your input :");

		    Scanner scanner = new Scanner(System.in);
			String input =scanner.nextLine();  // Read user input
			String newInput ="";
			String type ="";
			try {
				Map<String, Object> data = IntergalacticNumeral.checkQuery(input);
				//remove how much is/ how many credits is
				if(input.toLowerCase().contains(IntergalacticNumeral.questionWords[0])) {
					newInput = input.replaceAll(IntergalacticNumeral.questionWords[0], "");
					type=CONVERT;
				}else if(input.toLowerCase().contains(IntergalacticNumeral.questionWords[1])) {
					newInput = input.replaceAll(IntergalacticNumeral.questionWords[1], "");
					type=CALCULATE;
				}
				//remove question mark
				newInput = newInput.replace("?", "");
				
				
				System.out.println(showResult(newInput, (Double)data.get("value"), type));
				retry = retryQuestion();
				
			} catch (UnrecognizedStringException | InvalidNumericException | NullMaterialException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				retry = retryQuestion();
			}
		}
		if(!retry) shutdownApp(0);
	    	
	}

	public static boolean retryQuestion() {
		System.out.print("Do you want to try again? [Y/n default:Y] :");
	    Scanner scanner = new Scanner(System.in);
		String answer =scanner.nextLine();
		if(answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase(""))
		return true;
		else {
		System.out.println("==================================================");
		System.out.println("Thanks for using this apps. ^_^");
		System.out.println("Bye!");
		return false;
		}
	}
	
	public static String showResult(String input, Double value, String type) {
		String result = "";
		if(type.equalsIgnoreCase(CONVERT)) {
			result = input + "is " + value + ".";
		}else if(type.equalsIgnoreCase(CALCULATE)) {
			result = input + "is " + value + " Credits.";
		}
		return result;
	}
	
	public static String banner() throws IOException {
		 StringBuilder sb = new StringBuilder();
		 String resourceDir = Paths.get("")
	                .toAbsolutePath()
	                .toString() + "/classes/";
		 
		  BufferedReader in = new BufferedReader(new
		  FileReader(resourceDir+"banner.txt")); String line; while((line
		  =in.readLine()) != null) { sb.append(line + "\n"); } in.close();
		 
		sb.append("\tInter galactic Converter. \n");
		sb.append("==================================================\n");
		sb.append("\tUnit Conversion = [glob = I, prok = V, pish = X, tegj = L, sjoice = C, crexs = D, bjork = M]\n");
		sb.append("\tMaterials = [Iron, Silver, Gold ]\n\n");
		sb.append("\tuse \"how much is\" or \"how many Credits is\" then followed by the intergalactic numbers ends with question mark (?)\n");
		sb.append("\texample : \t\"how much is bjork crexs ?\"\n");
		sb.append("\t\t\t\"how many Credits is tegj pish pish Silver ?\"\n");
		sb.append("==================================================\n\n");
		return sb.toString();
		
	}

	public static void shutdownApp(int returnCode) {
		System.exit(0);
	}
	
	
}
