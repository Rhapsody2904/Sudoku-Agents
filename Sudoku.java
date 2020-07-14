/*
*
 */
package sudoku;
import java.io.*;
/**
 *
 * @author Milda
 * Sudoku class for building a Sudoku board
 * Methods: Sudoku(File)
 * char[]: getDomain() - returns puzzle's domain. Hard-coded
 * GridCell[]: getRow(int) - returns an array of GridCell objects that are in the same row as the index (parameter)
 * GridCell[]: getColumn(int) - returns an array of GridCell objects that are in the same column as the index (parameter)
 * GridCell[]: getSquare (int, int) - returns an array of GridCell objects that are in the same square as the index (parameter)
 * int: getSolvedCellNum() - returns the number of solved cells within the puzzle
 * void: setCell(int, int, char) - sets the value of a particular cell
 * void; clearCell (int, int) - resets value to '-' (EMPTY) of the target cell
 * char: getVal(int, int) - returns the value of a cell
 * boolean: isSolved() - checks if there are any empty cells left
 * boolean: checkConstraints(int, int) - checks the constraints on a particular cell
 * void: print() - helper method for testing. Prints a string representation of the puzzle and its values
 */
public class Sudoku {
    
    char[] domain = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F'};
    public static final char EMPTY = '-';
    
    //board dimensions
    public static final int HEIGHT = 16;
    public static final int WIDTH = 16;
    
    //puzzle array
    private GridCell[][] puzzle = new GridCell[HEIGHT][WIDTH]; 
    int r = 0;
    int c = 0;
 
    String temp = "";

