/* interface for methods required to perform operations in PA1 instructions */
public interface Benchmarkable {
    void build(int[] integers);
    void insertFirst(int value);
    void insertLast(int value);
    void insert(int index, int value);
    int read(int index);
}
