package com.mycompany.calculadora;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * Calculadora CalculadoraGui
 */
public class CalculadoraGui extends Application {

    private TextField displayField; // Campo de texto para mostrar los números y resultados
    private String currentInput = ""; // Almacena la entrada actual
    private double num1 = 0; // Almacena el primer número de una operación
    private String operator = ""; // Almacena el operador seleccionado

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculadora");

        // Crear elementos de la GUI
        displayField = new TextField();
        displayField.setEditable(false);
        
        Button[] numberButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            final int digit = i;
            numberButtons[i] = new Button(String.valueOf(i));
            numberButtons[i].setOnAction(e -> appendToDisplay(String.valueOf(digit)));
        }
        
        Button sumaButton = new Button("+");
        sumaButton.setOnAction(e -> setOperator("+"));
        
        Button restaButton = new Button("-");
        restaButton.setOnAction(e -> setOperator("-"));
        
        Button multiplicacionButton = new Button("*");
        multiplicacionButton.setOnAction(e -> setOperator("*"));
        
        Button divisionButton = new Button("/");
        divisionButton.setOnAction(e -> setOperator("/"));
        
        Button resultadoButton = new Button("=");
        resultadoButton.setOnAction(e -> calculate());
        
        Button limpiarButton = new Button("C");
        limpiarButton.setOnAction(e -> clear());
        
        Button sinButton = new Button("sin");
        sinButton.setOnAction(e -> seno());
        
        Button cosButton = new Button("cos");
        cosButton.setOnAction(e -> coseno());
        
        Button tanButton = new Button("tan");
        tanButton.setOnAction(e -> tangente());
        
        Button raizButton = new Button("√");
        raizButton.setOnAction(e -> setOperator("√"));
        
        Button potenciaButton = new Button("^");
        potenciaButton.setOnAction(e -> setOperator("^"));
        
        Button porcentajeButton = new Button("%");
        porcentajeButton.setOnAction(e -> setOperator("%"));
        
        Button ivaButton = new Button("IVA");
        ivaButton.setOnAction(e -> setOperator("IVA"));

        // Diseño de la calculadora utilizando un GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        
        gridPane.add(displayField, 0, 0, 5, 1);
        
        for (int i = 1; i <= 9; i++) {
            gridPane.add(numberButtons[i], (i - 1) % 3, (i - 1) / 3 + 1);
        }
        
        gridPane.add(numberButtons[0], 1, 4);
        gridPane.add(sumaButton, 3, 1);
        gridPane.add(restaButton, 3, 2);
        gridPane.add(multiplicacionButton, 3, 3);
        gridPane.add(divisionButton, 3, 4);
        gridPane.add(resultadoButton, 2, 4);
        gridPane.add(limpiarButton, 0, 4);
        gridPane.add(sinButton, 4, 1);
        gridPane.add(cosButton, 4, 2);
        gridPane.add(tanButton, 4, 3);
        gridPane.add(ivaButton, 4, 4);
        gridPane.add(raizButton, 1, 5);
        gridPane.add(potenciaButton, 2, 5);
        gridPane.add(porcentajeButton, 0, 5);

        // Crear la escena
        Scene scene = new Scene(gridPane, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void appendToDisplay(String text) {
        currentInput += text;
        displayField.setText(currentInput);
    }

    private void setOperator(String op) {
        num1 = Double.parseDouble(currentInput);
        operator = op;
        currentInput = "";
    }

    private void calculate() {
        double result = 0;
        double num2 = Double.parseDouble(currentInput);

        switch (operator) {
            case "+":
                result = suma(num1, num2);
                break;
            case "-":
                result = resta(num1, num2);
                break;
            case "*":
                result = multiplicacion(num1, num2);
                break;
            case "/":
                if (num2 != 0) {
                    result = division(num1, num2);
                } else {
                    displayField.setText("Error: División por cero");
                    return;
                }
                break;
            case "^":
                result = potenciaEnesima(num1, num2);
                break;
            case "√":
                result = raizEnesima(num1, num2);
                break;    
            case "%":
                result = porcentaje(num1, num2);
                break;    
            case "IVA":
                result = calcularIVA(num1, num2);
                break;    
        }

        displayField.setText(String.valueOf(result));
        currentInput = "";
    }

    private static double suma(double num1, double num2) {
        return num1 + num2;
    }
    
    private static double resta(double num1, double num2) {
        return num1 - num2;
    }
    
    private static double multiplicacion(double num1, double num2) {
        return num1 * num2;
    }
    
    private static double division(double dividendo, double divisor) {
        if (divisor == 0) {
            return Double.NaN;
        }
        return dividendo / divisor;
    }
    
    private void clear() {
        currentInput = "";
        num1 = 0;
        operator = "";
        displayField.clear();
    }
    
    private void seno() {
        double angulo = Double.parseDouble(currentInput);
        double result = Math.sin(Math.toRadians(angulo));
        displayField.setText(String.valueOf(result));
        currentInput = "";
    }
    
    private void coseno() {
        double angulo = Double.parseDouble(currentInput);
        double result = Math.cos(Math.toRadians(angulo));
        displayField.setText(String.valueOf(result));
        currentInput = "";
    }
    
    private void tangente() {
        double angulo = Double.parseDouble(currentInput);
        double result = Math.tan(Math.toRadians(angulo));
        
        // Manejar la tangente indeterminada
        if (Math.abs(Math.cos(Math.toRadians(angulo))) < 1e-6) {
            displayField.setText("Error: Tangente indeterminada");
            currentInput = "";
            return;
        }
        
        displayField.setText(String.valueOf(result));
        currentInput = "";
    }
    
    private double raizEnesima(double base, double indice) {
        if (base < 0 && indice % 2 == 0) {
            displayField.setText("Error: Número negativo con un índice par.");
        }
        return Math.pow(base, 1.0 / indice);
    }
    
    private static double potenciaEnesima(double base, double exponente) {
        return Math.pow(base, exponente);
    }
    
    private double porcentaje(double monto,double porcentaje) {
        return monto * (porcentaje / 100);
    }
    
    private double calcularIVA(double monto,double porcentajeIVA) {
        return monto * (1 + porcentajeIVA / 100);
    }

}