import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        ArrayList<String> input = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitLine = line.split("\\s+");
                for (int i = 0; i < splitLine.length; i++) {
                    if (i >= input.size())
                        input.add(splitLine[i]);
                    else
                        input.set(i, input.get(i) + " " + splitLine[i]);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("oof" + e);
        }

        long taskOneResult = 0;

        for (String string_calculation : input) {
            String[] string_calculation_split = string_calculation.split("\\s+");
            char operation = string_calculation_split[string_calculation_split.length - 1].charAt(0);

            long total = 0;
            for (int i = 0; i < string_calculation_split.length - 1; i++) {
                if (operation == '+' || i == 0)
                    total += Long.parseLong(string_calculation_split[i]);
                else if (operation == '*')
                    total *= Long.parseLong(string_calculation_split[i]);

            }

            System.out.println("Calculation " + string_calculation + " results in total: " + total + ".");

            taskOneResult += total;
        }

        System.out.println("Task 1 result: " + taskOneResult);
    }
}
