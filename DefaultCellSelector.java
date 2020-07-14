
package sudoku;
import java.util.*;

/**
 *class: DefaultCellSelector
 * Purpose: brute-force method for selecting the next in line empty sudoku cell
 * Methods: GridCell: getNextCell(Sudoku)
 * @author Milda
 */
public class DefaultCellSelector implements Selector{
  
     @Override
     public GridCell getNextCell(Sudoku p){

         for(int i = 0; i < p.WIDTH; i++){
             for(int j = 0; j < p.HEIGHT; j++){
                if(p.getCell(i, j).getCellVal() == p.EMPTY)
                    return p.getCell(i, j);
             }
         }
         return null;
        }
}
