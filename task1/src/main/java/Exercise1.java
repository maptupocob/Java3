public class Exercise1 {
    public static <T> void changeElements(T[] array, int firstElementIndex, int secondElementIndex){
        T tempElement = null;
        tempElement = array[firstElementIndex];
        array[firstElementIndex] = array[secondElementIndex];
        array[secondElementIndex]= tempElement;
    }
}
