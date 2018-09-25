package java3;

import java.util.ArrayList;

public class Box <F extends Fruit>{

    private ArrayList<F> box;

    public Box(){
        box = new ArrayList<>();
    }

    public void addFruit(F fruit){
        box.add(fruit);
    }

    public void removeFruit(){
        box.remove(box.size()-1);
    }

    public void clearBox(){
        box.clear();
    }

    public float getWeight(){
        float boxWeight=0;

        for(F fruit : box){
            boxWeight+=fruit.getWEIGHT();
        }

        return boxWeight;
    }

    public boolean compare(Box<? extends Fruit> box2){
        return this.getWeight()==box2.getWeight();
    }

    public void transferBoxTo(Box<F> box2){
        for(F fruit : box){
            box2.addFruit(fruit);
        }
        clearBox();
    }


    //Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
    //Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
    //Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются
    //объекты, которые были в первой;



}
