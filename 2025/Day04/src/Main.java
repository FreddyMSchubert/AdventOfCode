import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/*

..xx.xx@x.
x@@.@.@.@@
@@@@@.x.@@
@.@@@@..@.
x@.@@@@.@x
.@@@@@@@.@
.@.@.@.@@@
x.@@@.@@@@
.@@@@@@@@.
x.x.@@@.x.

 */

public class Main
{
    public static void main(String[] args)
    {
        try (BufferedReader br = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;
            List<String> map = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                map.add(line);
            }

            int totalAccessibleRolls = 0;
            int iteration = 0;

            while (true)
            {
                List<Integer> accessibleRollsX = new ArrayList<>();
                List<Integer> accessibleRollsY = new ArrayList<>();

                for (int y = 0; y < map.size(); y++)
                {
                    for (int x = 0; x < map.get(y).length(); x++)
                    {
                        if (map.get(y).charAt(x) != '@')
                            continue;

                        int rollsCount = 0;
                        for (int dy = -1; dy <= 1; dy++)
                        {
                            for (int dx = -1; dx <= 1; dx++)
                            {
                                int localY = y + dy;
                                int localX = x + dx;
                                if (localY < 0 || localY >= map.size() || localX < 0 || localX >= map.get(localY).length())
                                    continue;
                                if (localY == y && localX == x)
                                    continue;
                                if (map.get(localY).charAt(localX) == '@')
                                    rollsCount++;
                            }
                        }
                        if (rollsCount < 4)
                        {
                            accessibleRollsX.add(x);
                            accessibleRollsY.add(y);
                            System.out.println("Accessible roll found at [" + x + "|" + y + "].");
                        }
                    }
                }

                if (accessibleRollsX.size() <= 0)
                {
                    break;
                }

                totalAccessibleRolls += accessibleRollsX.size();

                System.out.println("Iteration " + iteration + ": " + accessibleRollsX.size() + " rolls cleared out.");
                iteration++;

                for (int i = 0; i < accessibleRollsX.size(); i++)
                {
                    int x = accessibleRollsX.get(i);
                    int y = accessibleRollsY.get(i);
                    char[] charString = map.get(y).toCharArray();
                    charString[x] = 'x';
                    map.set(y, String.copyValueOf(charString));
                }
            }

            System.out.println("Total accessible rolls are: " + totalAccessibleRolls + ". :P");
        }
        catch (Exception e)
        {
            System.out.println("oof" + e);
        }
    }
}