package annotation;

public class TestsInvoker {

    public static void main(String[] args) {
        Class clazz = Tests.class;
        new TestsInvoker().invokeTests(clazz);
    }

    public void invokeTests(Class c) {
        // TODO: 18/11/2019

    }
}
