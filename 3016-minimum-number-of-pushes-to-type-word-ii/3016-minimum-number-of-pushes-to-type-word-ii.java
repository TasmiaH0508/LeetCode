class Pair {
    int x;
    char y;
    Pair(int i, char c) {
        x = i;
        y = c;
    }
}

class Solution {
    public int minimumPushes(String word) {
        Map<Character, Integer> freq = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            if (freq.containsKey(word.charAt(i))) {
                int f = freq.get(word.charAt(i));
                f++;
                freq.put(word.charAt(i), f);
            } else {
                freq.put(word.charAt(i), 1);
            }
        }
        Comparator<Pair> c = (a, b) -> Integer.compare(-a.x, -b.x);
        Queue<Pair> pq = new PriorityQueue<>(c);
        for (Character p : freq.keySet()) {
            pq.add(new Pair(freq.get(p), p));
        }
        int click = 1;
        int sum = 0;
        while (!pq.isEmpty()) {
            for (int j = 0; j < 8; j++) {
                if (!pq.isEmpty()) {
                    Pair p = pq.poll();
                    int f = p.x;
                    sum += (click * f);
                } else {
                    break;
                }
            }
            click++;
        }
        return sum;
    }
}