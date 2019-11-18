public class Exercise4 implements Runnable {
    volatile static int counter;
    char letter;
    int orderNumber;
    static Object mutex = new Object();
    private int letterCounter = 0;

    public Exercise4(char letter) {
        this.letter = letter;
    }

    @Override
    public void run() {
        orderNumber = defineOrderNumber(letter);
        synchronized (mutex) {
           while( letterCounter < 5) {
                if (counter % 3 == orderNumber) {
                    System.out.print(letter);
                    mutex.notifyAll();
                    counter++;
                    letterCounter++;
                } else {
                    try {
                        mutex.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private int defineOrderNumber(char letter) {
        switch (letter) {
            case ('A'):
                return 0;
            case ('B'):
                return 1;
            case ('C'):
                return 2;
            default:
                return -1;
        }
    }

    public static void main(String[] args) {
        new Thread(new Exercise4('A')).start();
        new Thread(new Exercise4('B')).start();
        new Thread(new Exercise4('C')).start();
        System.out.println(counter);
    }
}
