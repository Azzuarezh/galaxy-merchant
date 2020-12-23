/**
 * Main package of the application
 */
package io.prospace.galaxymerchant;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Azzuarezh
 *
 * 2020
 */


@SpringBootApplication
public class GalaxyMerchantApplication {

	/** running web application base
	 * @param args <code>String</code> optional, by default if this arguments is null/empty, the app will switch to <code>cli</code> mode
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		SpringApplication.run(GalaxyMerchantApplication.class, args);		
	}
	


}
