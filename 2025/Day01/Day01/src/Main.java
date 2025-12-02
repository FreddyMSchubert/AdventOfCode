import java.io.BufferedReader;
import java.io.FileReader;

// 452

public class Main
{
    public static void main(String[] args)
    {
        int currentNotch = 50;
        int zeroNotches = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                char directionChar = line.charAt(0);
                String numString = line.substring(1);
                int num = Integer.parseInt(numString);

                System.out.println("Line: " + line + "; char: " + directionChar + "; num: " + num);

                if (directionChar == 'L')
                    currentNotch -= num;
                else
                    currentNotch += num;

                while (currentNotch >= 100)
                    currentNotch -= 100;
                while (currentNotch < 0)
                    currentNotch += 100;

                if (currentNotch == 0)
                    zeroNotches++;

                System.out.println("currentNotch: " + currentNotch + "; zeroNotches: " + zeroNotches);
            }
        }
        catch (Exception e)
        {
            System.out.println("oof");
        }

    }
}
