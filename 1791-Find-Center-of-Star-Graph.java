class Solution {
    /**
    All the nodes have a distinct value.
    
    Method:
    Use a hashmap to map the value of the nodes to the number of times 
    and edge is connected to the node.
    Run through the hashmap and return the node for which the 
    above-mentioned frequency is the greatest. */
    public int findCenter(int[][] edges) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int end1 = edges[i][0];
            int end2 = edges[i][1];
            if (freq.containsKey(end1)) {
                Integer f = freq.get(end1);
                f++;
                freq.put(end1, f);
            } else {
                freq.put(end1, 1);
            }
            if (freq.containsKey(end2)) {
                Integer f = freq.get(end2);
                f++;
                freq.put(end2, f);
            } else {
                freq.put(end2, 1);
            }
        }

        int i = 1;
        int maxFreq = freq.get(1);
        int max = 1;
        while (freq.containsKey(i)) {
            int currFreq = freq.get(i);
            if (currFreq > maxFreq) {
                max = i;
            }
            i++;
        }
        return max;
    }
}