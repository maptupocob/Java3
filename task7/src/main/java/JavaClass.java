import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class JavaClass implements Serializable {

    private int[][] side;
    private int size;

    public JavaClass() {
    }

    public JavaClass(int size) {
        this.size = size;
        side = new int[size][size];
        int cnt = 1;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                side[i][j] = cnt++;
            }
        }
    }

    public int[][] getSide() {
        return side;
    }

    //@XmlAttribute
    public void setSide(int[][] side) {
        this.side = side;
    }

    public int getSize() {
        return size;
    }

    @XmlElement
    public void setSize(int size) {
        this.size = size;
    }

    public void rotateLeft() {
        int[][] tmp = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tmp[i][j] = side[size - j - 1][i];
            }
        }
        side = tmp;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                s.append(side[i][j]).append(' ');
            }
            s.append('\n');
        }
        return s.toString();
    }

    public void rotateRight() {
        int[][] tmp = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tmp[i][j] = side[j][i];
            }
        }
        side = tmp;
    }

}
