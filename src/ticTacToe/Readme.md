# Tic-Tac-Toe

<img src="/art/tictactoe.jpeg"  />


## Explanation:

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

## Optimal Approach:

- Time Complexity:
    - Minimax Algorithm: The complexity of Minimax in this implementation is \(O(9!)\) in the worst case due to the exhaustive nature of the algorithm, but it is optimized with the depth consideration.
    - Move Validations and Updates: Each move validation and update are \(O(1)\), making them highly efficient.
    - Win Condition Check: Each win check is \(O(1)\) for rows, columns, and diagonals.

- Space Complexity:
    - Board Representation: The 3x3 board uses a fixed amount of space \(O(1)\).
    - Minimax Recursion: Depth of the recursion is limited by the number of empty cells, leading to a space complexity of \(O(9)\).

This implementation ensures efficient management of time and space complexity while maintaining a clean and functional code structure. The AI player provides a challenging opponent, enhancing the overall game experience.


### Code Structure and Initial Setup

```
fun main() {
  val game = TicTacToeGame()
  game.play()
}
```
This part defines the main function which is the entry point of the Kotlin application. It creates an instance of the TicTacToeGame class and calls its play method to start the game.

### Class Definition and State Initialization

```
class TicTacToeGame {
  private val board = Array(3) { CharArray(3) { ' ' } }
  private var currentPlayer = 'X'
  private var emptyCells = 9
  ...
}
```
 - board: A 3x3 character array initialized with spaces, representing an empty board.
 - currentPlayer: A character that tracks the current player, starting with player 'X'.
 - emptyCells: An integer count of how many cells are empty, starting with 9 (a full board).


### Gameplay Method

```
fun play() {
  val scanner = Scanner(System.`in`)
  while (true) {
    ...
  }
}
```
This method contains the main game loop, using a Scanner for input handling. It repeatedly performs the following until the game ends:

 - Displays the board.
 - Handles player input and AI moves.
 - Checks for a win or a tie.
 - Switches the player after each valid move.

### Utility Functions within TicTacToeGame

 - printBoard(): Prints the current state of the board.
 - isValidMove(row: Int, col: Int): Checks if a move is valid (i.e., if the chosen cell is empty and within bounds).
 - makeMove(row: Int, col: Int, player: Char): Executes a move by setting a board cell to the player's symbol ('X' or 'O') and decrements the emptyCells count.
 - hasWon(player: Char): Checks if the specified player has won the game by completing a row, column, diagonal, or anti-diagonal.
 - isBoardFull(): Returns true if there are no empty cells left on the board.
 - switchPlayer(): Toggles currentPlayer between 'X' and 'O'.

### AI Logic with Minimax Algorithm

 - getBestMove(): Determines the best move for the AI using the minimax algorithm. It iterates over possible moves, applying each temporarily, and uses the minimax function to evaluate the move's effectiveness.
 - minimax(depth: Int, isMaximizing: Boolean): A recursive function that evaluates the game board. It considers potential future moves and returns a score based on the likelihood of AI winning ('O'), losing ('X'), or tying. It uses a depth parameter to minimize the loss in worse scenarios and maximize gains in favorable ones. This function applies the typical minimax logic with optimization to prefer quicker wins or delayed losses.

### Conclusion
This Kotlin program demonstrates a basic but complete implementation of Tic Tac Toe with a minimax-based AI for decision-making. It efficiently manages game state, player interactions, and AI strategy within a well-structured object-oriented approach.

## License 📜
This project is licensed under the MIT License - see the LICENSE.md file for details.

## Show Your Support 💖
Give a ⭐️ if this project helped you!

## Resource / Learning

| Description                       | Link                                                         |
|-----------------------------------|--------------------------------------------------------------|
| **Looking for 1:1 Sessions?**         | [Click Here](https://topmate.io/anandwana001)                |
| **Looking for a Long Term Mentor**    | [Click Here](https://www.preplaced.in/profile/akshay-nandwana) |
| **14-Week Android Engineering Roadmap** | [Download PDF Here](https://topmate.io/anandwana001/1036233) |
| **Top 169 DSA Questions**            | [Download PDF Here](https://topmate.io/anandwana001/1022452) |