    //constructor
    public Sudoku (String file){
        File f = new File(file);
        try{
            BufferedReader reader = new BufferedReader(new FileReader(f));
            while((temp = reader.readLine()) != null)
            {
                for (int i = 0; i < HEIGHT; i++){
                    char val = temp.charAt(i);
                    puzzle[r][c] = new GridCell(r,c,val);
                    c++;
                    if(c == WIDTH){
                        r++; //move to next row
                        c = 0; //reset column
                    }
                }
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println("Error reading file. Please check your file and try again");
            System.exit(0);
        }
    }
    
    //return domain
    public char [] getDomain(){
        return domain;
    }
    
    //returns entire puzzle
    public GridCell[][] getPuzzle(){
        return puzzle;
    }
    
    public GridCell getCell(int r, int c){
        return puzzle[r][c];
    }
    
    //get target row
    public GridCell[] getRow(int index){
        GridCell[] row = new GridCell[WIDTH];
        
        for (int i = 0; i < WIDTH; i++)
            row[i] = puzzle [index][i];
        
        return row;
    }
    
    //get target column
    public GridCell[] getCol(int index){
    GridCell[] col = new GridCell[WIDTH];

    for (int i = 0; i < WIDTH; i++)
        col[i] = puzzle [i][index];

    return col;
    }
    
    //get target inner square
    public GridCell[] getSquare(int r, int c){
        GridCell[] square;
        int side = (int)(Math.sqrt(WIDTH));
        
        //left top 4x4
        if(r < side && c < side){
            square = new GridCell[] {puzzle[0][0], puzzle[0][1], puzzle[0][2], puzzle[0][3], 
                                     puzzle[1][0], puzzle[1][1], puzzle[1][2], puzzle[1][3], 
                                     puzzle[2][0], puzzle[2][1], puzzle[2][2], puzzle[2][3],
                                     puzzle[3][0], puzzle[3][1], puzzle[3][2], puzzle[3][3]}; 
        } 
        //first left middle 4x4
        else if (r < side*2 && c < side){
            square = new GridCell[] {puzzle[4][0], puzzle[4][1], puzzle[4][2], puzzle[4][3],
                                     puzzle[5][0], puzzle[5][1], puzzle[5][2], puzzle[5][3],
                                     puzzle[6][0], puzzle[6][1], puzzle[6][2], puzzle[6][3],
                                     puzzle[7][0], puzzle[7][1], puzzle[7][2], puzzle[7][3]};
        }
        //second left middle 4x4
        else if (r < side*3 && c < side){
            square = new GridCell[] {puzzle[8][0],  puzzle[8][1],  puzzle[8][2], puzzle[8][3],
                                     puzzle[9][0],  puzzle[9][1],  puzzle[9][2], puzzle[9][3],
                                     puzzle[10][0], puzzle[10][1], puzzle[10][2],puzzle[10][3],
                                     puzzle[11][0], puzzle[11][1], puzzle[11][2],puzzle[11][3]};
        }
        //left bottom 4x4
        else if (r < side*4 && c < side){
            square = new GridCell[] {puzzle[12][0], puzzle[12][1], puzzle[12][2],puzzle[12][3],
                                     puzzle[13][0], puzzle[13][1], puzzle[13][2],puzzle[13][3],
                                     puzzle[14][0], puzzle[14][1], puzzle[14][2],puzzle[14][3],
                                     puzzle[15][0], puzzle[15][1], puzzle[15][2],puzzle[15][3]};
        }
        //first middle top 4x4
        else if (r < side && c < side*2){
            square = new GridCell[] {puzzle[0][4], puzzle[0][5], puzzle[0][6], puzzle[0][7],
                                     puzzle[1][4], puzzle[1][5], puzzle[1][6], puzzle[1][7],
                                     puzzle[2][4], puzzle[2][5], puzzle[2][6], puzzle[2][7],
                                     puzzle[3][4], puzzle[3][5], puzzle[3][6], puzzle[3][7]};
        }
        //first middle middle-first 4x4
        else if (r < side*2 && c < side*2){
            square = new GridCell[] {puzzle[4][4], puzzle[4][5], puzzle[4][6], puzzle[4][7],
                                     puzzle[5][4], puzzle[5][5], puzzle[5][6], puzzle[5][7],
                                     puzzle[6][4], puzzle[6][5], puzzle[6][6], puzzle[6][7],
                                     puzzle[7][4], puzzle[7][5], puzzle[7][6], puzzle[7][7]};
        }
        //first middle middle-second 4x4
        else if(r < side*3 && c < side*2){
            square = new GridCell[] {puzzle[8][4],  puzzle[8][5],  puzzle[8][6], puzzle[8][7],
                                     puzzle[9][4],  puzzle[9][5],  puzzle[9][6], puzzle[9][7],
                                     puzzle[10][4], puzzle[10][5], puzzle[10][6],puzzle[10][7],
                                     puzzle[11][4], puzzle[11][5], puzzle[11][6],puzzle[11][7]};
        }
        //first middle bottom 4x4
        else if(r < side*4 && c < side*2){
            square = new GridCell[] {puzzle[12][4],  puzzle[12][5],  puzzle[12][6],  puzzle[12][7],
                                     puzzle[13][4],  puzzle[13][5],  puzzle[13][6],  puzzle[13][7],
                                     puzzle[14][4],  puzzle[14][5],  puzzle[14][6],  puzzle[14][7],
                                     puzzle[15][4],  puzzle[15][5],  puzzle[15][6],  puzzle[15][7]};
        }
        //second middle top 4x4
        else if(r < side && c < side*3){
            square = new GridCell[] {puzzle[0][8],   puzzle[0][9],   puzzle[0][10],  puzzle[0][11],
                                     puzzle[1][8],   puzzle[1][9],   puzzle[1][10],  puzzle[1][11],
                                     puzzle[2][8],   puzzle[2][9],   puzzle[2][10],  puzzle[2][11],
                                     puzzle[3][8],   puzzle[3][9],   puzzle[3][10],  puzzle[3][11]};
        }
        //second middle middle-first 4x4
        else if(r < side*2 && c < side*3){
            square = new GridCell[] {puzzle[4][8],   puzzle[4][9],   puzzle[4][10],  puzzle[4][11],
                                     puzzle[5][8],   puzzle[5][9],   puzzle[5][10],  puzzle[5][11],
                                     puzzle[6][8],   puzzle[6][9],   puzzle[6][10],  puzzle[6][11],
                                     puzzle[7][8],   puzzle[7][9],   puzzle[7][10],  puzzle[7][11]};
        }
        //second middle middle-second 4x4
        else if(r < side*3 && c < side*3){
            square = new GridCell[] {puzzle[8][8],   puzzle[8][9],   puzzle[8][10],  puzzle[8][11],
                                     puzzle[9][8],   puzzle[9][9],   puzzle[9][10],  puzzle[9][11],
                                     puzzle[10][8],  puzzle[10][9],  puzzle[10][10], puzzle[10][11],
                                     puzzle[11][8],  puzzle[11][9],  puzzle[11][10], puzzle[11][11]};
        }
        //second middle bottom 4x4
        else if(r <side*4 && c < side*3){
            square = new GridCell[] {puzzle[12][8],  puzzle[12][9],  puzzle[12][10], puzzle[12][11],
                                     puzzle[13][8],  puzzle[13][9],  puzzle[13][10], puzzle[13][11],
                                     puzzle[14][8],  puzzle[14][9],  puzzle[14][10], puzzle[14][11],
                                     puzzle[15][8],  puzzle[15][9],  puzzle[15][10], puzzle[15][11]};
        }//right top 4x4
        else if(r < side && c < side*4){
            square = new GridCell[] {puzzle[0][12],  puzzle[0][13],  puzzle[0][14],  puzzle[0][15],
                                     puzzle[1][12],  puzzle[1][13],  puzzle[1][14],  puzzle[1][15],
                                     puzzle[2][12],  puzzle[2][13],  puzzle[2][14],  puzzle[2][15],
                                     puzzle[3][12],  puzzle[3][13],  puzzle[3][14],  puzzle[3][15]};
        }//right middle-first 4x4
        else if(r < side && c < side*4){
            square = new GridCell[] {puzzle[4][12],  puzzle[4][13],  puzzle[4][14],  puzzle[4][15],
                                     puzzle[5][12],  puzzle[5][13],  puzzle[5][14],  puzzle[5][15],
                                     puzzle[6][12],  puzzle[6][13],  puzzle[6][14],  puzzle[6][15],
                                     puzzle[7][12],  puzzle[7][13],  puzzle[7][14],  puzzle[7][15]};
        }//right middle-scond 4x4
        else if(r < side && c < side*4){
            square = new GridCell[] {puzzle[8][12],  puzzle[8][13],  puzzle[8][14],  puzzle[8][15],
                                     puzzle[9][12],  puzzle[9][13],  puzzle[9][14],  puzzle[9][15],
                                     puzzle[10][12], puzzle[10][13], puzzle[10][14], puzzle[10][15],
                                     puzzle[11][12], puzzle[11][13], puzzle[11][14], puzzle[11][15]};
        }//right bottom 4x4
        else{
            square = new GridCell[] {puzzle[12][12], puzzle[12][13], puzzle[12][14], puzzle[12][15],
                                     puzzle[13][12], puzzle[13][13], puzzle[13][14], puzzle[13][15],
                                     puzzle[14][12], puzzle[14][13], puzzle[14][14], puzzle[14][15],
                                     puzzle[15][12], puzzle[15][13], puzzle[15][14], puzzle[15][15]};
        }
       
        return square;    
    }
    
    //get the number of filled cells
    public int getSolvedCellNum(){
       int solved = 0;
       for (int i = 0; i < WIDTH; i++)
           for(int j= 0; j < HEIGHT; j++)
               if(puzzle[i][j].getCellVal()!= EMPTY)
                   solved++;
       return solved;
    }
    
    //enter value into a cell
    public void setCell(int r, int c, char val){
        puzzle[r][c].setCellVal(val);
    }
    
    //clear value from a certain cell
    public void clearCell(int r, int c){
        puzzle[r][c].setCellVal(EMPTY);
    }
    
    //get cell's value
    public char getVal(int r, int c){
        return puzzle[r][c].getCellVal();
    }
    
    //check if puzzle is solved
    public boolean isSolved(){
        return getSolvedCellNum()== HEIGHT*WIDTH;
    }
    
    //check if any constraints are violated
    public boolean checkConstraints(int row, int col){
        Constraints c = new Constraints();
        return c.checkConstraint(getRow(row)) && c.checkConstraint(getCol(col)) && c.checkConstraint(getSquare(row,col));
    }
    //print puzzle
    public void print(){
        for(int i =0; i < WIDTH; i++){
           for(int j = 0; j < HEIGHT; j++)
               System.out.print(puzzle[i][j].getCellVal() + " ");
           System.out.println();
       }
    }
}