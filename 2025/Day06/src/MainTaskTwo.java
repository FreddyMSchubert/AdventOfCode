import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class MainTaskTwo
{
    public static void main(String[] args)
    {
        ArrayList<String> inputs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("/Users/freddy/AdventOfCode/2025/Day06/src/input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                char[] lineChars = line.toCharArray();
                for (int i = 0; i < lineChars.length; i++) {
                    if (i >= inputs.size())
                        inputs.add(String.valueOf(lineChars[i]));
                    else
                        inputs.set(i, inputs.get(i) + lineChars[i]);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("oof" + e);
        }

        ArrayList<ArrayList<String>> groupedInputs = new ArrayList<>();
        int j = 0;
        for (String input : inputs) {
            if (input.isBlank()) {
                j++;
                continue;
            }

            if (j >= groupedInputs.size()) {
                groupedInputs.add(j, new ArrayList<>());
            }

            groupedInputs.get(j).add(input);
        }

        long taskTwoResult = 0;
        for (ArrayList<String> singleInput : groupedInputs) {
            int inputWidth = singleInput.getFirst().length() - 1;
            char operation = singleInput.getFirst().charAt(inputWidth);
            singleInput.set(0, singleInput.getFirst().substring(0, inputWidth));

            long inputResult = 0;
            for (String inputNbrString : singleInput) {
                long inputNbr = Long.parseLong(inputNbrString.trim());
                if (operation == '+' || inputNbrString == singleInput.getFirst())
                    inputResult += inputNbr;
                else if (operation == '*')
                    inputResult *= inputNbr;
                else
                    System.out.println("Encountered unexpected operation char " + operation);
            }

            taskTwoResult += inputResult;
        }

        System.out.println("Task two result is " + taskTwoResult);
    }
}
