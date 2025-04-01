import java.math.BigDecimal;
import java.math.RoundingMode;

public class ClawMachine {
    public int aX = -1;
    public int aY = -1;
    public int bX = -1;
    public int bY = -1;
    public int prizeX = -1;
    public int prizeY = -1;

    public ClawMachine() {
    }

    public int GetMinAmountOfTokens() {
        int min = 401;

        for (int i = 1; i <= 100; i++) {
            for (int j = 1; j <= 100; j++) {
                if (aX*i+bX*j == prizeX && aY*i+bY*j == prizeY) {
                    if (i*3+j < min) min = i*3+j;
                }
            }
        }

        if (min == 401) return 0;
        return min;
    }

    public BigDecimal GetMinAmountOfTokensPart2() {
        BigDecimal adjustment = new BigDecimal("10000000000000");

        BigDecimal newPrizeX = BigDecimal.valueOf(prizeX).add(adjustment);
        BigDecimal newPrizeY = BigDecimal.valueOf(prizeY).add(adjustment);
        BigDecimal newaX = BigDecimal.valueOf(aX);
        BigDecimal newaY = BigDecimal.valueOf(aY);
        BigDecimal newbX = BigDecimal.valueOf(bX);
        BigDecimal newbY = BigDecimal.valueOf(bY);

        BigDecimal numeratorApress = newPrizeX.multiply(newbY).subtract(newPrizeY.multiply(newbX));
        BigDecimal denominatorApress = newaX.multiply(newbY).subtract(newaY.multiply(newbX));

        if (numeratorApress.divideAndRemainder(denominatorApress)[1].equals(BigDecimal.ZERO)) {

            BigDecimal apress = numeratorApress.divide(denominatorApress, RoundingMode.HALF_UP);
            BigDecimal numeratorBpress = newPrizeX.subtract(newaX.multiply(apress));
            BigDecimal bpress = numeratorBpress.divide(newbX, BigDecimal.ROUND_HALF_UP);
            return apress.multiply(new BigDecimal("3")).add(bpress);

        }
        return BigDecimal.ZERO;
    }
}
