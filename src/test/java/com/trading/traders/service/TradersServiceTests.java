package com.trading.traders.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.trading.traders.model.TradersModel;
import com.trading.traders.repository.TradersRepo;

@ExtendWith(MockitoExtension.class)
public class TradersServiceTests {
	
	@Mock
	private TradersRepo tradersRepo;
	
	@InjectMocks
	private TradersService tradersService;
	
	private TradersModel trader,trader1;
	
	private List<TradersModel> tradersList;
	
	@BeforeEach
	void init()
	{
	    trader = new TradersModel();
	    trader1=new TradersModel();
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
		
		tradersList=Stream.of(trader,trader1).toList();
		
	}
	
	@Test
	void createTraderServiceTest()
	{
		
		
		when(tradersRepo.save(trader)).
		thenReturn(trader);
		
		assertThat(tradersService.createTraderService(trader)).
		isEqualTo(new ResponseEntity<>(trader,HttpStatus.CREATED));
		
	}
	
	@Test
	void testgetAllTradersService()
	{
		when(tradersRepo.findAll())
		.thenReturn(tradersList);
		
		when(tradersRepo.findAll(Sort.by(Sort.Direction.ASC, "id"))).
		thenReturn(tradersList.stream().sorted(Comparator.comparing(TradersModel::getId)).toList());
		
		assertThat(tradersService.getAllTradersService()).
		isEqualTo(tradersList.stream().sorted(Comparator.comparing(TradersModel::getId)).toList());
	}
	
	@Test
	void testgetRecordByMail()
	{
		when(tradersRepo.getRecordByEmail(trader1.getEmail())).
		thenReturn(Stream.of(trader1).toList());
		
		assertThat(tradersService.getRecordByEmail(trader1.getEmail()))
		.isEqualTo(new ResponseEntity<>(Stream.of(trader1).toList(),HttpStatus.OK));
	}
	
	@Test
	void testupdatetraderservice()
	{
		when(tradersRepo.checkUpdated(trader.getName(), trader.getEmail())).thenReturn(1);
		
		when(tradersRepo.getRecordByEmail(trader.getEmail())).
		thenReturn(Stream.of(trader).toList());
		
		assertThat(tradersService.updatetraderservice(trader))
		.isEqualTo(new ResponseEntity<>(Stream.of(trader).toList(),HttpStatus.OK));
	}
	
	@Test
	void testaddmoneyservice()
	{
		when(tradersRepo.addAmount(trader.getBalance(), trader.getEmail())).thenReturn(1);
		
		when(tradersRepo.getRecordByEmail(trader.getEmail())).
		thenReturn(Stream.of(trader).toList());
		
		assertThat(tradersService.addmoneyservice(trader))
		.isEqualTo(new ResponseEntity<>(Stream.of(trader).toList(),HttpStatus.OK));
		
	}
	@Test
	void testdeleteByEmailService()
	{
		when(tradersRepo.getRecordByEmail(trader1.getEmail())).
		thenReturn(Stream.of(trader1).toList());
		
		assertThat(tradersService.getRecordByEmail(trader1.getEmail()))
		.isEqualTo(new ResponseEntity<>(Stream.of(trader1).toList(),HttpStatus.OK));
	}	

}