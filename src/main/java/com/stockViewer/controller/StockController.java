package com.stockViewer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.stockViewer.model.db.StockInfo;
import com.stockViewer.service.StockInfoService;

@Controller
public class StockController {
	
	@Autowired
	StockInfoService stockInfoService;
	
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
    	return "redirect:/stock/update";
    }
	
    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    public String getStockInfo(Model model, 
    		@RequestParam(value = "code", required = false) String code) throws Exception {
    	if (code != null) {
    		if (code.length() > 0) {
    			code = code.toUpperCase();
    			StockInfo stockInfo = stockInfoService.findByCode(code);
        		if (stockInfo != null) {
        			model.addAttribute("stockInfo", stockInfo);
        		}
        		else {
        			model.addAttribute("errorMsg", "You haven't added this stock.");
        		}
    		}
    		else {
    			model.addAttribute("errorMsg", "Please input a code.");
    		}
    	}
    	
    	return "search_stock";
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
	
//  @RequestMapping(value = "/stock/all", method = RequestMethod.GET/)
//  @ResponseBody
//  public /*ResponseEntity<List<StockInfo>>*/ void getAllStockInfos() {
//      List<StockInfo> stockInfos = stockInfoService.findAllStockInfos();
//  }
}
