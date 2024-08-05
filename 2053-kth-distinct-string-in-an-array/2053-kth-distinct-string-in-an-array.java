class Solution {
    public String kthDistinct(String[] arr, int k) {
        Map<String, Integer> freq = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (freq.containsKey(arr[i])) {
                Integer f = freq.get(arr[i]);
                f++;
                freq.put(arr[i], f);
            } else {
                freq.put(arr[i], 1);
            }
        }
        int j = 0;
        for (String s : arr) {
            if (freq.containsKey(s)) {
                Integer f = freq.get(s);
                if (f == 1) {
                    j++;
                    if (j == k) {
                        return s;
                    }
                }
            }
        }
        return "";
    }
}