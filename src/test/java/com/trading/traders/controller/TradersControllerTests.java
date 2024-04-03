package com.trading.traders.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.trading.traders.model.TradersModel;
import com.trading.traders.service.TradersService;

@ExtendWith(MockitoExtension.class)
class TradersControllerTests {
	
	@Mock
	private TradersService tradersService;
	
	@InjectMocks
	private TradersController tradersController;

	private TradersModel trader, trader1;

	private List<TradersModel> tradersList;

	@BeforeEach
	void init() {
		trader = new TradersModel();
		trader1 = new TradersModel();
		trader.setId(102);
		trader.setName("Mahesh");
		trader.setBalance(72435.6);
		trader.setCreatedDate(Instant.parse("2022-10-15T11:19:42.12Z"));
		trader.setUpdatedDate(Instant.parse("2022-10-16T05:10:22z"));
		trader.setEmail("Mahesh@gmail.com");

		trader1.setId(980);
		trader1.setName("Suresh");
		trader1.setBalance(872435.6);
		trader1.setCreatedDate(Instant.parse("2022-10-14T11:19:42.12Z"));
		trader1.setUpdatedDate(Instant.parse("2022-10-15T05:10:22z"));
		trader1.setEmail("Suresh@gmail.com");

		tradersList = Stream.of(trader, trader1).toList();

	}

	@Test
	void testcreateTrader() 
	{
          when(tradersService.createTraderService(trader))
          .thenReturn(new ResponseEntity<>(trader,HttpStatus.CREATED));
          
          assertEquals(new ResponseEntity<>(trader,HttpStatus.CREATED),
        		  tradersController.createTrader(trader));
          
	}
	
	@Test
	void testgetAllTraders()
	{
		when(tradersService.getAllTradersService())
		.thenReturn(tradersList);
		
		assertEquals(new ResponseEntity<>(tradersList,HttpStatus.OK)
				,tradersController.getAllTraders());
		
	}
	
	@Test
	void testgetTradersByEmail()
	{
		when(tradersService.getRecordByEmail(trader.getEmail()))
		.thenReturn(new ResponseEntity<>(Stream.of(trader).toList(),HttpStatus.OK));
		
		assertEquals(new ResponseEntity<>(Stream.of(trader).toList(),HttpStatus.OK)
				,tradersController.getAllTraders(trader.getEmail()));
		
	}
	@Test
    void testupdateTrader()
    {
		when(tradersService.updatetraderservice(trader1))
		.thenReturn(new ResponseEntity<>(Stream.of(trader1).toList(),HttpStatus.OK));
		
		assertEquals(new ResponseEntity<>(Stream.of(trader1).toList(),HttpStatus.OK),
				tradersController.updateTrader(trader1));
		
    }
	@Test
	void testaddMoney()
	{
		when(tradersService.addmoneyservice(trader))
		.thenReturn(new ResponseEntity<>(Stream.of(trader).toList(),HttpStatus.OK));
		
		assertEquals(new ResponseEntity<>(Stream.of(trader).toList(),HttpStatus.OK),
				tradersController.addMoney(trader));
		
	}
	@Test
	void testdeleteByEmail()
	{
		when(tradersService.getRecordByEmail(trader1.getEmail()))
		.thenReturn(new ResponseEntity<>(Stream.of(trader1).toList(),HttpStatus.OK));
		
		assertEquals(new ResponseEntity<>(Stream.of(trader1).toList(),HttpStatus.OK)
				,tradersController.getAllTraders(trader1.getEmail()));
		
	}
	
}