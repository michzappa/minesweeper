public class BoardLayout {
    private int num_mines;
    private int width;
    private int height;
    char[][] mine_locations;

    public BoardLayout(int mines, int h, int w) {
        num_mines = mines;
        width = w;
        height = h;
        mine_locations = new char[h][w];

        setBoard();

    }

    public char getValue(int r, int c) {
        return mine_locations[r][c];
    }

    public void setBoard() {
        // Mine Placement in mine_locations
        int randrow;
        int randcol;
        while (num_mines > 0) {
            randrow = (int) (Math.random() * height);
            randcol = (int) (Math.random() * width);
            // System.out.println(randrow + " " + randcol);

            if (mine_locations[randrow][randcol] != 'X') {
                mine_locations[randrow][randcol] = 'X';
                num_mines--;
            }

        }

        // Find number of nearby mines
        int nearby;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (mine_locations[i][j] != 'X') {
                    nearby = 0;
                    // First Row
                    if (i == 0) {
                        // Top Left Corner
                        if (j == 0) {
                            if (mine_locations[i][j + 1] == 'X') {
                                nearby++;
                            }
                            if (mine_locations[i + 1][j + 1] == 'X') {
                                nearby++;
                            }
                            if (mine_locations[i + 1][j] == 'X') {
                                nearby++;
                            }
                        }
                        // Top Right Corner
                        else if (j == width - 1) {
                            if (mine_locations[i + 1][j] == 'X') {
                                nearby++;
                            }
                            if (mine_locations[i + 1][j - 1] == 'X') {
                                nearby++;
                            }
                            if (mine_locations[i][j - 1] == 'X') {
                                nearby++;
                            }
                        }
                        // Edge
                        else {
                            if (mine_locations[i][j + 1] == 'X') {
                                nearby++;
                            }
                            if (mine_locations[i + 1][j + 1] == 'X') {
                                nearby++;
                            }
                            if (mine_locations[i + 1][j] == 'X') {
                                nearby++;
                            }
                            if (mine_locations[i + 1][j - 1] == 'X') {
                                nearby++;
                            }
                            if (mine_locations[i][j - 1] == 'X') {
                                nearby++;
                            }
                        }
                    }
                    // Bottom Row
                    else if (i == height - 1) {
                        // Bottom Left Corner
                        if (j == 0) {
                            if (mine_locations[i - 1][j] == 'X') {
                                nearby++;
                            }
                            if (mine_locations[i - 1][j + 1] == 'X') {
                                nearby++;
                            }
                            if (mine_locations[i][j + 1] == 'X') {
                                nearby++;
                            }
                        }
                        // Bottom Right Corner
                        else if (j == width - 1) {
                            if (mine_locations[i][j - 1] == 'X') {
                                nearby++;
                            }
                            if (mine_locations[i - 1][j - 1] == 'X') {
                                nearby++;
                            }
                            if (mine_locations[i - 1][j] == 'X') {
                                nearby++;
                            }
                        }
                        // Edge
                        else {
                            if (mine_locations[i][j - 1] == 'X') {
                                nearby++;
                            }
                            if (mine_locations[i - 1][j - 1] == 'X') {
                                nearby++;
                            }
                            if (mine_locations[i - 1][j] == 'X') {
                                nearby++;
                            }
                            if (mine_locations[i - 1][j + 1] == 'X') {
                                nearby++;
                            }
                            if (mine_locations[i][j + 1] == 'X') {
                                nearby++;
                            }
                        }
                    }
                    // Left Edge
                    else if (j == 0 && i != 0 && i != height - 1) {
                        if (mine_locations[i - 1][j] == 'X') {
                            nearby++;
                        }
                        if (mine_locations[i - 1][j + 1] == 'X') {
                            nearby++;
                        }
                        if (mine_locations[i][j + 1] == 'X') {
                            nearby++;
                        }
                        if (mine_locations[i + 1][j + 1] == 'X') {
                            nearby++;
                        }
                        if (mine_locations[i + 1][j] == 'X') {
                            nearby++;
                        }
                    }
                    // Right Edge
                    else if (j == width - 1 && i != 0 && i != height - 1) {
                        if (mine_locations[i + 1][j] == 'X') {
                            nearby++;
                        }
                        if (mine_locations[i + 1][j - 1] == 'X') {
                            nearby++;
                        }
                        if (mine_locations[i][j - 1] == 'X') {
                            nearby++;
                        }
                        if (mine_locations[i - 1][j - 1] == 'X') {
                            nearby++;
                        }
                        if (mine_locations[i - 1][j] == 'X') {
                            nearby++;
                        }
                    }
                    // Interior
                    else {
                        if (mine_locations[i - 1][j] == 'X') {
                            nearby++;
                        }
                        if (mine_locations[i - 1][j + 1] == 'X') {
                            nearby++;
                        }
                        if (mine_locations[i][j + 1] == 'X') {
                            nearby++;
                        }
                        if (mine_locations[i + 1][j + 1] == 'X') {
                            nearby++;
                        }
                        if (mine_locations[i + 1][j] == 'X') {
                            nearby++;
                        }
                        if (mine_locations[i + 1][j - 1] == 'X') {
                            nearby++;
                        }
                        if (mine_locations[i][j - 1] == 'X') {
                            nearby++;
                        }
                        if (mine_locations[i - 1][j - 1] == 'X') {
                            nearby++;
                        }
                    }
                    mine_locations[i][j] = (char) (nearby + 48);
                }

            }
        }
    }

    public void printBoard() {
        // Print out mine_locations
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(getValue(i, j) + "  ");
            }
            System.out.println(" ");
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getMines() {
        return num_mines;
    }

    public static void main(String[] args) {
        BoardLayout board = new BoardLayout(20, 10, 15);

        // Print out nearby_mines
        board.printBoard();
    }
}
