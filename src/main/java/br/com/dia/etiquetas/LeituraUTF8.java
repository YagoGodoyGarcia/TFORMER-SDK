package br.com.dia.etiquetas;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
public class LeituraUTF8 {
	public LeituraUTF8() throws IOException {
        BufferedReader myBuffer = new BufferedReader(new InputStreamReader(
        	    new FileInputStream("C:\\Users\\srvtag002br\\Documents\\arquivobug.txt"), StandardCharsets.UTF_8));
         
        String linha = myBuffer.readLine();
 
        while (linha != null) {
            linha = myBuffer.readLine();
            System.out.println(linha);
        }
 
        myBuffer.close();
	}
}
