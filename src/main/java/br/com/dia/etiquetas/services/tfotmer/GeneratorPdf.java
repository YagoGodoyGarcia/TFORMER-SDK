package br.com.dia.etiquetas.services.tfotmer;

import com.tecit.TFORMer.*; // Licensing, Error handling, Enumerations
import com.tecit.TFORMer.Repository.*; // Provides access to Repository elements 
import com.tecit.TFORMer.Printing.*; // Contains functionality for a Print-Job
import com.tecit.TFORMer.Enumerations.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
/**

 * @author Yago Garcia

 */
public class GeneratorPdf {

	public GeneratorPdf(JSONArray json, String tipo) throws TFormerException {
		if (json.length() != 0) {
			Etiquetar(json, tipo);
		}
	}

	public void Etiquetar(JSONArray json, String tipo) throws TFormerException {
		try {
			// Create a new Job instance
			Job printJob = new Job();
			JSONObject dados = new JSONObject(String.valueOf(json.get(0)));
			dados = (JSONObject) dados.get("Etiqueta");
			int loja = dados.getInt("Loja");

			// Select the stand-alone FormLayout
			String tff = dados.getString("Tff");
			printJob.setRepositoryName("C:/Users/yagog/Downloads/tff yago/tff yago/PADRAO/" + tff);

			// Create a new JobData instance.
			// Here we are using the JobDataRecordSet which accepts Records containing
			// name/value pairs
			JobDataRecordSet jobData = new JobDataRecordSet();
			JobDataRecordSet.Record record;

			if (tipo.equals("etiquetaUmQuadranteNormalOferta") || tipo.equals("etiquetaUmQuadranteClubDia")
					|| tipo.equals("etiquetaTresQuadranteNormalOferta")
					|| tipo.equals("etiquetaTresQuadranteClubDia")) {

				for (int i = 0; i < json.length(); i++) {
					JSONObject produto = new JSONObject(String.valueOf(json.get(i)));
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
					// Add the Record to the JobData
					jobData.add(record);

				}

			} else if (tipo.equals("tresQuadranteComboPorcMaisUnidade")
					|| tipo.equals("tresQuadranteComboPorcUmaUnidade")
					|| tipo.equals("etiquetaUmQuadranteComboPorcMaisUnidade")
					|| tipo.equals("etiquetaUmQuadranteComboPorcUmaUnidade")) {

				for (int i = 0; i < json.length(); i++) {
					JSONObject produto = new JSONObject(String.valueOf(json.get(i)));
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
					record.setData("Unidade", produto.getString("Unidade"));
					record.setData("Porcento", produto.getString("Porcento"));
					record.setData("Fecha_Fin_Promocion_China", produto.getString(" Fecha_Fin_Promocion_China"));
					// Add the Record to the JobData
					jobData.add(record);

				}

			} else if (tipo.equals("etiquetaUmQuadranteComboLevePague")
					|| tipo.equals("etiquetaTresQuadranteComboLevePague")) {

				for (int i = 0; i < json.length(); i++) {
					JSONObject produto = new JSONObject(String.valueOf(json.get(i)));
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
					record.setData("Pague", produto.getString("Pague"));
					record.setData("Leve", produto.getString("Leve"));
					record.setData("Fecha_Fin_Promocion_China", produto.getString(" Fecha_Fin_Promocion_China"));
					// Add the Record to the JobData
					jobData.add(record);

				}

			} else if (tipo.equals("etiquetaUmQuadranteComboAPartir")
					|| tipo.equals("etiquetaTresQuadranteComboAPartir")) {

				for (int i = 0; i < json.length(); i++) {
					JSONObject produto = new JSONObject(String.valueOf(json.get(i)));
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
					record.setData("Unidade", produto.getString("Unidade"));
					record.setData("Fecha_Fin_Promocion_China", produto.getString(" Fecha_Fin_Promocion_China"));
					// Add the Record to the JobData
					jobData.add(record);

				}

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
			String caminho = "C:.../" + +loja + "" + dia + "" + mes + "" + ano + "" + horas + "" + minutos + ""
					+ segundos + ".pdf";
			// Select PDF output to /temp/out.pdf
			printJob.setOutputName(caminho);
			printJob.setJobData(jobData);

			printJob.setPrinterType(EPrinterType.PDFFile);
			// Generate PDF
			printJob.print();

			ChamadaKaizala(caminho, loja);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void ChamadaKaizala(String caminho, int loja) throws IOException {
		URL obj = new URL("CAMINHO DA API KAIZALA");

		String body = "{ \"caminho\": " + caminho + ",  \"loja\": " + loja + "}";

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
