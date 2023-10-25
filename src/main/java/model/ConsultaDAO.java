package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor-Windows
 */
public class ConsultaDAO extends DAO {
    private static ConsultaDAO instance;
    
    private ConsultaDAO(){
        getConnection();
        createTable();
    }
    
    //isso é pra ter sempre só no máximo 1 instancia da classe, vulgo sinleton
    public static ConsultaDAO getInstance(){
        return (instance==null?(instance = new ConsultaDAO()):instance);
    }
    
    //CRUD
    public Consulta create(Calendar data, String horario, String comentario, Integer idAnimal, Integer idVeterinario, Integer terminado){
        try{
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO consulta (data, horario, comentario, id_animal, id_vet, terminado) VALUES (?,?,?,?,?,?)");
            stmt.setDate(1, new java.sql.Date(data.getTimeInMillis()));
            stmt.setString(2, horario);
            stmt.setString(3,comentario);
            stmt.setInt(4,idAnimal);
            stmt.setInt(5, idVeterinario);
            //stmt.setInt(6, idTratamento);
            stmt.setInt(6, terminado);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("consulta","id"));
    }
    
    //pra criar um objeto 'Consulta' automatico com algum resultSet
    private Consulta buildObject(ResultSet rs){
        Consulta consulta = null;
        try{            
            Calendar cal = Calendar.getInstance();
            cal.setTime(rs.getDate("data"));
            consulta = new Consulta(rs.getInt("id"), cal, rs.getString("horario"), rs.getString("comentario"), rs.getInt("id_animal"), rs.getInt("id_vet"), rs.getInt("terminado"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consulta;
    }
    
    //Select generico
    public List retrieve(String query){
        List<Consulta> consultas = new ArrayList();
        ResultSet rs = getResultSet(query);
        try{
            while(rs.next()) {
                consultas.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consultas;
    }
    
    //select todos
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM consulta");
    }
    
    //select o ultimo
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM consulta WHERE id = " + lastId("consulta", "id"));
    }
    
    //select pelo id
    public Consulta retrieveById(int id){
        List<Consulta> consultas = this.retrieve("SELECT * FROM consulta WHERE id = " + id);
        return (consultas.isEmpty() ? null : consultas.get(0));
    }
    
    //select por nome parecido
    /*public List retrieveBySimilarName(String nome){
        return this.retrieve("SELECT * FROM consulta WHERE nome LIKE '%" + nome + "%'");
    }*/
    
    //Update
    public void update(Consulta consulta){
        try{
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE consulta SET data=?, horario=?, comentario=?, id_animal=?, id_vet=?, terminado=? WHERE id=?");
            stmt.setString(1, consulta.getData().toString());
            stmt.setString(2, consulta.getHorario());
            stmt.setString(3, consulta.getComentario());
            stmt.setInt(4, consulta.getIdAnimal());
            stmt.setInt(5, consulta.getIdVeterinario());
            //stmt.setInt(6, consulta.getIdTratamento());
            stmt.setInt(6, consulta.getTerminado());
            stmt.setInt(7, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    //Delete
    public void delete(Consulta consulta){
        PreparedStatement stmt;
        try{
           stmt = DAO.getConnection().prepareStatement("DELETE FROM consulta WHERE id = ?");
            stmt.setInt(1, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
