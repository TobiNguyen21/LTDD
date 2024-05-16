package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCalculation;
    private StringBuilder currentInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCalculation = findViewById(R.id.editTextCalculation);
        currentInput = new StringBuilder();

        // Set up number buttons
        int[] numberButtonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
                R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
                R.id.btn8, R.id.btn9
        };

        for (int i = 0; i < numberButtonIds.length; i++) {
            final int number = i;
            findViewById(numberButtonIds[i]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appendToInput(String.valueOf(number));
                }
            });
        }

        // Set up operator buttons
        int[] operatorButtonIds = {
                R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply,
                R.id.btnDivide, R.id.btnPercentage, R.id.btnDecimal
        };

        String[] operators = {"+", "-", "x", "/", "%", "."};

        for (int i = 0; i < operatorButtonIds.length; i++) {
            final String operator = operators[i];
            findViewById(operatorButtonIds[i]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appendToInput(operator);
                }
            });
        }

        // Set up Calculate button
        findViewById(R.id.btnCalculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult();
            }
        });

        // Set up Clear button
        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentInput.length() > 0) {
                    currentInput.deleteCharAt(currentInput.length() - 1);
                    editTextCalculation.setText(currentInput.toString());
                }
            }
        });

        // Set up All Clear button
        findViewById(R.id.btnAllClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput.setLength(0);
                editTextCalculation.setText("");
            }
        });
    }

    private void appendToInput(String str) {
        currentInput.append(str);
        editTextCalculation.setText(currentInput.toString());
    }
    private void calculateResult() {
        if (currentInput.length() == 0) {
            return;
        }

        // Chuyển đổi chuỗi thành một biểu thức toán học hợp lệ
        String expression = currentInput.toString().replaceAll("%", "/100");

        try {
            // Thực hiện tính toán biểu thức
            double result = evaluateExpression(expression);

            // Hiển thị kết quả
            editTextCalculation.setText(String.valueOf(result));

            // Reset chuỗi đầu vào
            currentInput.setLength(0);
            currentInput.append(String.valueOf(result));
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có lỗi xảy ra trong quá trình tính toán
            editTextCalculation.setText("Error");
        }
    }

    private double evaluateExpression(String expression) {
        // Đây chỉ là một ví dụ đơn giản, bạn có thể thay thế bằng phương pháp tính toán phức tạp hơn nếu cần
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            boolean isDigit() {
                return Character.isDigit(ch);
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if (eat('+')) {
                        x += parseTerm();
                    } else if (eat('-')) {
                        x -= parseTerm();
                    } else {
                        return x;
                    }
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if (eat('x')) {
                        x *= parseFactor();
                    } else if (eat('/')) {
                        x /= parseFactor();
                    } else {
                        return x;
                    }
                }
            }

            double parseFactor() {
                if (eat('+')) {
                    return parseFactor();
                }
                if (eat('-')) {
                    return -parseFactor();
                }
                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if (isDigit() || ch == '.') {
                    while (isDigit() || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) {
                    x = Math.pow(x, parseFactor());
                }

                return x;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }
        }.parse();
    }
}
