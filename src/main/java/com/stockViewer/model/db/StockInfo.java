package com.stockViewer.model.db;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STOCKINFO")
public final class StockInfo {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	@Id
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getTs() {
		return ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getUtctime() {
		return utctime;
	}

	public void setUtctime(Date utctime) {
		this.utctime = utctime;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	
	private String name;
	private Float price;
	private String symbol;
	private String ts;
	private String type;
	private Date utctime;
	private Integer volume;
	
	public StockInfo() {
	}
	
	public StockInfo(String name, Float price, String symbol, String ts, String type, Date utctime, Integer volume){
	    this.name = name;
	    this.price = price;
	    this.symbol = symbol;
	    this.ts = ts;
	    this.type = type;
	    this.utctime = utctime;
	    this.volume = volume;
	}
	
	public StockInfo(com.stockViewer.model.StockInfo stockInfoDTO) {
		this.name = stockInfoDTO.list.resources[0].resource.fields.name;
	    this.price = Float.valueOf(stockInfoDTO.list.resources[0].resource.fields.price);
	    this.symbol = stockInfoDTO.list.resources[0].resource.fields.symbol;
	    this.ts = stockInfoDTO.list.resources[0].resource.fields.ts;
	    this.type = stockInfoDTO.list.resources[0].resource.fields.type;
	    DateFormat m_ISO8601Local = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	    try {
			this.utctime = m_ISO8601Local.parse(stockInfoDTO.list.resources[0].resource.fields.utctime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    this.volume = Integer.valueOf(stockInfoDTO.list.resources[0].resource.fields.volume);
	}
}