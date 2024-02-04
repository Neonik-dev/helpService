package com.example.innotech_hw1.services;


import com.example.innotech_hw1.utils.RandomizedSet;

public class HelpService implements IHelpService {
    private final RandomizedSet<String> helpPhrases = new RandomizedSet<>(
            "У тебя все получится",
            "Все будет хорошо",
            "Не унывай"
    );

    @Override
    public String getPhrase() {
        return helpPhrases.getRandom();
    }

    @Override
    public void addPhrase(String phrase) {
        helpPhrases.insert(phrase);
    }
}
