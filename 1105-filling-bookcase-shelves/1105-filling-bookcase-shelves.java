class Solution {

    Map<Integer, Map<Integer, Integer>> mem = new HashMap<>();

    public int minHeightShelves(int[][] books, int shelfWidth) {
        return minHeightShelves(0, 0, 0, shelfWidth, books);
    }

    private int minHeightShelves(int book, int currWidth, int currHeight, int maxWidth, int[][] books) {
        if (book >= books.length) {
            return currHeight;
        }
        // check if the result has already been computed
        if (mem.containsKey(book)) {
            Map<Integer, Integer> inner = mem.get(book);
            if (inner.containsKey(currWidth)) {
                return inner.get(currWidth);
            }
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

        int res = Math.min(heightOfShelfIfBookIsInSameTier, heightOfShelfIfBookIsInBottomTier);
        if (mem.containsKey(book)) {
            Map<Integer, Integer> inner = mem.get(book);
            inner.put(currWidth, res);
            mem.put(book, inner);
        } else {
            Map<Integer, Integer> inner = new HashMap<>();
            inner.put(currWidth, res);
            mem.put(book, inner);
        }

        return res;
    }
}
