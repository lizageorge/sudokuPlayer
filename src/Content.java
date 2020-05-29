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
                int counter = 0;
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        if(test1[counter] != 0){
                            matrix[i][j].value = test1[counter];
                            matrix[i][j].inked = true;
                        }
                        counter++;
                    }
                }
        }
    }

    // test inputs
    int[] test1 = {
            0, 8, 0, 0, 0, 1, 0, 0, 2,
            0, 0, 0, 0, 0, 4, 0, 0, 1,
            0, 2, 3, 0, 6, 8, 7, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0, 7,
            7, 0, 0, 0, 3, 0, 1, 0, 4,
            0, 6, 0, 0, 0, 9, 0, 3, 0,
            2, 0, 6, 0, 0, 0, 0, 0, 3,
            0, 0, 0, 2, 0, 7, 0, 0, 0,
            0, 5, 0, 6, 0, 0, 9, 0, 0
    };
    //https://www.puzzles.ca/sudoku_puzzles/sudoku_easy_607.html Problem 607

    int[] test3 = {
            0, 0, 0,
            0, 7, 0,
            0, 0, 0,
    };



}
