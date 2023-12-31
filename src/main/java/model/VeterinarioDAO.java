package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor-Windows
 */
public class VeterinarioDAO extends DAO{
    private static VeterinarioDAO instance;
    
    private VeterinarioDAO(){
        getConnection();
        createTable();
    }
    
    //isso é pra ter sempre só no máximo 1 instancia da classe, vulgo sinleton
    public static VeterinarioDAO getInstance(){
        return (instance==null?(instance = new VeterinarioDAO()):instance);
    }
    
    public Veterinario create(String nome, String email, String telefone){
        try{
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO vet (nome, email, telefone) VALUES (?,?,?)");
            stmt.setString(1,nome);
            stmt.setString(2, email);
            stmt.setString(3, telefone);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(VeterinarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("vet","id"));
    }
    
    //pra criar um objeto 'Veterinario' automatico com algum resultSet
    private Veterinario buildObject(ResultSet rs){
        Veterinario veterinario = null;
        try{
            veterinario = new Veterinario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"), rs.getString("telefone"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return veterinario;
    }
    
    //Select generico
    public List retrieve(String query){
        List<Veterinario> veterinarios = new ArrayList();
        ResultSet rs = getResultSet(query);
        try{
            while(rs.next()) {
                veterinarios.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return veterinarios;
    }
    
    //select todos
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM vet");
    }
    
    //select o ultimo
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM vet WHERE id = " + lastId("vet", "id"));
    }
    
    //select pelo id
    public Veterinario retrieveById(int id){
        List<Veterinario> veterinarios = this.retrieve("SELECT * FROM vet WHERE id = " + id);
        return (veterinarios.isEmpty() ? null : veterinarios.get(0));
    }
    
    //select por nome parecido
    public List retrieveBySimilarName(String nome){
        return this.retrieve("SELECT * FROM vet WHERE nome LIKE '%" + nome + "%'");
    }
    
    //Update
    public void update(Veterinario veterinario){
        try{
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE vet SET nome=?, email=?, telefone=? WHERE id=?");
            stmt.setString(1, veterinario.getNome());
            stmt.setString(2, veterinario.getEmail());
            stmt.setString(3, veterinario.getTelefone());
            stmt.setInt(4, veterinario.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    //Delete
    public void delete(Veterinario veterinario){
        PreparedStatement stmt;
        try{
           stmt = DAO.getConnection().prepareStatement("DELETE FROM vet WHERE id = ?");
            stmt.setInt(1, veterinario.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
