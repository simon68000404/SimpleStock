# SimpleStock

This is a simple web app that has two pages:
- `/stock/update` page has a search field, once searched with a stock code, server will fetch information of that stock from Yahoo Finance Web Service, save it to Database, and then display the information on the page.
- `/stock` page also has a search field, but this search only performs search within the local Database, and displays the stock's information on the page.

Keywords: Spring boot, Hibernate, Sqlite, Freemarker, SASS, BEM, Responsive.

Main dependencies:
- spring-boot-starter-web 1.4.1.RELEASE
- spring-boot-starter-freemarker 1.4.1.RELEASE
- hibernate-core 5.2.2.Final
- sqlite-jdbc 3.8.11.2

Plugins:
- spring-boot-maven-plugin
- sass-maven-plugin

Requirements:
- Java 1.8
- Run with "mvn spring-boot:run"
