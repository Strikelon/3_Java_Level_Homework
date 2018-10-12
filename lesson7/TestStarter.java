package hmJava3.hm7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestStarter {

    public static void start(Class c) throws InstantiationException, IllegalAccessException, InvocationTargetException {

        Method[] methods = c.getDeclaredMethods();

        boolean isBefore = false;
        boolean isAfter = false;
        boolean isTest = false;

        Method beforeMethod = null;
        Method afterMethod = null;
        ArrayList<Method> testMethodList = new ArrayList<>();

        for(Method o : methods){
            if(o.isAnnotationPresent(BeforeSuite.class)){
                if(isBefore){
                    throw new RuntimeException();
                }else {
                    isBefore = true;
                    beforeMethod = o;
                }
            }else if(o.isAnnotationPresent(AfterSuite.class)){
                if(isAfter){
                    throw new RuntimeException();
                }else {
                    isAfter = true;
                    afterMethod = o;
                }
            }else if(o.isAnnotationPresent(Test.class)){
                if(!isTest){
                    isTest = true;
                }
                testMethodList.add(o);
            }
        }

        sortTestMethodsPriority(testMethodList);

        if(isBefore){
            beforeMethod.invoke(c.newInstance());
        }
        if(isTest){
            for(Method m : testMethodList){
                m.invoke(c.newInstance());
            }
        }
        if(isAfter){
            afterMethod.invoke(c.newInstance());
        }


    }

    public static void sortTestMethodsPriority(ArrayList<Method> list){


        for(int i=list.size()-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(list.get(j).getAnnotation(Test.class).Priority()>list.get(j+1).getAnnotation(Test.class).Priority()){
                    Method tmp = list.get(j);
                    list.set(j,list.get(j+1));
                    list.set(j+1,tmp);
                }
            }
        }
    }


    public static void main(String[] args){
        try {
            TestStarter.start(TestClass1.class);
        }catch (InstantiationException | IllegalAccessException | InvocationTargetException e){
            e.printStackTrace();
        }
    }

}
