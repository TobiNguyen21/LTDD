package com.example.giaithua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class CalculateFactorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_factorial);

        int number = getIntent().getIntExtra("number", 1);
        Button calculateButton = findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long factorial = calculateFactorial(number);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("factorial", factorial);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private long calculateFactorial(int number) {
        long result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }
        return result;
    }
}

