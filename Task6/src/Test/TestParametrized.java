package Test;

import main.Main;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestParametrized {
    private int[] a, b;

    public TestParametrized(int[] a, int[] b) {
        this.a = a;
        this.b = b;
    }

    @Parameterized.Parameters
    public static Collection<Object> generator() {
        int[][][] arr = new int[][][]{
                {{1, 2, 3, 4}, {}},
                {{4, 3, 2, 1}, {3, 2, 1}},
                {{1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1, 0}, {3, 2, 1, 0}},
                {{4, 4, 4, 4, 4, 5, 6, 7}, {5, 6, 7}}
        };
        return Arrays.asList(arr);
    }

    @Test
    public void test() {
        Assert.assertEquals(b.length, Main.subArray(a).length);
        for (int i = 0; i < b.length; i++) {
            Assert.assertEquals(b[i], a[a.length - b.length + i]);
        }
    }

}
