package java3;

public class Apple extends Fruit{

    private final float WEIGHT;

    public Apple(){
        WEIGHT=1.0f;
    }

    @Override
    public float getWEIGHT(){
        return WEIGHT;
    }

}
