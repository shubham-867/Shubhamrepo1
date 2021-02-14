package Practice;

import org.testng.annotations.Test;

public class TestAttributes {


//    @Test(dependsOnMethods ={"BC4","BC99","BC2"}) // 2,4,99
//    public void BC3(){
//
//        System.out.println("BC3");
//    }

    @Test(priority = -5)
    public void BC99(){

        System.out.println("BC99-5");
    }

    @Test(priority = 2)
    public void BC2(){

        System.out.println("BC2");
    }
    @Test(priority = 1)
    public void BC4(){

        System.out.println("BC4");
    }
// description:
    //alwaysRun:
    //priority
    //dependsOnMethods
    //dependsOnGroups
    //dataproviderClass
    //https://mundrisoft.com/tech-bytes/testng-test-annotation-with-attributes-and-sample-code/

}
