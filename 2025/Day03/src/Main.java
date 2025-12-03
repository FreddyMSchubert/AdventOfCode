import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/*

119811
118911
999997

pick largest for 10 place, unless at the very end, then pick second largest or just earlier occurence of same digit
pick largest to the right of that for 1 place

 */

public class Main
{
    static ArrayList<Integer> splitBattery(String strBatt) {
        ArrayList<Integer> splitBatt = new ArrayList<>();
        while (!strBatt.isEmpty()) {
            splitBatt.add(strBatt.charAt(0) - '0');
            strBatt = strBatt.substring(1);
        }
        return splitBatt;
    }

    public static void main(String[] args)
    {
        int joltsTracker = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<Integer> battery = splitBattery(line);

                // 1. find largest digit not at the very end
                int tenPlace = 0;
                for (int i = 0; i < battery.size() - 1; i++) {
                    if (battery.get(i) > battery.get(tenPlace))
                        tenPlace = i;
                }

                // 2. find largest to the right of that
                int onePlace = tenPlace + 1;
                for (int i = onePlace; i < battery.size(); i++) {
                    if (battery.get(i) > battery.get(onePlace))
                        onePlace = i;
                }

                System.out.println("Battery " + line + " results in largest possible jolts " + battery.get(tenPlace) + battery.get(onePlace) + ".");

                joltsTracker += battery.get(tenPlace) * 10 + battery.get(onePlace);
            }

            System.out.println("For all batteries the largest possible jolts are " + joltsTracker + ". :D");
        }
        catch (Exception e)
        {
            System.out.println("oof" + e);
        }
    }
}