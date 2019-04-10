package br.com.dia.etiquetas.services.tfotmer;

import com.tecit.TFORMer.*; // Licensing, Error handling, Enumerations
import com.tecit.TFORMer.Repository.*; // Provides access to Repository elements 
import com.tecit.TFORMer.Printing.*; // Contains functionality for a Print-Job
import com.tecit.TFORMer.Enumerations.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Stream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
/**

 * @author Yago Garcia

 */
public class GeneratorPdf {

	public JSONArray Pdf(JSONArray json, String tipo, String rota) throws TFormerException {	
		JSONArray resultado = null;
		if (json.length() != 0) {
			resultado = Etiquetar(json, tipo, rota);
		}
		return resultado;
	}

	public JSONArray Etiquetar(JSONArray json, String tipo, String rota) throws TFormerException {		
		JSONArray objetoResultado = new JSONArray();
		try {
			List<Integer> empresas = new ArrayList<Integer>();
			List<Integer> lojasList = new ArrayList<Integer>();
			
			for(int i = 0; i < json.length(); i++) {
				JSONObject produto = new JSONObject(String.valueOf(json.get(i)));
				produto = (JSONObject) produto.get("Etiqueta");
				int loja = produto.getInt("Loja");
				
				empresas.add(loja);
			};
			
			Stream<Integer> stream = empresas.stream();
			stream.distinct().forEach(loja -> lojasList.add(loja));
			
			JSONArray lojas = new JSONArray(lojasList);
			
			Job printJob = new Job();
			JSONObject dados = new JSONObject(String.valueOf(json.get(0)));
			dados = (JSONObject) dados.get("Etiqueta");
			int loja = dados.getInt("Loja");

			// Select the stand-alone FormLayout
			String tff = dados.getString("Tff");
			
			String pathTff = "C:/projetodiametodos/TFFs/";
			String pathPdf = "C:/projetodiametodos/PDF/";
			
			// Create a new JobData instance.
			// Here we are using the JobDataRecordSet which accepts Records containing
			// name/value pairs

			if (tipo.equals("etiquetaUmQuadranteNormalOferta") || tipo.equals("etiquetaUmQuadranteClubDia")
					|| tipo.equals("etiquetaTresQuadranteNormalOferta")
					|| tipo.equals("etiquetaTresQuadranteClubDia")
					|| tipo.equals("etiquetaA5")
					|| tipo.equals("etiquetaA5ClubDia")) {
				
				for (int j = 0; j < lojas.length(); j++) {
										
					JobDataRecordSet jobData = new JobDataRecordSet();
					JobDataRecordSet.Record record;
					
					int ano = 0;
					int mes = 0;
					int dia = 0;
					int horas = 0;
					int minutos = 0;
					int segundos = 0;
					int milissegundo = 0;
					
					String caminho = null;
					Date data = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(data);
					
					List<String> ids = new ArrayList<String>();
					
					for (int i = 0; i < json.length(); i++) {						
						
						JSONObject produto = new JSONObject(String.valueOf(json.get(i)));
						produto = (JSONObject) produto.get("Etiqueta");
						
						if(lojas.get(j).toString().equals(produto.get("Loja").toString())) {

							ids.add((String) produto.get("Id"));
							
							printJob.setRepositoryName(pathTff + produto.getString("Tff"));
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
							//record.setData("Descuento_Oferta", produto.getString("Descuento_Oferta"));
							//record.setData("Precio_Neto_Oferta", produto.getString("Precio_Neto_Oferta"));
							//record.setData("Des_Pre_Uni_Med_Neto_Ofe", produto.getString("Des_Pre_Uni_Med_Neto_Ofe"));
							// Add the Record to the JobData
							jobData.add(record);						
						}
					}	

					ano = cal.get(Calendar.YEAR);
					mes = cal.get(Calendar.MONTH) + 1;
					dia = cal.get(Calendar.DAY_OF_MONTH);
					horas = cal.get(Calendar.HOUR_OF_DAY);
					minutos = cal.get(Calendar.MINUTE);
					segundos = cal.get(Calendar.SECOND);
					milissegundo = cal.get(Calendar.MILLISECOND);
					caminho = pathPdf + tipo + "" + lojas.getInt(j) + "" + dia + "" + mes + "" + ano + "" + horas + "" + minutos + ""
							+ segundos + ".pdf";

					printJob.setOutputName(caminho);
					printJob.setJobData(jobData);

					printJob.setPrinterType(EPrinterType.PDFFile);
					// Generate PDF
					printJob.print();
					
					String body = "{ \"caminho\": \"" + caminho + "\",  \"loja\": " + loja + ", \"ids\": "  + "\"" + ids + "\"" + "}";
					JSONObject objeto = new JSONObject(body);
					objetoResultado.put(objeto);
					//ChamadaKaizala(caminho, lojas.getInt(j), ids, rota);
				}

			} else if (tipo.equals("etiquetaTresQuadranteComboPorcMaisUnidade")
					|| tipo.equals("etiquetaTresQuadranteComboPorcUmaUnidade")
					|| tipo.equals("etiquetaUmQuadranteComboPorcMaisUnidade")
					|| tipo.equals("etiquetaUmQuadranteComboPorcUmaUnidade")
					|| tipo.equals("etiquetaA5ComboPorcUmaUnidade")
					|| tipo.equals("etiquetaA5ComboPorcMaisUnidade")) {
				
				for (int j = 0; j < lojas.length(); j++) {
					
					JobDataRecordSet jobData = new JobDataRecordSet();
					JobDataRecordSet.Record record;
					
					int ano = 0;
					int mes = 0;
					int dia = 0;
					int horas = 0;
					int minutos = 0;
					int segundos = 0;
					int milissegundo = 0;
					
					String caminho = null;
					Date data = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(data);
					
					List<String> ids = new ArrayList<String>();
					
					for (int i = 0; i < json.length(); i++) {
						
						JSONObject produto = new JSONObject(String.valueOf(json.get(i)));
						produto = (JSONObject) produto.get("Etiqueta");
						
						if(lojas.get(j).toString().equals(produto.get("Loja").toString())) {
							
							ids.add((String) produto.get("Id"));
							
							printJob.setRepositoryName(pathTff + produto.getString("Tff"));
							// Create a new Record
							record = new JobDataRecordSet.Record();
							// Add some name/value pairs for the DataField values to the Record
							record.setData("Codigo_de_articulo", produto.getString("Codigo_de_articulo"));
							//record.setData("Des_Pre_Uni_Med_Fideli", produto.getString("Des_Pre_Uni_Med_Fideli"));
							record.setData("Des_Pre_Uni_Med_No_Fideli", produto.getString("Des_Pre_Uni_Med_No_Fideli"));
							record.setData("Descripcion_Linea_1", produto.getString("Descripcion_Linea_1"));
							record.setData("Descripcion_Linea_2", produto.getString("Descripcion_Linea_2"));
							record.setData("Descripcion_Linea_3", produto.getString("Descripcion_Linea_3"));
							record.setData("EAN13", produto.getString("EAN13"));
							//record.setData("Precio_Fidelizado", produto.getString("Precio_Fidelizado"));
							record.setData("Precio_NO_Fidelizado", produto.getString("Precio_NO_Fidelizado"));
							//record.setData("Descuento_Oferta", produto.getString("Descuento_Oferta"));
							record.setData("Precio_Neto_Oferta", produto.getString("Precio_Neto_Oferta"));
							record.setData("Des_Pre_Uni_Med_Neto_Ofe", produto.getString("Des_Pre_Uni_Med_Neto_Ofe"));
							record.setData("Unidade", produto.getString("Unidade"));
							record.setData("Porcento", produto.getString("Porcento"));
							record.setData("Fecha_Fin_Promocion_China", produto.getString("Fecha_Fin_Promocion_China"));
							// Add the Record to the JobData
							jobData.add(record);
						}						
					}
					
					ano = cal.get(Calendar.YEAR);
					mes = cal.get(Calendar.MONTH) + 1;
					dia = cal.get(Calendar.DAY_OF_MONTH);
					horas = cal.get(Calendar.HOUR_OF_DAY);
					minutos = cal.get(Calendar.MINUTE);
					segundos = cal.get(Calendar.SECOND);
					milissegundo = cal.get(Calendar.MILLISECOND);
					caminho = pathPdf + tipo + "" + lojas.getInt(j) + "" + dia + "" + mes + "" + ano + "" + horas + "" + minutos + ""
							+ segundos + ".pdf";

					printJob.setOutputName(caminho);
					printJob.setJobData(jobData);

					printJob.setPrinterType(EPrinterType.PDFFile);
					// Generate PDF
					printJob.print();
					
					String body = "{ \"caminho\": \"" + caminho + "\",  \"loja\": " + loja + ", \"ids\": "  + "\"" + ids + "\"" + "}";
					JSONObject objeto = new JSONObject(body);
					objetoResultado.put(objeto);
					//ChamadaKaizala(caminho, lojas.getInt(j), ids, rota);
				}

			} else if (tipo.equals("etiquetaUmQuadranteComboLevePague")
					|| tipo.equals("etiquetaTresQuadranteComboLevePague")
					|| tipo.equals("etiquetaA5ComboLevePague")) {
				
				for (int j = 0; j < lojas.length(); j++) {
					JobDataRecordSet jobData = new JobDataRecordSet();
					JobDataRecordSet.Record record;
					
					int ano = 0;
					int mes = 0;
					int dia = 0;
					int horas = 0;
					int minutos = 0;
					int segundos = 0;
					int milissegundo = 0;
					
					String caminho = null;
					Date data = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(data);
					
					List<String> ids = new ArrayList<String>();		
					
					for (int i = 0; i < json.length(); i++) {
												
						JSONObject produto = new JSONObject(String.valueOf(json.get(i)));
						produto = (JSONObject) produto.get("Etiqueta");
						
						if(lojas.get(j).toString().equals(produto.get("Loja").toString())) {
							
							ids.add((String) produto.get("Id"));

							printJob.setRepositoryName(pathTff + produto.getString("Tff"));
							// Create a new Record
							record = new JobDataRecordSet.Record();
							// Add some name/value pairs for the DataField values to the Record
							record.setData("Codigo_de_articulo", produto.getString("Codigo_de_articulo"));
							//record.setData("Des_Pre_Uni_Med_Fideli", produto.getString("Des_Pre_Uni_Med_Fideli"));
							record.setData("Des_Pre_Uni_Med_No_Fideli", produto.getString("Des_Pre_Uni_Med_No_Fideli"));
							record.setData("Descripcion_Linea_1", produto.getString("Descripcion_Linea_1"));
							record.setData("Descripcion_Linea_2", produto.getString("Descripcion_Linea_2"));
							record.setData("Descripcion_Linea_3", produto.getString("Descripcion_Linea_3"));
							record.setData("EAN13", produto.getString("EAN13"));
							//record.setData("Precio_Fidelizado", produto.getString("Precio_Fidelizado"));
							record.setData("Precio_NO_Fidelizado", produto.getString("Precio_NO_Fidelizado"));
							//record.setData("Descuento_Oferta", produto.getString("Descuento_Oferta"));
							record.setData("Precio_Neto_Oferta", produto.getString("Precio_Neto_Oferta"));
							record.setData("Des_Pre_Uni_Med_Neto_Ofe", produto.getString("Des_Pre_Uni_Med_Neto_Ofe"));
							record.setData("Pague", produto.getString("Pague"));
							record.setData("Leve", produto.getString("Leve"));
							record.setData("Fecha_Fin_Promocion_China", produto.getString("Fecha_Fin_Promocion_China"));
							// Add the Record to the JobData
							jobData.add(record);
						}
						

						
					}
					ano = cal.get(Calendar.YEAR);
					mes = cal.get(Calendar.MONTH) + 1;
					dia = cal.get(Calendar.DAY_OF_MONTH);
					horas = cal.get(Calendar.HOUR_OF_DAY);
					minutos = cal.get(Calendar.MINUTE);
					segundos = cal.get(Calendar.SECOND);
					milissegundo = cal.get(Calendar.MILLISECOND);
					caminho = pathPdf + tipo + "" + lojas.getInt(j) + "" + dia + "" + mes + "" + ano + "" + horas + "" + minutos + ""
							+ segundos + ".pdf";

					printJob.setOutputName(caminho);
					printJob.setJobData(jobData);

					printJob.setPrinterType(EPrinterType.PDFFile);
					// Generate PDF
					printJob.print();
					String body = "{ \"caminho\": \"" + caminho + "\",  \"loja\": " + loja + ", \"ids\": "  + "\"" + ids + "\"" + "}";
					JSONObject objeto = new JSONObject(body);
					objetoResultado.put(objeto);
					//ChamadaKaizala(caminho, lojas.getInt(j), ids, rota);
				}

			} else if (tipo.equals("etiquetaUmQuadranteComboAPartir")
					|| tipo.equals("etiquetaTresQuadranteComboAPartir")
					|| tipo.equals("etiquetaA5ComboAPartir")) {

				for (int j = 0; j < lojas.length(); j++) {
					
					JobDataRecordSet jobData = new JobDataRecordSet();
					JobDataRecordSet.Record record;
					
					int ano = 0;
					int mes = 0;
					int dia = 0;
					int horas = 0;
					int minutos = 0;
					int segundos = 0;
					int milissegundo = 0;
					
					String caminho = null;
					Date data = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(data);
					
					List<String> ids = new ArrayList<String>();
					
					for (int i = 0; i < json.length(); i++) {
						JSONObject produto = new JSONObject(String.valueOf(json.get(i)));
						produto = (JSONObject) produto.get("Etiqueta");
						
						
						if(lojas.get(j).toString().equals(produto.get("Loja").toString())) {
							
							ids.add((String) produto.get("Id"));
							
							printJob.setRepositoryName(pathTff + produto.getString("Tff"));
							// Create a new Record
							record = new JobDataRecordSet.Record();
							// Add some name/value pairs for the DataField values to the Record
							record.setData("Codigo_de_articulo", produto.getString("Codigo_de_articulo"));
							//record.setData("Des_Pre_Uni_Med_Fideli", produto.getString("Des_Pre_Uni_Med_Fideli"));
							record.setData("Des_Pre_Uni_Med_No_Fideli", produto.getString("Des_Pre_Uni_Med_No_Fideli"));
							record.setData("Descripcion_Linea_1", produto.getString("Descripcion_Linea_1"));
							record.setData("Descripcion_Linea_2", produto.getString("Descripcion_Linea_2"));
							record.setData("Descripcion_Linea_3", produto.getString("Descripcion_Linea_3"));
							record.setData("EAN13", produto.getString("EAN13"));
							//record.setData("Precio_Fidelizado", produto.getString("Precio_Fidelizado"));
							record.setData("Precio_NO_Fidelizado", produto.getString("Precio_NO_Fidelizado"));
							//record.setData("Descuento_Oferta", produto.getString("Descuento_Oferta"));
							record.setData("Precio_Neto_Oferta", produto.getString("Precio_Neto_Oferta"));
							record.setData("Des_Pre_Uni_Med_Neto_Ofe", produto.getString("Des_Pre_Uni_Med_Neto_Ofe"));
							
							//OLHAR LAYOUT NO TFF, VERIFICAR O CAMPO
							record.setData("Unidade", produto.getString("Unidade"));
							record.setData("Fecha_Fin_Promocion_China", produto.getString("Fecha_Fin_Promocion_China"));
							
							// Add the Record to the JobData
							jobData.add(record);
						}
						
					}
					
					ano = cal.get(Calendar.YEAR);
					mes = cal.get(Calendar.MONTH) + 1;
					dia = cal.get(Calendar.DAY_OF_MONTH);
					horas = cal.get(Calendar.HOUR_OF_DAY);
					minutos = cal.get(Calendar.MINUTE);
					segundos = cal.get(Calendar.SECOND);
					milissegundo = cal.get(Calendar.MILLISECOND);
					caminho = pathPdf + tipo + "" + lojas.getInt(j) + "" + dia + "" + mes + "" + ano + "" + horas + "" + minutos + ""
							+ segundos + ".pdf";

					printJob.setOutputName(caminho);
					printJob.setJobData(jobData);

					printJob.setPrinterType(EPrinterType.PDFFile);
					// Generate PDF
					printJob.print();
					String body = "{ \"caminho\": \"" + caminho + "\",  \"loja\": " + loja + ", \"ids\": "  + "\"" + ids + "\"" + "}";
					JSONObject objeto = new JSONObject(body);
					objetoResultado.put(objeto);
					//ChamadaKaizala(caminho, lojas.getInt(j), ids, rota);
				}
			}

			
		} catch (Exception e) {
			System.out.println(e);
		}
	
		return objetoResultado;
	}

	public void ChamadaKaizala(String caminho, int loja, List<String> ids, String rota) throws IOException {
		String endpoint = null;

		if(rota.equals("")) {
			endpoint = "http://localhost:3000/api/notificacao";
		}
		else {
			endpoint = "http://localhost:3000/api/notificacao" + "/" + rota;
		}

		URL obj = new URL(endpoint);
		String body = "{ \"caminho\": \"" + caminho + "\",  \"loja\": " + loja + ", \"ids\": "  + "\"" + ids + "\"" + "}";
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("content-type", "application/json");
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(body.getBytes());
		os.flush();
		os.close();

		String inputLine;
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		StringBuilder response = new StringBuilder();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}

		in.close();
		System.out.println(response);
	}
}