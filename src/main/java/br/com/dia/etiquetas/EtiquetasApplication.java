package br.com.dia.etiquetas;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**

 * @author Yago Garcia

 */
@SpringBootApplication
public class EtiquetasApplication {

	public static void main(String[] args) throws IOException {
		//LeituraUTF8 leitura= new LeituraUTF8();
		SpringApplication.run(EtiquetasApplication.class, args);
	}

}
