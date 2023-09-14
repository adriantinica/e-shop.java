package student.example.orm.entities;

public class DummyEntity extends Entity {
    private String testValue;

    public DummyEntity(int id, String createdAt, String testValue) {
        super(id, createdAt);
        this.testValue = testValue;
    }

    public String getTestValue() {
        return testValue;
    }

    public void setTestValue(String testValue) {
        this.testValue = testValue;
    }

    @Override
    public String toString() {
        return super.toString()+" DummyEntity [testValue=" + testValue + "]";
    }

    


}
