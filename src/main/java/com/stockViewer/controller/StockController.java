package com.stockViewer.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.stockViewer.model.db.StockInfo;
import com.stockViewer.service.StockInfoService;

@Controller
public class StockController {
	
	@Autowired
	StockInfoService stockInfoService;
	
	@RequestMapping(value = "/greeting")
	public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
		return "greeting";
	}
	
    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    public String getStockInfo(Model model, @RequestParam(value = "code", required = false) String code) throws Exception {
    	System.out.println("code :" + code);
    	if (code != null) {
    		if (code.length() > 0) {
    			code = code.toUpperCase();
    			StockInfo stockInfo = stockInfoService.findByCode(code);
        		if (stockInfo != null) {
        			System.out.println(stockInfo.getSymbol());
        			model.addAttribute("stockInfo", stockInfo);
        		}
        		else {
        			System.out.println("You haven't added this stock, or this stock code is invalid");
        			model.addAttribute("errorMsg", "You haven't added this stock.");
        		}
    		}
    		else {
    			model.addAttribute("errorMsg", "Please input a code.");
    		}


    	}
    	
    	return "search_stock";
    }
	
    @RequestMapping(value = "/stock/all", method = RequestMethod.GET/*, produces = MediaType.APPLICATION_JSON_VALUE*/)
    @ResponseBody
    public /*ResponseEntity<List<StockInfo>>*/ void getAllStockInfos() {
        List<StockInfo> stockInfos = stockInfoService.findAllStockInfos();
    }
    
    @RequestMapping(value = "/stock/update", method = RequestMethod.GET)
    public String updateStockInfo() {
    	return "update_stock";
    }
    
    @RequestMapping(value = "/stock/update", method = RequestMethod.POST)
    public String updateStockInfo(Model model, @RequestParam(value = "code") String code) throws Exception {
    	if (code.length() == 0) {
    		model.addAttribute("errorMsg", "Please input a stock code.");
    		return "update_stock";
    	}
    	
    	code = code.toUpperCase();
    	StockInfo stockInfo = stockInfoService.updateStockInfo(code);
    	if (stockInfo != null) {
    		model.addAttribute("stockInfo", stockInfo);
    	}
    	else {
    		model.addAttribute("errorMsg", "The stock code you input is invalid.");
    	}
    	return "update_stock";
 
    }
}
