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
	
	@RequestMapping(value="/api/produtos", method= RequestMethod.POST)
    @ResponseBody
    public String CriandoSala(@RequestBody String json) throws TFormerException, JSONException{
		JSONObject etiquetas = new JSONObject(json);
		JSONArray teste = new JSONArray(String.valueOf(etiquetas.get("etiquetaUmQuadranteNormalOferta")));
		GeneratorPdf a = new GeneratorPdf(teste, "etiquetaUmQuadranteNormalOferta");
//		System.out.println(json);
//		JSONArray etiquetaUmQuadranteNormaOferta = new JSONArray(json);
//		System.out.println(my_obj);
//		
//		JSONObject teste = new JSONObject(String.valueOf(my_obj.get(0).toString()));
//		System.out.println(teste);
//		String titulo = teste.getString("a");
//		System.out.println(titulo);
		return null;
				//my_obj[].getString("a");

    }

}
