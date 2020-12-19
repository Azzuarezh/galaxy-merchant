package io.prospace.galaxymerchant;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.prospace.galaxymerchant.controller.HomeController;

@SpringBootTest
class GalaxyMerchantApplicationTests {
	
	@Autowired
	private HomeController homeController;
	
	@Test
	void contextLoads() {
		assertThat(homeController).isNotNull();
	}

}
