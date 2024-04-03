package com.trading.traders.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TradersModelTest {
	
	@Test
	void testTradersModel()
	{
		TradersModel tradersModel=new TradersModel();
		tradersModel.setId(102);
		tradersModel.setName("Mahesh");
		tradersModel.setBalance(72435.6);
		tradersModel.setCreatedDate(Instant.parse("2022-10-15T11:19:42.12Z"));
		tradersModel.setUpdatedDate(Instant.parse("2022-10-16T05:10:22z"));
		tradersModel.setEmail("Mahesh@gmail.com");
		
		assertEquals(102,tradersModel.getId());
		assertEquals("Mahesh",tradersModel.getName());
		assertEquals(72435.6,tradersModel.getBalance());
		assertEquals(Instant.parse("2022-10-15T11:19:42.12Z"),tradersModel.getCreatedDate());
		assertEquals(Instant.parse("2022-10-16T05:10:22z"),tradersModel.getUpdatedDate());
		assertEquals("Mahesh@gmail.com",tradersModel.getEmail());
	}

}