import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
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
    }
}
record Pair<A, B>(A first, B second) {}
