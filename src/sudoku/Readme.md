# Sudoku

<img src="/art/sudoku.webp"  />


## Explanation:

### Data Structures

1. **Two-Dimensional Array**:
  - **Usage**: The primary data structure used in the game is a two-dimensional integer array `Array<IntArray>`, which represents the Sudoku board. Each element of the array holds an integer value that corresponds to the numbers placed in the Sudoku grid (1 through 9) or 0 for empty cells.
  - **Purpose**: This structure is ideal for grid-based games like Sudoku where the access to any cell and its modification needs to be direct and efficient.

2. **Lists**:
  - **Usage**: Temporary lists (e.g., `possibleValues` in `getPossibleValues()`) are used to store valid numbers that can be placed in a specific cell during the computer's turn.
  - **Purpose**: These lists are crucial for dynamically determining the valid moves available based on the current state of the board, allowing the computer to choose a valid number randomly from this list.

### Algorithm

1. **Linear Scan for Empty Cells**:
  - **Description**: The `computerMove()` function scans the Sudoku board linearly to find the first available empty cell. This is a straightforward approach to locate where a move can be made.
  - **Purpose**: Identifying empty cells is the first step in making a move. The linear scan ensures that every cell is checked until an empty one is found, maintaining the game's progress.

2. **Validity Checking**:
  - **Description**: The `isValidPlacement()` function is used to determine whether placing a specific number in a given cell is valid. This involves checking the corresponding row, column, and 3x3 sub-grid to ensure no Sudoku rule is violated.
  - **Purpose**: Ensuring the validity of each move is essential for the game's integrity. This method guarantees that all game moves adhere to standard Sudoku rules, maintaining challenge and fairness.

3. **Random Selection of Moves**:
  - **Description**: Once possible moves are identified (numbers that can validly be placed in a cell), the computer randomly selects one of these moves to execute.
  - **Purpose**: Random selection introduces an element of unpredictability in the computer's gameplay, making it less deterministic and more fun, although it doesn't strategize beyond rule adherence.

### Overall Algorithmic Strategy

- The combined use of a straightforward linear scan with validity checks and random move selection forms a basic but effective algorithm for a computer opponent in a two-player Sudoku game. It ensures that the computer acts according to the rules and makes the game interactive by alternating turns between the human player and the computer.

### Potential Enhancements

To make the computer opponent more challenging and engaging:
- **Advanced Move Selection**: Implement heuristic or strategic algorithms, such as prioritizing moves that block the human player's obvious progress or choosing moves that open up more possibilities for future moves.
- **State Tracking**: Use advanced data structures to keep track of potential numbers for each row, column, and grid dynamically, reducing the need to perform full scans and checks for each move.

These enhancements could transform the simple computer opponent into a more formidable and strategic player, increasing the depth and enjoyment of the game.
## Optimal Approach:
The time and space complexity of the modified Kotlin code for a two-player Sudoku game where the computer acts as the second player can be analyzed based on how the board is accessed and updated, and how moves are determined. Let's delve into these aspects:

### Time Complexity

1. **`computerMove()` Function**:
  - **Board Scan**: The function scans the entire board to find an empty cell. In the worst case, all cells (81 for a 9x9 board) might need to be checked.
  - **`getPossibleValues()` Call**: For each empty cell, `getPossibleValues()` is called, which checks every number from 1 to 9 to see if it can be placed in that cell.
  - **`isValidPlacement()` Checks**: Each call to `isValidPlacement()` involves checking the current row, column, and the 3x3 subgrid to ensure no Sudoku rules are violated. This results in up to 27 checks per number per cell in the worst case.
  - **Overall Time Complexity**: For each empty cell, up to 9 numbers are checked, and each check involves up to 27 comparisons. Therefore, the worst-case time complexity for a single move can be approximated as \(O(1 \times 9 \times 27)\) for each empty cell. Since `computerMove()` exits after placing a single number, the worst case for a single move is \(O(243)\) per move, but this happens every turn, so the complexity accumulates based on the number of empty cells and is reduced as the board fills up.

2. **`getPossibleValues()` Function**:
  - This function contributes to the complexity mentioned above, being part of the process of determining where the computer can make a valid move.

### Space Complexity

1. **Board Storage**:
  - The main space usage comes from storing the board itself, which is \(O(n^2)\), where \(n = 9\) for a standard Sudoku board. Hence, it's \(O(81)\) or \(O(1)\) in terms of constant space since the board size does not change.

