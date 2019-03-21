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
			etiquetas =  new JSONObject(String.valueOf(etiquetas.get(("Etiquetas"))));

			JSONArray produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteNormalOferta")));
			GeneratorPdf etiquetaUmQuadranteNormalOferta = new GeneratorPdf(produtos,
					"etiquetaUmQuadranteNormalOferta");

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteClubDia")));
			GeneratorPdf etiquetaUmQuadranteClubDia = new GeneratorPdf(produtos, "etiquetaUmQuadranteClubDia");

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteComboLevePague")));
			GeneratorPdf etiquetaUmQuadranteComboLevePague = new GeneratorPdf(produtos,
					"etiquetaUmQuadranteComboLevePague");

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteComboAPartir")));
			GeneratorPdf etiquetaUmQuadranteComboAPartir = new GeneratorPdf(produtos,
					"etiquetaUmQuadranteComboAPartir");

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteComboPorcUmaUnidade")));
			GeneratorPdf etiquetaUmQuadranteComboPorcUmaUnidade = new GeneratorPdf(produtos,
					"etiquetaUmQuadranteComboPorcUmaUnidade");

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteComboPorcMaisUnidade")));
			GeneratorPdf etiquetaUmQuadranteComboPorcMaisUnidade = new GeneratorPdf(produtos,
					"etiquetaUmQuadranteComboPorcMaisUnidade");

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaTresQuadranteNormalOferta")));
			GeneratorPdf etiquetaTresQuadranteNormalOferta = new GeneratorPdf(produtos,
					"etiquetaTresQuadranteNormalOferta");

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaTresQuadranteClubDia")));
			GeneratorPdf etiquetaTresQuadranteClubDia = new GeneratorPdf(produtos, "etiquetaTresQuadranteClubDia");

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaTresQuadranteComboLevePague")));
			GeneratorPdf etiquetaTresQuadranteComboLevePague = new GeneratorPdf(produtos,
					"etiquetaTresQuadranteComboLevePague");

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaTresQuadranteNormalOferta")));
			GeneratorPdf etiquetaTresQuadranteComboAPartir = new GeneratorPdf(produtos,
					"etiquetaTresQuadranteComboAPartir");

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaTresQuadranteComboPorcUmaUnidade")));
			GeneratorPdf etiquetaTresQuadranteComboPorcUmaUnidade = new GeneratorPdf(produtos,
					"etiquetaTresQuadranteComboPorcUmaUnidade");

			produtos = new JSONArray(String.valueOf(etiquetas.get("etiquetaTresQuadranteComboPorcMaisUnidade")));
			GeneratorPdf etiquetaTresQuadranteComboPorcMaisUnidade = new GeneratorPdf(produtos,
					"etiquetaTresQuadranteComboPorcMaisUnidade");

			return "Ok";
		} catch (Exception e) {
			return "Error: " + e;
		}
	}
}
