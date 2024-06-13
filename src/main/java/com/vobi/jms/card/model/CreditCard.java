package com.vobi.jms.card.model;

import java.io.Serializable;

public class CreditCard implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String cardNumber; // Número de tarjeta de crédito
    private String cardHolder; // Nombre del titular de la tarjeta
    private String expirationDate; // Fecha de expiración de la tarjeta (MM/YY)
    private String cvv; // Código de seguridad (CVV)
    
    
	public CreditCard() {
		super();
	}
	
	public CreditCard(String cardNumber, String cardHolder, String expirationDate, String cvv) {
		super();
		this.cardNumber = cardNumber;
		this.cardHolder = cardHolder;
		this.expirationDate = expirationDate;
		this.cvv = cvv;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getCardHolder() {
		return cardHolder;
	}
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
    
    

}
