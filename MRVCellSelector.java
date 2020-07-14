
package sudoku;
import java.util.*;

/**
 *Class MRVCellSelector - "Smart" agent that chooses the most restricted value as the next cell
 * Methods: GridCell: getNextCell(Sudoku) - returns the next cell with the fewest possible values
 * int; getValNum(GridCell, Sudoku) - returns the number of available values for a given cell in a given puzzle
 * @author Milda
 */
public class MRVCellSelector implements Selector{
    
    @Override
    public GridCell getNextCell(Sudoku p){
       
        int minLeftVals = (p.getDomain()).length; //max number of values
        GridCell next = null; //placeholder
        
        for(int i = 0; i < p.HEIGHT; i++){
            for (int j = 0; j < p.WIDTH; j++){
                
                if(p.getCell(i, j).getCellVal() == p.EMPTY){
                    int available = getValNum(p.getCell(i, j), p);
                    if(available == 1)
                        return p.getCell(i, j);
                    
                     if(available < minLeftVals){
                         minLeftVals = available;
                         next = p.getCell(i, j);
                     }
                 }
            }
        }
        return next;    
    }      
    
    //returns the number of possible values
    public int getValNum(GridCell t, Sudoku p){
     
        HashSet <Character> used = new HashSet<Character>();
        int valLeft = p.WIDTH;
        
        int row = t.getRow();
        int col = t.getCol();
        
        GridCell[] rowVals = p.getRow(row);
    
        GridCell[] colVals = p.getCol(col);
  
        GridCell[] sqVals = p.getSquare(row, col);

        for(int i = 0; i < p.WIDTH; i++){
            if(rowVals[i].getCellVal() != p.EMPTY)
                used.add(rowVals[i].getCellVal());
            if(colVals[i].getCellVal() != p.EMPTY)
                used.add(colVals[i].getCellVal());
            if(sqVals[i].getCellVal() != p.EMPTY)
                used.add(sqVals[i].getCellVal());
        }

        return valLeft - used.size();
    }
    
}
