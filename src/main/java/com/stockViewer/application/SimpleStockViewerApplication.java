package com.stockViewer.application;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.stockViewer")
public class SimpleStockViewerApplication {

	public static void main(String[] args) throws IOException {		
		SpringApplication.run(SimpleStockViewerApplication.class, args);
	}
}
