package tij.initializationCleanUp;

import org.junit.Test;

public class E2String {

    @Test
    public void test1(){
        TestString ts = new TestString();
        System.out.println("fieldStr = " + ts.getFieldStr());
        System.out.println("fieldStr2 = " + ts.getFieldStr2());


        TestString ts2 = new TestString("InitialByConstructor");
        System.out.println("fieldStr = " + ts2.getFieldStr());
        System.out.println("fieldStr2 = " + ts2.getFieldStr2());
    }
}

class TestString{

    private String fieldStr = "InitializedOnSpot";

    private String fieldStr2;

    public TestString(String fieldStr2) {
        this.fieldStr2 = fieldStr2;
    }

    public TestString() {
    }

    public String getFieldStr() {
        return fieldStr;
    }

    public String getFieldStr2() {
        return fieldStr2;
    }
}
