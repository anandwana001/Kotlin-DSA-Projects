package sudoku

class TwoPlayerSudoku(private val board: Array<IntArray>) {
  private val size = 9
  private val empty = 0

  fun playGame() {
    var playerTurn = true // True for human, false for computer
    while (true) {
      printBoard()
      if (!hasEmptyCells()) {
        println("Game over! The board is complete.")
        break
      }

      if (playerTurn) {
        val (row, col, num) = getPlayerInput()
        if (!isValidPlacement(num, row, col)) {
          println("Invalid move! Try again.")
          continue
        }
        board[row][col] = num
        println("Player move placed at ($row, $col) with number $num")
      } else {
        computerMove()
      }
      playerTurn = !playerTurn
    }
  }

  private fun getPlayerInput(): Triple<Int, Int, Int> {
    println("Enter your move as 'row col num':")
    val input = readLine()!!.split(" ").map { it.toInt() }
    return Triple(input[0], input[1], input[2])
  }

  private fun computerMove() {
    val validMoves = mutableListOf<Triple<Int, Int, Int>>()
    for (row in board.indices) {
      for (col in board.indices) {
        if (board[row][col] == empty) {
          for (num in 1..9) {
            if (isValidPlacement(num, row, col)) {
              validMoves.add(Triple(row, col, num))
            }
          }
        }
      }
    }
    if (validMoves.isNotEmpty()) {
      val (row, col, num) = validMoves.random()
      board[row][col] = num
      println("Computer placed $num at ($row, $col)")
    }
  }

  private fun isValidPlacement(num: Int, row: Int, col: Int): Boolean {
    // Check row and column
    for (i in 0 until size) {
      if (board[row][i] == num || board[i][col] == num) return false
    }
    // Check 3x3 sub-box
    val startRow = (row / 3) * 3
    val startCol = (col / 3) * 3
    for (r in startRow until startRow + 3) {
      for (c in startCol until startCol + 3) {
        if (board[r][c] == num) return false
      }
    }
    return true
  }

  private fun hasEmptyCells(): Boolean {
    return board.any { row -> row.any { it == empty } }
  }

  private fun printBoard() {
    println("Current board:")
    for (row in board) {
      println(row.joinToString(" "))
    }
    println()
  }
}

fun main() {
  val initialBoard = arrayOf(
    intArrayOf(5, 3, 0, 0, 7, 0, 0, 0, 0),
    intArrayOf(6, 0, 0, 1, 9, 5, 0, 0, 0),
    intArrayOf(0, 9, 8, 0, 0, 0, 0, 6, 0),
    intArrayOf(8, 0, 0, 0, 6, 0, 0, 0, 3),
    intArrayOf(4, 0, 0, 8, 0, 3, 0, 0, 1),
    intArrayOf(7, 0, 0, 0, 2, 0, 0, 0, 6),
    intArrayOf(0, 6, 0, 0, 0, 0, 2, 8, 0),
    intArrayOf(0, 0, 0, 4, 1, 9, 0, 0, 5),
    intArrayOf(0, 0, 0, 0, 8, 0, 0, 7, 9)
  )
  val sudoku = TwoPlayerSudoku(initialBoard)
  sudoku.playGame()
}
