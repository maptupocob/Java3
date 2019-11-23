package Test;

import main.Main;
import org.junit.Assert;
import org.junit.Test;


public class TestClass {

    @Test
    public void testSubArray1() {
        int[] exp = new int[]{5, 7, 9, 10};
        int[] param = new int[]{4, 1, 2, 3, 4, 5, 7, 9, 10};
        Assert.assertEquals(exp.length, Main.subArray(param).length);
        for (int i = 0; i < exp.length; i++) {
            Assert.assertEquals(exp[i], param[i + 5]);
        }
    }

    @Test
    public void testSubArray2() {
        int[] exp = new int[]{};
        int[] param = new int[]{4, 1, 2, 3, 4};
        Assert.assertEquals(exp.length, Main.subArray(param).length);
        for (int i = 0; i < exp.length; i++) {
            Assert.assertEquals(exp[i], param[i + 1]);
        }
    }

    @Test
    public void testSubArray3() {
        int[] exp = new int[]{1, 2, 3};
        int[] param = new int[]{4, 1, 2, 3};
        Assert.assertEquals(exp.length, Main.subArray(param).length);
        for (int i = 0; i < exp.length; i++) {
            Assert.assertEquals(exp[i], param[i + 1]);
        }
    }

    @Test(expected = RuntimeException.class)
    public void testSubArray4() {
        int[] param = new int[]{1, 2, 3, 1, 2, 3, 6};
        Main.subArray(param);
    }

    @Test
    public void testCheckArray1() {
        int[] arr = {1, 1, 1, 1, 4};
        Assert.assertTrue(Main.checkArray1And4(arr));
    }


    @Test
    public void testCheckArray2() {
        int[] arr = {4, 4, 4, 4, 4};
        Assert.assertFalse(Main.checkArray1And4(arr));
    }


    @Test
    public void testCheckArray3() {
        int[] arr = {1, 1, 1, 1, 1};
        Assert.assertFalse(Main.checkArray1And4(arr));
    }


    @Test
    public void testCheckArray4() {
        int[] arr = {1, 1, 1, 1, 4, 666};
        Assert.assertFalse(Main.checkArray1And4(arr));
    }


}
