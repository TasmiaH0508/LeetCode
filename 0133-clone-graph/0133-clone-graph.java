/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    /**
    1. Run through the given graph in bfs style
    2. While running through the graph, fill up the hashmap 
    that maps from the value of the node to the connected nodes
    3. After the hashmap is complete, run through the hashmap and
    create the graph. At the same time, tracking must be carried 
    out to ensure duplicate nodes are not added. This can be done by 
    using an array. 
    */ 
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        HashMap<Integer, LinkedList<Integer>> graphMap = new HashMap<>();
        LinkedList<Node> q = new LinkedList<>();
        q.add(node);
        HashSet<Node> visited = new HashSet<>();
        int nodeCount = 1; // deliberately set as 1
        while (!q.isEmpty()) {
            Node curr = q.poll();
            nodeCount++;
            List<Node> neighbourNodes = curr.neighbors;
            LinkedList<Integer> ll = new LinkedList<>();
            for (Node n : neighbourNodes) {
                if (!visited.contains(n)) {
                    visited.add(n);
                    q.add(n);
                }
                ll.addFirst(n.val);
            }
            if (ll != null) {
                graphMap.put(curr.val, ll);
            }
        }
        Node[] arr = new Node[nodeCount];
        Node newGraph = new Node(1);
        if (!graphMap.containsKey(newGraph.val)) {
            return new Node(1);
        } else {
            // arr[i] contains the node with val i,
            // arr[1] containes the node with val 1
            int i = 1;
            while (graphMap.containsKey(i)) {
                if (arr[i] == null) {
                    arr[i] = new Node(i);
                }
                LinkedList<Integer> ll = graphMap.get(i);
                List<Node> nodeList = arr[i].neighbors;
                for (Integer k : ll) {
                    if (arr[k] == null) {
                        arr[k] = new Node(k);
                        nodeList.add(arr[k]);
                    } else {
                        nodeList.add(arr[k]);
                    }
                }
                i++;
            }
        }
        return arr[1];
    }
}