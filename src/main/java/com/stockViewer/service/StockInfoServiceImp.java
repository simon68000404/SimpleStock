package com.stockViewer.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stockViewer.util.HibernateUtil;
import com.stockViewer.model.db.StockInfo;

@Service("StockInfoService")
public class StockInfoServiceImp implements StockInfoService {
	private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private static final String YAHOO_WEBSERVICE = 
			"http://finance.yahoo.com/webservice/v1/symbols/$SYMBOLS/quote?format=json";
	private static final String[] userAgents = {"Mozilla/5.0 (Linux; Android 6.0.1; MotoG3 Build/MPI24.107-55)", 
			"AppleWebKit/537.36 (KHTML, like Gecko)", 
			"Chrome/51.0.2704.81", 
			"Safari/537.36"};

	@Override
	public List<StockInfo> findAllStockInfos() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List result = session.createQuery( "from StockInfo" ).getResultList();
		session.close();
		
		StockInfo result0 = ((List<StockInfo>)result).get(0);
		
		return (List<StockInfo>) result;
	}

	@Override
	public StockInfo updateStockInfo(String stockCode) {
		com.stockViewer.model.StockInfo stockInfo = fetchLatestStockInfo(stockCode);
		
		if (stockInfo != null) {
			// write to db
			StockInfo stockInfoDB = new StockInfo(stockInfo);
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.saveOrUpdate(stockInfoDB);
			session.flush();
			tx.commit();
			session.close();
			
			return stockInfoDB;	
		}
		else {
			return null;
		}
			
	}

	@Override
	public void deleteStockInfo(long id) {
		// TODO Auto-generated method stub
		
	}
	
	private com.stockViewer.model.StockInfo fetchLatestStockInfo(String stockCode) {
		HttpURLConnection connection = null;
		
		String targetURL = YAHOO_WEBSERVICE.replace("$SYMBOLS", stockCode);
		try {
			URL url = new URL(targetURL);
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("GET");
		    connection.setRequestProperty("User-Agent", String.join(" ", userAgents));  
	
		    connection.setUseCaches(false);
		    connection.setDoOutput(true);
	
		    // Get Response  
		    InputStream is = connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuilder response = new StringBuilder();
		    String line;
		    while ((line = rd.readLine()) != null) {
		      response.append(line);
		    }
		    rd.close();
		    
		    // read from json to model object
		    ObjectMapper mapper = new ObjectMapper();
		    com.stockViewer.model.StockInfo infoStock = mapper.readValue(response.toString(), 
		    		com.stockViewer.model.StockInfo.class);
		    if (infoStock.list.resources.length > 0) {
		    	return infoStock;
		    }
		    else {
		    	return null;
		    }
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}

	@Override
	public boolean isStockInfoExist(StockInfo stockInfo) {
		return findByCode(stockInfo.getSymbol()) != null;
	}
	
	@Override
	public StockInfo findByCode(String symbol) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery( "from StockInfo where symbol = :symbol" );
		query.setParameter("symbol", symbol);
		List list = query.getResultList();
		session.close();
		
		if (list.size() == 1) {
			return (StockInfo) list.get(0);
		}
		else {
			return null;
		}
	}
}
