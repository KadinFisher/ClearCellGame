package model;

import java.util.Random;

/**
 * This class extends GameModel and implements the logic of the clear cell game.
 * We define an empty cell as BoardCell.EMPTY. An empty row is defined as one
 * where every cell corresponds to BoardCell.EMPTY.
 * 
 * @author Department of Computer Science, UMCP
 * @author King Bass
 */

public class ClearCellGame extends Game {
	
	private Random random;
	private int strategy;
	private int score;
	
	/**
	 * Defines a board with empty cells. It relies on the super class constructor to
	 * define the board. The random parameter is used for the generation of random
	 * cells. The strategy parameter defines which clearing cell strategy to use
	 * (for this project it will be 1). For fun, you can add your own strategy by
	 * using a value different that one.
	 * 
	 * @param maxRows
	 * @param maxCols
	 * @param random
	 * @param strategy
	 */
	public ClearCellGame(int maxRows, int maxCols, Random random, int strategy) {
		super(maxRows, maxCols);
		this.random = random;
		this.strategy = strategy;
	}

	/**
	 * The game is over when the last board row (row with index board.length -1) is
	 * different from empty row.
	 */
	public boolean isGameOver() {
		boolean over = false;
		for (int colNum = 0; colNum < this.getMaxCols(); colNum++) {
			if (board[board.length-1][colNum] != BoardCell.EMPTY) {
				over = true;
			}
		}
		return over;
	}

	public int getScore() {
		return this.score;
	}
	
	/**
	 * This method will attempt to insert a row of random BoardCell objects if the
	 * last board row (row with index board.length -1) corresponds to the empty row;
	 * otherwise no operation will take place.
	 */
	public void nextAnimationStep() {
		if ( !(this.isGameOver()) ) {
			BoardCell[][] copy = new 
					BoardCell[this.getMaxRows()][this.getMaxCols()];
			for (int rowNum = 1; rowNum < this.getMaxRows(); rowNum++) {
				for (int colNum = 0; colNum < this.getMaxCols(); colNum++) {
					copy[rowNum][colNum] = this.board[rowNum - 1][colNum];
				}
			}
			
			for (int colNum = 0; colNum < this.getMaxCols(); colNum++) {
				copy[0][colNum] = BoardCell.getNonEmptyRandomBoardCell(random);
			}
			this.board = copy;
		}
	}
	
	/**
	 * This method will turn to BoardCell.EMPTY the cell selected and any adjacent
	 * surrounding cells in the vertical, horizontal, and diagonal directions that
	 * have the same color. The clearing of adjacent cells will continue as long as
	 * cells have a color that corresponds to the selected cell. Notice that the
	 * clearing process does not clear every single cell that surrounds a cell
	 * selected (only those found in the vertical, horizontal or diagonal
	 * directions).
	 * 
	 * IMPORTANT: Clearing a cell adds one point to the game's score.<br />
	 * <br />
	 * 
	 * If after processing cells, any rows in the board are empty,those rows will
	 * collapse, moving non-empty rows upward. For example, if we have the following
	 * board (an * represents an empty cell):<br />
	 * <br />
	 * RRR<br />
	 * GGG<br />
	 * YYY<br />
	 * * * *<br/>
	 * <br />
	 * then processing each cell of the second row will generate the following
	 * board<br />
	 * <br />
	 * RRR<br />
	 * YYY<br />
	 * * * *<br/>
	 * * * *<br/>
	 * <br />
	 * IMPORTANT: If the game has ended no action will take place.
	 * 
	 * 
	 */
	public void processCell(int rowIndex, int colIndex) {
		if (! (this.isGameOver()) ) {
			this.turnCellsEmpty(rowIndex, colIndex, this.board);
			int emptyRowIdx = this.getEmptyRow(board);
			if (emptyRowIdx != 0) {
				this.removeEmptyRow(emptyRowIdx);
			}
		}
	}
	
	/**
	 * Turns the correct number of cells to BoardCell.EMPTY
	 * 
	 * @param rowIdx
	 * @param colIdx
	 * @param board
	 */
	private void turnCellsEmpty(int rowIdx, int colIdx, BoardCell[][] board) {
		this.score += this.turnUpCellsEmpty(rowIdx, colIdx, board);
		this.score += this.turnDownCellsEmpty(rowIdx, colIdx, board);
		this.score += this.turnLeftCellsEmpty(rowIdx, colIdx, board);
		this.score += this.turnRightCellsEmpty(rowIdx, colIdx, board);
		this.score += this.turnTopLeftCellsEmpty(rowIdx, colIdx, board); 
		this.score += this.turnTopRightCellsEmpty(rowIdx, colIdx, board);
		this.score += this.turnBottomLeftCellsEmpty(rowIdx, colIdx, board);
		this.score += this.turnBottomRightCellsEmpty(rowIdx, colIdx, board);
		board[rowIdx][colIdx] = BoardCell.EMPTY;
		this.score++;
	}
	
