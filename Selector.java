/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku;

/**
 * Interface for the two selector methods
 * @author Milda
 */
public interface Selector {
    public GridCell getNextCell(Sudoku puzzle);
}
