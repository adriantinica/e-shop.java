package student.example.orm.entities;

public class DummyEntity extends Entity {
    private String testValue;
    private String anotherTestValue;
    

    

    public DummyEntity() {
    }

    public DummyEntity(int id, String createdAt, String testValue, String anotherTestValue) {
        super(id, createdAt);
        this.testValue = testValue;
        this.anotherTestValue = anotherTestValue;
    }

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }
    
    public String getAnotherTestValue() {
        return anotherTestValue;
    }
    
    public void setAnotherTestValue(String anotherTestValue) {
        this.anotherTestValue = anotherTestValue;
    }

    @Override
    public String toString() {
        return super.toString()+" \n DummyEntity [testValue=" + testValue + ", anotherTestValue=" + anotherTestValue + "]";
    }

    
   
    
    
   
}
