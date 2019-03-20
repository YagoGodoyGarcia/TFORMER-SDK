package br.com.dia.etiquetas.services;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;



/**

 * @author Yago Garcia

 */

@Controller
public class RootPage {
	 @RequestMapping(value="/")
	    @ResponseBody
	    public String startWebService(){
	        return "Bem-Vindo gerador de etiquetas Dia!";
	 }
}
