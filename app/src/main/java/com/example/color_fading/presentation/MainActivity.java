package com.example.color_fading.presentation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.color_fading.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MemoryGameViewModel viewModel;
    private List<Integer> userInput = new ArrayList<>();
    private TextView status;
    private Button startBtn;
    private Button redBtn;
    private Button blueBtn;
    private Button greenBtn;
    private Button yellowBtn;

    // Цвета для подсветки
    private int redColor;
    private int blueColor;
    private int greenColor;
    private int yellowColor;
    private int defaultColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация цветов
        redColor = ContextCompat.getColor(this, android.R.color.holo_red_light);
        blueColor = ContextCompat.getColor(this, android.R.color.holo_blue_light);
        greenColor = ContextCompat.getColor(this, android.R.color.holo_green_light);
        yellowColor = ContextCompat.getColor(this, android.R.color.holo_orange_light);
        defaultColor = ContextCompat.getColor(this, android.R.color.darker_gray);
        // Инициализация ViewModel
        viewModel = new MemoryGameViewModel();
        // Инициализация View
        initViews();
        // Установка обработчиков нажатий
        setupClickListeners();
    }

    // Нахождение View
    private void initViews() {
        status = findViewById(R.id.statusText);
        startBtn = findViewById(R.id.startButton);
        redBtn = findViewById(R.id.redButton);
        blueBtn = findViewById(R.id.blueButton);
        greenBtn = findViewById(R.id.greenButton);
        yellowBtn = findViewById(R.id.yellowButton);

        // Установим серый цвет по умолчанию
        setButtonsDefaultColor();
    }

    // Установка обработчиков нажатий
    private void setupClickListeners() {
        startBtn.setOnClickListener(v -> {
            // Очищаем список ввода
            userInput.clear();
            // Запускаем новую игру с уровнем 4
            viewModel.startGame(4);
            // Показываем последовательность
            showSequence(viewModel.getSequence());
        });
        // Установка обработчиков нажатий
        redBtn.setOnClickListener(v -> handleButtonClick(0, redBtn));
        blueBtn.setOnClickListener(v -> handleButtonClick(1, blueBtn));
        greenBtn.setOnClickListener(v -> handleButtonClick(2, greenBtn));
        yellowBtn.setOnClickListener(v -> handleButtonClick(3, yellowBtn));
    }

    // Обработка нажатий цветной кнопки и передача информации ViewModel
    private void handleButtonClick(int color, Button button) {
        animateButton(button);
        onUserInput(color);
    }

    // Анимация нажатия
    private void animateButton(Button button) {
        // Анимация нажатия
        button.animate()
                .scaleX(0.8f)
                .scaleY(0.8f)
                .setDuration(100)
                .withEndAction(() -> button.animate()
                        // Анимация отпускания
                        .scaleX(1f)
                        .scaleY(1f)
                        .setDuration(100));
    }

    // Показ последовательности
    public void showSequence(List<Integer> sequence) {
        status.setText("Смотрите и запоминайте...");
        // Отключаем кнопки
        disableButtons();

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            for (int i = 0; i < sequence.size(); i++) {
                int finalI = i;
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    highlightButton(sequence.get(finalI));
                }, i * 1000L); // Интервал 1 секунда между кнопками
            }

            // После показа всей последовательности включаем кнопки
            new Handler(Looper.getMainLooper()).postDelayed(() -> {
                enableButtons();
                status.setText("Ваш ход! Повторите последовательность");
            }, sequence.size() * 1000L);
        }, 500);
    }

    // Подсвечиваем кнопку и востанавливаем цвет по умолчанию
    private void highlightButton(int color) {
        Button button = getButtonByColor(color);
        int highlightColor = getHighlightColor(color);

        // Подсвечиваем кнопку на 500 мс
        button.setBackgroundColor(highlightColor);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            button.setBackgroundColor(getDefaultColor(color));
        }, 500);
    }

    // Возвращает цвет для подсветки
    private int getHighlightColor(int color) {
        switch (color) {
            case 0:
                return redColor;
            case 1:
                return blueColor;
            case 2:
                return greenColor;
            case 3:
                return yellowColor;
            default:
                return defaultColor;
        }
    }

    // Возвращает цвет по умолчанию
    private int getDefaultColor(int color) {
        switch (color) {
            case 0:
                return ContextCompat.getColor(this, android.R.color.holo_red_dark);
            case 1:
                return ContextCompat.getColor(this, android.R.color.holo_blue_dark);
            case 2:
                return ContextCompat.getColor(this, android.R.color.holo_green_dark);
            case 3:
                return ContextCompat.getColor(this, android.R.color.holo_orange_dark);
            default:
                return defaultColor;
        }
    }

    // Сопоставляет числовой код цвета с конкретной кнопкой.
    private Button getButtonByColor(int color) {
        switch (color) {
            case 0:
                return redBtn;
            case 1:
                return blueBtn;
            case 2:
                return greenBtn;
            case 3:
                return yellowBtn;
            default:
                return redBtn;
        }
    }

    // Отключаем кнопки
    private void disableButtons() {
        redBtn.setEnabled(false);
        blueBtn.setEnabled(false);
        greenBtn.setEnabled(false);
        yellowBtn.setEnabled(false);
        startBtn.setEnabled(false);
    }

    // Включаем кнопки
    private void enableButtons() {
        redBtn.setEnabled(true);
        blueBtn.setEnabled(true);
        greenBtn.setEnabled(true);
        yellowBtn.setEnabled(true);
        startBtn.setEnabled(true);
    }

    // Установка цветов по умолчанию
    private void setButtonsDefaultColor() {
        redBtn.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        blueBtn.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_blue_dark));
        greenBtn.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_green_dark));
        yellowBtn.setBackgroundColor(ContextCompat.getColor(this, android.R.color.holo_orange_dark));
    }

    // Обработка ввода пользователя
    public void onUserInput(int color) {
        userInput.add(color);
        if (userInput.size() == viewModel.getSequence().size()) {
            boolean correct = viewModel.validateUserInput(userInput);
            if (correct) {
                status.setText("Правильно!");
                viewModel.startGame(viewModel.getSequence().size() + 1);
            } else {
                status.setText("Ошибка!");
            }
            userInput.clear();
        }
    }
}