package annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class TestsInvoker {

    public void invokeTests(Class c) {
        Tests tests = new Tests();
        Class cl = Tests.class;
        Method[] methods = cl.getDeclaredMethods();
        Arrays.sort(methods, new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                if (o1.getDeclaredAnnotation(BeforeSuit.class) != null) {
                    return -1;
                } else if (o2.getDeclaredAnnotation(BeforeSuit.class) != null) {
                    return 1;
                } else if (o1.getDeclaredAnnotation(AfterSuit.class) != null) {
                    return 1;
                } else if (o2.getDeclaredAnnotation(AfterSuit.class) != null) {
                    return -1;
                } else {
                    return (o1.getDeclaredAnnotation(Test.class).priority() - o2.getDeclaredAnnotation(Test.class).priority());
                }
            }
        });

        for (Method m : methods) {
            try {
                m.setAccessible(true);
                m.invoke(tests, null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        Class clazz = Tests.class;
        new TestsInvoker().invokeTests(clazz);
    }
}
