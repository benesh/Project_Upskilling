package testsuite;

import org.opensourcedemo.listerners.Mylisterner;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(Mylisterner.class)
public class TestExemple {

    @Test
    public void test1(){
        System.out.println("Testing 1");
    }
    @Test
    public void test2(){
        System.out.println("Testing 2");
    }
    @Test
    public void test3(){
        System.out.println("Testing 2");
    }

}
