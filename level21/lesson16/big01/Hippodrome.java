package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

public class Hippodrome {
    public static Hippodrome game;
    private ArrayList<Horse> horses = new ArrayList<>();

    public ArrayList<Horse> getHorses() {
        return this.horses;
    }

    public void run() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            move();
            print();
            Thread.sleep(200);
        }
    }

    public void move() {
        for (int i = 0; i < horses.size(); i++) {
            horses.get(i).move();
        }
    }

    public void print() {
        for (int i = 0; i < horses.size(); i++) {
            horses.get(i).print();
        }
        System.out.println();
        System.out.println();
    }

    public Horse getWinner() {
        double max = 0;
        Horse winner = null;
        for (int i = 0; i < horses.size(); i++) {
            if (horses.get(i).getDistance() > max) {
                max = horses.get(i).getDistance();
                winner = horses.get(i);
            }
        }
        return winner;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().getName() + "!");

    }

    public static void main(String[] args) {
        game = new Hippodrome();
        Horse h1 = new Horse("Jeyran", 3, 0);
        Horse h2 = new Horse("Maral", 3, 0);
        Horse h3 = new Horse("Chalo", 3, 0);
        game.getHorses().add(h1);
        game.getHorses().add(h2);
        game.getHorses().add(h3);
        try {
            game.run();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        game.printWinner();
    }
}
