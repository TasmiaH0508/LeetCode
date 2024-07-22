class KeyValPair {
    int height;
    String name;
    KeyValPair(int h, String n) {
        height = h;
        name = n;
    }
}

class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        // stability of sorting not important here since the heights 
        // are distinct anyway
        Comparator<KeyValPair> c = (a, b) -> Integer.compare(-a.height, -b.height);
        Queue<KeyValPair> pq = new PriorityQueue<>(c);
        for (int i = 0; i < heights.length; i++) {
            KeyValPair k = new KeyValPair(heights[i], names[i]);
            pq.add(k);
        }
        String[] res = new String[names.length];
        int i = 0;
        while (!pq.isEmpty()) {
            KeyValPair k = pq.poll();
            res[i] = k.name;
            i++;
        }
        return res;
    }
}