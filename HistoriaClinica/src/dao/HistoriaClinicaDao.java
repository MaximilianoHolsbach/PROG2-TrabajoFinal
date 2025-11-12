/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import config.DatabaseConnection;
import java.util.ArrayList;
import java.util.List;
import modelo.GrupoSanguineo;
import modelo.HistoriaClinica;

/**
 *
 * @author Maxi
 */
public class HistoriaClinicaDao implements GenericDao<HistoriaClinica>{
    //public void insertarConTransaccion(HistoriaClinica hc, Connection conn);

    @Override
    public void insertar(HistoriaClinica entidad) throws Exception {
        String sql = "INSERT INTO HistoriaClinica (NroHistoria, GrupoSanguineo, Antecedentes, MedicacionActual, Observaciones, eliminado) VALUES (?, ?, ?, ?, ?, false)";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
            stmt.setString(1, entidad.getNroHistoria());
            stmt.setString(2, entidad.getGrupoSanguineo().name());
            stmt.setString(3, entidad.getAntecedentes());
            stmt.setString(4, entidad.getMedicacionActual());
            stmt.setString(5, entidad.getObservaciones());

            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) entidad.setId(rs.getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException("Error en transacción al insertar historia clínica: " + e.getMessage(), e);
        }

    }

    @Override
    public void actualizar(HistoriaClinica entidad) throws Exception {
        String sql = "UPDATE HistoriaClinica SET NroHistoria=?, GrupoSanguineo=?, Antecedentes=?, MedicacionActual=?, Observaciones=? WHERE ID_HistoriaClinica=? AND eliminado=false";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, entidad.getNroHistoria());
            stmt.setString(2, entidad.getGrupoSanguineo().name());
            stmt.setString(3, entidad.getAntecedentes());
            stmt.setString(4, entidad.getMedicacionActual());
            stmt.setString(5, entidad.getObservaciones());
            stmt.setInt(6, entidad.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar historia clínica: " + e.getMessage());
        }
    }

    @Override
    public void eliminar(int id) throws Exception {
        String sql = "UPDATE HistoriaClinica SET eliminado=true WHERE ID_HistoriaClinica=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar historia clínica: " + e.getMessage());
        }
    }

    @Override
    public void recuperar(int id) throws Exception {
        String sql = "UPDATE HistoriaClinica SET Eliminado = false WHERE ID_HistoriaClinica = ?";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("Error al recuperar historia clínica: " + e.getMessage(), e);
        }
    }

    @Override
    public HistoriaClinica getById(int id) throws Exception {
            String sql = "SELECT * FROM HistoriaClinica WHERE ID_HistoriaClinica = ? AND Eliminado = false";
            try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    return new HistoriaClinica(
                        rs.getString("NroHistoria"),
                        GrupoSanguineo.valueOf(rs.getString("GrupoSanguineo")),
                        rs.getString("Antecedentes"),
                        rs.getString("MedicacionActual"),
                        rs.getString("Observaciones"),
                        rs.getInt("ID_HistoriaClinica"),
                        rs.getBoolean("Eliminado")
                    );
                } else {
            return null;
                }

            } catch (SQLException e) {
                throw new Exception("Error al obtener historia clínica por ID: " + e.getMessage(), e);
            }
    }
    public HistoriaClinica getByNroHistoria(String NroHistoria) throws Exception{
        String sql = "SELECT * FROM HistoriaClinica WHERE NroHistoria = ? AND eliminado = false";
        try (Connection conn = DatabaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setString(1, NroHistoria);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return new HistoriaClinica(
                    rs.getString("NroHistoria"),
                    GrupoSanguineo.valueOf(rs.getString("GrupoSanguineo")),
                    rs.getString("Antecedentes"),
                    rs.getString("MedicacionActual"),
                    rs.getString("Observaciones"),
                    rs.getInt("ID_HistoriaClinica"),
                    rs.getBoolean("Eliminado")
                );
            }else{
                return null;
            }
            
        } catch (Exception e) {
            throw new Exception("Error al obtener historia clínica por NroHistoria: " + e.getMessage(), e);
        }
    }

    @Override
    public List<HistoriaClinica> getAll() throws Exception {
            List<HistoriaClinica> lista = new ArrayList<>();
            String sql = "SELECT * FROM HistoriaClinica WHERE Eliminado = false";

            try (Connection conn = DatabaseConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    HistoriaClinica hc = new HistoriaClinica(
                        rs.getString("NroHistoria"),
                        GrupoSanguineo.valueOf(rs.getString("GrupoSanguineo")),
                        rs.getString("Antecedentes"),
                        rs.getString("MedicacionActual"),
                        rs.getString("Observaciones"),
                        rs.getInt("ID_HistoriaClinica"),
                        rs.getBoolean("Eliminado")
                    );
                    lista.add(hc);
                }

            } catch (SQLException e) {
                throw new Exception("Error al listar historias clínicas: " + e.getMessage(), e);
            }

            return lista;

        }
    
}
