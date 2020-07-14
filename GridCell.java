/*
 * GridCell class
 * Purpose: create and manage the cells and values within a sudoku puzzle
 * Methods: GridCell (int, int, char)
 * char: getCellVal()
 * void: setCellVal(char)
 * int: getRow()
 * int: getCol()
 */
package sudoku;

/**
 *
 * @author Milda
 */
public class GridCell {
    
    int col, row;
    char val;
    
    public GridCell(int row, int col, char val){
        this.col = col;
        this.row = row;
        this.val = val;
    }
    
    public char getCellVal(){
        return this.val;
    }
    
    public void setCellVal(char val){
        this.val = val;
    }
    
    public int getRow(){
        return this.row;
    }
    
    public int getCol(){
        return this.col;
    }
}
