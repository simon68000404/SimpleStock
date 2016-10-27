package com.stockViewer.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
	final String yahooWebService = "http://finance.yahoo.com/webservice/v1/symbols/$SYMBOLS/quote?format=json";

	@Override
	public List<StockInfo> findAllStockInfos() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List result = session.createQuery( "from StockInfo" ).getResultList();
		for ( StockInfo stockInfo : (List<StockInfo>) result ) {
		    System.out.println( "StockInfo (" + stockInfo.getName() + ") : " + stockInfo.getSymbol() );
		}
		session.close();
		
		StockInfo result0 = ((List<StockInfo>)result).get(0);
		
		System.out.println(result0.getName());
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
			System.out.println(stockInfoDB.getSymbol() + " " + stockInfoDB.getUtctime());
			
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
		
		String targetURL = yahooWebService.replace("$SYMBOLS", stockCode);
		
		try {
			URL url = new URL(targetURL);
		    connection = (HttpURLConnection) url.openConnection();
		    connection.setRequestMethod("GET");
		    connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 6.0.1; MotoG3 Build/MPI24.107-55) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.81 Mobile Safari/537.36");  
	
		    connection.setUseCaches(false);
		    connection.setDoOutput(true);
	
	//	    //Send request
	//	    DataOutputStream wr = new DataOutputStream (
	//	        connection.getOutputStream());
	//	    wr.writeBytes(urlParameters);
	//	    wr.close();
	
		    //Get Response  
		    InputStream is = connection.getInputStream();
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
		    StringBuffer response = new StringBuffer(); // or StringBuffer if Java version 5+
		    String line;
		    while ((line = rd.readLine()) != null) {
		      response.append(line);
		      response.append('\r');
		    }
		    rd.close();
		    
//		    String test = "{\"list\":{\"meta\":{\"type\":\"resource-list\",\"start\":0,\"count\":1}}}";
		    ObjectMapper mapper = new ObjectMapper();
//		    StockInfo infoStock = mapper.readValue(test, StockInfo.class);
		    System.out.println(response.toString());
		    com.stockViewer.model.StockInfo infoStock = mapper.readValue(response.toString(), com.stockViewer.model.StockInfo.class);
		    
		    if (infoStock.list.resources.length > 0) {
		    	return infoStock;
		    }
		    else {
		    	return null;
		    }
//			System.out.println(infoStock);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		
//	    return response.toString();
//		return infoStock;
	}

	@Override
	public boolean isStockInfoExist(StockInfo stockInfo) {
		return findByCode(stockInfo.getSymbol()) != null;
	}
	
	public StockInfo findByCode(String symbol) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery( "from StockInfo where symbol = :symbol" );
		query.setParameter("symbol", symbol);
		List list = query.getResultList();
//		for ( Contact contact : (List<Contact>) result ) {
//		    System.out.println( "Contact (" + contact.getName() + ") : " + contact.getAddress() );
//		}
		session.close();
		System.out.println("size:" + list.size());
		
		if (list.size() == 1) {
			return (StockInfo) list.get(0);
		}
		else {
			return null;
		}
	}
}
