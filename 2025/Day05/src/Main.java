import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        List<Pair<Long, Long>> ranges = new ArrayList<>();
        List<Long> ingredientIds = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) continue;
                if (line.indexOf('-') >= 0) {
                    // range
                    String[] rangeParts = line.split("-");
                    ranges.add(new Pair<>(Long.valueOf(rangeParts[0]), Long.valueOf(rangeParts[1])));
                } else {
                    // ingredient id
                    ingredientIds.add(Long.valueOf(line));
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("oof" + e);
        }

        // PART 1

        int freshIngredientCount = 0;

        for (Long ingredientId : ingredientIds) {
            for (Pair<Long, Long> range : ranges) {
                if (ingredientId >= range.first() && ingredientId <= range.second()) {
                    freshIngredientCount++;
                    break;
                }
            }
        }

        System.out.println("Found " + freshIngredientCount + " fresh ingredients. :)");

        // PART 2

        // create a sanitized range pair array first
        // for each range to be added, check existing ranges.
        // if overlap, remove that range and incorporate it into current range

        List<Pair<Long, Long>> sanitizedRanges = new ArrayList<>();

        for (Pair<Long, Long> rangeToAdd : ranges) {
            Iterator<Pair<Long, Long>> it = sanitizedRanges.iterator();
            while (it.hasNext()) {
                Pair<Long, Long> rangeToCompare = it.next();

                if (rangeToAdd.first() <= rangeToCompare.second() && rangeToAdd.second() >= rangeToCompare.first())
                {
                    System.out.print("Detected an overlap between ranges " + rangeToAdd.first() + "-" + rangeToAdd.second() + " and " + rangeToCompare.first() + "-" + rangeToCompare.second() + ", turning that into range ");
                    rangeToAdd = new Pair<>(Long.min(rangeToAdd.first(), rangeToCompare.first()), Long.max(rangeToAdd.second(), rangeToCompare.second()));
                    System.out.println(rangeToAdd.first() + "-" + rangeToAdd.second() + ". :)");
                    it.remove();
                    it = sanitizedRanges.iterator();
                }
            }

            sanitizedRanges.add(rangeToAdd);
        }

        long totalUnspoiledIngredientIds = 0;
        for (Pair<Long, Long> sanitizedRange : sanitizedRanges) {
            totalUnspoiledIngredientIds += sanitizedRange.second() - sanitizedRange.first() + 1;
            System.out.println("Found sanitized range " + sanitizedRange.first() + "-" + sanitizedRange.second() + ".");
        }
        System.out.println("Total unspoiled ingredient IDs: " + totalUnspoiledIngredientIds + ".");
    }
}
record Pair<A, B>(A first, B second) {}
