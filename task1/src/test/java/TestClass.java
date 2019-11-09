//import com.sun.tools.corba.se.idl.constExpr.Or;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestClass {
    Box<Orange> b1, b3;
    Box<Apple> b2;
    String[] strArr;
    Integer[] intArr;


    @Before
    public void init() {
        b1 = new Box<>();
        b2 = new Box<>();
        b3 = new Box<>();
        for (int i = 0; i < 10; i++) {
            b1.addFruit(new Orange());
        }
        for (int i = 0; i < 10; i++) {
            b2.addFruit(new Apple());
        }
        for (int i = 0; i < 10; i++) {
            b3.addFruit(new Orange());
        }

        strArr = new String[] {"Test1", "Test2", "Test3"};

        intArr = new Integer[] {1, 2, 3, 4, 5, 6, 7};

    }

    @Test
    public void testCompare() {
        Assert.assertFalse(b1.compareTo(b2));
        Assert.assertTrue(new Box<Orange>()
                .compareTo(new Box<Apple>()));
        Assert.assertTrue(b1.compareTo(b3));
    }

    @Test
    public void testDrop() {
        b1.dropFruits(b3);
        Assert.assertEquals(30., b3.getWeight(), 0.0001);
        Assert.assertEquals(0., b1.getWeight(), 0.0001);
    }

    @Test
    public void testChangeElements() {
        Exercise1.changeElements(strArr, 0,2);
        Assert.assertEquals(strArr[0], "Test3");
        Assert.assertEquals(strArr[2], "Test1");
    }

    @Test
    public void testConvertArrayToList(){
        ArrayList<Integer> testArrList = Exercise1.convertArrayToList(intArr);
        Assert.assertTrue(testArrList.size() == intArr.length);
        Assert.assertTrue(testArrList.get(3)==intArr[3]);
    }


}
