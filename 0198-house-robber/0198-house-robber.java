class Solution {

    Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

    public int rob(int[] nums) {
        return rob(0, 0, nums);
    }

    private int rob(int house, int currMoney, int[] houses) {
        if (house >= houses.length) {
            return currMoney;
        } else {
            // For such a problem, memoization would be needed. The states are house and currMoney
            if (map.containsKey(house)) {
                Map<Integer, Integer> inner = map.get(house);
                if (inner.containsKey(currMoney)) {
                    return inner.get(currMoney);
                }
            }

            // consider robbing the current house
            // this would mean the next house that can be robbed is house + 2
            int moneyIfCurrentHouseIsRobbed = rob(house + 2, currMoney + houses[house], houses);
            
            // consider not robbing the next house
            // this would mean that the next house that can be robbed is house + 1
            int moneyIfCurrentHouseIsNotRobbed = rob(house + 1, currMoney, houses);

            int max = Math.max(moneyIfCurrentHouseIsRobbed, moneyIfCurrentHouseIsNotRobbed);

            // memoize the result
            if (map.containsKey(house)) {
                Map<Integer, Integer> inner = map.get(house);
                inner.put(currMoney, max);
                map.put(house, inner);
            } else {
                Map<Integer, Integer> inner = new HashMap<>();
                inner.put(currMoney, max);
                map.put(house, inner);
            }

            return max;
        }
    }
}