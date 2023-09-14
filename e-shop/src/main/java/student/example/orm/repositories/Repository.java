package student.example.orm.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import student.example.orm.entities.DummyEntity;
import student.example.orm.entities.Entity;

public abstract class Repository {
    
    private final String url = "jdbc:postgresql://localhost:postgres:5432/e_shop?user=postgres&password=Laser112&ssl=false";
    protected Connection conn;

    public Repository(){
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create (Entity entity){
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate("INSERT into Entity VALUES("+"'DummyEntity'"+ entity.getId()+ entity.getCreatedAt()+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    // to be continued:
    public Entity read (int id){
        Statement st;
        Entity entity = null;
        try {
            st = conn.createStatement();
            ResultSet rs= st.executeQuery("SELECT *FROM Entity where id="+ id);
            rs.next();
            entity = (Entity) new DummyEntity(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3));
            
            } catch (SQLException e) {
               
            e.printStackTrace();
        }
        return entity;
    }
    
    public void update (Entity entity){
       
          
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate("UPDATE Entity SET createdat ='"+ entity.getCreatedAt()+  "' WHERE id = " +entity.getId());
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
        
    }
    
    public void delete (Entity entity){
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate("DELETE FROM Entity WHERE id ="+ entity.getId());
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }
    

}

