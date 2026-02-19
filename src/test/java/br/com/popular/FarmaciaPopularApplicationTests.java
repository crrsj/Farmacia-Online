package br.com.popular;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@OpenAPIDefinition(
		info = @Info(
				title = "API - Farmácia Online.",
				version = "1.0",
				description = " Sistema voltado para venda de remédios online.",
				contact = @Contact()
		)
)	
class FarmaciaPopularApplicationTests {

	@Test
	void contextLoads() {
	}

}
