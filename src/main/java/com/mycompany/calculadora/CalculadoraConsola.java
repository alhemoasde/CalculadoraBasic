
package com.mycompany.calculadora;

import java.util.Scanner;

/**
 * Calculadora Consola
 */
public class CalculadoraConsola {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Calculadora - Versión de Consola");
        
        while (true) {
            System.out.println("Seleccione una operación:");
            System.out.println("1. Suma");
            System.out.println("2. Resta");
            System.out.println("3. Multiplicación");
            System.out.println("4. División");
            System.out.println("5. Seno");
            System.out.println("6. Coseno");
            System.out.println("7. Tangente");
            System.out.println("8. Raíz Enésima");
            System.out.println("9. Potencia Enésima");
            System.out.println("10. Calcular IVA");
            System.out.println("0. Salir");
            
            int opcion = scanner.nextInt();
            
            if (opcion == 0) {
                System.out.println("Saliendo de la calculadora...");
                break;
            }
            
            double resultado = 0.0;
            
            switch (opcion) {
                case 1:
                    resultado = suma();
                    break;
                case 2:
                    resultado = resta();
                    break;
                case 3:
                    resultado = multiplicacion();
                    break;
                case 4:
                    resultado = division();
                    break;
                case 5:
                    resultado = seno();
                    break;
                case 6:
                    resultado = coseno();
                    break;
                case 7:
                    resultado = tangente();
                    break;
                case 8:
                    resultado = raizEnesima();
                    break;
                case 9:
                    resultado = potenciaEnesima();
                    break;
                case 10:
                    resultado = calcularIVA();
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
            System.out.println("|:::::::::::::::::::::::::::::::::::::|");
            System.out.println("|:: El resultado es: " + resultado);
            System.out.println("|:::::::::::::::::::::::::::::::::::::|");
            
        }
        
        scanner.close();
    }
    
    // Funciones para cada operación
    private static double suma() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el primer número: ");
        double num1 = scanner.nextDouble();
        System.out.println("Ingrese el segundo número: ");
        double num2 = scanner.nextDouble();
        return num1 + num2;
    }
    
    private static double resta() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el primer número: ");
        double num1 = scanner.nextDouble();
        System.out.println("Ingrese el segundo número: ");
        double num2 = scanner.nextDouble();
        return num1 - num2;
    }
    
    private static double multiplicacion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el primer número: ");
        double num1 = scanner.nextDouble();
        System.out.println("Ingrese el segundo número: ");
        double num2 = scanner.nextDouble();
        return num1 * num2;
    }
    
    private static double division() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el dividendo: ");
        double dividendo = scanner.nextDouble();
        System.out.println("Ingrese el divisor: ");
        double divisor = scanner.nextDouble();
        
        if (divisor == 0) {
            System.out.println("Error: División por cero.");
            return Double.NaN;
        }
        
        return dividendo / divisor;
    }
    
    private static double seno() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ángulo: ");
        double angulo = scanner.nextDouble();
        return Math.sin(Math.toRadians(angulo));
    }
    
    private static double coseno() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ángulo: ");
        double angulo = scanner.nextDouble();
        return Math.cos(Math.toRadians(angulo));
    }
    
    private static double tangente() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el ángulo: ");
        double angulo = scanner.nextDouble();
        
        /**
         * Math.abs(...) calcula el valor absoluto del resultado anterior para asegurarse de manejar tanto valores positivos como negativos.
         * Math.cos(angulo) calcula el valor del coseno del ángulo proporcionado.
         * (< 1e-6) verifica si el valor absoluto del coseno es menor que 1e-6 (10 elevado a la potencia -6) o 0.000001.
         */
        if (Math.abs(Math.cos(Math.toRadians(angulo))) < 1e-6) {
            System.out.println("Error: Tangente indeterminada para este ángulo.");
            return Double.NaN;
        }
        
        return Math.tan(Math.toRadians(angulo));
    }
    
    private static double raizEnesima() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el número base: ");
        double base = scanner.nextDouble();
        System.out.println("Ingrese el índice de la raíz: ");
        double indice = scanner.nextDouble();
        
        /**
         * (base < 0): Esta parte verifica si el número base (el número del que se desea calcular la raíz) es negativo.
         * (indice % 2 == 0): Esta parte verifica si el índice (el número de raíz) es un número par. 
         * El operador % calcula el residuo de la división del índice por 2. Si el residuo es 0, significa que el índice es un número par.
         */
        if (base < 0 && indice % 2 == 0) {
            System.out.println("Error: No se puede calcular la raíz enésima de un número negativo con un índice par.");
            return Double.NaN;
        }
        
        return Math.pow(base, 1.0 / indice);
    }
    
    private static double potenciaEnesima() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el número base: ");
        double base = scanner.nextDouble();
        System.out.println("Ingrese el exponente: ");
        double exponente = scanner.nextDouble();
        return Math.pow(base, exponente);
    }
    
    private static double calcularIVA() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el monto: ");
        double monto = scanner.nextDouble();
        System.out.println("Ingrese el porcentaje de IVA: ");
        double porcentajeIVA = scanner.nextDouble();
        System.out.println("Valor IVA: "+monto * (porcentajeIVA / 100));
        return monto * (1 + porcentajeIVA / 100);
    }
}

