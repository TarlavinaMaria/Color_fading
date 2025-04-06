package com.example.color_fading.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemoryGameRepository {
    List<Integer> sequence = new ArrayList<>();

    public void generateNewSequence(int length) {
        sequence.clear();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sequence.add(random.nextInt(4));
        }
    }

    public List<Integer> getSequence() {
        return new ArrayList<>(sequence);
    }
}
