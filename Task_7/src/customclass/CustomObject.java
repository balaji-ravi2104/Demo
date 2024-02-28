package customclass;

public class CustomObject<E> {
    private E object;

    public CustomObject(E obj){
        this.object = obj;
    }

    public void setObject(E obj){
        this.object = obj;
    }

    public E getObject() {
        return object;
    }

    @Override
    public String toString() {
        return "CustomObject{" +
                "Object='" + object + '\'' +
                '}';
    }
}
