public class Comp implements Comparable<Comp> {
    private String name;
    protected double cost = 0.24;
    protected int btns = 2;

    public Comp(String name, double cost, int btns) {
        this.name = name;
        this.cost = cost;
        this.btns = btns;
    }

    @Override
    public int compareTo(Comp k) {
        return this.btns - k.btns;
    }
}