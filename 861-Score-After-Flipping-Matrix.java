class Solution {
    public int matrixScore(int[][] grid) {
        // row-wise
        // the left-most digit is the most significant
        // if the leftmost digit can be modified, then the row should be modified
        for (int i = 0; i < grid.length; i++) {
            if (grid[i][0] == 0) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 0) {
                        grid[i][j] = 1;
                    } else {
                        grid[i][j] = 0;
                    }
                }
            }
        }
        // if there are more 0s than 1s in a col, do the swaps
        int col = 1;
        while (col < grid[0].length) {
            int zeroes = 0;
            int ones = 0;
            for (int r = 0; r < grid.length; r++) {
                if (grid[r][col] == 0) {
                    zeroes++;
                } else {
                    ones++;
                }
            }
            if (zeroes > ones) {
                for (int q = 0; q < grid.length; q++) {
                    if (grid[q][col] == 0) {
                        grid[q][col] = 1;
                    } else {
                        grid[q][col] = 0;
                    }
                }
            }
            col++;
        }
        int sum = 0;
        for (int row = 0; row < grid.length; row++) {
            int pow = 0;
            for (int c = grid[0].length - 1; c >= 0; c--) {
                if (grid[row][c] == 1) {
                    sum += mathPow(2, pow);
                }
                pow++;
            }
        }
        return sum;
    }

    int mathPow(int a, int b) {
        if (b == 0) {
            return 1;
        } else {
            return a * mathPow(a, b - 1);
        }
    }
}