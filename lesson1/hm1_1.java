package java3;

import java.util.ArrayList;
import java.util.Arrays;

public class hm1_1 {
    public static void main(String[] args){

        // Проверка первого задания

        System.out.println("Проверка задания №1");

        Integer[] array1 = {0,1,2,3,4,5,6,7,8,9,10};
        exchangeArrayEl(array1,4,8);
        System.out.println(Arrays.toString(array1));
        Boolean[] array2 = {false,false,false,true,true,true};
        exchangeArrayEl(array2,0,5);
        System.out.println(Arrays.toString(array2));
        String[] array3 = {"Ноль","Один","Два","Три","Четыре","Пять"};
        exchangeArrayEl(array3,1,3);
        System.out.println(Arrays.toString(array3));

        // Проверка второго задания

        System.out.println("Проверка задания №2");

        Integer[] array4 = {0,1,2,3,4,5,6,7,8,9,10};
        ArrayList<Integer> list4 = arrayToArrayList(array4);
        System.out.println(list4);
        Boolean[] array5 = {false,false,false,true,true,true};
        ArrayList<Boolean> list5 = arrayToArrayList(array5);
        System.out.println(list5);
        String[] array6 = {"Ноль","Один","Два","Три","Четыре","Пять"};
        ArrayList<String> list6 = arrayToArrayList(array6);
        System.out.println(list6);

        // Проверка третьего задания

        System.out.println("Проверка задания №3");

        Box<Apple> appleBox = new Box<>();
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        System.out.println("Текущий вес коробки с яблоками: "+appleBox.getWeight());
        appleBox.addFruit(new Apple());
        appleBox.addFruit(new Apple());
        System.out.println("Текущий вес коробки с яблоками: "+appleBox.getWeight());
        appleBox.removeFruit();
        System.out.println("Текущий вес коробки с яблоками: "+appleBox.getWeight());

        Box<Orange> orangeBox = new Box<>();
        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());
        orangeBox.addFruit(new Orange());
        System.out.println("Текущий вес коробки с апельсинами: "+orangeBox.getWeight());

        System.out.println("Равный ли коробка с яблоками и апельсинами по весу: "+appleBox.compare(orangeBox));

        orangeBox.addFruit(new Orange());
        System.out.println("Текущий вес коробки с апельсинами: "+orangeBox.getWeight());
        appleBox.addFruit(new Apple());
        System.out.println("Текущий вес коробки с яблоками: "+appleBox.getWeight());
        System.out.println("Равный ли коробка с яблоками и апельсинами по весу: "+appleBox.compare(orangeBox));

        Box<Apple> appleBox2 = new Box<>();
        appleBox2.addFruit(new Apple());
        appleBox2.addFruit(new Apple());
        System.out.println("Текущий вес коробки #2 с яблоками: "+appleBox2.getWeight());

        appleBox.transferBoxTo(appleBox2);

        System.out.println("Текущий вес коробки #2 с яблоками: "+appleBox2.getWeight());
        System.out.println("Текущий вес коробки #1 с яблоками: "+appleBox.getWeight());








    }

    //Написать метод, который меняет два элемента массива местами
    // (массив может быть любого ссылочного типа);

    public static <T> void exchangeArrayEl(T[] array, int index1, int index2){
        T temp;
        temp=array[index1];
        array[index1]=array[index2];
        array[index2]=temp;
    }

    //Написать метод, который преобразует массив в ArrayList;

    public static <T> ArrayList<T> arrayToArrayList(T[] array){

        ArrayList<T> list = new ArrayList<>();

        for(int i=0;i<array.length;i++){
            list.add(array[i]);
        }

        return list;

    }



}
