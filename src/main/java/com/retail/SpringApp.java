package com.retail;

import static com.retail.Application.create;
import static com.retail.Application.loadProperties;
import static com.retail.constant.Constants.BUCKET_DEFAULT_VALUE;
import static com.retail.constant.Constants.BUCKET_KEY;
import static com.retail.constant.Constants.NODES_DEFAULT_VALUE;
import static com.retail.constant.Constants.NODES_KEY;

import java.io.IOException;
import java.util.Properties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.couchbase.client.java.Cluster;
import com.retail.action.StockAction;
import com.retail.data.CouchbaseRepository;
import com.retail.data.IRepository;

@RestController
@EnableAutoConfiguration
public class SpringApp {
	
	//Variables globales
	private static Properties props;
	private static String[] nodes;
	private static Cluster cluster;
	private static String bucket;
	private static IRepository repo = null;
	private static StockAction stockAction;
	//variables para controlar el tiempo de ejecucion
	private static long lTimeBefore, lTimeAfter;
	
	
	/***************************
	* Mapeos SprongBoot        *
	***************************/
	
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
		
		
		try {
			// inicializamos la conexión a la BBDD la primera vez
			if (repo==null){
				init();
			}
			
			lTimeBefore = System.currentTimeMillis();
			stockAction.createStock(sDistibCenter, sProductID, sInit);
			lTimeAfter = System.currentTimeMillis();

		} catch (Exception e) {
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
		
		try {
			// inicializamos la conexión a la BBDD la primera vez
			if (repo==null){
				init();
			}
			
			lTimeBefore = System.currentTimeMillis();
			stockAction.incrementStock(sDistibCenter, sProductID);
			lTimeAfter = System.currentTimeMillis();

		} catch (Exception e) {
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
		
		try {
			// inicializamos la conexión a la BBDD la primera vez
			if (repo==null){
				init();
			}
			
			lTimeBefore = System.currentTimeMillis();
			stockAction.decrementStock(sDistibCenter, sProductID);
			lTimeAfter = System.currentTimeMillis();

		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return "<br><h1><strong>CouchBase Client ==> DECREMENT STOCK </strong></h1></br>"
		+ "<br>MicroServicio ejecutado en " +  (lTimeAfter - lTimeBefore) +" Milisegundos</br>";
		
	}
	
	/**
	 * Metodo que recupera el stock de un producto
	 * @param sProductID
	 * @return
	 */
	@RequestMapping("/getStock")
	String getStockProduct(@RequestParam(value = "dc") String sDistibCenter,
			 				@RequestParam(value = "pid") String sProductID) {
		
		String stock = "-1";
		try {
			// inicializamos la conexión a la BBDD la primera vez
			if (repo==null){
				init();
			}
			
			lTimeBefore = System.currentTimeMillis();
			stock = stockAction.getStock(sDistibCenter, sProductID);
			lTimeAfter = System.currentTimeMillis();

		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return "<br><h1><strong>CouchBase Client ==> GET STOCK </strong></h1></br>"
			 + "<br>El stock del centro " + sDistibCenter + " para el producto " + sProductID + " es: " + stock +" Unidades.</br>"
			 + "<br>MicroServicio ejecutado en " +  (lTimeAfter - lTimeBefore) +" Milisegundos</br>";
		
	}
	
	
	
	/**
	 * Init nodes, cluster, bucket, CouchbaseRepository and stockAction
	 * @throws Exception
	 */
	private void init(){
		try {
			props = loadProperties();
			
			nodes = props.getProperty(NODES_KEY, NODES_DEFAULT_VALUE).split(",");
			cluster = create(nodes);
			bucket = props.getProperty(BUCKET_KEY, BUCKET_DEFAULT_VALUE);
			repo = new CouchbaseRepository(cluster, bucket);
			stockAction = new StockAction(repo);
		}
		catch (IOException e) {
			System.out.println("Init Caught: " + e);
			e.printStackTrace();
		}
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
