package com.example.mastermind;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Random;

public class HelloController {
    //variables
    int clicked = 0;
    int guess = 1;
    int a_value, b_value;
    int[] secret = new int[4];
    int[] guessed_colors = new int[4];   // contains the colors that the player guessed in the current row

    @FXML
    private Circle c11, c12, c13, c14, c21, c22, c23, c24, c31, c32, c33, c34, c41, c42, c43, c44, c51, c52, c53, c54,
                   c61, c62, c63, c64, c71, c72, c73, c74, c81, c82, c83, c84, c91, c92, c93, c94, c101, c102, c103, c104,
                   secret1, secret2, secret3, secret4;
    @FXML
    private Circle circle;

    @FXML
    private Label a, a1, a2, a3, a4, a5, a6, a7, a8, a9, a10,
                  b, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10;
    @FXML
    private Label status_lab;

    @FXML
    private Button generate_btn, check_btn, clear_btn;

    //signals
    public void secretGenerator(ActionEvent event) {
        Random rand = new Random();
        //red: 1, orange: 2, yellow: 3, green: 4, blue: 5, purple: 6
        for (int i = 0; i < 4; i++) {
            secret[i] = rand.nextInt(6) + 1;
        }
        generate_btn.setDisable(true);
        System.out.println("Secret: " + Arrays.toString(secret));
    }

    @FXML
    public void findCircle() {
        if (guess == 1){
            if (clicked % 4== 1){circle = c11;}
            else if (clicked % 4 == 2){circle = c12;}
            else if (clicked % 4== 3){circle = c13;}
            else if (clicked % 4 == 0){circle = c14;}
        }

        if (guess == 2){
            if (clicked % 4== 1){circle = c21;}
            else if (clicked % 4 == 2){circle = c22;}
            else if (clicked % 4== 3){circle = c23;}
            else if (clicked % 4 == 0){circle = c24;}
        }

        if (guess == 3){
            if (clicked % 4== 1){circle = c31;}
            else if (clicked % 4 == 2){circle = c32;}
            else if (clicked % 4== 3){circle = c33;}
            else if (clicked % 4 == 0){circle = c34;}
        }

        if (guess == 4){
            if (clicked % 4== 1){circle = c41;}
            else if (clicked % 4 == 2){circle = c42;}
            else if (clicked % 4== 3){circle = c43;}
            else if (clicked % 4 == 0){circle = c44;}
        }

        if (guess == 5){
            if (clicked % 4== 1){circle = c51;}
            else if (clicked % 4 == 2){circle = c52;}
            else if (clicked % 4== 3){circle = c53;}
            else if (clicked % 4 == 0){circle = c54;}
        }

        if (guess == 6){
            if (clicked % 4== 1){circle = c61;}
            else if (clicked % 4 == 2){circle = c62;}
            else if (clicked % 4== 3){circle = c63;}
            else if (clicked % 4 == 0){circle = c64;}
        }

        if (guess == 7){
            if (clicked % 4== 1){circle = c71;}
            else if (clicked % 4 == 2){circle = c72;}
            else if (clicked % 4== 3){circle = c73;}
            else if (clicked % 4 == 0){circle = c74;}
        }

        if (guess == 8){
            if (clicked % 4== 1){circle = c81;}
            else if (clicked % 4 == 2){circle = c82;}
            else if (clicked % 4== 3){circle = c83;}
            else if (clicked % 4 == 0){circle = c84;}
        }

        if (guess == 9){
            if (clicked % 4== 1){circle = c91;}
            else if (clicked % 4 == 2){circle = c92;}
            else if (clicked % 4== 3){circle = c93;}
            else if (clicked % 4 == 0){circle = c94;}
        }

        if (guess == 10){
            if (clicked % 4== 1){circle = c101;}
            else if (clicked % 4 == 2){circle = c102;}
            else if (clicked % 4== 3){circle = c103;}
            else if (clicked % 4 == 0){circle = c104;}
        }
    }

