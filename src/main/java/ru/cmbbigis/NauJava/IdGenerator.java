package ru.cmbbigis.NauJava;

public class IdGenerator {
    public static long id = 1;

    public static long getNextId() {
        return id++;
    }
}