2. **Recursion and Call Stack**:
  - There is no recursion used in the current implementation for the computer's moves; thus, there's no additional call stack depth to consider for the computer's logic. The recursion would primarily be a factor if you used a recursive strategy to solve or optimize the board, which is not the case here.

3. **Auxiliary Structures**:
  - Temporary structures like the list of valid numbers in `getPossibleValues()` do not exceed 9 elements (since there are only 9 possible numbers in Sudoku). Therefore, the space for these structures is also constant, \(O(1)\).

### Overall Complexity Summary

- **Time Complexity**: The per-move complexity is primarily influenced by the number of empty cells and the validation checks, with a worst-case scenario of \(O(243)\) operations per move when the board is nearly empty. This complexity reduces as the board fills up.
- **Space Complexity**: The space complexity is constant relative to the size of the board and the temporary structures used for move determination, \(O(81) + O(1)\), considering standard Sudoku board dimensions.

In practice, these complexities mean the implementation is reasonably efficient for standard 9x9 Sudoku but could be computationally intensive if adapted for larger boards or more complex Sudoku variants without further optimizations like better state tracking or strategic move planning.

## Code Breakdown

### `computerMove()` Function

This function is responsible for making a move on behalf of the computer. It scans the Sudoku board to find an empty cell, determines all valid numbers that can be placed in that cell, and then selects one randomly to place on the board.

```kotlin
private fun computerMove() {
    val validMoves = mutableListOf<Triple<Int, Int, Int>>()
    for (row in board.indices) {
        for (col in board.indices) {
            if (board[row][col] == empty) {
                val possibleValues = getPossibleValues(row, col)
                if (possibleValues.isNotEmpty()) {
                    val num = possibleValues.random()
                    board[row][col] = num
                    println("Computer placed $num at ($row, $col)")
                    return
                }
            }
        }
    }
}
```

- **Iterating Through the Board**: The nested `for` loops iterate through each cell of the board by row and column.
- **Check for Empty Cell**: It checks if the current cell is empty (`board[row][col] == empty`).
- **Get Possible Values**: If the cell is empty, it calls `getPossibleValues(row, col)` to retrieve a list of all valid numbers that can be placed in that cell.
- **Make a Move**: If there are valid numbers (i.e., the list is not empty), it selects one randomly and places it in the cell. It then prints which number was placed and where, and exits the function early using `return`, meaning the computer makes only one move per call.

### `getPossibleValues()` Function

This function determines all valid numbers that can be placed in a specified cell. It's called by `computerMove()` to find valid choices for each empty cell.

```kotlin
private fun getPossibleValues(row: Int, col: Int): List<Int> {
    val possibleValues = mutableListOf<Int>()
    for (num in 1..9) {
        if (isValidPlacement(num, row, col)) {
            possibleValues.add(num)
        }
    }
    return possibleValues
}
```

- **List of Possible Values**: A mutable list `possibleValues` is initialized to store numbers that can be legally placed in the specified cell.
- **Check Each Number**: The function iterates through numbers 1 to 9, checking if each can be placed in the cell without violating Sudoku rules.
- **Use `isValidPlacement()`**: This existing function is used to check if placing a number `num` in the cell at `row, col` would be valid. If valid, the number is added to the list of possible values.
- **Return Valid Numbers**: After checking all numbers, it returns the list of possible numbers that can be legally placed in that cell.

### Key Points

- **Efficiency and Optimization**: While this approach optimizes the search by targeting only empty cells and determining possible numbers for each, it still involves scanning and checking every number for every empty cell. Further optimization could involve more sophisticated state tracking, reducing the need to reevaluate every possible number for every move.
- **Random Move Selection**: The computer's strategy in this implementation is purely random among valid choices. While this ensures legal moves, it does not strategically challenge the player. To enhance gameplay, strategies like blocking player moves or setting up for future moves could be incorporated.

This setup effectively allows the computer to participate in the game by making informed, valid moves, ensuring the game progresses smoothly while adhering to Sudoku rules.

## Conclusion
This Kotlin program demonstrates a basic but complete implementation of Sudoku with a minimax-based AI for decision-making. It efficiently manages game state, player interactions, and AI strategy within a well-structured object-oriented approach.

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






