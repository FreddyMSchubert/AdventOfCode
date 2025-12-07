import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/*

1590 too low

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

        // PART 2
        HashMap<Long, Long> memo = new HashMap<>(); // y * maxX + x, result
        long timelines = getTimelinesOriginatingFrom(map, map.getFirst().indexOf('S'), 0, memo);
        System.out.println("Found " + timelines + " possible tachyon timelines.");
    }

    // stack overflow incoming
    // yes took way too long doing memoization i guess
    // memoization is so op :)
    public static long getTimelinesOriginatingFrom(ArrayList<String> map, long x, long y, HashMap<Long, Long> memo) {
        if (memo.containsKey(y * map.getFirst().length() + x))
            return memo.get(y * map.getFirst().length() + x);

        long originalY = y;

        do {
            y++;
            if (memo.containsKey(y * map.getFirst().length() + x))
                return memo.get(y * map.getFirst().length() + x);
            if (y >= map.size())
                return 1;
        }
        while (map.get((int)y).charAt((int)x) != '^');

        long timelinesOriginating = getTimelinesOriginatingFrom(map, x - 1, y, memo) + getTimelinesOriginatingFrom(map, x + 1, y, memo);
        memo.put(originalY * map.getFirst().length() + x, timelinesOriginating);
        return timelinesOriginating;
    }
}
