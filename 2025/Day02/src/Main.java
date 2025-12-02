import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;

public class Main
{
    public static boolean isInvalidId(String id)
    {
        for (int assumedPairs = 2; assumedPairs <= id.length(); assumedPairs++)
        {
            if ((id.length() / assumedPairs) * assumedPairs != id.length())
                continue;
            boolean repeating = true;
            int firstSegmentPos = id.length() / assumedPairs;
            for (int pos = 0; pos < firstSegmentPos; pos ++)
            {
                for (int i = 1; i < assumedPairs; i ++)
                    if (id.charAt(pos) != id.charAt((firstSegmentPos * i) + pos))
                        repeating = false;
            }
            if (repeating)
            {
                System.out.println("Number " + id + " is an invalid id as the numbers repeat " + assumedPairs + " times.");
                return true;
            }
        }
        // System.out.println("Number " + id + " is a valid id as it doesn't repeat.");
        return false;
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
                    if (isInvalidId(String.valueOf(i)))
                        invalidIdSum = invalidIdSum.add(i);
            }
        }
        catch (Exception e)
        {
            System.out.println("oof" + e);
        }

        System.out.println("invalid id sum: " + invalidIdSum);
    }
}