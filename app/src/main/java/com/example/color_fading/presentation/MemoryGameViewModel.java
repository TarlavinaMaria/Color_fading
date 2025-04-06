package com.example.color_fading.presentation;

import com.example.color_fading.data.MemoryGameRepository;
import com.example.color_fading.domain.MemoryGameUseCase;

import java.util.List;

public class MemoryGameViewModel {
    MemoryGameUseCase useCase;

    //TODO Поменять
    public MemoryGameViewModel() {
        this.useCase = new MemoryGameUseCase(new MemoryGameRepository());
    }

    public void startGame(int level) {
        useCase.StartNewGame(level);
    }

    public boolean validateUserInput(List<Integer> userInput) {
        return useCase.checkUserInput(userInput);
    }

    public List<Integer> getSequence() {
        return useCase.getSequence();
    }

}
