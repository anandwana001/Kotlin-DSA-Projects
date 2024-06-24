<p align="center">
<img src="/art/poster.webp"/>
</p>


<p>
<img src="https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white" />
</p>


## Projects 📱

1. [Tic-Tac-Toe](https://github.com/anandwana001/Kotlin-DSA-Projects/tree/main/src/ticTacToe)
<img src="/art/tictactoe.jpeg"  width=800 height=450 />

- Time Complexity:
    - Minimax Algorithm: The complexity of Minimax in this implementation is \(O(9!)\) in the worst case due to the exhaustive nature of the algorithm, but it is optimized with the depth consideration.
    - Move Validations and Updates: Each move validation and update are \(O(1)\), making them highly efficient.
    - Win Condition Check: Each win check is \(O(1)\) for rows, columns, and diagonals.

- Space Complexity:
    - Board Representation: The 3x3 board uses a fixed amount of space \(O(1)\).
    - Minimax Recursion: Depth of the recursion is limited by the number of empty cells, leading to a space complexity of \(O(9)\).

- Data Structures:
    - 2D Array (`Array<CharArray>`): Efficiently represents the Tic-Tac-Toe board.
    - Primitive Counter (`emptyCells`): Keeps track of the number of empty cells on the board, optimizing the check for a full board.

- Algorithms:
    - Minimax Algorithm: Implements the Minimax algorithm for the AI player, considering depth to optimize decisions based on potential future moves. This ensures that the AI plays optimally.
    - Game Loop: Efficiently manages the game's progression.
    - Input Validation: Ensures valid player moves while handling invalid input gracefully.
    - Move Making: Updates the board state and decrements the `emptyCells` counter.
    - Win Condition Checking: Determines if a player has won by checking rows, columns, and diagonals.
    - Board Full Check: Optimized by using the `emptyCells` counter, reducing the need for repeated full-board scans.
    - Player Switching: Alternates player turns efficiently.


... More Projects coming soon



## Credits
Author: Akshay Nandwana


<a href="https://www.linkedin.com/in/anandwana001/">
  <img align="left" alt="Akshay's Linkdein" width="22px" src="https://cdn.jsdelivr.net/npm/simple-icons@v3/icons/linkedin.svg" />
</a>
<br><br>


## Looking for 1:1 Sessions? [Click Here](https://topmate.io/anandwana001)

## Looking for a Long Term Mentor [Click Here](https://www.preplaced.in/profile/akshay-nandwana)

## 14-Week Android Engineering Roadmap [Download PDF Here](https://topmate.io/anandwana001/1036233)

## Top 169 DSA Questions [Download PDF Here](https://topmate.io/anandwana001/1022452)

## License

                                 Apache License
                           Version 2.0, January 2004
                        http://www.apache.org/licenses/

  
