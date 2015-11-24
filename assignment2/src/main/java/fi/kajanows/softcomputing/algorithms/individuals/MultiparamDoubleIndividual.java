package fi.kajanows.softcomputing.algorithms.individuals;

import java.util.Random;

/**
 * Created by kgjan on 24.11.2015.
 */
public class MultiparamDoubleIndividual implements Individual<MultiparamDoubleIndividual> {

    private static final double CR_PROBABILITY = 0.1;

    private Random random = new Random();

    private final double [] value;

    public double[] getValue() {
        return value;
    }

    public MultiparamDoubleIndividual(double... params) {
        this.value = params;
    }

    @Override
    public MultiparamDoubleIndividual plus(MultiparamDoubleIndividual individual) {
        double [] result = new double[value.length];
        for (int i = 0; i < value.length; i++) {
            result[i] = value[i] + individual.value[i];
        }
        return new MultiparamDoubleIndividual(result);
    }

    @Override
    public MultiparamDoubleIndividual minus(MultiparamDoubleIndividual individual) {
        double [] result = new double[value.length];
        for (int i = 0; i < value.length; i++) {
            result[i] = value[i] - individual.value[i];
        }
        return new MultiparamDoubleIndividual(result);
    }

    private String toBinary(final double [] nums) {
        String finalBinary = "";
        for (int i = 0; i < nums.length; i++) {
            String bin = Long.toBinaryString(Double.doubleToLongBits(nums[i]));
            while (bin.length() < 64) {
                bin = "0" + bin;
            }
            finalBinary += bin;
        }
        return finalBinary;
    }

    private boolean shouldUseThisGene(final int currentX, final int xRandom) {
        return currentX == xRandom || random.nextDouble() <= CR_PROBABILITY;
    }

    private double[] toDoubles(final String binary) {
        double [] result = new double[binary.length() / 64];
        for (int i = 0; i < result.length; i++) {
            String bin = binary.substring(64 * i, 64 * (i + 1));
            result[i] = Double.longBitsToDouble(Long.parseUnsignedLong(bin, 2));
        }
        return result;
    }

    @Override
    public MultiparamDoubleIndividual makeBaby(MultiparamDoubleIndividual i) {
        String thisGenes = toBinary(this.value);
        String iGenes = toBinary(i.value);
        StringBuilder babyGenes = new StringBuilder();
        int xRandom = random.nextInt(thisGenes.length());
        for (int x = 0; x < thisGenes.length(); x++) {
            babyGenes.append(shouldUseThisGene(x, xRandom) ? thisGenes.charAt(x) : iGenes.charAt(x));
        }
        return new MultiparamDoubleIndividual(toDoubles(babyGenes.toString()));
    }
}
