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
            case 2:
                counter = 0;
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        if(test2[counter] != 0){
                            matrix[i][j].value = test2[counter];
                            matrix[i][j].inked = true;
                        }
                        counter++;
                    }
                }
        }
    }

    public boolean matches(int[] mKEY){
        boolean matches = true;

        int counter = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(mKEY[counter] != matrix[i][j].value){
                    matches = false;
                }
                counter++;
            }
        }

        return matches;
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
    int[] test1KEY = {
            6, 8, 4, 7, 9, 1, 3, 5, 2,
            5, 7, 9, 3, 2, 4, 6, 8, 1,
            1, 2, 3, 5, 6, 8, 7, 4, 9,
            3, 4, 2, 1, 5, 6, 8, 9, 7,
            7, 9, 5, 8, 3, 2, 1, 6, 4,
            8, 6, 1, 4, 7, 9, 2, 3, 5,
            2, 1, 6, 9, 8, 5, 4, 7, 3,
            9, 3, 8, 2, 4, 7, 5, 1, 6,
            4, 5, 7, 6, 1, 3, 9, 2, 8
    };
    //https://www.puzzles.ca/sudoku_puzzles/sudoku_easy_607.html Problem 607

    int[] test2 = {
            6, 8, 4, 7, 9, 0, 3, 5, 2,
            5, 7, 9, 3, 2, 4, 6, 8, 0,
            0, 2, 3, 5, 6, 8, 7, 4, 9,
            3, 4, 2, 0, 5, 6, 8, 9, 7,
            7, 9, 5, 8, 3, 2, 0, 6, 4,
            8, 6, 0, 4, 7, 9, 2, 3, 5,
            2, 0, 6, 9, 8, 5, 4, 7, 3,
            9, 3, 8, 2, 4, 7, 5, 0, 6,
            4, 5, 7, 6, 0, 3, 9, 2, 8
    };
    int[] test2KEY = {
            6, 8, 4, 7, 9, 1, 3, 5, 2,
            5, 7, 9, 3, 2, 4, 6, 8, 1,
            1, 2, 3, 5, 6, 8, 7, 4, 9,
            3, 4, 2, 1, 5, 6, 8, 9, 7,
            7, 9, 5, 8, 3, 2, 1, 6, 4,
            8, 6, 1, 4, 7, 9, 2, 3, 5,
            2, 1, 6, 9, 8, 5, 4, 7, 3,
            9, 3, 8, 2, 4, 7, 5, 1, 6,
            4, 5, 7, 6, 1, 3, 9, 2, 8
    };

    int[] test3 = {
            0, 0, 0,
            0, 7, 0,
            0, 0, 0,
    };



}