    @FXML
    public void findAB() {
        if (guess == 2) {a = a1; b = b1;}
        else if (guess == 3) {a = a2; b = b2;}
        else if (guess == 4) {a = a3; b = b3;}
        else if (guess == 5) {a = a4; b = b4;}
        else if (guess == 6) {a = a5; b = b5;}
        else if (guess == 7) {a = a6; b = b6;}
        else if (guess == 8) {a = a7; b = b7;}
        else if (guess == 9) {a = a8; b = b8;}
        else if (guess == 10) {a = a9; b = b9;}
        else if (guess == 11) {a = a10; b = b10;}
    }

    @FXML
    public void setAB() {
        int correct_color, correct_position, color, guess_counter, secret_counter, constant;
        a_value = 0;
        b_value = 0;
        for (int i = 1; i < 7; i++){
            color = i;
            correct_color = correct_position = guess_counter = secret_counter = 0;
            for (int j = 0; j < 4; j++){
                if (guessed_colors[j] == color) {
                    guess_counter++;
                }
                if (secret[j] == color){
                    secret_counter++;
                    for (int k = 0; k < 4; k++){
                        if (guessed_colors[k] == color){
                            correct_color++;
                            if (j == k) correct_position++;
                        }
                    }
                }
            }
            if (secret_counter == 0) continue;
            constant = Math.max(guess_counter, secret_counter);
            a_value += correct_position;
            b_value += (correct_color / constant) - correct_position;
        }
        a.setText("a: " + String.valueOf(a_value));
        b.setText("b: " + String.valueOf(b_value));
    }

    @FXML
    public void checkWin() {
        if (a_value == 4) {
            status_lab.setTextFill(Paint.valueOf("#119d2a"));
            status_lab.setText("You guessed the secret!");
            revealSecret();
            check_btn.setDisable(true);
            clear_btn.setDisable(true);
        }
        else if (guess == 11) {
            status_lab.setTextFill(Paint.valueOf("#dc0909"));
            status_lab.setText("      It's ok. Play again.");
            revealSecret();
            check_btn.setDisable(true);
            clear_btn.setDisable(true);
        }
    }

    @FXML
    public void revealSecret() {
        if (secret[0] == 1) {
            secret1.setFill(Paint.valueOf("#ff0000"));
        }
        else if (secret[0] == 2) {
            secret1.setFill(Paint.valueOf("#ffad00"));
        }
        else if (secret[0] == 3) {
            secret1.setFill(Paint.valueOf("#fcfc00"));
        }
        else if (secret[0] == 4) {
            secret1.setFill(Paint.valueOf("#00fa2a"));
        }
        else if (secret[0] == 5) {
            secret1.setFill(Paint.valueOf("#1fc1ff"));
        }
        else if (secret[0] == 6) {
            secret1.setFill(Paint.valueOf("#a01fff"));
        }

        if (secret[1] == 1) {
            secret2.setFill(Paint.valueOf("#ff0000"));
        }
        else if (secret[1] == 2) {
            secret2.setFill(Paint.valueOf("#ffad00"));
        }
        else if (secret[1] == 3) {
            secret2.setFill(Paint.valueOf("#fcfc00"));
        }
        else if (secret[1] == 4) {
            secret2.setFill(Paint.valueOf("#00fa2a"));
        }
        else if (secret[1] == 5) {
            secret2.setFill(Paint.valueOf("#1fc1ff"));
        }
        else if (secret[1] == 6) {
            secret2.setFill(Paint.valueOf("#a01fff"));
        }

        if (secret[2] == 1) {
            secret3.setFill(Paint.valueOf("#ff0000"));
        }
        else if (secret[2] == 2) {
            secret3.setFill(Paint.valueOf("#ffad00"));
        }
        else if (secret[2] == 3) {
            secret3.setFill(Paint.valueOf("#fcfc00"));
        }
        else if (secret[2] == 4) {
            secret3.setFill(Paint.valueOf("#00fa2a"));
        }
        else if (secret[2] == 5) {
            secret3.setFill(Paint.valueOf("#1fc1ff"));
        }
        else if (secret[2] == 6) {
            secret3.setFill(Paint.valueOf("#a01fff"));
        }

        if (secret[3] == 1) {
            secret4.setFill(Paint.valueOf("#ff0000"));
        }
        else if (secret[3] == 2) {
            secret4.setFill(Paint.valueOf("#ffad00"));
        }
        else if (secret[3] == 3) {
            secret4.setFill(Paint.valueOf("#fcfc00"));
        }
        else if (secret[3] == 4) {
            secret4.setFill(Paint.valueOf("#00fa2a"));
        }
        else if (secret[3] == 5) {
            secret4.setFill(Paint.valueOf("#1fc1ff"));
        }
        else if (secret[3] == 6) {
            secret4.setFill(Paint.valueOf("#a01fff"));
        }
    }

