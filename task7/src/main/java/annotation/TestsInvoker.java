package annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestsInvoker {

    private void invokeTests(Class c) {
        Tests tests = new Tests();
        Method[] methods = c.getDeclaredMethods();

        List<Method> beforeMethods = new ArrayList<>();
        List<Method> afterMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();
        for (Method m : methods) {
            if (m.getDeclaredAnnotation(BeforeSuit.class) != null) beforeMethods.add(m);
            if (m.getDeclaredAnnotation(AfterSuit.class) != null) afterMethods.add(m);
            if (m.getDeclaredAnnotation(Test.class) != null) testMethods.add(m);
        }

        testMethods.sort(Comparator.comparingInt(o -> o.getDeclaredAnnotation(Test.class).priority()));
        try {
            if (beforeMethods.size() > 1) {
                throw new RuntimeException("More than 1 method annotated BeforeSuit");
            } else {
                beforeMethods.get(0).invoke(tests);
            }
            for (Method testMethod : testMethods) {
                testMethod.invoke(tests);
            }
            if (afterMethods.size() > 1) {
                throw new RuntimeException("More than 1 method annotated AfterSuit");
            } else {
                afterMethods.get(0).invoke(tests);
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Class clazz = Tests.class;
        new TestsInvoker().invokeTests(clazz);
    }
}
