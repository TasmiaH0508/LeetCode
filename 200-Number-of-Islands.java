import java.util.LinkedList;

class Coordinates {
    int row;
    int col;
    Coordinates(int r, int c) {
        row = r;
        col = c;
    }
}

class Solution {
    boolean[][] visited;
    char[][] grid;
    public int numIslands(char[][] grid) {
        this.grid = grid;
        visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                visited[i][j] = false;
            }
        }

        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j]) {
                    if (grid[i][j] == '1') {
                        dfs(new Coordinates(i, j));
                        count++;
                    } else {
                        visited[i][j] = true;
                    }
                }
            }
        }

        return count;
    }

    private void dfs(Coordinates c) {
        visited[c.row][c.col] = true;
        LinkedList<Coordinates> neighbours = getAllFeasibleRooms(c);
        if (neighbours.size() != 0) {
            for (Coordinates n : neighbours) {
                dfs(n);
            }
        }
    }

    private LinkedList<Coordinates> getAllFeasibleRooms(Coordinates c) {
        int row = c.row;
        int col = c.col;
        Coordinates n = new Coordinates(row - 1, col);
        Coordinates s = new Coordinates(row + 1, col);
        Coordinates e = new Coordinates(row, col + 1);
        Coordinates w = new Coordinates(row, col - 1);
        LinkedList<Coordinates> ll = new LinkedList<>();
        if (isFeasible(n)) {
            ll.add(n);
        }
        if (isFeasible(s)) {
            ll.add(s);
        }
        if (isFeasible(e)) {
            ll.add(e);
        }
        if (isFeasible(w)) {
            ll.add(w);
        }
        return ll;
    }

    private boolean isFeasible(Coordinates c) {
        return c.row >= 0 && c.row < grid.length && c.col >= 0 && c.col < grid[0].length && !visited[c.row][c.col] && grid[c.row][c.col] == '1';
    }
}