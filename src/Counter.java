public class Counter {
    private int count;

    public Counter() {
        this.count = 1;
    }

    public int getCount() {
        return count ++;
    }
}