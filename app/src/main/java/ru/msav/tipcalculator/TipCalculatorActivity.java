package ru.msav.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class TipCalculatorActivity extends AppCompatActivity {

    // Для форматирования денежных сумм и процентов
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private double billAmount = 0.0; // Сумма счёта, введённая пользователем
    private double customPercent = 0.18; // Пользовательский процент чаевых
    private TextView amountDisplayTextView; // Для отформатированной суммы счёта
    private TextView percentCustomTextView; // Для пользовательского процента чаевых
    private TextView tip15TextView; // Для вывода 15% чаевых
    private TextView total15TextView; // Для вывода суммы с 15% чаевых
    private TextView tipCustomTextView; // Для вывода пользовательских чаевых
    private TextView totalCustomTextView; // Для вывода суммы с пользовательскими чаевыми

    /**
     * Обработка изменения суммы счёта
     */
    private TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Преобразование amountEditText в тип double
            try {
                billAmount = Double.parseDouble(charSequence.toString());
            } catch (NumberFormatException e) {
                billAmount = 0.0;
            }

            amountDisplayTextView.setText( currencyFormat.format(billAmount) );
            updateStandard();
            updateCustom();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    /**
     * Обработка процента пользовательских чаевых
     */
    private SeekBar.OnSeekBarChangeListener customTipSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            customPercent = progress / 100.0;
            updateCustom();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        /**
         * Получение ссылок на компоненты TextView, с которыми TipCalculatorActivity
         * взаимодействует на программном уровне.
         */
        amountDisplayTextView = (TextView) findViewById(R.id.amountDisplayTextView);
        percentCustomTextView = (TextView) findViewById(R.id.percentCustomTextView);
        tip15TextView = (TextView) findViewById(R.id.tip15TextView);
        total15TextView = (TextView) findViewById(R.id.total15TextView);
        tipCustomTextView = (TextView) findViewById(R.id.tipCustomTextView);
        totalCustomTextView = (TextView) findViewById(R.id.totalCustomTextView);

        /**
         * Обновление GUI по данным billAmount и customPercent
         */
        amountDisplayTextView.setText( currencyFormat.format(billAmount) );
        updateStandard();
        updateCustom();

        /**
         * Назначение TextWatcher для amountEditText
         */
        EditText amountEditText =
                (EditText) findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

        SeekBar customTipSeekBar = (SeekBar) findViewById(R.id.customTipSeekBar);
        customTipSeekBar.setOnSeekBarChangeListener(customTipSeekBarListener);
    }

    /**
     * Обновление компонентов TextView для 15% чаевых
     */
    private void updateStandard() {
        // Вычисление чаевых и общей суммы
        double percentTip = billAmount * 0.15;
        double totalAmount = billAmount + percentTip;

        // Вывод чаевых и суммы
        tip15TextView.setText( currencyFormat.format(percentTip) );
        total15TextView.setText( currencyFormat.format(totalAmount) );
    }

    /**
     * Обновление компонентов TextView для пользовательских чаевых
     */
    private void updateCustom() {
        // Вывод пользовательского процента
        percentCustomTextView.setText( percentFormat.format(customPercent) );

        // Вычисление чаевых и общей суммы
        double percentTip = billAmount * customPercent;
        double totalAmount = billAmount + percentTip;

        // Вывод чаевых и суммы
        tipCustomTextView.setText( currencyFormat.format(percentTip) );
        totalCustomTextView.setText( currencyFormat.format(totalAmount) );
    }
}
