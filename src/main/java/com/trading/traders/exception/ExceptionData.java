package com.trading.traders.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionData {
	private final String message;

	//private final LocalDateTime localDateTime;
	//private final Throwable throwable;

}