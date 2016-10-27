package com.stockViewer.application;

import java.io.File;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

@SpringBootApplication
@ComponentScan(basePackages = "com.stockViewer")
public class SimpleStockViewerApplication {

	public static void main(String[] args) throws IOException {
//		// Create your Configuration instance, and specify if up to what FreeMarker
//		// version (here 2.3.25) do you want to apply the fixes that are not 100%
//		// backward-compatible. See the Configuration JavaDoc for details.
//		Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
//
//		// Specify the source where the template files come from. Here I set a
//		// plain directory for it, but non-file-system sources are possible too:
//		cfg.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
//
//		// Set the preferred charset template files are stored in. UTF-8 is
//		// a good choice in most applications:
//		cfg.setDefaultEncoding("UTF-8");
//
//		// Sets how errors will appear.
//		// During web page *development* TemplateExceptionHandler.HTML_DEBUG_HANDLER is better.
//		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
//
//		// Don't log exceptions inside FreeMarker that it will thrown at you anyway:
//		cfg.setLogTemplateExceptions(false);
		
		SpringApplication.run(SimpleStockViewerApplication.class, args);
	}
}
