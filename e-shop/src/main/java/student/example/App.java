package student.example;

import student.example.orm.entities.DummyEntity;

import student.example.orm.repositories.DumyRepository;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        DumyRepository dr = new DumyRepository();
        dr.create(new DummyEntity(1001, " 12-08-1995", "Test Value 1"));
        







    }
}
