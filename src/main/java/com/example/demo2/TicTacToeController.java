package com.example.demo2;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import javafx.scene.layout.GridPane;
//
//public class TicTacToeController {
//
//    @FXML
//    private GridPane gridBoard;
//    @FXML
//    private RadioButton rbX;
//    @FXML
//    private RadioButton rbO;
//
//    private boolean isPlayerVsComputer = false;
//    private char currentPlayer = 'X';
//    private char[][] board = new char[3][3];
//
//    @FXML
//    private void handlePlayerVsPlayer() {
//        isPlayerVsComputer = false;
//        resetBoard();
//    }
//
//    @FXML
//    private void handlePlayerVsComputer() {
//        isPlayerVsComputer = true;
//        resetBoard();
//    }
//
//    @FXML
//    private void handleCellClick(javafx.event.ActionEvent event) {
//        Button clickedButton = (Button) event.getSource();
//        String id = clickedButton.getId();
//        int row = Character.getNumericValue(id.charAt(3));
//        int col = Character.getNumericValue(id.charAt(4));
//
//        if (board[row][col] == '\0') {
//            board[row][col] = currentPlayer;
//            clickedButton.setText(String.valueOf(currentPlayer));
//            clickedButton.setDisable(true);  // Disable the button after it's clicked
//            if (checkWin()) {
//                showAlert("Ο παίκτης " + currentPlayer + " κέρδισε!");
//                resetBoard();
//            } else if (isBoardFull()) {
//                showAlert("Ισοπαλία!");
//                resetBoard();
//            } else {
//                switchPlayer();
//                if (isPlayerVsComputer && currentPlayer == 'O') {
//                    makeComputerMove();
//                }
//            }
//        }
//    }
//
//    private void switchPlayer() {
//        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
//    }
//
//    private void makeComputerMove() {
//        // Simple AI: pick the first available cell
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (board[i][j] == '\0') {
//                    board[i][j] = 'O';
//                    updateButton(i, j, 'O');
//                    if (checkWin()) {
//                        showAlert("Ο υπολογιστής κέρδισε!");
//                        resetBoard();
//                    } else if (isBoardFull()) {
//                        showAlert("Ισοπαλία!");
//                        resetBoard();
//                    } else {
//                        switchPlayer();
//                    }
//                    return;
//                }
//            }
//        }
//    }
//
//    private void updateButton(int row, int col, char player) {
//        Button btn = (Button) gridBoard.lookup("#btn" + row + col);
//        btn.setText(String.valueOf(player));
//        btn.setDisable(true);  // Disable the button
//
//    }
//
//    private boolean checkWin() {
//        // Check rows, columns, and diagonals
//        for (int i = 0; i < 3; i++) {
//            if (board[i][0] != '\0' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) return true;
//            if (board[0][i] != '\0' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) return true;
//        }
//        if (board[0][0] != '\0' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return true;
//        if (board[0][2] != '\0' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) return true;
//        return false;
//    }
//
//    private boolean isBoardFull() {
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                if (board[i][j] == '\0') return false;
//            }
//        }
//        return true;
//    }
//
//    private void showAlert(String message) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Game Over");
//        alert.setHeaderText(null);
//        alert.setContentText(message);
//        alert.showAndWait();
//    }
//
//    private void resetBoard() {
//        board = new char[3][3];
//        for (int i = 0; i < 3; i++) {
//            for (int j = 0; j < 3; j++) {
//                Button btn = (Button) gridBoard.lookup("#btn" + i + j);
//                btn.setText("");
//                btn.setDisable(false);  // Enable the button
//            }
//        }
//        currentPlayer = rbX.isSelected() ? 'X' : 'O';
//        if (isPlayerVsComputer && currentPlayer == 'O') {
//            makeComputerMove();
//        }
//    }
//}

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class TicTacToeController {

    @FXML
    private GridPane gridBoard;
    @FXML
    private RadioButton rbX;
    @FXML
    private RadioButton rbO;

    private boolean isPlayerVsComputer = false;
    private char currentPlayer = 'X';
    private char[][] board = new char[3][3];

    @FXML
    private void handlePlayerVsPlayer() {
        isPlayerVsComputer = false;
        resetBoard();
    }

    @FXML
    private void handlePlayerVsComputer() {
        isPlayerVsComputer = true;
        resetBoard();
    }

    @FXML
    private void handleCellClick(javafx.event.ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String id = clickedButton.getId();
        int row = Character.getNumericValue(id.charAt(3));
        int col = Character.getNumericValue(id.charAt(4));

        if (board[row][col] == '\0') {
            board[row][col] = currentPlayer;
            clickedButton.setText(String.valueOf(currentPlayer));
            clickedButton.setDisable(true);  // Disable the button after it's clicked
            clickedButton.setOpacity(1);
            if (checkWin()) {
                showAlert("Ο παίκτης " + currentPlayer + " κέρδισε!");
                resetBoard();
            } else if (isBoardFull()) {
                showAlert("Ισοπαλία!");
                resetBoard();
            } else {
                switchPlayer();
                if (isPlayerVsComputer && currentPlayer == 'O') {
                    makeComputerMove();
                }
            }
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    private void makeComputerMove() {
        int[] bestMove = findBestMove();
        int row = bestMove[0];
        int col = bestMove[1];
        board[row][col] = 'O';
        updateButton(row, col, 'O');
        if (checkWin()) {
            showAlert("Κέρδισε ο υπολογιστής");
            resetBoard();
        } else if (isBoardFull()) {
            showAlert("Ισοπαλία!");
            resetBoard();
        } else {
            switchPlayer();
        }
    }

    private int[] findBestMove() {
        int bestVal = Integer.MIN_VALUE;
        int[] bestMove = {-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') {
                    board[i][j] = 'O';
                    int moveVal = minimax(board, 0, false);
                    board[i][j] = '\0';
                    if (moveVal > bestVal) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(char[][] board, int depth, boolean isMax) {
        int score = evaluate(board);

        if (score == 10) return score - depth;
        if (score == -10) return score + depth;
        if (isBoardFull()) return 0;

        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '\0') {
                        board[i][j] = 'O';
                        best = Math.max(best, minimax(board, depth + 1, false));
                        board[i][j] = '\0';
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '\0') {
                        board[i][j] = 'X';
                        best = Math.min(best, minimax(board, depth + 1, true));
                        board[i][j] = '\0';
                    }
                }
            }
            return best;
        }
    }

    private int evaluate(char[][] b) {
        for (int row = 0; row < 3; row++) {
            if (b[row][0] == b[row][1] && b[row][1] == b[row][2]) {
                if (b[row][0] == 'O') return 10;
                else if (b[row][0] == 'X') return -10;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (b[0][col] == b[1][col] && b[1][col] == b[2][col]) {
                if (b[0][col] == 'O') return 10;
                else if (b[0][col] == 'X') return -10;
            }
        }

        if (b[0][0] == b[1][1] && b[1][1] == b[2][2]) {
            if (b[0][0] == 'O') return 10;
            else if (b[0][0] == 'X') return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0]) {
            if (b[0][2] == 'O') return 10;
            else if (b[0][2] == 'X') return -10;
        }

        return 0;
    }

    private void updateButton(int row, int col, char player) {
        Button btn = (Button) gridBoard.lookup("#btn" + row + col);
        btn.setText(String.valueOf(player));
        btn.setDisable(true);  // Disable the button
    }

    private boolean checkWin() {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '\0' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) return true;
            if (board[0][i] != '\0' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) return true;
        }
        if (board[0][0] != '\0' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return true;
        if (board[0][2] != '\0' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) return true;
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\0') return false;
            }
        }
        return true;
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void resetBoard() {
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button btn = (Button) gridBoard.lookup("#btn" + i + j);
                btn.setText("");
                btn.setDisable(false);  // Enable the button
            }
        }
        currentPlayer = rbX.isSelected() ? 'X' : 'O';
        if (isPlayerVsComputer && currentPlayer == 'O') {
            makeComputerMove();
        }
    }
}

