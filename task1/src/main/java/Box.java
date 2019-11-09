import java.util.ArrayList;
import java.util.Iterator;

public class Box<T extends Fruit> {
    ArrayList<T> list;

    public Box() {
        list = new ArrayList<>();
    }

    public float getWeight() {
        float sum = 0f;
        for (T fruit : list) {
            sum += fruit.getWeight();
        }
        return sum;
    }

    public boolean compareTo(Box<? extends Fruit> other) {
        return (this.getWeight() - other.getWeight()) == 0;
    }

    public void addFruit(T fruit) {
        list.add(fruit);
    }

    public void dropFruits(Box<T> otherBox) {
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()){
            T fruit= iterator.next();
            otherBox.addFruit(fruit);
            iterator.remove();
        }
    }
}
