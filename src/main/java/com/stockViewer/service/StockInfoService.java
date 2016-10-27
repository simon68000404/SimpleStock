package com.stockViewer.service;

import java.util.List;

import com.stockViewer.model.db.StockInfo;

public interface StockInfoService {

	List<StockInfo> findAllStockInfos();
	StockInfo updateStockInfo(String stockCode);
	void deleteStockInfo(long id);
	boolean isStockInfoExist(StockInfo stockInfo);
	StockInfo findByCode(String stockCode);
}
