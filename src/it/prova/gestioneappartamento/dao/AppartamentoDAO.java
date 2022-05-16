package it.prova.gestioneappartamento.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.prova.gestioneappartamento.connection.MyConnection;
import it.prova.gestioneappartamento.model.Appartamento;

public class AppartamentoDAO {
	public List<Appartamento> list() {

		List<Appartamento> result = new ArrayList<Appartamento>();

		try (Connection c = MyConnection.getConnection();
				Statement s = c.createStatement();
				// STRATEGIA EAGER FETCHING
				ResultSet rs = s.executeQuery("SELECT * FROM appartamento a;")) {

			while (rs.next()) {
				Appartamento appartamentoTemp = new Appartamento();
				appartamentoTemp.setId(rs.getLong("a.id"));
				appartamentoTemp.setQuartiere(rs.getString("a.quartiere"));
				appartamentoTemp.setPrezzo(rs.getInt("a.metriQuadri"));
				appartamentoTemp.setDataCostruzione(rs.getDate("a.data_costruzione"));

				result.add(appartamentoTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// rilancio in modo tale da avvertire il chiamante
			throw new RuntimeException(e);
		}
		return result;
	}

	public int insert(Appartamento appartamentoInput) {

		/*if (appartamentoInput.getId() < 1)
			throw new RuntimeException("Impossibile inserire Articolo: Negozio mancante!");*/

		int result = 0;
		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c
						.prepareStatement("INSERT INTO appartamento (quartiere, metriquadri, prezzo, data_costruzione) VALUES (?, ?, ?, ?);")) {

			ps.setString(1, appartamentoInput.getQuartiere());
			ps.setInt(2, appartamentoInput.getMetriQuadri());
			ps.setInt(3, appartamentoInput.getPrezzo());
			ps.setDate(4,new java.sql.Date(appartamentoInput.getDataCostruzione().getTime()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// rilancio in modo tale da avvertire il chiamante
			throw new RuntimeException(e);
		}
		return result;
	}

}
