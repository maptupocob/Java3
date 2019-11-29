import com.google.gson.Gson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StringWriter;
import java.lang.reflect.Field;

public class ObjectInfo {
    //Use your path
    //Объект неизвестен)))
    private String path = "/Users/maptu/IdeaProjects/Java3" +
            "/task7/src/main/resources/";
    private Object[] objects;
    private int counter;
    private Gson gson = new Gson();

    public ObjectInfo() {
        objects = new Object[2];
        try {
            readInstances(new ObjectInputStream(
                    new FileInputStream(path + "object.txt")));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        counter = 0;
    }

    public static void main(String[] args) throws IllegalAccessException {
        ObjectInfo info = new ObjectInfo();
        while (info.hasNext()) {
            System.out.println(info.objectInfo());
            System.out.println(info.JSONInfo());
            System.out.println(info.XMLInfo());
            info.next();
        }
    }

    public boolean hasNext() {
        return counter < objects.length;
    }

    public void next() {
        counter++;
    }

    public Object getInstance() {
        return objects[counter];
    }

    /*
     * Верните строку с информацией о содержимом объекта в формате:
     * имя класса:
     * тип1 имя_поля1 : значение1
     * тип2 имя_поля2 : значение2
     * ......
     * типN имя_поляN : значениеN
     * */
    public String objectInfo() {
        System.out.println(counter + " object");
        System.out.println(getInstance().getClass().getName());
        StringBuilder stringBuilder = new StringBuilder();
        Class cl = getInstance().getClass();
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            stringBuilder.append(field.getType().getName());
            stringBuilder.append(" ");
            stringBuilder.append(field.getName());
            stringBuilder.append(" : ");
            try {
                stringBuilder.append(field.get(getInstance()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    /*
     * Необходимо вернуть строку в формате JSON
     * посмотрите на импорты)))
     * */
    public String JSONInfo() {
        return gson.toJson(getInstance());
    }

    /*
     *  Необходимо аернуть строку в формате  XML
     * поля классов отмаркированны так, чтобы работали методы
     * javax.xml.bind.*
     * */
    public String XMLInfo() {
        // TODO: 18/11/2019
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(getInstance().getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            StringWriter sw = new StringWriter();
            jaxbMarshaller.marshal(getInstance(), sw);
            String xmlString = sw.toString();
            return xmlString;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void readInstances(ObjectInputStream is) throws IOException, ClassNotFoundException {
        objects[0] = is.readObject();
        objects[1] = is.readObject();
    }
}
