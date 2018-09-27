package com.ticketorder.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Ticket_OrderVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String order_no;
	private String mem_no;
	private String meal_no;
	private String uuid;
	private String amount;
	private Integer order_state;
	private Integer payment_state;
	private Timestamp created_at;
	private Timestamp updated_at;
	private Integer exchange_state;
	private String credit_card;
	private String deadline;
	private String auth_key;

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getMeal_no() {
		return meal_no;
	}

	public void setMeal_no(String meal_no) {
		this.meal_no = meal_no;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Integer getOrder_state() {
		return order_state;
	}

	public void setOrder_state(Integer order_state) {
		this.order_state = order_state;
	}

	public Integer getPayment_state() {
		return payment_state;
	}

	public void setPayment_state(Integer payment_state) {
		this.payment_state = payment_state;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}

	public Integer getExchange_state() {
		return exchange_state;
	}

	public void setExchange_state(Integer exchange_state) {
		this.exchange_state = exchange_state;
	}

	public String getCredit_card() {
		return credit_card;
	}

	public void setCredit_card(String credit_card) {
		this.credit_card = credit_card;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getAuth_key() {
		return auth_key;
	}

	public void setAuth_key(String auth_key) {
		this.auth_key = auth_key;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
