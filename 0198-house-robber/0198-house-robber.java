class Solution {
    public int rob(int[] nums) {
        // let the dp array be such that dp[i] stores the max amount of money
        // robbed from 0 to i-
        int[] maxToRob = new int[nums.length + 1];
        // maxToRob[0] stores the max amount of money that can be robbed from house -1
        maxToRob[0] = 0;
        maxToRob[1] = nums[0];
        for (int i = 2; i < maxToRob.length; i++) {
            maxToRob[i] = Math.max(maxToRob[i - 2] + nums[i - 1], maxToRob[i - 1]);
        }
        return maxToRob[nums.length];
    }
}