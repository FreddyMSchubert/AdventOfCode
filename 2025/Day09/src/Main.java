import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        ArrayList<Pair<Integer, Integer>> redTiles = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split(",");
                redTiles.add(new Pair<>(Integer.parseInt(lineSplit[0]), Integer.parseInt(lineSplit[1])));
            }
        }
        catch (Exception e)
        {
            System.out.println("oof" + e);
        }

        long largestRect = -1;
        for (int first = 0; first < redTiles.size(); first++) {
            for (int second = first + 1; second < redTiles.size(); second++) {
                long xLen = Math.abs(redTiles.get(first).first() - redTiles.get(second).first()) + 1;
                long yLen = Math.abs(redTiles.get(first).second() - redTiles.get(second).second()) + 1;
                long rectSize = xLen * yLen;
                if (largestRect < rectSize) {
                    System.out.println("Reassigned largestRect from " + largestRect + " to " + rectSize + ".");
                    largestRect = rectSize;
                }
            }
        }
    }
}
record Pair<A, B>(A first, B second) {}