package student.example.orm.repositories;

import java.sql.SQLException;
import java.sql.Statement;

import student.example.orm.entities.DummyEntity;
import student.example.orm.entities.Entity;

public class DumyRepository extends Repository {

    @Override
    public void create(Entity entity) {
        
        super.create(entity);

        Statement st;
        DummyEntity dummyEntity = (DummyEntity)entity;
        try {
            st = conn.createStatement();
            st.executeUpdate("INSERT into DummyEntity VALUES("+ entity.getId()+ entity.getCreatedAt()+dummyEntity.getTestValue()+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Entity entity) {
        
        super.delete(entity);
    }

    @Override
    public Entity read(int id) {
        
        return super.read(id);
    }

    @Override
    public void update(Entity entity) {
        
        super.update(entity);
    }
    

    
    
    
}
