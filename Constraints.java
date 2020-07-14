
package sudoku;
import java.util.*;
/**
 *Class: Constraints
 * Purpose: evaluate whether there are violations in constraints
 * Method: boolean: checkConstraint(GridCell[])
 * @author Milda
 */
public class Constraints {
    
    public boolean checkConstraint(GridCell[] cells){
        HashSet<Character> used = new HashSet<Character>(); //takes distinct values
        
        for(int i = 0; i < cells.length; i++){    
            if(used.contains(cells[i].getCellVal()) && cells[i].getCellVal()!='-')
                return false;
            used.add(cells[i].getCellVal()); //add to set
        }
        return true;
    }
}