	/**
	 * Determines if any of the rows are empty and returns the index if so.
	 * 
	 * @param board
	 * @return The index of the empty row. Index will be -1 if no row is empty.
	 */
	private int getEmptyRow(BoardCell[][] board) {
		int emptyRowIdx = -1;
		for (int rowNum = 0; rowNum < board.length; rowNum++) {
			for (int colNum = 0; colNum < board[rowNum].length; colNum++) {
				if(board[rowNum][colNum] != BoardCell.EMPTY) {
					// Goes to next row if a BoardCell isn't empty
					break;
				}
				if (colNum == board[rowNum].length - 1) {
					emptyRowIdx = rowNum;
				}
				if (emptyRowIdx > 0) {
					// Stops processing since the empty row was found
					break;
				}
			}
		}
		return emptyRowIdx;
	}
	
	/**
	 * Removes the empty row and shifts by shifting the rest of the board up one.
	 * The bottom row becomes an empty row.
	 * 
	 * @param emptyRowIdx
	 */
	private void removeEmptyRow(int emptyRowIdx) {
		BoardCell[][] copy = this.board;
		for (int rowNum = emptyRowIdx; rowNum < copy.length - 1; rowNum++) {
			for (int colNum = 0; colNum < copy[rowNum].length; colNum++) {
				copy[rowNum][colNum] = this.board[rowNum + 1][colNum];
			}
		}
		this.board = copy;
		this.setRowWithColor(copy.length - 1, BoardCell.EMPTY);
	}
	
	/**
	 * Turns all the correct number of cells above the target to empty.
	 * 
	 * @param rowIdx
	 * @param colIdx
	 * @param board
	 * @return Updated score.
	 */
	private int turnUpCellsEmpty(int rowIdx, int colIdx, BoardCell[][] board) {
		int score = 0;
		BoardCell targetCell = board[rowIdx][colIdx];
		for (int rowNum = rowIdx - 1; rowNum > -1; rowNum--) {
			BoardCell cellCopy = board[rowNum][colIdx];
			if (cellCopy.getName().equals(targetCell.getName())) {
				board[rowNum][colIdx] = BoardCell.EMPTY;
				score++;
			} else {
				// Stops processing because the cell is not the same as 
				// the target cell
				break;
			}
		}
		return score;
	}
	
	/**
	 * Turns all the correct number of cells below the target to empty.
	 * 
	 * @param rowIdx
	 * @param colIdx
	 * @param board
	 * @return Updated score.
	 */
	private int turnDownCellsEmpty(int rowIdx, int colIdx, BoardCell[][] board) {
		int score = 0;
		BoardCell targetCell = board[rowIdx][colIdx];
		for (int rowNum = rowIdx + 1; rowNum < board.length; rowNum++) {
			BoardCell cellCopy = board[rowNum][colIdx];
			if (cellCopy.getName().equals(targetCell.getName())) {
				board[rowNum][colIdx] = BoardCell.EMPTY;
				score++;
			} else {
				// Stops processing because the cell is not the same as 
				// the target cell
				break;
			}
		}
		return score;
	}
	
	/**
	 * Turns all the correct number of cells left of target to empty.
	 * 
	 * @param rowIdx
	 * @param colIdx
	 * @param board
	 * @return Updated score.
	 */
	private int turnLeftCellsEmpty(int rowIdx, int colIdx, BoardCell[][] board) {
		int score = 0;
		BoardCell targetCell = board[rowIdx][colIdx];
		for (int colNum = colIdx - 1; colNum > -1; colNum--) {
			BoardCell cellCopy = board[rowIdx][colNum];
			if (cellCopy.getName().equals(targetCell.getName())) {
				board[rowIdx][colNum] = BoardCell.EMPTY;
				score++;
			} else {
				// Stops processing because the cell is not the same as 
				// the target cell
				break;
			}
		}
		return score;
	}
	
