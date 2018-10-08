package com.siwoo.workshop.one;

import org.junit.Test;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by sm123tt@gmail.com on 2018-10-08
 * Project : workshop1
 * Github : http://github.com/Siwoo-Kim
 */

public class NumberProgram {

    public static class NumberGuess {
        enum GAME_STATUS {
            HIGH, LOW, GOOD_JOB, BAD_JOB
        }
        static final int MIN = 100;
        static final int MAX = 5000;
        static final Random random = new Random();
        private int answer;
        private boolean matched = true;

        public void newGame() {
            if(matched) {
                this.answer = random.nextInt(MAX - MIN) + MIN;
                //System.out.println(this.answer);
                this.matched = false;
                return;
            }
            throw new IllegalStateException("You should finish the previous game first");
        }

        public GAME_STATUS guess(int userGuess) {
            if(!(userGuess >= MIN && userGuess <=MAX )) {
                return GAME_STATUS.BAD_JOB;
            }
            if(userGuess == answer) {
                this.matched = true;
                return GAME_STATUS.GOOD_JOB;
            }
            int result = Math.max(userGuess, answer);
            return result == answer ? GAME_STATUS.LOW : GAME_STATUS.HIGH;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        NumberGuess numberGuess = new NumberGuess();

        while (true) {

            if(numberGuess.matched) {
                System.out.println("Number Guess game start!");
                numberGuess.newGame();
            }

            try {
                System.out.print(">> ");
                int guess = scanner.nextInt();
                NumberGuess.GAME_STATUS status = numberGuess.guess(guess);
                switch (status) {
                    case LOW:
                        System.out.println("Try High number, try again."); break;
                    case HIGH:
                        System.out.println("Try Low number, try again."); break;
                    case BAD_JOB:
                        System.out.println("Bad Input!"); break;
                    default:
                        System.out.println("Good Job!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Only number is accepted");
            }

        }
    }

}
