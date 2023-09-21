package student.example.orm.repositories;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import student.example.orm.entities.Entity;
import student.example.utils.NamingConventionTransformer;

public abstract class Repository {
    
    //this is an object of type Field
    private final NamingConventionTransformer nct = new NamingConventionTransformer();
    private final String url = "jdbc:postgresql://localhost:5433/e_shop?user=postgres&password=laser112&ssl=false";
    protected Connection conn;

    public Repository(){
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // this is an object of type Method
    //                      |
    //                      v
    public void create (Entity entity){

        Statement st;

        //using REFLECTION API
        Class<?> entityClass =  entity.getClass();
        String entityClassName = entityClass.getName();
        String [] parts = entityClassName.split("\\.");
        entityClassName = parts[parts.length-1];
        try {

            //using REFLECTION API
            // 1. get the entity class name 
            System.out.println("class name: "+entityClassName);

            st = conn.createStatement();
            st.executeUpdate("INSERT into "+nct.pascalToSnake("Entity")+" VALUES("+entity.getId()+",'"+ entityClassName +"','"+ entity.getCreatedAt()+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        
        

        
        try {
            st = conn.createStatement();
            String sql = ("INSERT into "+ nct.pascalToSnake(entityClassName)+" VALUES("+ entity.getId());

            Method[] methods = entityClass.getDeclaredMethods();
            for (Method method : methods) {
             if (method.getName().startsWith("get")) {
               if (method.getReturnType().getName().equals("java.lang.String")) {
                 sql += ",'"+ method.invoke(entity)+ "'";
                } else{
                 sql += ","+ method.invoke(entity)+ "";
                }
              }
            }  
            
            sql += ")";
            st.executeUpdate(sql);
         
        } catch (SQLException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        
    }
    
    // to be continued:
    public Entity read (int id, String fqEntityClassName ){
        Entity entity = null;
        String [] parts = fqEntityClassName.split("\\.");
        String entityClassName = parts[parts.length-1];
        
        try {
            
            Statement st;
            st= conn.createStatement();
            
            String sql = "SELECT *  FROM " + nct.pascalToSnake(entityClassName)+ " JOIN " + 
            " " + nct.pascalToSnake("Entity")+ " ON " 
                + nct.pascalToSnake(entityClassName) + ".entity_id= " 
                + nct.pascalToSnake("Entity")+ ".id WHERE " 
                + nct.pascalToSnake("Entity")+".id =  " + id;
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int size = rsmd.getColumnCount();
            
            // 1: invoke class Constructor
            Class entityClass;
            try {
                entityClass = Class.forName(fqEntityClassName);
                Constructor defaultConstrucor = entityClass.getConstructor();
                entity = (Entity)defaultConstrucor.newInstance();
                //loop through columns use setters:
                for(int i=1; i<= size; i++){
                    String columnName = nct.snakeToPascal(rsmd.getColumnName(i));
                    String setterName = "set" + columnName;
                    //System.out.println(columnName+": " +  rs.getObject(i) + " " + rsmd.getColumnTypeName(i));
                    if(!(columnName.equals("Discriminator") || columnName.equals("EntityId")) ){
                        String typeName = rsmd.getColumnTypeName(i);
                        Class type;

                        if(typeName.equals("int4")) {
                            type=int.class;
                            } else {
                             type = String.class;
                        }

                        Method method = entityClass.getMethod(setterName, type);
                        method.invoke(entity, rs.getObject(i) );
                    }
                }


            } catch (ClassNotFoundException | NoSuchMethodException | SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InstantiationException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            

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
