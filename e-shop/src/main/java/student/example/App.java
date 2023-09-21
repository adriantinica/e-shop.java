package student.example;


import student.example.orm.entities.DummyEntity;
import student.example.orm.repositories.DummyRepository;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        DummyRepository dr = new DummyRepository();
        //dr.create(new DummyEntity(1009, " 12-08-1999", "Test Value 9","Test value 9.1"));
        DummyEntity de = (DummyEntity)dr.read(1005,"student.example.orm.entities.DummyEntity");
        System.out.println(de);

        





    }
}
