package com.javarush.test.level38.lesson06.home01;

/* Фабрика исключений
Создайте класс - фабрику исключений, который содержит один статический метод, возвращающий нужное исключение.
Этот метод должен принимать один параметр - энум.
Если передан энум:
* ExceptionApplicationMessage, верните исключение Exception
* ExceptionDBMessage, верните исключение RuntimeException
* ExceptionUserMessage, верните Error
иначе верните IllegalArgumentException без сообщения.

В качестве сообщения (message) для каждого возвращаемого объекта используйте элементы энума, в которых все знаки
подчеркивания замените на пробелы. Сообщение должно быть в нижнем регистре за исключением первого символа.
Например, сообщение для ExceptionApplicationMessage.SOCKET_IS_CLOSED - "Socket is closed".

Верните класс созданной фабрики в методе Solution.getFactoryClass.

Энумы не меняйте.
*/

import java.util.Enumeration;

public class Solution {
    public static Class getFactoryClass() {
        return ExceptionFactory.class;
    }
}

class ExceptionFactory {

    public static Throwable getException(Enum e) {
        if (e != null)
        {
            String message = e.name().charAt(0) + "" + e.name().substring(1).toLowerCase().replaceAll("_", " ");
            if (e instanceof ExceptionApplicationMessage) {
                return new Exception(message);
            }
            if (e instanceof ExceptionDBMessage) {
                return new RuntimeException(message);
            }
            if (e instanceof ExceptionUserMessage) {
                return new Error(message);
            }
        }
        return new IllegalArgumentException();
    }
}
