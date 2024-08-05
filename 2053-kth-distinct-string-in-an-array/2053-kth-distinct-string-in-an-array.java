class Pair {
    int index;
    String str;
    Pair(int i, String s) {
        index = i;
        str = s;
    }
}

class Solution {
    /**
    Naiive:
    1. Iterate through the array in O(n) time and initialise a hashmap that maps a
    character to the index of the positions it appears in the array. If the letter
    appears again, add it to the list. 
    2. For the letters in the hashmap, if it appears only once, create arr = [index, String] and 
    push this into the priority queue. O(n) time. 
    3. If the size of the pq is less than k, return ''. If the size of the pq is equal, 
    poll from pq. Else, keep polling until the correct elem is reached. O(n lg k)

    - use a max pq, so that if the max index is larger, it will be polled out. 
    */
    public String kthDistinct(String[] arr, int k) {
        Map<String, Queue<Integer>> strToPositions = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (strToPositions.containsKey(arr[i])) {
                Queue<Integer> ll = strToPositions.get(arr[i]);
                ll.add(i);
            } else {
                Queue<Integer> ll = new LinkedList<>();
                ll.add(i);
                strToPositions.put(arr[i], ll);
            }
        }
        Comparator<Pair> c = (a, b) -> Integer.compare(-(a.index), -(b.index));
        Queue<Pair> pq = new PriorityQueue<>(c);
        for (String s : strToPositions.keySet()) {
            Queue<Integer> ll = strToPositions.get(s);
            Pair p = new Pair(ll.peek(), s);
            if (ll.size() == 1) {
                if (pq.size() >= k) {
                    Pair max = pq.peek();
                    int maxPos = max.index;
                    if (maxPos > p.index) {
                        pq.poll();
                        pq.add(p);
                    }
                } else {
                    pq.add(p);
                }
            }
        }
        if (pq.size() < k) {
            return "";
        } else {
            return pq.poll().str;
        }
    }
}