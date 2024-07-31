/*class Solution {

    int[][] mem;
    public int minHeightShelves(int[][] books, int shelfWidth) {
        mem = new int[books.length][shelfWidth + 1];
        for (int i = 0; i < books.length; i++) {
            for (int j = 0; j < shelfWidth; j++) {
                mem[i][j] = -1;
            }
        }
        return minHeightShelves(0, 0, 0, shelfWidth, books);
    }

    private int minHeightShelves(int book, int currWidth, int currHeight, int maxWidth, int[][] books) {
        if (book >= books.length) {
            // book does not exist
            return currHeight;
        } else {
            // check if the result has already been computed
            if (mem[book][currWidth] != -1) {
                return mem[book][currWidth];
            }
            int currBookWidth = books[book][0];
            int currBookHeight = books[book][1];
            int heightOfShelfIfBookIsInSameTier = -1;
            // check whether the book can fit in the current tier
            if (currBookWidth + currWidth <= maxWidth) {
                if (currBookHeight > currHeight) {
                    heightOfShelfIfBookIsInSameTier = minHeightShelves(book + 1, currBookWidth + currWidth, currBookHeight, maxWidth, books);
                } else {
                    heightOfShelfIfBookIsInSameTier = minHeightShelves(book + 1, currBookWidth + currWidth, currHeight, maxWidth, books);
                }
            }
            // considering the probability that the book can also be placed in the bottom shelf -> note that this is always possible
            int heightOfShelfIfBookIsInBottomTier = currHeight + minHeightShelves(book + 1, currBookWidth, currBookHeight, maxWidth, books);
            if (heightOfShelfIfBookIsInSameTier == -1) {
                mem[book][currWidth] = heightOfShelfIfBookIsInBottomTier;
            } else {
                mem[book][currWidth] = Math.min(heightOfShelfIfBookIsInSameTier, heightOfShelfIfBookIsInBottomTier);
            }
            return mem[book][currWidth];
        }
    }
}*/

class Solution {
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        int[][] memo = new int[n + 1][shelfWidth + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= shelfWidth; j++) {
                memo[i][j] = -1; // Initialize with -1 to indicate that the state hasn't been computed yet
            }
        }
        return minHeightShelves(0, 0, 0, shelfWidth, books, memo);
    }

    private int minHeightShelves(int book, int currWidth, int currHeight, int maxWidth, int[][] books, int[][] memo) {
        if (book >= books.length) {
            return currHeight;
        }
        if (memo[book][currWidth] != -1) {
            return memo[book][currWidth]; // Return the precomputed value
        }

        int currBookWidth = books[book][0];
        int currBookHeight = books[book][1];
        int heightOfShelfIfBookIsInSameTier = Integer.MAX_VALUE;

        // Check whether the book can fit in the current tier
        if (currBookWidth + currWidth <= maxWidth) {
            heightOfShelfIfBookIsInSameTier = minHeightShelves(
                book + 1,
                currWidth + currBookWidth,
                Math.max(currHeight, currBookHeight),
                maxWidth,
                books,
                memo
            );
        }

        // Consider the book placed on a new tier
        int heightOfShelfIfBookIsInBottomTier = currHeight + minHeightShelves(
            book + 1,
            currBookWidth,
            currBookHeight,
            maxWidth,
            books,
            memo
        );

        // Find the minimum height
        int result = Math.min(heightOfShelfIfBookIsInSameTier, heightOfShelfIfBookIsInBottomTier);

        memo[book][currWidth] = result; // Store the result in the memoization array
        return result;
    }
}
