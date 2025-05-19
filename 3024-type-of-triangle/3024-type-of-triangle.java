class Solution {
    public String triangleType(int[] nums) {
        if (nums.length != 3) {
            return "none";
        }
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            if (!freq.containsKey(num)) {
                freq.put(num, 1);
            } else {
                int f = freq.get(num);
                f++;
                freq.put(num, f);
            }
        }
        if (freq.keySet().size() == 1) {
            return "equilateral";
        } else if (freq.keySet().size() == 2) {
            int[] sideLengths = new int[2];
            for (int f : freq.keySet()) {
                if (freq.get(f) == 2) {
                    sideLengths[1] = f;
                } else {
                    sideLengths[0] = f;
                }
            }
            if (sideLengths[0] < 2 * sideLengths[1]) {
                return "isosceles";
            }
            return "none";
        } else {
            for (int i = 0; i < 3; i++) {
                int firstIndex = (i + 1) % 3;
                int secondIndex = (i + 2) % 3;
                if (nums[firstIndex] + nums[secondIndex] <= nums[i]) {
                    return "none";
                }
            }
            return "scalene";
        }
    }
}