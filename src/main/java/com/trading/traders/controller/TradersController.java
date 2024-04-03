package com.trading.traders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trading.traders.model.TradersModel;
import com.trading.traders.service.TradersService;

import java.util.List;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/trading/traders")
public class TradersController {
	
	@Autowired
	private TradersService traderservice;
	
	@PostMapping(path="/register")
	public ResponseEntity<TradersModel> createTrader(@Valid @RequestBody TradersModel trader) {
		return traderservice.createTraderService(trader);
	}
	
	@GetMapping(path="/all")
	public ResponseEntity<List<TradersModel>> getAllTraders(){
		return new ResponseEntity<>(traderservice.getAllTradersService(),HttpStatus.OK);
	}
	
	@GetMapping(path="/{email}")
	public ResponseEntity<List<TradersModel>> getAllTraders(@PathVariable("email") String email){
		return traderservice.getRecordByEmail(email);}
	
	@PutMapping(path="/update")
	public ResponseEntity<List<TradersModel>> updateTrader(@RequestBody TradersModel trader) {
		return traderservice.updatetraderservice(trader);
	}
	
	@PutMapping(path="/add")
	public ResponseEntity<List<TradersModel>> addMoney(@RequestBody TradersModel trader) {
		return traderservice.addmoneyservice(trader);
	}
	
	@DeleteMapping(path="/delete/{email}")
	public ResponseEntity<String> deleteByEmail(@PathVariable("email") String email){
		return traderservice.deleteByEmailService(email);
	}
}
	
	
	
	
	
	
	
	
	
	
	
	