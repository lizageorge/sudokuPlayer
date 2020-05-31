public class Content {
    Cell[][] matrix = new Cell[9][9];

    public Content(int version){
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                matrix[row][column] = new Cell(0);
            }
        }

        switch (version){
            case 1:
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        if(medium1[i][j] != 0){
                            matrix[i][j].value = medium1[i][j];
                            matrix[i][j].inked = true;
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        if(easy[i][j] != 0){
                            matrix[i][j].value = easy[i][j];
                            matrix[i][j].inked = true;
                        }
                    }
                }
                break;
        }
    }

    public boolean matches(int[][] mKEY){
        boolean matches = true;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(mKEY[i][j] != matrix[i][j].value){
                    matches = false;
                }
            }
        }

        return matches;
    }

    public boolean checkCompletedRow(int[][] mKEY, int currentRow) {
        boolean rowComplete = true;

        for (int i = 0; i < matrix.length; i++) {
            if(mKEY[currentRow][i] != matrix[currentRow][i].value){
                rowComplete = false;
            }
        }

        return rowComplete;
    }

    public boolean checkCompletedColumn(int[][] mKEY, int currentColumn) {
        boolean columnComplete = true;

        for (int i = 0; i < matrix[0].length; i++) {
            if(mKEY[i][currentColumn] != matrix[i][currentColumn].value){
                columnComplete = false;
            }
        }

        return columnComplete;
    }

    public boolean checkCompleteBox(int[][] mKEY, int r, int c) {
        boolean boxComplete = true;

        int rowStart = r - r%3;
        int columnStart = c - c%3;
        for (int i = rowStart; i < rowStart+3; i++) {
            for (int j = columnStart; j < columnStart+3; j++) {
                if(mKEY[i][j] != matrix[i][j].value){
                    boxComplete = false;
                }
            }
        }

        return boxComplete;
    }

    // test inputs
    //easy
    final int[][] easy = {
            {6, 8, 4, 7, 9, 0, 3, 5, 2},
            {5, 7, 9, 3, 2, 4, 6, 8, 0},
            {0, 2, 3, 5, 6, 8, 7, 4, 9},
            {3, 4, 2, 0, 5, 6, 8, 9, 7},
            {7, 9, 5, 8, 3, 2, 0, 6, 4},
            {8, 6, 0, 4, 7, 9, 2, 3, 5},
            {2, 0, 6, 9, 8, 5, 4, 7, 3},
            {9, 3, 8, 2, 4, 7, 5, 0, 6},
            {4, 5, 7, 6, 0, 3, 9, 2, 8}
    };
    final int[][] easyKEY = {
            {6, 8, 4, 7, 9, 1, 3, 5, 2},
            {5, 7, 9, 3, 2, 4, 6, 8, 1},
            {1, 2, 3, 5, 6, 8, 7, 4, 9},
            {3, 4, 2, 1, 5, 6, 8, 9, 7},
            {7, 9, 5, 8, 3, 2, 1, 6, 4},
            {8, 6, 1, 4, 7, 9, 2, 3, 5},
            {2, 1, 6, 9, 8, 5, 4, 7, 3},
            {9, 3, 8, 2, 4, 7, 5, 1, 6},
            {4, 5, 7, 6, 1, 3, 9, 2, 8}
    };

    //medium

    //https://www.puzzles.ca/sudoku_puzzles/sudoku_easy_607.html Problem 607
    final int[][] medium1 = {
            {0, 8, 0, 0, 0, 1, 0, 0, 2},
            {0, 0, 0, 0, 0, 4, 0, 0, 1},
            {0, 2, 3, 0, 6, 8, 7, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 7},
            {7, 0, 0, 0, 3, 0, 1, 0, 4},
            {0, 6, 0, 0, 0, 9, 0, 3, 0},
            {2, 0, 6, 0, 0, 0, 0, 0, 3},
            {0, 0, 0, 2, 0, 7, 0, 0, 0},
            {0, 5, 0, 6, 0, 0, 9, 0, 0}
    };
    final int[][] medium1KEY = {
            {6, 8, 4, 7, 9, 1, 3, 5, 2},
            {5, 7, 9, 3, 2, 4, 6, 8, 1},
            {1, 2, 3, 5, 6, 8, 7, 4, 9},
            {3, 4, 2, 1, 5, 6, 8, 9, 7},
            {7, 9, 5, 8, 3, 2, 1, 6, 4},
            {8, 6, 1, 4, 7, 9, 2, 3, 5},
            {2, 1, 6, 9, 8, 5, 4, 7, 3},
            {9, 3, 8, 2, 4, 7, 5, 1, 6},
            {4, 5, 7, 6, 1, 3, 9, 2, 8}
    };

    //https://dingo.sbs.arizona.edu/~sandiway/sudoku/examples.html intermediate
    final int[][] medium2 = {
            {0, 2, 0, 6, 0, 8, 0, 0, 0},
            {5, 8, 0, 0, 0, 9, 7, 0, 0},
            {0, 0, 0, 0, 4, 0, 0, 0, 0},
            {3, 7, 0, 0, 0, 0, 5, 0, 0},
            {6, 0, 0, 0, 0, 0, 0, 0, 4},
            {0, 0, 8, 0, 0, 0, 0, 1, 3},
            {0, 0, 0, 0, 2, 0, 0, 0, 0},
            {0, 0, 9, 8, 0, 0, 0, 3, 6},
            {0, 0, 0, 3, 0, 6, 0, 9, 0}
    };
    final int[][] medium2KEY = {
            {1, 2, 3, 6, 7, 8, 9, 4, 5},
            {5, 8, 4, 2, 3, 9, 7, 6, 1},
            {9, 6, 7, 1, 4, 5, 3, 2, 8},
            {3, 7, 2, 4, 6, 1, 5, 8, 9},
            {6, 9, 1, 5, 8, 3, 2, 7, 4},
            {4, 5, 8, 7, 9, 2, 6, 1, 3},
            {8, 3, 6, 9, 2, 4, 1, 5, 7},
            {2, 1, 9, 8, 5, 7, 4, 3, 6},
            {7, 4, 5, 3, 1, 6, 8, 9, 2}
    };

    //hard
    //https://dingo.sbs.arizona.edu/~sandiway/sudoku/examples.html Vegard Hanssen puzzle 2155141
    final int[][] hard1 = {
            {0, 0, 0, 6, 0, 0, 4, 0, 0},
            {7, 0, 0, 0, 0, 3, 6, 0, 0},
            {0, 0, 0, 0, 9, 1, 0, 8, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 5, 0, 1, 8, 0, 0, 0, 3},
            {0, 0, 0, 3, 0, 6, 0, 4, 5},
            {0, 4, 0, 2, 0, 0, 0, 6, 0},
            {9, 0, 3, 0, 0, 0, 0, 0, 0},
            {0, 2, 0, 0, 0, 0, 1, 0, 0}
    };
    final int[][] hard1KEY = {
            {5, 8, 1, 6, 7, 2, 4, 3, 9},
            {7, 9, 2, 8, 4, 3, 6, 5, 1},
            {3, 6, 4, 5, 9, 1, 7, 8, 2},
            {4, 3, 8, 9, 5, 7, 2, 1, 6},
            {2, 5, 6, 1, 8, 4, 9, 7, 3},
            {1, 7, 9, 3, 2, 6, 8, 4, 5},
            {8, 4, 5, 2, 1, 9, 3, 6, 7},
            {9, 1, 3, 7, 6, 8, 5, 2, 4},
            {6, 2, 7, 4, 3, 5, 1, 9, 8}
    };


    //test
    final int[] test3 = {
            0, 0, 0,
            0, 7, 0,
            0, 0, 0,
    };
}

