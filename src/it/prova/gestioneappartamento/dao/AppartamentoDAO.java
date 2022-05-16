package it.prova.gestioneappartamento.dao;

import java.sql.Connection;
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

		if (appartamentoInput == null)
			throw new RuntimeException("Impossibile inserire l' appartamento: Appartamento mancante!");

		int result = 0;
		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(
						"INSERT INTO appartamento (quartiere, metriquadri, prezzo, data_costruzione) VALUES (?, ?, ?, ?);")) {

			ps.setString(1, appartamentoInput.getQuartiere());
			ps.setInt(2, appartamentoInput.getMetriQuadri());
			ps.setInt(3, appartamentoInput.getPrezzo());
			ps.setDate(4, new java.sql.Date(appartamentoInput.getDataCostruzione().getTime()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// rilancio in modo tale da avvertire il chiamante
			throw new RuntimeException(e);
		}
		return result;
	}

	public int update(Appartamento appartamentoInput) {
		if (appartamentoInput == null || appartamentoInput.getId() < 1) {
			return 0;
		}

		int result = 0;
		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(
						"UPDATE appartamento a SET a.quartiere = ?, a.metriquadri = ?, a.prezzo = ?, a.data_costruzione = ? WHERE a.id = ?;")) {

			ps.setString(1, appartamentoInput.getQuartiere());
			ps.setInt(2, appartamentoInput.getMetriQuadri());
			ps.setInt(3, appartamentoInput.getPrezzo());
			ps.setDate(4, new java.sql.Date(appartamentoInput.getDataCostruzione().getTime()));
			ps.setLong(5, appartamentoInput.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// rilancio in modo tale da avvertire il chiamante
			throw new RuntimeException(e);
		}
		return result;
	}

	public int delete(Appartamento appartamentoInput) {
		if (appartamentoInput == null || appartamentoInput.getId() < 1) {
			return 0;
		}

		int result = 0;
		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("DELETE FROM appartamento a WHERE a.id = ?;")) {

			ps.setLong(1, appartamentoInput.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			// rilancio in modo tale da avvertire il chiamante
			throw new RuntimeException(e);
		}
		return result;
	}

	public Appartamento findByID(Long idAppartamento) {
		Appartamento result = new Appartamento();

		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement("SELECT * FROM appartamento a WHERE a.id = ?;")) {

			ps.setLong(1, idAppartamento);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result.setId(rs.getLong("a.id"));
					result.setQuartiere(rs.getString("a.quartiere"));
					result.setMetriQuadri(rs.getInt("a.metriquadri"));
					result.setPrezzo(rs.getInt("a.prezzo"));
					result.setDataCostruzione(rs.getDate("a.data_costruzione"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// rilancio in modo tale da avvertire il chiamante
			throw new RuntimeException(e);
		}
		return result;
	}

	public List<Appartamento> findByExample(Appartamento example) {
		if (example == null) {
			throw new RuntimeException("Errore: il criterio di ricerca e' vuoto");
		}

		List<Appartamento> result = new ArrayList<Appartamento>();
		String query = "SELECT * FROM appartamento a WHERE ";
		if (example.getQuartiere() != null && example.getQuartiere() != "") {
			query += "a.quartiere = ? AND ";
		}
		if (example.getMetriQuadri() != 0) {
			query += "a.metriquadri = ? AND";
		}
		if (example.getPrezzo() != 0) {
			query += "a.prezzo = ? AND ";
		}
		if (example.getDataCostruzione() != null) {
			query += "a.data_cotruzione";
		}

		query += ";";

		try (Connection c = MyConnection.getConnection();
				PreparedStatement ps = c.prepareStatement(query);
				ResultSet rs = ps.executeQuery()) {

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

}
