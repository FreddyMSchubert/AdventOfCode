import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/*

.......S.......
.......|.......
......|^|......
......|.|......
.....|^|^|.....
...............
.....^.^.^.....
...............
....^.^...^....
...............
...^.^...^.^...
...............
..^...^.....^..
...............
.^.^.^.^.^...^.
...............

1. find the s
2. then maintain a list of beam location x values
3. loop through, progressing all beams before going one down vertically

 */

public class Main
{
    public static void main(String[] args)
    {
        ArrayList<String> map = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                map.add(line);
            }
        }
        catch (Exception e)
        {
            System.out.println("oof" + e);
        }

        // PART 1

        Set<Integer> beamsX = new LinkedHashSet<>();
        int beamSplits = 0;

        beamsX.add(map.getFirst().indexOf('S'));

        for (int y = 1; y < map.size(); y++) {
            ArrayList<Integer> beamsToBeAdded = new ArrayList<>();
            ArrayList<Integer> beamsToBeRemoved = new ArrayList<>();
            for (Integer beamX : beamsX) {
                if (map.get(y).charAt(beamX) != '^')
                    continue;
                beamsToBeRemoved.add(beamX);
                beamsToBeAdded.add(beamX - 1);
                beamsToBeAdded.add(beamX + 1);
                beamSplits++;
            }
            beamsX.addAll(beamsToBeAdded);
            beamsX.removeAll(beamsToBeRemoved);
        }

        System.out.println("Beam Splits: " + beamSplits);
    }
}
