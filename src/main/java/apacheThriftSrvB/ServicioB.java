package apacheThriftSrvB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
@EnableAutoConfiguration
public class ServicioB {

	List<PrendaNoThrift> prendas;

	public ServicioB() {
		prendas = PrendaDao.instance.getPrendas();
	}


	@RequestMapping(value = "/servicioB", method = RequestMethod.POST)
	public @ResponseBody MensajeOutServicioNoThrift servicioB(@RequestBody MensajeInServicioNoThrift mensajeIn) {
		
		// Variables
		MensajeOutServicioNoThrift respuestaNoThrift = new MensajeOutServicioNoThrift();
		List<PrendaNoThrift> listadoPrendas = new ArrayList<PrendaNoThrift>();
		long iniTime = System.currentTimeMillis();
		
		// Recorremos el catálogo de prendas DAO para seleccionar aquellas que se retornarán al servicioA
		for(PrendaNoThrift prenda:prendas){
			// Filtramos aquellas que son del tipo y color que solicita el servicioA 
			if(prenda.getTipo().equals(TipoNoThrift.findByValue(Integer.parseInt(mensajeIn.getCuerpo().get("TipoPrenda")))) 
			    && prenda.getColor().toUpperCase().equals(mensajeIn.getCuerpo().get("Color").toUpperCase())){
				
				 //Convertimos la prenda en un bean de tipo RequestMessageStock necesaria para invocar al servicioC
				 RequestMessageStock inServicioC = convertPrendaNoThrift_TO_RequestMessageStock(prenda, mensajeIn);
						 
				 // Invocamos al servicioC para solicitar el stock de cada prenda
				 RestTemplate restTemplate = new RestTemplate();
				 RespuestaNoThriftStock outServicioC = restTemplate.postForObject("http://no-thrift-srvc:8080/servicioC", inServicioC, RespuestaNoThriftStock.class);
				
				 // añadimos el stock y la prenda
				 prenda.setStock(outServicioC.getStock());	 
				 listadoPrendas.add(prenda);
			 
			 }
		}		 
		
		// Construimos el bean de respuesta al servicioA
		respuestaNoThrift = buildMensajeOutServicioNoThrift(mensajeIn, listadoPrendas);
		
		// Traza de fin del servicioB
		System.out.println("FIN ServicioB.  ts = {" + (System.currentTimeMillis() - iniTime) + "}");
		
		return respuestaNoThrift;
	}
	
	/**
	 * 
	 * @param mensajeIn
	 * @param listadoPrendas
	 * @return
	 */
	private MensajeOutServicioNoThrift buildMensajeOutServicioNoThrift(MensajeInServicioNoThrift mensajeIn, 
																	   List<PrendaNoThrift> listadoPrendas){
		
		MensajeOutServicioNoThrift respuestaNoThrift = new MensajeOutServicioNoThrift();
		
		Map<String,String> cabeceraSalida = mensajeIn.getCabecera();
        cabeceraSalida.put("UID", "Respuesta_" + cabeceraSalida.get("UID"));
        Map<String,List<PrendaNoThrift>> cuerpoSalida = new HashMap<String,List<PrendaNoThrift>>();
        cuerpoSalida.put("Prendas", listadoPrendas);
        respuestaNoThrift.setCabecera(cabeceraSalida);
        respuestaNoThrift.setCuerpo(cuerpoSalida);
		if(listadoPrendas.size()==0) respuestaNoThrift.setAviso("No se han encontrado prendas con las especificaciones");
		
		return respuestaNoThrift;
	}
	
	/**
	 * 
	 * @param prenda
	 * @return
	 */
	private RequestMessageStock convertPrendaNoThrift_TO_RequestMessageStock(PrendaNoThrift prenda, MensajeInServicioNoThrift mensajeIn){
		RequestMessageStock inSrvC = new RequestMessageStock();
		
		inSrvC.setNombre(prenda.getNombre());
		inSrvC.setTalla(prenda.getTalla());
		inSrvC.setColor(prenda.getColor());
		
		return inSrvC;
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
		SpringApplication.run(ServicioB.class, args);
	}
}
