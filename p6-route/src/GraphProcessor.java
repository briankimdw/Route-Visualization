import java.security.InvalidAlgorithmParameterException;
import java.util.List;
import java.io.FileInputStream;
import java.util.*;

/**
 * Models a weighted graph of latitude-longitude points
 * and supports various distance and routing operations.
 * To do: Add your name(s) as additional authors
 * @author Brian Kim
 *
 */
public class GraphProcessor {
    /**
     * Creates and initializes a graph from a source data
     * file in the .graph format. Should be called
     * before any other methods work.
     * @param file a FileInputStream of the .graph file
     * @throws Exception if file not found or error reading
     */
    private HashMap<Point, HashSet<Point>> aList = new HashMap<>();
    public void initialize(FileInputStream file) throws Exception {
        Scanner scan = new Scanner(file);
        int nodes = scan.nextInt();
        int edges = scan.nextInt();
        ArrayList<Point> temp = new ArrayList<>();
        scan.nextLine();
        
        for(int i = 0; i < nodes; i++){
            String line = scan.nextLine();
            String[] lineSplit = line.split(" ");
            Point A = new Point(Double.parseDouble(lineSplit[1]), Double.parseDouble(lineSplit[2]));
            temp.add(A);
            aList.put(A, new HashSet<>());
        }

        for(int i = 0; i < edges; i++){
            String line = scan.nextLine();
            String[] lineSplit = line.split(" ");
            int ind1 = Integer.parseInt(lineSplit[0]);
            int ind2 = Integer.parseInt(lineSplit[1]);

            Point p1 = temp.get(ind1);
            Point p2 = temp.get(ind2);

            aList.get(p1).add(p2);
            aList.get(p2).add(p1);
        }
        scan.close();

    }
    /**
     * Searches for the point in the graph that is closest in
     * straight-line distance to the parameter point p
     * @param p A point, not necessarily in the graph
     * @return The closest point in the graph to p
     */
    public Point nearestPoint(Point p) {
        double nearest = Integer.MAX_VALUE;
        Point re = p;

        for(Point po: aList.keySet()){
            if(p.distance(po) < nearest){
                nearest = p.distance(po);
                re = po;
            }
        }
        return re;
    }
    /**
     * Calculates the total distance along the route, summing
     * the distance between the first and the second Points, 
     * the second and the third, ..., the second to last and
     * the last. Distance returned in miles.
     * @param start Beginning point. May or may not be in the graph.
     * @param end Destination point May or may not be in the graph.
     * @return The distance to get from start to end
     */
    public double routeDistance(List<Point> route) {
        double c = 0;

        for(int i = 1; i < route.size(); i ++){
            c += route.get(i-1).distance(route.get(i));
        }
        return c;
    }
    /**
     * Checks if input points are part of a connected component
     * in the graph, that is, can one get from one to the other
     * only traversing edges in the graph
     * @param p1 one point
     * @param p2 another point
     * @return true if p2 is reachable from p1 (and vice versa)
     */
    public boolean connected(Point p1, Point p2) {
        Set<Point> visited = new HashSet<>();
        Stack<Point> toExplore = new Stack();
        Point cur = p1;
        toExplore.add(cur);
        visited.add(cur);

        while(!toExplore.isEmpty()){
            cur = toExplore.pop();
            
            for(Point con: aList.get(cur)){
                if(!visited.contains(con)){
                    if(con.equals(p2)){
                        return true;
                    }
                    visited.add(con);
                    toExplore.push(con);
                }
            }
        }
        return false;
    }
    /**
     * Returns the shortest path, traversing the graph, that begins at start
     * and terminates at end, including start and end as the first and last
     * points in the returned list. If there is no such route, either because
     * start is not connected to end or because start equals end, throws an
     * exception.
     * @param start Beginning point.
     * @param end Destination point.
     * @return The shortest path [start, ..., end].
     * @throws InvalidAlgorithmParameterException if there is no such route, 
     * either because start is not connected to end or because start equals end.
     */
    public List<Point> route(Point start, Point end) throws InvalidAlgorithmParameterException {
        Map<Point, Double> distance = new HashMap<>();
        Comparator<Point> comp = (a,b) -> (int)(distance.get(a)-distance.get(b));
        Map<Point, Point> prev = new HashMap<>();
        PriorityQueue<Point> toExplore = new PriorityQueue<>(comp);
        Point cur = start;
        distance.put(cur, 0.0);
        toExplore.add(cur);
        LinkedList<Point> re = new LinkedList();

        while(!toExplore.isEmpty()){
            cur = toExplore.remove();
            for(Point con: aList.get(cur)){
                double w = cur.distance(con);
                if(!distance.containsKey(con) || (distance.get(con) > distance.get(cur) + w)){
                    distance.put(con, distance.get(cur) + w);
                    prev.put(con, cur);
                    toExplore.add(con);
                }
            }
        }

        if(prev.containsKey(end)){
            re.addFirst(end);

            while(end != start){
                re.addFirst(prev.get(end));
                end = prev.get(end);
            }
            return re;
        }
        throw new InvalidAlgorithmParameterException("No path between start and end");
    }
}
