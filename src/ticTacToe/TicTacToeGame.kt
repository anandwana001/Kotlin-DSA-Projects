
package ticTacToe

import java.util.*

fun main() {
  val game = TicTacToeGame()
  game.play()
}

class TicTacToeGame {
  private val board = Array(3) { CharArray(3) { ' ' } }
  private var currentPlayer = 'X'
  private var emptyCells = 9

  fun play() {
    val scanner = Scanner(System.`in`)
    while (true) {
      printBoard()
      if (currentPlayer == 'X') {
        println("Player $currentPlayer, enter your move (row and column): ")
        val input = scanner.nextLine()
        val (row, col) = input.split(" ").map { it.toIntOrNull() }
        if (row == null || col == null || !isValidMove(row, col)) {
          println("This move is not valid")
          continue
        }
        makeMove(row, col, currentPlayer)
      } else {
        println("AI is making a move...")
        val (row, col) = getBestMove()
        makeMove(row, col, currentPlayer)
      }

      if (hasWon(currentPlayer)) {
        printBoard()
        println("Player $currentPlayer wins!")
        break
      }

      if (isBoardFull()) {
        printBoard()
        println("The game is a tie!")
        break
      }

      switchPlayer()
    }
  }

  private fun printBoard() {
    println("Current board:")
    for (row in board) {
      println(row.joinToString(" | "))
      println("---------")
    }
  }

  private fun isValidMove(row: Int, col: Int): Boolean {
    return row in 0..2 && col in 0..2 && board[row][col] == ' '
  }

  private fun makeMove(row: Int, col: Int, player: Char) {
    board[row][col] = player
    emptyCells--
  }

  private fun hasWon(player: Char): Boolean {
    for (i in 0..2) {
      if (board[i].all { it == player }) return true
      if (board.all { it[i] == player }) return true
    }
    if ((0..2).all { board[it][it] == player }) return true
    if ((0..2).all { board[it][2 - it] == player }) return true

    return false
  }

  private fun isBoardFull(): Boolean {
    return emptyCells == 0
  }

  private fun switchPlayer() {
    currentPlayer = if (currentPlayer == 'X') 'O' else 'X'
  }

  private fun getBestMove(): Pair<Int, Int> {
    var bestScore = Int.MIN_VALUE
    var move = Pair(-1, -1)
    for (i in 0..2) {
      for (j in 0..2) {
        if (board[i][j] == ' ') {
          board[i][j] = 'O'
          val score = minimax(0, false)
          board[i][j] = ' '
          if (score > bestScore) {
            bestScore = score
            move = Pair(i, j)
          }
        }
      }
    }
    return move
  }

  private fun minimax(depth: Int, isMaximizing: Boolean): Int {
    if (hasWon('O')) return 10 - depth
    if (hasWon('X')) return depth - 10
    if (isBoardFull()) return 0

    if (isMaximizing) {
      var bestScore = Int.MIN_VALUE
      for (i in 0..2) {
        for (j in 0..2) {
          if (board[i][j] == ' ') {
            board[i][j] = 'O'
            val score = minimax(depth + 1, false)
            board[i][j] = ' '
            bestScore = maxOf(score, bestScore)
          }
        }
      }
      return bestScore
    } else {
      var bestScore = Int.MAX_VALUE
      for (i in 0..2) {
        for (j in 0..2) {
          if (board[i][j] == ' ') {
            board[i][j] = 'X'
            val score = minimax(depth + 1, true)
            board[i][j] = ' '
            bestScore = minOf(score, bestScore)
          }
        }
      }
      return bestScore
    }
  }
}

/**
Explanation of Enhancements and Optimizations:

1. Data Structures:
  - 2D Array (`Array<CharArray>`): Efficiently represents the Tic-Tac-Toe board.
  - Primitive Counter (`emptyCells`): Keeps track of the number of empty cells on the board, optimizing the check for a full board.

2. Algorithms:
  - Minimax Algorithm: Implements the Minimax algorithm for the AI player, considering depth to optimize decisions based on potential future moves. This ensures that the AI plays optimally.
  - Game Loop: Efficiently manages the game's progression.
  - Input Validation: Ensures valid player moves while handling invalid input gracefully.
  - Move Making: Updates the board state and decrements the `emptyCells` counter.
  - Win Condition Checking: Determines if a player has won by checking rows, columns, and diagonals.
  - Board Full Check: Optimized by using the `emptyCells` counter, reducing the need for repeated full-board scans.
  - Player Switching: Alternates player turns efficiently.

Optimal Approach:

- Time Complexity:
    - Minimax Algorithm: The complexity of Minimax in this implementation is \(O(9!)\) in the worst case due to the exhaustive nature of the algorithm, but it is optimized with the depth consideration.
    - Move Validations and Updates: Each move validation and update are \(O(1)\), making them highly efficient.
    - Win Condition Check: Each win check is \(O(1)\) for rows, columns, and diagonals.

- Space Complexity:
    - Board Representation: The 3x3 board uses a fixed amount of space \(O(1)\).
    - Minimax Recursion: Depth of the recursion is limited by the number of empty cells, leading to a space complexity of \(O(9)\).

This implementation ensures efficient management of time and space complexity while maintaining a clean and functional code structure. The AI player provides a challenging opponent, enhancing the overall game experience.
 **/