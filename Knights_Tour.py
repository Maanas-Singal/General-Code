### You can adjust the m and n parameters in the knight_tour function to solve the Knight's Tour problem on different-sized chessboards. 
### Note that for larger chessboards, this backtracking algorithm might take a considerable amount of time to find a solution, if one exists.

def is_valid_move(x, y, m, n, visited):
    return 0 <= x < m and 0 <= y < n and not visited[x][y]

def knight_tour(m, n):
    def backtracking(x, y, move_number):
        if move_number == m * n:
            return True

        for dx, dy in [(1, 2), (2, 1), (2, -1), (1, -2),
                       (-1, -2), (-2, -1), (-2, 1), (-1, 2)]:
            next_x, next_y = x + dx, y + dy
            if is_valid_move(next_x, next_y, m, n, visited):
                visited[next_x][next_y] = True
                if backtracking(next_x, next_y, move_number + 1):
                    return True
                visited[next_x][next_y] = False

        return False

    # Initialize the chessboard and starting position
    visited = [[False for _ in range(n)] for _ in range(m)]
    start_x, start_y = 0, 0
    visited[start_x][start_y] = True

    # Start the backtracking algorithm
    if backtracking(start_x, start_y, 1):
        print("Solution exists:")
        for row in visited:
            print(row)
    else:
        print("No solution exists.")

# Example usage with a 5x5 chessboard
knight_tour(5, 5)
