package com.accenture.retail;

import static com.accenture.retail.Application.create;
import static com.accenture.retail.Application.loadProperties;
import static com.accenture.retail.constant.Constants.BUCKET_DEFAULT_VALUE;
import static com.accenture.retail.constant.Constants.BUCKET_KEY;
import static com.accenture.retail.constant.Constants.NODES_DEFAULT_VALUE;
import static com.accenture.retail.constant.Constants.NODES_KEY;

import java.io.IOException;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.retail.action.StockAction;
import com.accenture.retail.data.CouchbaseRepository;
import com.accenture.retail.data.IRepository;
import com.accenture.retail.load.EmptyStock;
import com.accenture.retail.load.LoadConsistentCounter;
import com.couchbase.client.java.Cluster;

@RestController
@EnableAutoConfiguration
public class SpringApp {
	
	//variable vacía para invocar a los diferentes mains de la aplicación 
	String [] emptyArgs = {};
	//variables para controlar el tiempo de ejecucion
	long lTimeBefore, lTimeAfter;
	
//	/**
//	 * Metodo homeMethod que lanza el main de la clase Application
//	 * @return
//	 */
//	@RequestMapping("/")
//	String homeMethod() {
//
//		lTimeBefore = System.currentTimeMillis();
//		Application.main(emptyArgs);
//		lTimeAfter = System.currentTimeMillis();
//		
//		return "<br><h1><strong>CouchBase Client ==> APPLICATION </strong></h1></br>"
//				+ "<br>Servicio ejecutado en " +  (lTimeAfter - lTimeBefore) +" Milisegundos</br>";
//	}
//	
//	
//	/**
//	 * Método lanza el main de la clase EmptyStock
//	 * 
//	 * @param sParametro1
//	 * @return
//	 */
//	@RequestMapping("/emptyStock")
//	String emptyStockMethod() {
//		
//		lTimeBefore = System.currentTimeMillis();
//		EmptyStock.main(emptyArgs);
//		lTimeAfter = System.currentTimeMillis();
//		
//		return "<br><h1><strong>CouchBase Client ==> EMPTY STOCK </strong></h1></br>"
//		+ "<br>Servicio ejecutado en " +  (lTimeAfter - lTimeBefore) +" Milisegundos</br>";
//		
//	}
//	
//	/**
//	 * Método lanza el main de la clase c
//	 * 
//	 * @param sParametro1
//	 * @return
//	 */
//	@RequestMapping("/loadConsistentCounter")
//	String loadConsistentCounterMethod() {
//		
//		lTimeBefore = System.currentTimeMillis();
//		LoadConsistentCounter.main(emptyArgs);
//		lTimeAfter = System.currentTimeMillis();
//		
//		return "<br><h1><strong>CouchBase Client ==> LOAD CONSISTENT COUNTER </strong></h1></br>"
//		+ "<br>Servicio ejecutado en " +  (lTimeAfter - lTimeBefore) +" Milisegundos</br>";
//		
//	}

	/*******************************************************************/
	/*******************************************************************/
	/*******************************************************************/
	
	/**
	 * Metodo que crea un stock
	 * @param sDistibCenter
	 * @param sProductID
	 * @param sInit
	 * @return
	 */
	@RequestMapping("/crearStock")
	String crearStockMethod(@RequestParam(value = "dc") String sDistibCenter,
			 				@RequestParam(value = "pid") String sProductID,
			 				@RequestParam(value = "init") long sInit) {
		
		Properties props;
		try {
			props = loadProperties();
			
			String[] nodes = props.getProperty(NODES_KEY, NODES_DEFAULT_VALUE).split(",");
			Cluster cluster = create(nodes);
			String bucket = props.getProperty(BUCKET_KEY, BUCKET_DEFAULT_VALUE);
			IRepository repo = new CouchbaseRepository(cluster, bucket);
			StockAction stockAction = new StockAction(repo);
			
			lTimeBefore = System.currentTimeMillis();
			stockAction.createStock(sDistibCenter, sProductID, sInit);
			lTimeAfter = System.currentTimeMillis();

		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return "<br><h1><strong>CouchBase Client ==> CREATE STOCK </strong></h1></br>"
		+ "<br>MicroServicio ejecutado en " +  (lTimeAfter - lTimeBefore) +" Milisegundos</br>";
		
	}
	
	/**
	 * Metodo que incrementa el stock de un producto en un distribution center
	 * @param sDistibCenter
	 * @param sProductID
	 * @return
	 */
	@RequestMapping("/incrementarStock")
	String incrementarStockMethod(@RequestParam(value = "dc") String sDistibCenter,
			 					  @RequestParam(value = "pid") String sProductID) {
		
		Properties props;
		try {
			props = loadProperties();
			
			String[] nodes = props.getProperty(NODES_KEY, NODES_DEFAULT_VALUE).split(",");
			Cluster cluster = create(nodes);
			String bucket = props.getProperty(BUCKET_KEY, BUCKET_DEFAULT_VALUE);
			IRepository repo = new CouchbaseRepository(cluster, bucket);
			StockAction stockAction = new StockAction(repo);
			
			lTimeBefore = System.currentTimeMillis();
			stockAction.incrementStock(sDistibCenter, sProductID);
			lTimeAfter = System.currentTimeMillis();

		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return "<br><h1><strong>CouchBase Client ==> INCREMENT STOCK </strong></h1></br>"
		+ "<br>MicroServicio ejecutado en " +  (lTimeAfter - lTimeBefore) +" Milisegundos</br>";
		
	}
	
	/**
	 * Metodo que decrementa el stock de un producto en un distribution center
	 * @param sDistibCenter
	 * @param sProductID
	 * @return
	 */
	@RequestMapping("/decrementarStock")
	String decrementarStockMethod(@RequestParam(value = "dc") String sDistibCenter,
			 					  @RequestParam(value = "pid") String sProductID) {
		
		Properties props;
		try {
			props = loadProperties();
			
			String[] nodes = props.getProperty(NODES_KEY, NODES_DEFAULT_VALUE).split(",");
			Cluster cluster = create(nodes);
			String bucket = props.getProperty(BUCKET_KEY, BUCKET_DEFAULT_VALUE);
			IRepository repo = new CouchbaseRepository(cluster, bucket);
			StockAction stockAction = new StockAction(repo);
			
			lTimeBefore = System.currentTimeMillis();
			stockAction.decrementStock(sDistibCenter, sProductID);
			lTimeAfter = System.currentTimeMillis();

		} catch (IOException e) {
			e.printStackTrace();
		}
			
		return "<br><h1><strong>CouchBase Client ==> DECREMENT STOCK </strong></h1></br>"
		+ "<br>MicroServicio ejecutado en " +  (lTimeAfter - lTimeBefore) +" Milisegundos</br>";
		
	}
	
	/*******************************************************************/
	/*******************************************************************/
	/*******************************************************************/

	
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
