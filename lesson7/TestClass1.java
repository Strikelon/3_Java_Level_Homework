package hmJava3.hm7;

public class TestClass1 {

    @BeforeSuite
    public void before(){
        System.out.println("Выполняется метод before()");
    }


    @AfterSuite
    public void after(){
        System.out.println("Выполняется метод after()");
    }


    @Test (Priority = 10)
    public void t5(){
        System.out.println("Выполняется метод t5");
    }


    @Test(Priority = 2)
    public void t2(){
        System.out.println("Выполняется метод t2");
    }

    @Test(Priority = 1)
    public void t1(){
        System.out.println("Выполняется метод t1");
    }

    @Test(Priority = 3)
    public void t3(){
        System.out.println("Выполняется метод t3");
    }

    @Test(Priority = 4)
    public void t4(){
        System.out.println("Выполняется метод t4");
    }

}

