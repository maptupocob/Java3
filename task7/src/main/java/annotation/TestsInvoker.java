package annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestsInvoker {

    public void invokeTests(Class c) {
        Tests tests = new Tests();
        Class cl = Tests.class;
        Method[] methods = cl.getDeclaredMethods();

        List<Method> beforeMethods = new ArrayList<>();
        List<Method> afterMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();
        for (Method m : methods) {
            if (m.getDeclaredAnnotation(BeforeSuit.class) != null) beforeMethods.add(m);
            if (m.getDeclaredAnnotation(AfterSuit.class) != null) afterMethods.add(m);
            if (m.getDeclaredAnnotation(Test.class) != null) testMethods.add(m);
        }

        Collections.sort(testMethods, new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                return (o1.getDeclaredAnnotation(Test.class).priority() - o2.getDeclaredAnnotation(Test.class).priority());
            }
        });
        try {
            if (beforeMethods.size() > 1) {
                throw new RuntimeException("More than 1 method annotated BeforeSuit");
            } else {
                beforeMethods.get(0).invoke(tests, null);
            }
            for (Method testMethod : testMethods) {
                testMethod.invoke(tests, null);
            }
            if (afterMethods.size() > 1) {
                throw new RuntimeException("More than 1 method annotated AfterSuit");
            } else {
                afterMethods.get(0).invoke(tests, null);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Class clazz = Tests.class;
        new TestsInvoker().invokeTests(clazz);
    }
}
