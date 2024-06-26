package com.trading.traders.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter; 
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Traders")
@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class TradersModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	@Pattern(regexp = ".+[@].+[\\.].+")
	private String email;
	private double balance;
	@CreatedDate
	private Instant createdDate;
	@LastModifiedDate
	private Instant updatedDate;

}