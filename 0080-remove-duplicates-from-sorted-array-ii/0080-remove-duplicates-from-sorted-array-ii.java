class Solution {
    public int removeDuplicates(int[] nums) {
        int max = Integer.MIN_VALUE;
        Set<Integer> duplicateIndices = new HashSet<>();
        Map<Integer, Integer> freq = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            int numTimesAppeared = 0;
            if (freq.containsKey(nums[i])) {
                numTimesAppeared = freq.get(nums[i]);
            }
            if (numTimesAppeared == 2) {
                duplicateIndices.add(i);
            } else {
                numTimesAppeared++;
                freq.put(nums[i], numTimesAppeared);
            }
        }

        for (Integer i : duplicateIndices) {
            nums[i] = max;
        }

        Arrays.sort(nums);

        int numDuplicates = duplicateIndices.size();
        int k = nums.length - numDuplicates;

        return k;
    }
}