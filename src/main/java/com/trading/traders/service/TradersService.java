package com.trading.traders.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.trading.traders.exception.CustomException;
import com.trading.traders.exception.TradeAlreadyExist;
import com.trading.traders.model.TradersModel;
import com.trading.traders.repository.TradersRepo;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TradersService {
	
	@Autowired
	private TradersRepo tradersRepo;
	
	public ResponseEntity<TradersModel> createTraderService(TradersModel trader) throws TradeAlreadyExist{
		if(tradersRepo.checkName(trader.getEmail())==null) {
			return new ResponseEntity<>(tradersRepo.save(trader),HttpStatus.CREATED);		
		}
		throw new TradeAlreadyExist("Trader already exists!");
	}

	public List<TradersModel> getAllTradersService() throws CustomException{	
		if(tradersRepo.findAll().size()>0)
				{
			          return tradersRepo.findAll(Sort.by(Sort.Direction.ASC, "id"));
				}
		throw new CustomException("No Trader is present in the db.");
	}

	public ResponseEntity<List<TradersModel>> getRecordByEmail(String email) throws CustomException {
		Optional<List<TradersModel>> res=Optional.ofNullable(tradersRepo.getRecordByEmail(email));
		log.info("List: \n\n"+res+"\n\n"+res.isPresent());
		if(res.isPresent() && res.get().isEmpty()) {
			throw new CustomException("Trader is not found!!");
			
		}
		return new ResponseEntity<>(tradersRepo.getRecordByEmail(email),HttpStatus.OK);
	}

	public ResponseEntity<List<TradersModel>> updatetraderservice(TradersModel trader) throws CustomException{
	if(tradersRepo.checkUpdated(trader.getName(),trader.getEmail())==0) {
		throw new CustomException("Trader is not found!!");
	}
	return new ResponseEntity<>(tradersRepo.getRecordByEmail(trader.getEmail()),HttpStatus.OK);
	}
	
	public ResponseEntity<List<TradersModel>> addmoneyservice(TradersModel trader) throws CustomException{
		if(tradersRepo.addAmount(trader.getBalance(), trader.getEmail())==0) {
			throw new CustomException("Trader is not found!!");
		}
		return new ResponseEntity<>(tradersRepo.getRecordByEmail(trader.getEmail()),HttpStatus.OK);
	}
	
	public ResponseEntity<String> deleteByEmailService(String email) throws CustomException{
		TradersModel tradersModel=tradersRepo.getRecordByEmail(email).isEmpty()?null:tradersRepo.getRecordByEmail(email).get(0);
		if(tradersModel==null) {
			throw new CustomException("Trader is not found!!");
		}
		tradersRepo.delete(tradersModel);
		return new ResponseEntity<>("Trader deleted successfully!!!",HttpStatus.OK);
	}

}