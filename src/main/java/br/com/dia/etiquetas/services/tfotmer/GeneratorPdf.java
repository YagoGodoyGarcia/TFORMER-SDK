package br.com.dia.etiquetas.services.tfotmer;

import com.tecit.TFORMer.*;                   // Licensing, Error handling, Enumerations
import com.tecit.TFORMer.Repository.*;        // Provides access to Repository elements 
import com.tecit.TFORMer.Printing.*;          // Contains functionality for a Print-Job
import com.tecit.TFORMer.Enumerations.*; 
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;

public class GeneratorPdf {

	public GeneratorPdf(JSONArray json, String tipo) throws TFormerException {		  
		if(json.length() != 0) {
		if(tipo.equals("etiquetaUmQuadranteNormalOferta")) {
			Etiquetar(json, tipo);
		}
//		}else if(tipotipo.equals("etiquetaUmQuadranteClubDia")) {
//			for(int i = 0; i < json.length(); i++) {
//				System.out.println(json.get(i));
//			}
//		}else if(tipo.equals("etiquetaUmQuadranteComboLevePague")) {
//			for(int i = 0; i < json.length(); i++) {
//				System.out.println(json.get(i));
//			}
//		}else if(tipo.equals("etiquetaUmQuadranteComboAPartir")) {
//			for(int i = 0; i < json.length(); i++) {
//				System.out.println(json.get(i));
//			}
//		}else if(tipo.equals("etiquetaUmQuadranteComboPorcUmaUnidade")) {
//			for(int i = 0; i < json.length(); i++) {
//				System.out.println(json.get(i));
//			}
//		}else if(tipo.equals("etiquetaUmQuadranteComboPorcMaisUnidade")) {
//			for(int i = 0; i < json.length(); i++) {
//				System.out.println(json.get(i));
//			}
//		}else if(tipo.equals("etiquetaTresQuadranteNormalOferta")) {
//			for(int i = 0; i < json.length(); i++) {
//				System.out.println(json.get(i));
//			}
//		}else if(tipo.equals("etiquetaTresQuadranteClubDia")) {
//			for(int i = 0; i < json.length(); i++) {
//				System.out.println(json.get(i));
//			}
//		}else if(tipo.equals("etiquetaTresQuadranteComboLevePague")) {
//			for(int i = 0; i < json.length(); i++) {
//				System.out.println(json.get(i));
//			}
//		}else if(tipo.equals("etiquetaTresQuadranteComboAPartir")) {
//			for(int i = 0; i < json.length(); i++) {
//				System.out.println(json.get(i));
//			}
//		}else if(tipo.equals("etiquetaTresQuadranteComboPorcUmaUnidade")) {
//			for(int i = 0; i < json.length(); i++) {
//				System.out.println(json.get(i));
//			}
//		}else if(tipo.equals("etiquetaTresQuadranteComboPorcMaisUnidade")) {
//			for(int i = 0; i < json.length(); i++) {
//				System.out.println(json.get(i));
//			}
//		}else {
//			System.out.println("Tipo não encotrado!");
//		}	
		}else {
			
		}
	}
	
	public void Etiquetar(JSONArray json, String tipo) throws TFormerException {
		 // Create a new Job instance
		  Job printJob = new Job();
		  JSONObject dados = new JSONObject(String.valueOf(json.get(0)));
		  dados =  (JSONObject) dados.get("Etiqueta");
		  int loja = dados.getInt("Loja") ;
		  // Select the stand-alone FormLayout 
		  String tff = dados.getString("Tff");
		  printJob.setRepositoryName ("C:/Users/yagog/Downloads/tff yago/tff yago/PADRAO/"+tff+".tff");
		 // Create a new JobData instance. 
		 // Here we are using the JobDataRecordSet which accepts Records containing name/value pairs
		  JobDataRecordSet jobData = new JobDataRecordSet();
		  
		 
		  JobDataRecordSet.Record record;
		  
		  for(int i = 0; i < json.length(); i++) {
			  JSONObject produto = new JSONObject(String.valueOf(json.get(0)));
			  produto = (JSONObject) produto.get("Etiqueta");
			  // Create a new Record
			  record = new JobDataRecordSet.Record();
			  // Add some name/value pairs for the DataField values to the Record
			  record.setData("Codigo_de_articulo", produto.getString("Codigo_de_articulo"));
			  record.setData("Des_Pre_Uni_Med_Fideli", produto.getString("Des_Pre_Uni_Med_Fideli"));
			  record.setData("Des_Pre_Uni_Med_No_Fideli", produto.getString("Des_Pre_Uni_Med_No_Fideli"));
			  record.setData("Descripcion_Linea_1", produto.getString("Descripcion_Linea_1"));
			  record.setData("Descripcion_Linea_2", produto.getString("Descripcion_Linea_2"));
			  record.setData("Descripcion_Linea_3", produto.getString("Descripcion_Linea_3"));
			  record.setData("EAN13", produto.getString("EAN13"));
			  record.setData("Precio_Fidelizado", produto.getString("Precio_Fidelizado"));
			  record.setData("Precio_NO_Fidelizado", produto.getString("Precio_NO_Fidelizado"));
			  record.setData("Descuento_Oferta", produto.getString("Descuento_Oferta"));
			  record.setData("Precio_Neto_Oferta", produto.getString("Precio_Neto_Oferta"));
			  record.setData("Des_Pre_Uni_Med_Neto_Ofe", produto.getString("Des_Pre_Uni_Med_Neto_Ofe"));
			  record.setData("Data_Fim_Promo", produto.getString("Data_Fim_Promo"));
			  
			  // Add the Record to the JobData
			  jobData.add(record);
	
		  }
		  Date data = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(data);
			
			int ano = cal.get(Calendar.YEAR);
			int mes = cal.get(Calendar.MONTH);
			int dia = cal.get(Calendar.DAY_OF_MONTH);
			int horas = cal.get(Calendar.HOUR_OF_DAY);
			int minutos = cal.get(Calendar.MINUTE);
			int segundos = cal.get(Calendar.SECOND);
			int milissegundo = cal.get(Calendar.MILLISECOND);
		  
		  // Select PDF output to /temp/out.pdf 
		  printJob.setOutputName ("C:/Users/yagog/Downloads/TFFS/"+loja+""+dia+""+mes+""+ano+""+horas+""+minutos+""+segundos+".pdf");
		  printJob.setJobData(jobData);
		  
		  printJob.setPrinterType (EPrinterType.PDFFile);
		  // Generate PDF
		  printJob.print(); 
		  // Assign the JobData to the Job
		   

		
	}
}
