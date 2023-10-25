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
public class AnimalDAO extends DAO {
    private static AnimalDAO instance;
    
    private AnimalDAO(){
        getConnection();
        createTable();
    }
    
    //isso é pra ter sempre só no máximo 1 instancia da classe, vulgo sinleton
    public static AnimalDAO getInstance(){
        return (instance==null?(instance = new AnimalDAO()):instance);
    }
    
    public Animal create(String nome, Integer anoNascimento, String sexo, Integer idEspecie, Integer idCliente){
        try{
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO animal (nome, anoNasc, sexo, id_especie, id_cliente) VALUES (?,?,?,?,?)");
            stmt.setString(1,nome);
            stmt.setInt(2, anoNascimento);
            stmt.setString(3,sexo);
            stmt.setInt(4,idEspecie);
            stmt.setInt(5, idCliente);
            executeUpdate(stmt);
        } catch (SQLException ex) {
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("animal","id"));
    }
    
    //pra criar um objeto 'Animal' automatico com algum resultSet
    private Animal buildObject(ResultSet rs){
        Animal animal = null;
        try{
            animal = new Animal(rs.getInt("id"), rs.getString("nome"), rs.getInt("anoNasc"), rs.getString("sexo"), rs.getInt("id_especie"),rs.getInt("id_cliente"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return animal;
    }
    
    //Select generico
    public List retrieve(String query){
        List<Animal> animais = new ArrayList();
        ResultSet rs = getResultSet(query);
        try{
            while(rs.next()) {
                animais.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return animais;
    }
    
    //select todos
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM animal");
    }
    
    //select o ultimo
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM animal WHERE id = " + lastId("animal", "id"));
    }
    
    //select pelo id
    public Animal retrieveById(int id){
        List<Animal> animais = this.retrieve("SELECT * FROM animal WHERE id = " + id);
        return (animais.isEmpty() ? null : animais.get(0));
    }
    
    //select por nome parecido
    public List retrieveBySimilarName(String nome){
        return this.retrieve("SELECT * FROM animal WHERE nome LIKE '%" + nome + "%'");
    }
    
    //Update
    public void update(Animal animal){
        try{
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE animal SET nome=?, anoNasc=?, sexo=?, id_especie=?, id_cliente=? WHERE id=?");
            stmt.setString(1, animal.getNome());
            stmt.setInt(2, animal.getAnoNascimento());
            stmt.setString(3, animal.getSexo());
            stmt.setInt(4, animal.getIdEspecie());
            stmt.setInt(5, animal.getIdCliente());
            stmt.setInt(6, animal.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    //Delete
    public void delete(Animal animal){
        PreparedStatement stmt;
        try{
           stmt = DAO.getConnection().prepareStatement("DELETE FROM animal WHERE id = ?");
            stmt.setInt(1, animal.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    public void deleteAll(){
        PreparedStatement stmt;
        try{
           stmt = DAO.getConnection().prepareStatement("DELETE FROM animal");
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
