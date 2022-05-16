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
		
		//testInserimentoAppartamento(appartamentoDAOInstance);
		
		//testUpdateAppartamento(appartamentoDAOInstance);
		
		testDeleteAppartamento(appartamentoDAOInstance);

	}
	
	private static void testInserimentoAppartamento(AppartamentoDAO appartamentoDAOInstance) {
		System.out.println("--------------------- Inizio Test Inserimento Appartamenti ----------------------------------------");
		
		Date dataInputDaStringADate = null;
		try {
			dataInputDaStringADate = new SimpleDateFormat("dd/MM/yyyy").parse("14/09/2020");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int appartamentiPresenti = appartamentoDAOInstance.insert(new Appartamento(3L, "Girandola", 80, 875, dataInputDaStringADate));
		
		if(appartamentiPresenti < 1) {
			throw new RuntimeException("testInserimentoNegozio : FAILED");
		}
		
		System.out.println("--------------------- Fine Test Inserimento Appartamenti ----------------------------------------");
	}
	
	private static void testUpdateAppartamento(AppartamentoDAO appartamentoDAOInstance) {
		System.out.println("--------------------- Inizio Test Update Appartamenti ----------------------------------------");
		
		Date dataInputDaStringADate = null;
		try {
			dataInputDaStringADate = new SimpleDateFormat("dd/MM/yyyy").parse("22/03/2017");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Appartamento updateAppartamento = new Appartamento(2L, "Piazza Domitilla", 55, 650, dataInputDaStringADate);
		
		if(appartamentoDAOInstance.update(updateAppartamento) != 0) {
			System.out.println("Appartamento aggiornato con successo");
		}
		else {
			System.out.println("Si e'verificato un errore");
		}
		
		System.out.println("--------------------- Fine Test Update Appartamenti ----------------------------------------");
	}
	
	private static void testDeleteAppartamento(AppartamentoDAO appartamentoDAOInstance) {
		System.out.println("--------------------- Inizio Test Delete Appartamenti ----------------------------------------");
		
		Date dataInputDaStringADate = null;
		try {
			dataInputDaStringADate = new SimpleDateFormat("dd/MM/yyyy").parse("14/09/2020");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Appartamento deleteAppartamento = new Appartamento(3L, "Girandola", 80, 875, dataInputDaStringADate);
		
		if(appartamentoDAOInstance.delete(deleteAppartamento) != 0) {
			System.out.println("L'appartamento e'stato eliminato dal DB");
		}
		else {
			System.out.println("Si e' verificato un errore");
		}
		
		System.out.println("--------------------- Fine Test Delete Appartamenti ----------------------------------------");
	}

}
