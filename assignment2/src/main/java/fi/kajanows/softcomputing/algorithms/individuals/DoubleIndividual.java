package fi.kajanows.softcomputing.algorithms.individuals;

import java.util.Random;

/**
 * Created by kgjan on 23.11.2015.
 */
public class DoubleIndividual implements Individual<DoubleIndividual> {

    private static final double CR_PROBABILITY = 0.1;

    private Random random = new Random();

    private final double value;

    public DoubleIndividual(final double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public DoubleIndividual plus(DoubleIndividual i) {
        return new DoubleIndividual(i.value + this.value);
    }

    @Override
    public DoubleIndividual minus(DoubleIndividual i) {
        return new DoubleIndividual(this.value - i.value);
    }

    private String toBinary(final double num) {
        String bin = Long.toBinaryString(Double.doubleToLongBits(num));
        while (bin.length() < 64) {
            bin = "0" + bin;
        }
        return bin;
    }

    private boolean shouldUseThisGene(final int currentX, final int xRandom) {
        return currentX == xRandom || random.nextDouble() <= CR_PROBABILITY;
    }

    @Override
    public DoubleIndividual makeBaby(DoubleIndividual i) {
        String thisGenes = toBinary(this.value);
        String iGenes = toBinary(i.getValue());
        StringBuilder babyGenes = new StringBuilder();
        int xRandom = random.nextInt(thisGenes.length());
        for (int x = 0; x < thisGenes.length(); x++) {
            babyGenes.append(shouldUseThisGene(x, xRandom) ? thisGenes.charAt(x) : iGenes.charAt(x));
        }
        return new DoubleIndividual(Double.longBitsToDouble(Long.parseUnsignedLong(babyGenes.toString(), 2)));
    }
}
