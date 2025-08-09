## US Route Visualizer (P6-Route)

Shortest-path routing and visualization on the US highway network. Given two latitude/longitude inputs, the program:
- Finds the nearest highway vertices
- Computes the shortest path between them (miles)
- Reports total distance and computation time
- Draws the route on a US map

### What I achieved
- Implemented a routing engine in `p6-route/src/GraphProcessor.java`:
  - `initialize(...)`: loads `.graph` files into an adjacency list of `Point` vertices
  - `nearestPoint(p)`: closest vertex by straight-line distance
  - `connected(a, b)`: reachability via DFS
  - `route(start, end)`: Dijkstra’s algorithm over edge weights from `Point.distance`
  - `routeDistance(path)`: sums segment distances along a route
- Built a demo in `p6-route/src/GraphDemo.java` to accept user coordinates, compute, time, and visualize routes on `images/usa.png` using `p6-route/src/Visualize.java`.
- Verified behavior with JUnit tests: `p6-route/src/TestSimpleGraphProcessor.java` and `p6-route/src/TestUSGraphProcessor.java`.

### How it works (high level)
1. Parse `.graph` files into a `Map<Point, Set<Point>>` adjacency list.
2. Map arbitrary inputs to graph vertices via `nearestPoint`.
3. Use Dijkstra to find the shortest path between vertices (edge weights = straight-line distances between endpoints).
4. Sum with `routeDistance` and render with `Visualize.drawRoute`.

### Build and run
Run these from the `p6-route/` directory:

```
mkdir -p out
javac -d out src/*.java
java -cp out GraphDemo
```

When prompted, enter two coordinates (lat then lon). Example pairs:
- Durham, NC: 35.989709 / -78.902124
- Redmond, WA: 47.679175 / -122.184502

### Project layout
- `p6-route/src/Point.java`: immutable lat/lon with distance in miles
- `p6-route/src/GraphProcessor.java`: parsing + nearest, connected, Dijkstra, distance
- `p6-route/src/Visualize.java`: reads `.vis`, draws points/edges/routes on map images
- `p6-route/src/GraphDemo.java`: CLI demo for the USA dataset
- `p6-route/data/*.graph`, `p6-route/images/*.png`, `p6-route/data/*.vis`: graph data and map assets; `p6-route/data/uscities.csv` for reference
- `p6-route/lib/*`: JUnit jars for local testing

### Notes
- Distances use a straight-line approximation along edges; this is for educational/visual purposes.
- Visualization may take a moment for long routes; the window can open behind other windows.


