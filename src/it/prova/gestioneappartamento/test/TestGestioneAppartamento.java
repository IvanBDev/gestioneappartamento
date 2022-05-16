package it.prova.gestioneappartamento.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import it.prova.gestioneappartamento.dao.AppartamentoDAO;
import it.prova.gestioneappartamento.model.Appartamento;

public class TestGestioneAppartamento {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AppartamentoDAO appartamentoDAOInstance = new AppartamentoDAO();
		
		testInserimentoAppartamento(appartamentoDAOInstance);

	}
	
	private static void testInserimentoAppartamento(AppartamentoDAO appartamentoDAOInstance) {
		System.out.println("--------------------- Inizio Test Inserimento Appartamenti ----------------------------------------");
		
		Date dataInputDaStringADate = null;
		try {
			dataInputDaStringADate = new SimpleDateFormat("dd/MM/yyyy").parse("10/05/2015");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int appartamentiPresenti = appartamentoDAOInstance.insert(new Appartamento("San Marco", 77, 650, dataInputDaStringADate ));
		
		if(appartamentiPresenti < 1) {
			throw new RuntimeException("testInserimentoNegozio : FAILED");
		}
		
		System.out.println("--------------------- Fine Test Inserimento Appartamenti ----------------------------------------");
	}

}
