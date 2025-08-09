/**
 * Demonstrates the calculation of shortest paths in the US Highway
 * network, showing the functionality of GraphProcessor and using
 * Visualize
 * To do: Add your name(s) as authors
 */
import java.security.InvalidAlgorithmParameterException;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class GraphDemo {
    public static void main(String[] args) throws Exception{
        GraphProcessor g = new GraphProcessor();
        FileInputStream a = new FileInputStream("data/usa.graph");
        Visualize v = new Visualize("data/usa.vis", "images/usa.png");
        g.initialize(a);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the latitude of the first city");
        double lx1 = sc.nextDouble(); 
        System.out.println("Enter the longtitude of the first city");
        double ly1 = sc.nextDouble();
        System.out.println("Enter the latitude of the second city");
        double lx2 = sc.nextDouble();
        System.out.println("Enter the longtitude of the second city");
        double ly2 = sc.nextDouble();

        Point p1 = new Point(lx1, ly1);
        Point p2 = new Point(lx2, ly2);
        
        long startTime = System.nanoTime();
        p1 = g.nearestPoint(p1);
        p2 = g.nearestPoint(p2);
        List<Point> path = g.route(p1, p2);
        System.out.print("The distance between the two points is " + g.routeDistance(path));
        System.out.println(" miles");
        long elapsedNanos = System.nanoTime() - startTime;

        double newTime = (double) elapsedNanos/1000000000;
        System.out.print("It took " + newTime);
        System.out.println(" seconds for this route!");
        v.drawRoute(path);
    }
}
