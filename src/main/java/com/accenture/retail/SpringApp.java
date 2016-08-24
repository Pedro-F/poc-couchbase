package com.accenture.retail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.retail.load.EmptyStock;
import com.accenture.retail.load.LoadConsistentCounter;

@RestController
@EnableAutoConfiguration
public class SpringApp {
	
	//variable vacía para invocar a los diferentes mains de la aplicación 
	String [] emptyArgs = {};
	//variables para controlar el tiempo de ejecucion
	long lTimeBefore, lTimeAfter;
	
	/**
	 * Metodo homeMethod que lanza el main de la clase Application
	 * @return
	 */
	@RequestMapping("/")
	String homeMethod() {

		lTimeBefore = System.currentTimeMillis();
		Application.main(emptyArgs);
		lTimeAfter = System.currentTimeMillis();
		
		return "<br><h1><strong>CouchBase Client ==> APPLICATION </strong></h1></br>"
				+ "<br>Servicio ejecutado en " +  (lTimeAfter - lTimeBefore) +" Milisegundos</br>";
	}
	
	
	/**
	 * Método lanza el main de la clase EmptyStock
	 * 
	 * @param sParametro1
	 * @return
	 */
	@RequestMapping("/emptyStock")
	String emptyStockMethod() {
		
		lTimeBefore = System.currentTimeMillis();
		EmptyStock.main(emptyArgs);
		lTimeAfter = System.currentTimeMillis();
		
		return "<br><h1><strong>CouchBase Client ==> EMPTY STOCK </strong></h1></br>"
		+ "<br>Servicio ejecutado en " +  (lTimeAfter - lTimeBefore) +" Milisegundos</br>";
		
	}
	
	/**
	 * Método lanza el main de la clase c
	 * 
	 * @param sParametro1
	 * @return
	 */
	@RequestMapping("/loadConsistentCounter")
	String loadConsistentCounterMethod() {
		
		lTimeBefore = System.currentTimeMillis();
		LoadConsistentCounter.main(emptyArgs);
		lTimeAfter = System.currentTimeMillis();
		
		return "<br><h1><strong>CouchBase Client ==> LOAD CONSISTENT COUNTER </strong></h1></br>"
		+ "<br>Servicio ejecutado en " +  (lTimeAfter - lTimeBefore) +" Milisegundos</br>";
		
	}


	
	/*******************************************
	 * MAIN *
	 * 
	 * @param args
	 *            *
	 * @throws Exception
	 *             *
	 ******************************************/
	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringApp.class, args);
	}
}
