package student.example.orm.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import student.example.orm.entities.DummyEntity;
import student.example.orm.entities.Entity;

public class DummyRepository extends Repository {






   

    

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
