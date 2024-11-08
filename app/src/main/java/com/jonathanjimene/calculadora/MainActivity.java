package com.jonathanjimene.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;
    private String currentInput = "";
    private String operacion = "";
    private double valor1 = 0;
    private double valor2 = 0;
    private String operador = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewResult = findViewById(R.id.Texto);
        Button buttonAC = findViewById(R.id.buttonAC);
        Button buttonC = findViewById(R.id.buttonC);
        Button buttonDivision = findViewById(R.id.buttonDivision);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button buttonMultiplicar = findViewById(R.id.buttonMultiplicar);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button buttonRestar = findViewById(R.id.buttonRestar);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button buttonSumar = findViewById(R.id.buttonSumar);
        Button button0 = findViewById(R.id.button0);
        Button buttonPunto = findViewById(R.id.buttonPunto);
        Button buttonCalcular = findViewById(R.id.buttonCalcular);

        View.OnClickListener numberListener = new View.OnClickListener() { // <-- Este código está bien, pero necesitas asegurarte de que esté asignado a todos los botones correctamente.
            @Override
            public void onClick(View v) {
                String number = ((Button) v).getText().toString(); // Obtiene el texto del botón.
                currentInput += number; // Agrega el número a la entrada actual.
                textViewResult.setText(currentInput); // Muestra la entrada en la pantalla.
            }
        };

        button1.setOnClickListener(numberListener);
        button2.setOnClickListener(numberListener);
        button3.setOnClickListener(numberListener);
        button4.setOnClickListener(numberListener);
        button5.setOnClickListener(numberListener);
        button6.setOnClickListener(numberListener);
        button7.setOnClickListener(numberListener);
        button8.setOnClickListener(numberListener);
        button9.setOnClickListener(numberListener);
        button0.setOnClickListener(numberListener);

        // Botón punto (.) - Agregar un punto si no se ha agregado antes
        buttonPunto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.contains(".")) {
                    currentInput += ".";
                    textViewResult.setText(currentInput);
                }
            }
        });

        // Botones de operación (+, -, *, /)
        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String operation = ((Button) v).getText().toString();
                if (!currentInput.isEmpty()) {
                    valor1 = Double.parseDouble(currentInput);  // Guardamos el primer valor
                    operador = operation;  // Guardamos el operador
                    currentInput = "";  // Limpiamos la entrada para el siguiente número
                    textViewResult.setText("");  // Limpiamos la pantalla
                }
            }
        };

        buttonSumar.setOnClickListener(operatorListener);
        buttonRestar.setOnClickListener(operatorListener);
        buttonMultiplicar.setOnClickListener(operatorListener);
        buttonDivision.setOnClickListener(operatorListener);

        // Botón de igual (=) para calcular el resultado
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentInput.isEmpty() && !operador.isEmpty()) {
                    valor2 = Double.parseDouble(currentInput);  // Guardamos el segundo valor

                    double result = 0;
                    switch (operador) {
                        case "+":
                            result = valor1 + valor2;
                            break;
                        case "-":
                            result = valor1 - valor2;
                            break;
                        case "X":
                            result = valor1 * valor2;
                            break;
                        case "/":
                            if (valor2 != 0) {
                                result = valor1 / valor2;
                            } else {
                                textViewResult.setText("Error");
                                return;
                            }
                            break;
                    }

                    // Mostramos el resultado en la pantalla
                    textViewResult.setText(String.valueOf(result));
                    currentInput = String.valueOf(result);  // Guardamos el resultado para futuras operaciones
                    operador = "";  // Limpiamos el operador
                }
            }
        });

        // Botón "AC" - Limpiar todo
        buttonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valor1 = 0;
                valor2 = 0;
                operador = "";
                currentInput = "";
                textViewResult.setText("0");
            }
        });

        // Botón "C" - Limpiar la entrada
        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentInput = "";
                textViewResult.setText("0");
            }
        });
        }
    }