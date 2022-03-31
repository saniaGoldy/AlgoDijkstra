import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static boolean isNumber(String s) {
        return s.matches("\\d+");
    }

    public static void main(String[] arg) {
        int V = 6;
        int source = 0;
        Scanner scanner = new Scanner(System.in);
        boolean def = true;

        while (true) {
            System.out.println("Input the number of vertices, or press enter to use default value (6)");
            String input = scanner.nextLine();
            if (isNumber(input)) {
                V = Integer.parseInt(input);
                def = false;
                break;
            } else if (input.isEmpty()) {
                break;
            } else {
                System.out.println("Input is not valid please try again");
            }
        }

        // Adjacency list representation of the connected edges
        List<List<Node>> adj = new ArrayList<>();

        // Initialize list for every node
        for (int i = 0; i < V; i++) {
            List<Node> item = new ArrayList<>();
            adj.add(item);
        }

        while (true) {
            if (def){
                adj.get(0).add(new Node(1, 9));
                adj.get(0).add(new Node(2, 6));
                adj.get(0).add(new Node(3, 5));
                adj.get(0).add(new Node(4, 3));

                adj.get(1).add(new Node(5,8));

                adj.get(2).add(new Node(1, 2));
                adj.get(2).add(new Node(3, 4));
                break;
            }
            System.out.println("Insert 's' to stop adding nodes, insert 'd' to add default nodes, insert any other value to add next node");
            String input = scanner.nextLine();
            if (Objects.equals(input, "s")) {
                break;
            } else {
                System.out.println("Input the vertice from 0 to " + (V-1));
                String vertice = scanner.nextLine();
                System.out.println("Input the node from 0 to " + (V-1));
                String node = scanner.nextLine();
                System.out.println("Input it`s cost: ");
                String cost = scanner.nextLine();
                if (isNumber(vertice) && isNumber(node) && isNumber(cost)){
                    adj.get(Integer.parseInt(vertice)).add(new Node(Integer.parseInt(node), Integer.parseInt(cost)));
                }else {
                    System.out.println("input is not valid");
                }
            }
        }

        // Calculate the single source the shortest path
        DijkstraPriorityQueue dpq = new DijkstraPriorityQueue(V);
        dpq.runAlgorithm(adj, source);

        // Print the shortest path to all the nodes from the source node
        System.out.println("The shorted path from node :");
        for (int i = 0; i < dpq.dist.length; i++)
            System.out.println(source + " to " + i + " is "
                    + dpq.dist[i]);
    }

}
