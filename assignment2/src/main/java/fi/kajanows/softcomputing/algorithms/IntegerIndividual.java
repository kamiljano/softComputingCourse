package fi.kajanows.softcomputing.algorithms;

import java.util.Random;

/**
 * Created by kgjan on 8.11.2015.
 */
public class IntegerIndividual implements Individual<IntegerIndividual> {

    private static final double CR_PROBABILITY = 0.1;

    private Random random = new Random();

    private final int value;

    public IntegerIndividual(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public IntegerIndividual plus(IntegerIndividual i) {
        return new IntegerIndividual(i.value + this.value);
    }

    @Override
    public IntegerIndividual minus(IntegerIndividual i) {
        return new IntegerIndividual(this.value - i.value);
    }

    private boolean shouldUseThisGene(final int currentX, final int xRandom) {
        return currentX == xRandom || random.nextDouble() <= CR_PROBABILITY;
    }

    private String toBinary(int num) {
        String bin = Integer.toBinaryString(num);
        while (bin.length() < 8) {
            bin = "0" + bin;
        }
        return bin;
    }

    @Override
    public IntegerIndividual makeBaby(IntegerIndividual i) {
        String thisGenes = toBinary(this.value);
        String iGenes = toBinary(i.getValue());
        StringBuilder babyGenes = new StringBuilder();
        int xRandom = random.nextInt(thisGenes.length());
        for (int x = 0; x < thisGenes.length(); x++) {
            babyGenes.append(shouldUseThisGene(x, xRandom) ? thisGenes.charAt(x) : iGenes.charAt(x));
        }
        return new IntegerIndividual(Integer.parseInt(babyGenes.toString(), 2));
    }
}
