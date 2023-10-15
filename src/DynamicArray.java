/*
20050111011, İBRAHİM BAHÇA
20050111034, MERTER ÇOBAN
20050111008, SELÇUK GENÇAY
21050141038, YOUSIF HARITH SUHAIL SUHAIL

 */

public class DynamicArray implements Benchmarkable{

    /* variables */
    private int[] arr;
    private int maxSize = 0;
    private int size;

    /* default constructor */
    public DynamicArray() {

        arr = new int[maxSize];
        size = 0;
    }

    /* method to expand array by doubling the size */
    public int[] doubleArray(){
        maxSize = maxSize * 2;  // double max capacity
        int[] newArr = new int[maxSize];    // new array

        /* copy old array to new array */
        for(int i = 0; i < size; i++){
            newArr[i] = arr[i];
        }

        return newArr;
    }

    /* method to shrink array to half its size */
    public int[] halveArray(){
        maxSize = maxSize / 2;  // halve max capacity
        int[] newArr = new int[maxSize];    // new array

        /* copy old array to new array */
        for(int i = 0; i < size; i++){
            newArr[i] = arr[i];
        }

        return newArr;
    }

    /* insert data into the last index of the array */
    public void addLast(int num) {
        if(size == maxSize){
            if(maxSize == 0)
                maxSize++;
            arr = doubleArray();
        }

        if(size < maxSize){
            arr[size] = num;
            size++;

        }

    }

    /* shift-insert helper method */
    public void addPosition(int pos, int num) {
        if(pos < 0 || pos > size){
            throw new IndexOutOfBoundsException("Index: " + pos + " is out of range for size: " + size + ".");
        }

        /* check if array expansion is needed */
        if(size == maxSize){
            arr = doubleArray();
        }

        /* shift array starting from the back to make the specified index empty and ready for data insertion */
        if(size < maxSize && pos <= size){
            for(int i = size-1; i > pos-1; i--){
                arr[i+1] = arr[i];
            }

            arr[pos] = num;
            size++;

        }
    }

    /* populate DS with data */
    @Override
    public void build(int[] integers) {
        for(int num : integers)
            addLast(num);
    }

    /* shift-insert into first index */
    @Override
    public void insertFirst(int value) {
        addPosition(0, value);
    }

    /* shift-insert into last index */
    @Override
    public void insertLast(int value) {
        addPosition(size - 1, value);
    }

    /* shift-insert into specified index */
    @Override
    public void insert(int index, int value) {
        addPosition(index, value);
    }

    /* read data from specified index */
    @Override
    public int read(int index) {
        return arr[index];
    }

}
