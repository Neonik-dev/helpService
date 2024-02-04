package com.example.innotech_hw1.services;

import com.example.innotech_hw1.services.HelpService;
import com.example.innotech_hw1.services.IHelpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class HelpServiceTest {
    private IHelpService helpService;

    @BeforeEach
    public void setUp() {
        helpService = new HelpService();
    }

    @Test
    public void getPhrase_CheckRandom() {
        // given
        String phrase = helpService.getPhrase();
        int count = 0;

        // when
        for (; count < 101; count++) {
            if (!phrase.equals(helpService.getPhrase())) {
                break;
            }
        }

        // then
        assertNotEquals(count, 100);
    }

    @Test
    public void addPhrase_Ok() {
        // given
        String phrase = "Help phrase for test";
        int count = 0;

        // when
        for (; count < 101; count++) {
            if (phrase.equals(helpService.getPhrase())) {
                break;
            }
        }

        // then
        assertNotEquals(count, 100);
    }

    @Test
    public void addMorePhrase_Ok() {
        // given
        String phrase = "Help phrase for test";
        List<String> arrPhrases = new ArrayList<>(50);
        for (int i = 0; i < 50; i++) {
            arrPhrases.add(phrase + i);
            helpService.addPhrase(arrPhrases.get(i));
        }
        int count = 10;

        // when
        for (int i = 0; i < 100 && count > 0; i++) {
            if (arrPhrases.contains(helpService.getPhrase())) {
                count--;
            }
        }

        // then
        assertEquals(count, 0);
    }
}
