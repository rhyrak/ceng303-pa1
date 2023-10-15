public class DynamicArray implements Benchmarkable{
    private int[] arr;
    private int maxSize = 0;
    private int size;


    public DynamicArray() {

        arr = new int[maxSize];
        size = 0;
    }

    public int[] doubleArray(){
        maxSize = maxSize * 2;
        int[] newArr = new int[maxSize];

        for(int i = 0; i < size; i++){
            newArr[i] = arr[i];
        }

        return newArr;
    }

    public int[] halveArray(){
        maxSize = maxSize / 2;
        int[] newArr = new int[maxSize];

        for(int i = 0; i < size; i++){
            newArr[i] = arr[i];
        }

        return newArr;
    }

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

        //return display();
    }


    public String addPosition(int pos, int num) {
        if(pos < 0 || pos > size){
            return "error\n";
        }

        if(size == maxSize){
            arr = doubleArray();
        }

        if(size < maxSize && pos <= size){

            for(int i = size-1; i > pos-1; i--){
                arr[i+1] = arr[i];

            }

            arr[pos] = num;
            size++;

        }

        return "ok";
        //return display();
    }

    public String removeLast() {
        if(size < 0){
            return "error\n";
        }

        size--;
        float temp = (float)size;
        if(temp/maxSize <= 0.25){
            arr = halveArray();
        }

        return "ok";
        //return display();
    }

    public String removePosition(int pos) {
        if(size <= 0 || pos > size-1){
            return "error\n";
        }

        if(size-1 >= pos){

            for(int i = 0; i < size-1; i++){
                arr[i] = arr[i+1];
            }

            //arr[size-1] = null;
            size--;
            float temp = (float)size;
            if(temp/maxSize == 0.25){
                arr = halveArray();
            }
        }

        return "ok";
        //return display();
    }

    public String display() {
        String out = "";

        for(int i = 0; i < size; i++){
            out = out + arr[i] + " ";
        }

        out = out + "(" + size + "/" + maxSize + ")" + '\n';
        return out;
    }

    public int displayIndex(int index){
        if(index >= size)
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for Dynamic Array.");
        else{
            return arr[index];
        }

    }

    @Override
    public void build(int[] integers) {
        for(int num : integers)
            addLast(num);
    }

    @Override
    public void insertFirst(int value) {
        addPosition(0, value);
    }

    @Override
    public void insertLast(int value) {
        addPosition(size - 1, value);
    }

    @Override
    public void insert(int index, int value) {
        addPosition(index, value);
    }

    @Override
    public int read(int index) {
        return arr[index];
    }

}
