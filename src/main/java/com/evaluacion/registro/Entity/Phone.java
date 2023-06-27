package com.evaluacion.registro.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Phone {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column
	private String number;
	
	@Column
	@JsonProperty("citycode")
    private String cityCode;
	
	@Column
	@JsonProperty("contrycode")
    private String countryCode;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
    
	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}
	
	/**
	 * Sets the number.
	 *
	 * @param number the new number
	 */
	public void setNumber(String number) {
		this.number = number;
	}
	
	/**
	 * Gets the city code.
	 *
	 * @return the city code
	 */
	public String getCityCode() {
		return cityCode;
	}
	
	/**
	 * Sets the city code.
	 *
	 * @param cityCode the new city code
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	/**
	 * Gets the country code.
	 *
	 * @return the country code
	 */
	public String getCountryCode() {
		return countryCode;
	}
	
	/**
	 * Sets the country code.
	 *
	 * @param countryCode the new country code
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
}
