import java.util.HashMap;
import java.util.LinkedList;

class Solution {
    /*
     * Method of brute force:
     * 1. create a hashmap of freq
     * 2. greedily push all the frequencies into the PQ
     * 3. poll from PQ k times
     * 
     * Method 2:
     * 1. while creating the hashmap of frequencies,
     * count the number of different types of elems
     * 2. if there are b different elems in an array of size n,
     * the max freq would be: n - (b - 1)
     * 3. count down the frequencies and do the required computations ...
     */
    public int[] topKFrequent(int[] nums, int k) {
        int[] res = new int[k];
        int count = 0;
        HashMap<Integer, Integer> freq = new HashMap<>();
        HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (freq.containsKey(nums[i])) {
                Integer f = freq.get(nums[i]);
                f++;
                freq.put(nums[i], f);
            } else {
                count++;
                freq.put(nums[i], 1);
            }
        }

        for (Integer i : freq.keySet()) {
            if (map.containsKey(freq.get(i))) {
                LinkedList<Integer> ll = map.get(freq.get(i));
                ll.add(i);
                map.put(freq.get(i), ll);
            } else {
                LinkedList<Integer> ll = new LinkedList<>();
                ll.add(i);
                map.put(freq.get(i), ll);
            }
        }

        int max = nums.length - count + 1;
        int p = 0;
        while (p < k) {
            if (map.containsKey(max)) {
                LinkedList<Integer> ll = map.get(max);
                for (Integer i : ll) {
                    res[p] = i;
                    p++;
                }
            }
            max--;
        }

        return res;
    }
}