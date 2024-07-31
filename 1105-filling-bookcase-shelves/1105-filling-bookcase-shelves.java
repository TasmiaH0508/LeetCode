class Solution {
    int[][] mem;
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        mem = new int[n + 1][shelfWidth + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= shelfWidth; j++) {
                mem[i][j] = -1; // Initialize with -1 to indicate that the state hasn't been computed yet
            }
        }
        return minHeightShelves(0, 0, 0, shelfWidth, books);
    }

    private int minHeightShelves(int book, int currWidth, int currHeight, int maxWidth, int[][] books) {
        if (book >= books.length) {
            return currHeight;
        }
        // check if the result has already been computed
        if (mem[book][currWidth] != -1) {
            return mem[book][currWidth]; 
        }

        int currBookWidth = books[book][0];
        int currBookHeight = books[book][1];
        int heightOfShelfIfBookIsInSameTier = Integer.MAX_VALUE;

        // check whether the book can fit in the current tier
        if (currBookWidth + currWidth <= maxWidth) {
            heightOfShelfIfBookIsInSameTier = minHeightShelves(book + 1, currWidth + currBookWidth, Math.max(currHeight, currBookHeight), maxWidth, books);
        }

        // considering the probability that the book can also be placed in the bottom shelf -> note that this is always possible
        int heightOfShelfIfBookIsInBottomTier = currHeight + minHeightShelves(book + 1, currBookWidth, currBookHeight, maxWidth, books);

        mem[book][currWidth] = Math.min(heightOfShelfIfBookIsInSameTier, heightOfShelfIfBookIsInBottomTier); // Store the result in the memoization array
        return mem[book][currWidth];
    }
}
