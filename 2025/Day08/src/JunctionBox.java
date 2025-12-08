import java.util.*;

public class JunctionBox
{
    int x;
    int y;
    int z;
    Set<JunctionBox> connections = new HashSet<>();
    Map<JunctionBox, Double> distances = new HashMap<>();

    public JunctionBox(int posX, int posY, int posZ)
    {
        x = posX;
        y = posY;
        z = posZ;
    }

    public double distance(JunctionBox other)
    {
        double deltaX = Math.abs(x - other.x);
        double deltaY = Math.abs(y - other.y);
        double deltaZ = Math.abs(z - other.z);

        double squared = Math.pow(deltaX, 2) + Math.pow(deltaY, 2) + Math.pow(deltaZ, 2);

        return Math.sqrt(squared);
    }

    public void calcDistances(ArrayList<JunctionBox> junctionBoxes) {
        for (JunctionBox junctionBox : junctionBoxes) {
            if (junctionBox == this) continue;

            distances.put(junctionBox, distance(junctionBox));
        }
    }

    public String getString() {
        return x + "," + y + "," + z;
    }
}
