# Sudoku-Agents

Brute-force and MRV agents for solving sudoku puzzles.

Observations:
The MRV agent consistently out-did the Brute-force agent; however, in some instances, where the missing values in the first several rows of the puzzle were those at the beginning of the domain, the Brute-force agent outdid the MRV agent, as it quickly selected the correct values, greatly reducing the search space. An example of that would be SudokuPuzzle2.txt, which took the MRV agent 391,979 assignments, while the Brute-force agent got lucky and completed it in 3061 assignments.
