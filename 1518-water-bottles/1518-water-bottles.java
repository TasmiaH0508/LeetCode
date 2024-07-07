class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        if (numBottles < numExchange) {
            return numBottles;
        } else {
            // assume all current bottles are emptied
            int numReceived = numBottles / numExchange;
            int numRemaining = numBottles%numExchange;
            int numExchangedSuccessfully = numBottles - numRemaining;
            return numExchangedSuccessfully + numWaterBottles(numRemaining + numReceived, numExchange);
        }
    }
}