    @FXML
    public void redBtn(ActionEvent event) {
        clicked++;
        findCircle();
        if (circle.getFill().toString().equals("0xffffffff")) {
            circle.setFill(Paint.valueOf("#ff0000"));
            guessed_colors[clicked - 1] = 1;
        }
        else clicked--;
        //if all the circles in the row are filled, enable the "Check" button.
        if (clicked == 4) {
            check_btn.setDisable(false);
        }
    }

    @FXML
    public void orangeBtn(ActionEvent event) {
        clicked++;
        findCircle();
        if (circle.getFill().toString().equals("0xffffffff")) {
            circle.setFill(Paint.valueOf("#ffad00"));
            guessed_colors[clicked - 1] = 2;
        }
        else clicked--;
        //if all the circles in the row are filled, enable the "Check" button.
        if (clicked == 4) {
            check_btn.setDisable(false);
        }
    }

    @FXML
    public void yellowBtn(ActionEvent event) {
        clicked++;
        findCircle();
        if (circle.getFill().toString().equals("0xffffffff")) {
            circle.setFill(Paint.valueOf("#fcfc00"));
            guessed_colors[clicked - 1] = 3;
        }
        else clicked--;
        //if all the circles in the row are filled, enable the "Check" button.
        if (clicked == 4) {
            check_btn.setDisable(false);
        }
    }

    @FXML
    public void greenBtn(ActionEvent event) {
        clicked++;
        findCircle();
        if (circle.getFill().toString().equals("0xffffffff")) {
            circle.setFill(Paint.valueOf("#00fa2a"));
            guessed_colors[clicked - 1] = 4;
        }
        else clicked--;
        //if all the circles in the row are filled, enable the "Check" button.
        if (clicked == 4) {
            check_btn.setDisable(false);
        }
    }

    @FXML
    public void blueBtn(ActionEvent event) {
        clicked++;
        findCircle();
        if (circle.getFill().toString().equals("0xffffffff")) {
            circle.setFill(Paint.valueOf("#1fc1ff"));
            guessed_colors[clicked - 1] = 5;
        }
        else clicked--;
        //if all the circles in the row are filled, enable the "Check" button.
        if (clicked == 4) {
            check_btn.setDisable(false);
        }
    }

    @FXML
    public void purpleBtn(ActionEvent event) {
        clicked++;
        findCircle();
        if (circle.getFill().toString().equals("0xffffffff")) {
            circle.setFill(Paint.valueOf("#a01fff"));
            guessed_colors[clicked - 1] = 6;
        }
        else clicked--;
        //if all the circles in the row are filled, enable the "Check" button.
        if (clicked == 4) {
            check_btn.setDisable(false);
        }
    }

    @FXML
    public void clearBtn(ActionEvent event) {
        check_btn.setDisable(true);
        clicked = 4;
        for (int i = 4; i > 0; i--){
            clicked--;
            findCircle();
            circle.setFill(Paint.valueOf("#ffffff"));
            guessed_colors[i - 1] = 0;
        }
    }

    @FXML
    public void checkBtn(ActionEvent event) {
        guess++;
        clicked = 0;
        findAB();
        setAB();
        checkWin();
        check_btn.setDisable(true);
    }

    @FXML
    public void exitBtn(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}