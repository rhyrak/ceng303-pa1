public class StaticArray implements Benchmarkable {

    /* variables */
    private int[] arr;
    private int size;

    /* default constructor */
    public StaticArray(int size) {
        arr = new int[size];
        this.size = size;
    }

    /* populate DS with data */
    @Override
    public void build(int[] integers) {
        if (integers.length > size) {
            throw new IllegalArgumentException("Size is different");
        }
        for (int i = 0; i < integers.length; i++) {
            arr[i] = integers[i];
        }
    }

    /* insert into first index and shift the array*/
    @Override
    public void insertFirst(int value) {

        /* use defined method to shift-insert*/
        insert(0, value);
    }

    /* replace last index's data */
    @Override
    public void insertLast(int value) {

        arr[size - 1] = value;
    }

    /* shift-insert new data into the specified index */
    @Override
    public void insert(int pos, int num) {
        if (pos == size - 1) {  // speed optimization
            insertLast(num);
            return;
        }
        /* make new array with expanded size */
        int[] newArr = new int[size + 1];

        /* copy existing data before the new index to the new array */
        for (int i = 0; i < pos; i++)
            newArr[i] = arr[i];

        /* insert new data into the index */
        newArr[pos] = num;

        /* populate the rest of the array with data (effectively shifted) */
        for (int i = pos + 1; i < size + 1; i++)
            newArr[i] = arr[i - 1];

        /* update reference */
        arr = newArr;
        size++;
    }

    /* read data from specified index */
    @Override
    public int read(int index) {

        return arr[index];
    }
}
