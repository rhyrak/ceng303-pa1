/*
20050111011, İBRAHİM BAHÇA
20050111034, MERTER ÇOBAN
20050111008, SELÇUK GENÇAY
21050141038, YOUSIF HARITH SUHAIL SUHAIL
 */

/* interface for methods required to perform operations in PA1 instructions */
public interface Benchmarkable {
    void build(int[] integers);

    void insertFirst(int value);

    void insertLast(int value);

    void insert(int index, int value);

    int read(int index);
}
