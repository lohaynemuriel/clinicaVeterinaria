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
public class EspecieDAO extends DAO {
    private static EspecieDAO instance;
    
    private EspecieDAO(){
        getConnection();
        createTable();
    }
    
    //isso é pra ter sempre só no máximo 1 instancia da classe, vulgo sinleton
    public static EspecieDAO getInstance(){
        return (instance==null?(instance = new EspecieDAO()):instance);
    }
    
    static {
    EspecieDAO dao = EspecieDAO.getInstance();
    if (dao.retrieveAll().isEmpty()) {
        dao.create(1,"Cão");
        dao.create(2,"Gato");
        dao.create(3,"Peixe");
        dao.create(4,"Pássaro");
        dao.create(5,"Hamster");
        dao.create(6,"Porquinho-da-índia");
        dao.create(7,"Coelho");
        dao.create(8,"Tartaruga");
        dao.create(9,"Lagarto");
        dao.create(10,"Serpente");
    }
    
    }

    
   public Especie create(int id, String nome){
    try{
        PreparedStatement stmt;
        stmt = DAO.getConnection().prepareStatement("INSERT INTO especie (id, nome) VALUES (?, ?)");
        stmt.setInt(1, id);
        stmt.setString(2, nome);
        executeUpdate(stmt);
    } catch (SQLException ex) {
        Logger.getLogger(EspecieDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
        return this.retrieveById(id);
    }

    
    //pra criar um objeto 'Especie' automatico com algum resultSet
    private Especie buildObject(ResultSet rs){
        Especie especie = null;
        try{
            especie = new Especie(rs.getInt("id"), rs.getString("nome"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return especie;
    }
    
    //Select generico
    public List retrieve(String query){
        List<Especie> especies = new ArrayList();
        ResultSet rs = getResultSet(query);
        try{
            while(rs.next()) {
                especies.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return especies;
    }
    
    //select todos
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM especie");
    }
    
    //select o ultimo
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM especie WHERE id = " + lastId("especie", "id"));
    }
    
    //select pelo id
    public Especie retrieveById(int id){
        List<Especie> especies = this.retrieve("SELECT * FROM especie WHERE id = " + id);
        return (especies.isEmpty() ? null : especies.get(0));
    }
    
    //select por nome parecido
    public List retrieveBySimilarName(String nome){
        return this.retrieve("SELECT * FROM especie WHERE nome LIKE '%" + nome + "%'");
    }
    
    //Update
    public void update(Especie especie){
        try{
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE especie SET nome=? WHERE id=?");
            stmt.setString(1, especie.getNome());
            stmt.setInt(2, especie.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    //Delete
    public void delete(Especie especie){
        PreparedStatement stmt;
        try{
           stmt = DAO.getConnection().prepareStatement("DELETE FROM especie WHERE id = ?");
            stmt.setInt(1, especie.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    public void deleteAll(){
        PreparedStatement stmt;
        try{
           stmt = DAO.getConnection().prepareStatement("DELETE FROM especie");
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
