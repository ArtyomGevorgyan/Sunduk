package com.javarush.test.level34.lesson15.big01.model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        if (level > 60) {
            level = level - 60;
        }

        Set<Box> boxes = new HashSet<>();
        Set<Wall> walls = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Player player = new Player(0,0);

        try (BufferedReader reader = new BufferedReader(new FileReader(levels.toString()))) {
            while (true) {
                String input = reader.readLine();
                if (("Maze: " + level).equals(input))
                    break;
            }

            reader.readLine();
            int width = Integer.parseInt(reader.readLine().split(" ")[2]);
            int height = Integer.parseInt(reader.readLine().split(" ")[2]);
            reader.readLine();
            reader.readLine();
            reader.readLine();
            for (int y = 0; y < height; y++) {
                String in = reader.readLine();
                char[] array = in.toCharArray();
                for (int x = 0; x < width; x++)
                {
                    char character = array[x];
                    switch (character)
                    {
                        case ' ':
                            break;
                        case '@':
                            player = new Player(x * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2, y * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2);
                            break;
                        case 'X':
                            walls.add(new Wall(x * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2, y * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2));
                            break;
                        case '.':
                            homes.add(new Home(x * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2, y * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2));
                            break;
                        case '*':
                            boxes.add(new Box(x * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2, y * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2));
                            break;
                        case '&':
                            boxes.add(new Box(x * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2, y * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2));
                            homes.add(new Home(x * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2, y * Model.FIELD_SELL_SIZE + Model.FIELD_SELL_SIZE / 2));
                            break;
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return new GameObjects(walls, boxes,homes,player);
    }
}
