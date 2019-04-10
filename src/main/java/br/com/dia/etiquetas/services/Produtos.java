package br.com.dia.etiquetas.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecit.TFORMer.TFormerException;

import org.json.JSONException;
import org.json.JSONObject;

import org.json.JSONArray;

import br.com.dia.etiquetas.services.tfotmer.GeneratorPdf;

@Controller
public class Produtos {

	@RequestMapping(value = "/api/produtos", method = RequestMethod.POST)
	@ResponseBody
	public String CriandoSala(@RequestBody String json) throws TFormerException, JSONException {
		JSONArray body = new JSONArray();
		try {
			JSONObject etiquetas = new JSONObject(json);
			String rota = (String) etiquetas.get("Rota");
			GeneratorPdf gerador = new GeneratorPdf();
					
			etiquetas =  new JSONObject(String.valueOf(etiquetas.get(("Etiquetas"))));
			
			JSONArray produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteNormalOferta")));
			body.put(gerador.Pdf(produtos, "etiquetaUmQuadranteNormalOferta", rota));

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteClubDia")));
			body.put(gerador.Pdf(produtos, "etiquetaUmQuadranteClubDia", rota));	
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteComboLevePague")));
			body.put(gerador.Pdf(produtos, "etiquetaUmQuadranteComboLevePague", rota));	

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteComboAPartir")));
			body.put(gerador.Pdf(produtos, "etiquetaUmQuadranteComboAPartir", rota));
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteComboPorcUmaUnidade")));
			body.put(gerador.Pdf(produtos, "etiquetaUmQuadranteComboPorcUmaUnidade", rota));
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteComboPorcMaisUnidade")));
			body.put(gerador.Pdf(produtos, "etiquetaUmQuadranteComboPorcMaisUnidade", rota));
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaTresQuadranteNormalOferta")));
			body.put(gerador.Pdf(produtos, "etiquetaTresQuadranteNormalOferta", rota));
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaTresQuadranteClubDia")));
			body.put(gerador.Pdf(produtos, "etiquetaTresQuadranteClubDia", rota));
			/******** AQUI PARA ************/
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaTresQuadranteComboLevePague")));
			body.put(gerador.Pdf(produtos, "etiquetaTresQuadranteComboLevePague", rota));
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaTresQuadranteComboAPartir")));
			body.put(gerador.Pdf(produtos, "etiquetaTresQuadranteComboAPartir", rota));
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaTresQuadranteComboPorcUmaUnidade")));
			body.put(gerador.Pdf(produtos, "etiquetaTresQuadranteComboPorcUmaUnidade", rota));
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaTresQuadranteComboPorcMaisUnidade")));			
			body.put(gerador.Pdf(produtos, "etiquetaTresQuadranteComboPorcMaisUnidade", rota));
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaA5")));			
			body.put(gerador.Pdf(produtos, "etiquetaA5", rota));
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaA5ClubDia")));			
			body.put(gerador.Pdf(produtos, "etiquetaA5ClubDia", rota));
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaA5ComboLevePague")));			
			body.put(gerador.Pdf(produtos, "etiquetaA5ComboLevePague", rota));
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaA5ComboAPartir")));			
			body.put(gerador.Pdf(produtos, "etiquetaA5ComboAPartir", rota));
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaA5ComboPorcUmaUnidade")));			
			body.put(gerador.Pdf(produtos, "etiquetaA5ComboPorcUmaUnidade", rota));
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaA5ComboPorcMaisUnidade")));			
			body.put(gerador.Pdf(produtos, "etiquetaA5ComboPorcMaisUnidade", rota));

			
			return "Ok";
		} catch (Exception e) {
			return "Error: " + e;
		}
	}
		
}
