package annotation;

class Utility {

    private int a, b;

    Utility(int a, int b) {
        this.a = a;
        this.b = b;
    }

    int getA() {
        return a;
    }

    int getB() {
        return b;
    }

    int sum() {
        return a + b;
    }

    int mul() {
        return a * b;
    }

    int sub() {
        return a - b;
    }

    int div() {
        return a / b;
    }

    int mod() {
        return a % b;
    }
}
