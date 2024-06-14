class Solution {
    /*
    consider some cases:
    1. an array where all the numbers are the same:
    - 2, 2, 2, 2, 2, 2 => 2, 3, 2, 2, 2, 2 => 2, 3, 4, 2, 2, 2 => ...
    2. an array where the differences in numbers are very large:
    - 2, 2000, 200
    */
    public int minIncrementForUnique(int[] nums) {
        int count = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i != 0) {
                int prev = nums[i - 1];
                int curr = nums[i];
                if (prev >= curr) {
                    int needsToBecome = prev + 1;
                    int incr = needsToBecome - nums[i];
                    count += incr;
                    nums[i] = needsToBecome;
                } else {
                    // do nothing has the numbers are 
                    // already in correct order 
                }
            }
        }
        return count;
    }
}