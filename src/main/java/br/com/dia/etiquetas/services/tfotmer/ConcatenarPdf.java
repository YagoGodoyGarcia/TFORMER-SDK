package br.com.dia.etiquetas.services.tfotmer;

import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfCopyFields;
import com.itextpdf.text.pdf.PdfReader;

public class ConcatenarPdf {
	private String caminho = "C:/ caminho ";
	
	public ConcatenarPdf() {
		ETQ_PEQ_LARANJA();
		ETQ_PEQ_BRANCA();
		ETQ_GRD_LARANJA();
		ETQ_GRD_BRANCA();
	}
	
	public String ETQ_PEQ_LARANJA() {
		String resultado = "";
		try 
		{
			PdfReader reader1 = new PdfReader (this.caminho + "1QCOMBOAPARTIRDE.tff");
			PdfReader reader2 = new PdfReader(this.caminho + "1QCOMBOLEVEMAISPAGUEMENOS.tff"); 
			PdfReader reader3 = new PdfReader(this.caminho + "1QCOMBOPORCENTO.tff"); 
			PdfReader reader4 = new PdfReader (this.caminho + "1QCOMBOPORCENTOMAIORQUE1.tff");
			PdfReader reader5 = new PdfReader(this.caminho + "1QFIDELIZADO.tff"); 
			PdfReader reader6 = new PdfReader(this.caminho + "3QCLUBDIA.tff");
			PdfReader reader7 = new PdfReader(this.caminho + "3QCOMBOAPARTIRDE.tff"); 
			PdfReader reader8 = new PdfReader(this.caminho + "3QCOMBOLEVEMAISPAGUEMENOS.tff"); 
			PdfReader reader9 = new PdfReader (this.caminho + "3QCOMBOPORCENTOMAIORQUE1.tff");
			PdfReader reader10 = new PdfReader(this.caminho + "3QPORCENTO.tff"); 
			
			PdfCopyFields copy;
			copy = new PdfCopyFields(new FileOutputStream(this.caminho + "ETQ_PEQ_LARANJA.pdf"));
	
			 copy.addDocument(reader1); 
			 copy.addDocument(reader2); 
			 copy.addDocument(reader3);
			 copy.addDocument(reader4); 
			 copy.addDocument(reader5); 
			 copy.addDocument(reader6);
			 copy.addDocument(reader7); 
			 copy.addDocument(reader8); 
			 copy.addDocument(reader9);
			 copy.addDocument(reader10);
			 copy.close(); 
			 
			 resultado = "Ok";
		} 
		catch (IOException eio) 
		{
		 eio.printStackTrace();
		} 
		catch (DocumentException ed)
		{
		 ed.printStackTrace();
		}
		
		return resultado;
	}
	
	public String ETQ_PEQ_BRANCA() {
		String resultado = "";
		try 
		{
			PdfReader reader1 = new PdfReader (this.caminho + "1QNAOFIDELIZADO.tff");
			PdfReader reader2 = new PdfReader(this.caminho + "3QNAOFIDELIZADO.tff"); 
			
			PdfCopyFields copy;
			copy = new PdfCopyFields(new FileOutputStream(caminho + "ETQ_PEQ_BRANCA.pdf"));
	
			 copy.addDocument(reader1); 
			 copy.addDocument(reader2); 
			 copy.close(); 
			 
			 resultado = "Ok";
		} 
		catch (IOException eio) 
		{
		 eio.printStackTrace();
		} 
		catch (DocumentException ed)
		{
		 ed.printStackTrace();
		}
		
		return resultado;
	}
	
	public String ETQ_GRD_LARANJA() {
		String resultado = "";
		try 
		{
			PdfReader reader1 = new PdfReader (this.caminho + "A5COMBOAPARTIRDE.tff");
			PdfReader reader2 = new PdfReader(this.caminho + "A5COMBOLEVEMAISPAGUEMENOS.tff");
			PdfReader reader3 = new PdfReader(this.caminho + "A5COMBOPORCENTO.tff");
			PdfReader reader4 = new PdfReader(this.caminho + "A5COMBOPORCENTOMAIORQUE1.tff");
			PdfReader reader5 = new PdfReader(this.caminho + "A5POUPECLUBDIA.tff");
			
			PdfCopyFields copy;
			copy = new PdfCopyFields(new FileOutputStream(caminho + "ETQ_GRD_LARANJA.pdf"));
	
			 copy.addDocument(reader1); 
			 copy.addDocument(reader2);
			 copy.addDocument(reader3); 
			 copy.addDocument(reader4);
			 copy.addDocument(reader5);
			 copy.close(); 
			 
			 resultado = "Ok";
		} 
		catch (IOException eio) 
		{
		 eio.printStackTrace();
		} 
		catch (DocumentException ed)
		{
		 ed.printStackTrace();
		}
		
		return resultado;
	}
	
	public String ETQ_GRD_BRANCA() {
		String resultado = "";
		try 
		{
			PdfReader reader1 = new PdfReader (this.caminho + "1QNAOFIDELIZADO.tff");
			
			PdfCopyFields copy;
			copy = new PdfCopyFields(new FileOutputStream(this.caminho + "ETQ_GRD_BRANCA.pdf"));
	
			 copy.addDocument(reader1); 
			 copy.close(); 
			 
			 resultado = "Ok";
		} 
		catch (IOException eio) 
		{
		 eio.printStackTrace();
		} 
		catch (DocumentException ed)
		{
		 ed.printStackTrace();
		}
		
		return resultado;
	}
}
