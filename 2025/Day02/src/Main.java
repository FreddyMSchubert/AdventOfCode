import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;

// 35367539283 too high

public class Main
{
    public static boolean isInvalidId(String id)
    {
        if (id.length() % 2 != 0) return false;
        int halfPos = id.length() / 2;
        for (int i = 0; i < halfPos; i ++)
            if (id.charAt(i) != id.charAt(i + halfPos))
                return false;
        return true;
    }

    public static void main(String[] args)
    {
        BigInteger invalidIdSum = BigInteger.ZERO;

        try (BufferedReader br = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line = br.readLine();
            String[] rangeStrings = line.split(",");
            for (String range : rangeStrings)
            {
                String[] rangeParts = range.split("-");
                BigInteger rangeMin = new BigInteger(rangeParts[0]);
                BigInteger rangeMax = new BigInteger(rangeParts[1]);

                System.out.println("Parsed range parts: " + rangeMin + "-" + rangeMax);

                for (BigInteger i = rangeMin; i.compareTo(rangeMax) < 1; i = i.add(BigInteger.ONE))
                {
                    if (isInvalidId(String.valueOf(i)))
                        invalidIdSum = invalidIdSum.add(i);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("oof" + e);
        }

        System.out.println("invalid id sum: " + invalidIdSum);
    }
}