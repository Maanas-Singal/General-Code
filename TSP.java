import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class City {
    int x;
    int y;

    public City(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distanceTo(City otherCity) {
        int dx = this.x - otherCity.x;
        int dy = this.y - otherCity.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}

public class TravelingSalesmanProblem {

    public static List<City> findShortestRoute(List<City> cities) {
        int n = cities.size();
        boolean[] visited = new boolean[n];
        int[] route = new int[n];

        // Start from the first city (0)
        int currentCityIndex = 0;
        visited[currentCityIndex] = true;
        route[0] = currentCityIndex;

        for (int i = 1; i < n; i++) {
            int nearestCityIndex = -1;
            double minDistance = Double.MAX_VALUE;

            // Find the nearest unvisited city
            for (int j = 0; j < n; j++) {
                if (!visited[j]) {
                    double distance = cities.get(currentCityIndex).distanceTo(cities.get(j));
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestCityIndex = j;
                    }
                }
            }

            // Move to the nearest unvisited city
            visited[nearestCityIndex] = true;
            route[i] = nearestCityIndex;
            currentCityIndex = nearestCityIndex;
        }

        // Add the first city to complete the route
        route[n - 1] = 0;

        // Construct the final route based on the route indices
        List<City> shortestRoute = new ArrayList<>();
        for (int cityIndex : route) {
            shortestRoute.add(cities.get(cityIndex));
        }

        return shortestRoute;
    }

    public static void main(String[] args) {
        // Example usage:
        List<City> cities = new ArrayList<>();
        cities.add(new City(0, 0));
        cities.add(new City(1, 5));
        cities.add(new City(2, 3));
        cities.add(new City(5, 1));

        List<City> shortestRoute = findShortestRoute(cities);
        System.out.println("Shortest Route: " + shortestRoute);
    }
}
