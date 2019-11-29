package annotation;

public class Tests {
    private boolean allTestsPassed = true;

    private Utility util;

    @BeforeSuit
    public void starter() {
        util = new Utility(25, 13);
        System.err.println("Test prepare");
    }

    @Test(priority = 1)
    public void sumTest() {
        if (util.sum() == util.getA() + util.getB()) {
            System.out.println("Sum test passed!");
            allTestsPassed = allTestsPassed && true;
        } else {
            allTestsPassed = allTestsPassed && false;
            System.err.println("Sum test failed!");
        }
    }

    @Test(priority = 1)
    public void subTest() {
        if (util.sub() == util.getA() - util.getB()) {
            System.out.println("Sub test passed!");
            allTestsPassed = allTestsPassed && true;
        } else {
            allTestsPassed = allTestsPassed && false;
            System.err.println("Sub test failed!");
        }
    }

    @Test(priority = 3)
    public void mulTest() {
        if (util.mul() == util.getA() * util.getB()) {
            System.out.println("Mul test passed!");
            allTestsPassed = allTestsPassed && true;
        } else {
            allTestsPassed = allTestsPassed && false;
            System.err.println("Mul test failed!");
        }
    }

    @Test(priority = 3)
    public void divTest() {
        if (util.div() == util.getA() / util.getB()) {
            System.out.println("Div test passed!");
            allTestsPassed = allTestsPassed && true;
        } else {
            allTestsPassed = allTestsPassed && false;
            System.err.println("Div test failed!");
        }
    }

    @Test(priority = 3)
    public void modTest() {
        if (util.mod() == util.getA() % util.getB()) {
            System.out.println("Mod test passed!");
            allTestsPassed = allTestsPassed && true;
        } else {
            allTestsPassed = allTestsPassed && false;
            System.err.println("Mod test failed!");
        }
    }

    @AfterSuit
    public void end() {
        if (allTestsPassed) {
            System.err.println("All tests passed!");
        } else {
            System.err.println("Not all test passed!");
        }
    }
}
