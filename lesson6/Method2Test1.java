import hmJava3.hm6HomeWork.HomeWork6;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class Method2Test1 {


    HomeWork6 hm6;

    @Before
    public void init(){
        hm6 = new HomeWork6();
    }


    @Test
    public void test1Method2(){
        int[] arr1 = {1,2,4,4,2,3,4,1,7};
        Assert.assertEquals(false,hm6.method2(arr1));
    }

    @Test
    public void test2Method2(){
        int[] arr1 = {1,1,4,4,1};
        Assert.assertEquals(true,hm6.method2(arr1));
    }

    @Test
    public void test3Method2(){
        int[] arr1 = {1,1,1,1,1,1};
        Assert.assertEquals(false,hm6.method2(arr1));
    }

    @Test
    public void test4Method2(){
        int[] arr1 = {4,4,4,4};
        Assert.assertEquals(false,hm6.method2(arr1));
    }

    @Test
    public void test5Method2(){
        int[] arr1 = {4,4,1,4,2};
        Assert.assertEquals(false,hm6.method2(arr1));
    }



}
