package shop.main;
import shop.data.Record;

class numRentalsComparator<T> implements java.util.Comparator {
    public int compare (Object o1, Object o2) {
        Record r1 = (Record)o1;
        Record r2 = (Record)o2;
        return r2.numRentals() - r1.numRentals();
      }

}
