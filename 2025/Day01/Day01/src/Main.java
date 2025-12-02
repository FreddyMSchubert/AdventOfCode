import java.io.BufferedReader;
import java.io.FileReader;

// 5856
// 6851
// 5847

public class Main
{
    public static void main(String[] args)
    {
        int currentNotch = 50;
        int zeroNotches = 0;
        int passedZeroCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                char directionChar = line.charAt(0);
                String numString = line.substring(1);
                int num = Integer.parseInt(numString);

                System.out.println("Line: " + line + "; char: " + directionChar + "; num: " + num);

                while (num > 0)
                {
                    if (directionChar == 'L')
                        currentNotch--;
                    else
                        currentNotch++;
                    if (currentNotch > 99) currentNotch = 0;
                    if (currentNotch < 0) currentNotch = 99;
                    if (currentNotch == 0) passedZeroCount++;

                    num--;
                }

                System.out.println("currentNotch: " + currentNotch + "; zeroNotches: " + zeroNotches + "; passedZeroCount: " + passedZeroCount);
            }
        }
        catch (Exception e)
        {
            System.out.println("oof");
        }

    }
}
