package com.banderkat.jokes;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public final class Jokes {

    private static final Random RANDOM = new Random();

    private static final List<String> JOKES = Arrays.asList(
            "Why did the test joke contain a TODO?\nNo comment.",
            "How much space will be saved if the UK leaves the European Union?\n1 GB.",
            "I like computers. They always do what I say. Which is not usually what I meant, but always precisely what I say.");

    private Jokes() {
        //no instance
    }

    public static String getJoke() {
        int position = RANDOM.nextInt(JOKES.size());
        System.out.println("Get joke at " + position);
        return JOKES.get(position);
    }
}
