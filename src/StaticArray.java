public class StaticArray implements Benchmarkable {
    private int[] arr;
    private int size;

    public StaticArray(int size) {
        arr = new int[size];
        this.size = size;
    }

    @Override
    public void build(int[] integers) {
        if (integers.length > size) {
            throw new IllegalArgumentException("Size is different");
        }
        for (int i = 0; i < integers.length; i++) {
            arr[i] = integers[i];
        }
    }

    @Override
    public void insertFirst(int value) {
        insert(0, value);
    }

    @Override
    public void insertLast(int value) {
        arr[size - 1] = value;
    }

    @Override
    public void insert(int pos, int num) {
        if (pos == size-1) {
            insertLast(num);
            return;
        }

        int[] newArr = new int[size + 1];

        for (int i = 0; i < pos; i++)
            newArr[i] = arr[i];
        newArr[pos] = num;
        for (int i = pos + 1; i < size + 1; i++)
            newArr[i] = arr[i - 1];

        arr = newArr;
        size++;
    }

    @Override
    public int read(int index) {
            return arr[index];
    }
}
