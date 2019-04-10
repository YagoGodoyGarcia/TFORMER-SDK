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
		try {
			JSONObject etiquetas = new JSONObject(json);
			String rota = (String) etiquetas.get("Rota");
			
			etiquetas =  new JSONObject(String.valueOf(etiquetas.get(("Etiquetas"))));
			
			JSONArray produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteNormalOferta")));
			GeneratorPdf etiquetaUmQuadranteNormalOferta = new GeneratorPdf(produtos, "etiquetaUmQuadranteNormalOferta", rota);

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteClubDia")));
			GeneratorPdf etiquetaUmQuadranteClubDia = new GeneratorPdf(produtos, "etiquetaUmQuadranteClubDia", rota);

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteComboLevePague")));
			GeneratorPdf etiquetaUmQuadranteComboLevePague = new GeneratorPdf(produtos, "etiquetaUmQuadranteComboLevePague", rota);

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteComboAPartir")));
			GeneratorPdf etiquetaUmQuadranteComboAPartir = new GeneratorPdf(produtos, "etiquetaUmQuadranteComboAPartir", rota);

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteComboPorcUmaUnidade")));
			GeneratorPdf etiquetaUmQuadranteComboPorcUmaUnidade = new GeneratorPdf(produtos, "etiquetaUmQuadranteComboPorcUmaUnidade", rota);

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteComboPorcMaisUnidade")));
			GeneratorPdf etiquetaUmQuadranteComboPorcMaisUnidade = new GeneratorPdf(produtos, "etiquetaUmQuadranteComboPorcMaisUnidade", rota);

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaTresQuadranteNormalOferta")));
			GeneratorPdf etiquetaTresQuadranteNormalOferta = new GeneratorPdf(produtos, "etiquetaTresQuadranteNormalOferta", rota);

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaTresQuadranteClubDia")));
			GeneratorPdf etiquetaTresQuadranteClubDia = new GeneratorPdf(produtos, "etiquetaTresQuadranteClubDia", rota);

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaTresQuadranteComboLevePague")));
			GeneratorPdf etiquetaTresQuadranteComboLevePague = new GeneratorPdf(produtos, "etiquetaTresQuadranteComboLevePague", rota);

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaTresQuadranteComboAPartir")));
			GeneratorPdf etiquetaTresQuadranteComboAPartir = new GeneratorPdf(produtos, "etiquetaTresQuadranteComboAPartir", rota);

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaTresQuadranteComboPorcUmaUnidade")));
			GeneratorPdf etiquetaTresQuadranteComboPorcUmaUnidade = new GeneratorPdf(produtos, "etiquetaTresQuadranteComboPorcUmaUnidade", rota);

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaTresQuadranteComboPorcMaisUnidade")));
			GeneratorPdf etiquetaTresQuadranteComboPorcMaisUnidade = new GeneratorPdf(produtos, "etiquetaTresQuadranteComboPorcMaisUnidade", rota);
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaA5")));
			GeneratorPdf etiquetaA5 = new GeneratorPdf(produtos, "etiquetaA5", rota);
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaA5ClubDia")));
			GeneratorPdf etiquetaA5ClubDia = new GeneratorPdf(produtos, "etiquetaA5ClubDia", rota);
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaA5ComboLevePague")));
			GeneratorPdf etiquetaA5ComboLevePague = new GeneratorPdf(produtos, "etiquetaA5ComboLevePague", rota);
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaA5ComboAPartir")));
			GeneratorPdf etiquetaA5ComboAPartir = new GeneratorPdf(produtos, "etiquetaA5ComboAPartir", rota);
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaA5ComboPorcUmaUnidade")));
			GeneratorPdf etiquetaA5ComboPorcUmaUnidade = new GeneratorPdf(produtos, "etiquetaA5ComboPorcUmaUnidade", rota);
			
			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaA5ComboPorcMaisUnidade")));
			GeneratorPdf etiquetaA5ComboPorcMaisUnidade = new GeneratorPdf(produtos, "etiquetaA5ComboPorcMaisUnidade", rota);

			return "Ok";
		} catch (Exception e) {
			return "Error: " + e;
		}
	}
}
