package com.example.color_fading.domain;

import com.example.color_fading.data.MemoryGameRepository;

import java.util.List;

public class MemoryGameUseCase {
    MemoryGameRepository repository;

    public MemoryGameUseCase(MemoryGameRepository repository) {
        this.repository = repository;
    }

    public void StartNewGame(int level) {
        repository.generateNewSequence(level);
    }

    public boolean checkUserInput(List<Integer> userInput) {
        return userInput.equals(repository.getSequence());
    }

    public List<Integer> getSequence() {
        return repository.getSequence();
    }

}
