
package sudoku;

/**
 * Class: BacktrackingSearch
 * Purpose: Recursive algorithm that uses a Selector type and a sudoku puzzle to create a solution
 * Methods: BacktrackingSearch(Sudoku, Selector) - constructor
 * Sudoku: BacktrackingSearch(Sudoku)
 * Sudoku: Backtrack(Sudoku) - recursive algorithm that performs the search
 * long: getAssignments() - returns the number of attempted assignments within the search
 * @author Milda
 */
public class BacktrackingSearch {
    private long assignments = 0;
    private Selector s;
    
    public BacktrackingSearch(Sudoku p, Selector s){
        this.s = s;
    }
    
    public Sudoku BacktrackingSearch(Sudoku p){
        return Backtrack(p);
    }
    
    private Sudoku Backtrack(Sudoku p){
       if(p.isSolved()) //check if puzzle is solved
           return p;

       GridCell next = s.getNextCell(p); //next cell to be filled
      
       
       for(char val: p.getDomain()){ //try the possible values
           p.setCell(next.getRow(), next.getCol(), val );
   
           if(p.checkConstraints(next.getRow(), next.getCol())){
  
            assignments++; //track assignment attempts
         
               Sudoku result = Backtrack(p);
        
               if(result != null){ //check if there was a solution
                   return result;
               }
               p.clearCell(next.getRow(), next.getCol()); //bad solution; undo move
           }
           else{
               p.clearCell(next.getRow(), next.getCol()); //val violates constraints; undo move
           }
       }
       return null; //no solution
    } 
    
    //return assignments
    public long getAssignments(){
        return assignments;
    }
}
