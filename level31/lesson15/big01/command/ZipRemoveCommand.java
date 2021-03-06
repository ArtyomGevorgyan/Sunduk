package com.javarush.test.level31.lesson15.big01.command;


import com.javarush.test.level31.lesson15.big01.ConsoleHelper;
import com.javarush.test.level31.lesson15.big01.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipRemoveCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Udalenie содержимого архива.");

        ZipFileManager zipFileManager = getZipFileManager();

        ConsoleHelper.writeMessage("Введите полное имя файла или директории для udalenia");
        Path file = Paths.get(ConsoleHelper.readString());

        zipFileManager.removeFile(file);

        ConsoleHelper.writeMessage("Udalenie fayla proshlo uspeshno.");
    }
}
