package ru.msav.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle; // Сохранение информации состояния
import java.text.NumberFormat; // Для форматирования денежных сумм

public class TipCalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
    }
}
