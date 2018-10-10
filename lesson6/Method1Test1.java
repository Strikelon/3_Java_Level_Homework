import hmJava3.hm6HomeWork.HomeWork6;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Method1Test1 {

    HomeWork6 hm6;

    @Before
    public void init(){
        hm6 = new HomeWork6();
    }

    @Test
    public void test1Method1(){
        int[] arr1 = {1,2,4,4,2,3,4,1,7};
        int[] arr2 = {1,7};
        Assert.assertArrayEquals(arr2,hm6.method1(arr1));
    }

    @Test (expected = RuntimeException.class)
    public void test2Method1(){
        int[] arr1 = {1,2,3,5,6,7};
        int[] arr2 = {1,7};
        Assert.assertArrayEquals(arr2,hm6.method1(arr1));
    }

    @Test
    public void test3Method1(){
        int[] arr1 = {4,4,4,5,7,8,4,9};
        int[] arr2 = {9};
        Assert.assertArrayEquals(arr2,hm6.method1(arr1));
    }

    @Test
    public void test4Method1(){
        int[] arr1 = {1,2,3,4};
        int[] arr2 = new int[0];
        Assert.assertArrayEquals(arr2,hm6.method1(arr1));
    }

}
