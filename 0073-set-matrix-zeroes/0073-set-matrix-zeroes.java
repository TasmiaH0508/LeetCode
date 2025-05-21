class Solution {
    public void setZeroes(int[][] matrix) {
        Set<Integer> rowsToZero = new HashSet<>();
        Set<Integer> colsToZero = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            if (colsToZero.size() == matrix[0].length || rowsToZero.size() == matrix.length) {
                break;
            }
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rowsToZero.add(i);
                    colsToZero.add(j);
                }
                if (colsToZero.size() == matrix[0].length || rowsToZero.size() == matrix.length) {
                    break;
                }
            }
        }
        for (int rowIndex : rowsToZero) {
            int[] rowToZero = matrix[rowIndex];
            for (int i = 0; i < rowToZero.length; i++) {
                rowToZero[i] = 0;
            }
        }
        for (int colIndex : colsToZero) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][colIndex] = 0;
            }
        }
    }
}