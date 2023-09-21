package student.example.orm.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import student.example.orm.entities.DummyEntity;
import student.example.orm.entities.Entity;

public class DumyRepository extends Repository {






   

    @Override
    public Entity read(int id) {
        
        Statement st;
        
        Entity entity = null;
        try {
             st = conn.createStatement();
            ResultSet rs= st.executeQuery("SELECT *FROM dummy_entity entity_id="+ id);
            rs.next();
            entity =  new DummyEntity(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4)
                );
            
            } catch (SQLException e) {
               
            e.printStackTrace();
        

            if (entity == null) {
                 entity = super.read(id);
            
            }
           
        }
        return entity;
        
    }

    @Override
    public void update(Entity entity) {
        
        super.update(entity);

        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate("UPDATE Entity SET createdat ='"+ entity.getCreatedAt()+  "' WHERE id = " +entity.getId());
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    
    }
    


    @Override
    public void delete(Entity entity) {
        
        super.delete(entity);

        DummyEntity dummyEntity = (DummyEntity)entity;
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate("DELETE FROM DummyEntity WHERE entity_id ="+ dummyEntity.getId());
        } catch (SQLException e) {
            
            e.printStackTrace();
        }
    }

   

    
    

    
    
    
}


