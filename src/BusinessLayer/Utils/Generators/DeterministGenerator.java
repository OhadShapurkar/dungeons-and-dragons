package BusinessLayer.Utils.Generators;

public class DeterministGenerator implements Generator {
    private int seed;
    private int index;

    public DeterministGenerator(int seed) {
        this.seed = seed;
        this.index = 0;
    }

    @Override
    public int generate(int value) {
        return (seed + index++) % value;
    }
}