	/**
	 * Turns all the correct number of cells right of target to empty.
	 * 
	 * @param rowIdx
	 * @param colIdx
	 * @param board
	 * @return Updated score.
	 */
	private int turnRightCellsEmpty(int rowIdx, int colIdx, BoardCell[][] board) {
		int score = 0;
		BoardCell targetCell = board[rowIdx][colIdx];
		for (int colNum = colIdx + 1; colNum < board[rowIdx].length; colNum++) {
			BoardCell cellCopy = board[rowIdx][colNum];
			if (cellCopy.getName().equals(targetCell.getName())) {
				board[rowIdx][colNum] = BoardCell.EMPTY;
				score++;
			} else {
				// Stops processing because the cell is not the same as 
				// the target cell
				break;
			}
		}
		return score;
	}
	
	/**
	 *  Turns all the correct number of cells above and to the left of the 
	 *  target cell to empty.
	 *  
	 * @param rowIdx
	 * @param colIdx
	 * @param board
	 * @return Updated score.
	 */
	private int turnTopLeftCellsEmpty(int rowIdx, int colIdx, BoardCell[][] board) {
		int score = 0;
		BoardCell targetCell = board[rowIdx][colIdx];
		int colNum = colIdx - 1;
		for (int rowNum = rowIdx - 1; rowNum > -1; rowNum--) {
			if (colNum > -1) {
				BoardCell cellCopy = board[rowNum][colNum];
				if (cellCopy.getName().equals(targetCell.getName())) {
					board[rowNum][colNum] = BoardCell.EMPTY;
					score++;
				} else {
					// Stops processing because the cell is not the same as 
					// the target cell
					break;
				}
				colNum--;
			} else {
				// Stops processing since the colNum is outside of the board
				break;
			}
		}
		return score;
	}
	
	/**
	 *  Turns all the correct number of cells above and to the right of the 
	 *  target cell to empty.
	 *  
	 * @param rowIdx
	 * @param colIdx
	 * @param board
	 * @return Updated score.
	 */
	private int turnTopRightCellsEmpty(int rowIdx, int colIdx, BoardCell[][] board) {
		int score = 0;
		BoardCell targetCell = board[rowIdx][colIdx];
		int colNum = colIdx + 1;
		for (int rowNum = rowIdx - 1; rowNum > -1; rowNum--) {
			if (colNum < board[rowIdx].length) {
				BoardCell cellCopy = board[rowNum][colNum];
				if (cellCopy.getName().equals(targetCell.getName())) {
					board[rowNum][colNum] = BoardCell.EMPTY;
					score++;
				} else {
					// Stops processing because the cell is not the same as 
					// the target cell
					break;
				}
				colNum++;
			} else {
				// Stops processing since the colNum is outside of the board
				break;
			}
		}
		return score;
	}
	
	/**
	 * Turns all the correct number of cells bottom and to the left of the 
	 * target cell to empty.
	 *  
	 * @param rowIdx
	 * @param colIdx
	 * @param board
	 * @return Updated score.
	 */
	private int turnBottomLeftCellsEmpty(int rowIdx, int colIdx, 
			BoardCell[][] board) {
		int score = 0;
		BoardCell targetCell = board[rowIdx][colIdx];
		int colNum = colIdx - 1;
		for (int rowNum = rowIdx + 1; rowNum < board.length; rowNum++) {
			if (colNum > -1) {
				BoardCell cellCopy = board[rowNum][colNum];
				if (cellCopy.getName().equals(targetCell.getName())) {
					board[rowNum][colNum] = BoardCell.EMPTY;
					score++;
				} else {
					// Stops processing because the cell is not the same as 
					// the target cell
					break;
				}
				colNum--;
			} else {
				// Stops processing since the colNum is outside of the board
				break;
			}
		}
		return score;
	}
	
	/**
	 * Turns all the correct number of cells bottom and to the right of the 
	 * target cell to empty.
	 *  
	 * @param rowIdx
	 * @param colIdx
	 * @param board
	 * @return Updated score.
	 */
	private int turnBottomRightCellsEmpty(int rowIdx, int colIdx, 
			BoardCell[][] board) {
		int score = 0;
		BoardCell targetCell = board[rowIdx][colIdx];
		int colNum = colIdx + 1;
		for (int rowNum = rowIdx + 1; rowNum < board.length; rowNum++) {
			if (colNum < board[rowIdx].length) {
				BoardCell cellCopy = board[rowNum][colNum];
				if (cellCopy.getName().equals(targetCell.getName())) {
					board[rowNum][colNum] = BoardCell.EMPTY;
					score++;
				} else {
					// Stops processing because the cell is not the same as 
					// the target cell
					break;
				}
				colNum++;
			} else {
				// Stops processing since the colNum is outside of the board
				break;
			}
		}
		return score;
	}
}