package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageSlf4j {

    private static final Logger LOG =
            LoggerFactory.getLogger(UsageSlf4j.class.getName());

    public static void main(String[] args) {
        LOG.trace("trace message");
        LOG.debug("debug message");
        LOG.info("info message");
        LOG.warn("warn message");
        LOG.error("error message");

        byte b = 66;
        short s = 1995;
        char c = 'a';
        int i = 5;
        long l = 77L;
        float f = 23f;
        double d = 67;
        boolean bool = true;

        LOG.debug("Primitive types in Java: byte = {}, short = {}, char = {}, int = {}, long = {}, float = {}, double = {}, boolean = {}", b, s, c, i, l, f, d, bool);
        LOG.error("Primitive types in Java: byte = {}, short = {}, char = {}, int = {}, long = {}, float = {}, double = {}, boolean = {}", b, s, c, i, l, f, d, bool);
    }
}
