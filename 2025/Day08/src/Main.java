import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main
{
    public static void main(String[] args)
    {
        ArrayList<JunctionBox> junctionBoxes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("./src/input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split(",");
                junctionBoxes.add(new JunctionBox(Integer.parseInt(lineSplit[0]), Integer.parseInt(lineSplit[1]), Integer.parseInt(lineSplit[2])));
            }
        }
        catch (Exception e)
        {
            System.out.println("oof" + e);
        }

        // calc all distances & cache them
        for (JunctionBox junctionBox : junctionBoxes) {
            junctionBox.calcDistances(junctionBoxes);
        }

        for (int i = 0; i < 1000; i++) {
            JunctionBox bestFrom = null;
            JunctionBox bestTo = null;
            double bestDistance = Double.MAX_VALUE;
            for (JunctionBox from : junctionBoxes) {
                for (Map.Entry<JunctionBox, Double> entry : from.distances.entrySet()) {
                    JunctionBox to = entry.getKey();
                    double connDist = entry.getValue();

                    if (from.getString().compareTo(to.getString()) >= 0) {
                        continue;
                    }

                    if (from.connections.contains(to)) {
                        continue;
                    }

                    if (connDist < bestDistance) {
                        bestDistance = connDist;
                        bestFrom = from;
                        bestTo = to;
                    }
                }
            }

            if (bestFrom == null) {
                break;
            }

            // establish connection
            bestFrom.connections.add(bestTo);
            bestTo.connections.add(bestFrom);
            System.out.println("Established connection between " + bestFrom.getString() + " and " + bestTo.getString() + ".");
        }

        // find networks & corresponding sizes
        ArrayList<Integer> foundNetworkSizes = new ArrayList<>();
        ArrayList<JunctionBox> unaccountedForJunctionBoxes = new ArrayList<>(junctionBoxes);
        while (!unaccountedForJunctionBoxes.isEmpty()) {
            System.out.println("Starting a new network!");
            JunctionBox first = unaccountedForJunctionBoxes.getFirst(); // starting point
            System.out.println("Adding to network: " + first.getString() + ".");
            int networkSize = 1;
            unaccountedForJunctionBoxes.remove(first);
            Set<JunctionBox> connections = first.connections;
            while (!connections.isEmpty()) {
                JunctionBox firstConn = connections.iterator().next();
                for (JunctionBox element : firstConn.connections) {
                    if (!unaccountedForJunctionBoxes.contains(element))
                        continue;
                    connections.add(element);
                }
                unaccountedForJunctionBoxes.remove(firstConn);
                connections.remove(firstConn);
                networkSize++;
                System.out.println("Adding to network: " + firstConn.getString());
            }
            foundNetworkSizes.add(networkSize);
            System.out.println("Found a new network size: " + networkSize + ".");
        }

        foundNetworkSizes.sort(Comparator.reverseOrder());
        System.out.println("Three largest circuits: " + foundNetworkSizes.get(0) * foundNetworkSizes.get(1) * foundNetworkSizes.get(2) + ".");
    }

    public static boolean areJunctionBoxesConnected(JunctionBox box1, JunctionBox box2, ArrayList<JunctionBox> alreadyChecked) {
        if (box1 == box2)
            return true;
        alreadyChecked.add(box1);
        for (JunctionBox connection : box1.connections) {
            if (alreadyChecked.contains(connection))
                continue;
            if (areJunctionBoxesConnected(connection, box2, alreadyChecked))
                return true;
        }
        return false;
    }
}
