class Solution {
    public double averageWaitingTime(int[][] customers) {
        double timeAfterFoodHasBeenPrepared = 0;
        double totalWaitingTime = 0;
        for (int i = 0; i < customers.length; i++) {
            if (timeAfterFoodHasBeenPrepared <= customers[i][0]) {
                // chef is idle
                totalWaitingTime += customers[i][1]; // customer only has to wait for as long as food preparation
                timeAfterFoodHasBeenPrepared = customers[i][0]; // chef can only start once customer arrives
                timeAfterFoodHasBeenPrepared += customers[i][1];
            } else {
                // chef is not idle
                totalWaitingTime += timeAfterFoodHasBeenPrepared - customers[i][0]; // customer has to wait until chef becomes free
                totalWaitingTime += customers[i][1]; // customer has to wait until food has been prepared
                timeAfterFoodHasBeenPrepared += customers[i][1];
            }
        }
        return totalWaitingTime / customers.length;
    }
}