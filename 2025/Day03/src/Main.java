import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;

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
        int twoDigitJoltsTracker = 0;
        BigInteger twelveDigitJoltsTracker = BigInteger.ZERO;

        try (BufferedReader br = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                ArrayList<Integer> battery = splitBattery(line);

                // ----- 2 - digit jolts

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

                System.out.println("Battery " + line + " results in largest possible 2 - digit jolts " + battery.get(tenPlace) + battery.get(onePlace) + ".");

                twoDigitJoltsTracker += battery.get(tenPlace) * 10 + battery.get(onePlace);

                // ----- 12 - digit jolts

                Integer[] twelveDigits = new Integer[12];
                for (int i = 0; i < 12; i++)
                    twelveDigits[i] = 0;

                for (int currDigit = 0; currDigit < 12; currDigit++) {
                    int prevDigit = currDigit == 0 ? -1 : twelveDigits[currDigit - 1];
                    for (int i = prevDigit + 1; i < battery.size() - 12 + currDigit + 1; i++) {
                        if (battery.get(i) > battery.get(twelveDigits[currDigit]) || i == prevDigit + 1)
                            twelveDigits[currDigit] = i;
                    }
                }

                BigInteger twelveMax = BigInteger.ZERO;
                BigInteger currentMult = BigInteger.ONE;
                for (int i = 11; i >= 0; i--) {
                    twelveMax = twelveMax.add(BigInteger.valueOf(battery.get(twelveDigits[i])).multiply(currentMult));
                    currentMult = currentMult.multiply(BigInteger.TEN);
                }

                System.out.println("Battery " + line + " results in largest possible 12 - digit jolts " + twelveMax + ".");

                twelveDigitJoltsTracker = twelveDigitJoltsTracker.add(twelveMax);
            }

            System.out.println("For all batteries the largest possible 2 - digit jolts are " + twoDigitJoltsTracker + ". :D");
            System.out.println("For all batteries the largest possible 12 - digit jolts are " + twelveDigitJoltsTracker + ". :P");
        }
        catch (Exception e)
        {
            System.out.println("oof" + e);
        }
    }
}