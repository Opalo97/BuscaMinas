package es.lvo;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        char[] tablero, tableroOculto;
        int extensionTablero, casillaDestapada, contadorDeNumeros = 0;
        Scanner entrada = new Scanner(System.in);

        System.out.println("~~BIENVENIDX AL BUSCAMINAS~~");
        System.out.println("=====================================================");
        extensionTablero = pedirNumero(entrada, 5, 15,"¿Cuánta extension quieres que tenga el tablero? [De 5 a 15]: ");
        System.out.println();

        tablero = generarTablero(extensionTablero);
        tableroOculto = generarTableroOculto(extensionTablero);

        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i] == '1' || tablero[i] == '0' || tablero[i] == '2') {
                contadorDeNumeros++;
            }
        }

        System.out.println(contadorDeNumeros);
        mostrarTablero(tablero);
        mostrarTablero(tableroOculto);

        do {
            casillaDestapada = pedirNumero(entrada,1, extensionTablero, "Elige que casilla destapar: ");
            System.out.println();
            tableroOculto[casillaDestapada - 1] = tablero[casillaDestapada - 1];

            mostrarTablero(tablero);
            mostrarTablero(tableroOculto);

            if (tablero[casillaDestapada - 1] != '*') {
                contadorDeNumeros--;
            }

        } while (tablero[casillaDestapada - 1] != '*' && contadorDeNumeros != 0 );


    }

    private static char[] generarTableroOculto(int tam) {
        char[] tableroOculto = new char[tam];
        for (int i = 0; i < tableroOculto.length; i++) {
            tableroOculto[i] = '·';
        }
        return tableroOculto;
    }
    private static char[] generarTablero(int tam){
        char[] tablero = new char[tam];
        int n, bombas;

        for (int i = 0; i < tablero.length; i++) {
            n = (int) (Math.random() * 2);
            if (n == 0) {
                tablero[i] = '0';
            } else {
                tablero[i] = '*';
            }
        }

        for (int i = 0; i < tablero.length; i++) {
            if (i == 0) {
                if (tablero[i] == '0' && tablero[i + 1] == '*') {
                    tablero[i] = '1';
                }
            } else if (i == tablero.length  - 1) {
                if (tablero[i] == '0' && tablero[i - 1] == '*') {
                    tablero[i] = '1';
                }
            } else if (tablero[i] == '0') {
                bombas = 0;
                if (tablero[i - 1] == '*') {
                    bombas++;
                }
                if (tablero[i + 1] == '*'){
                    bombas++;
                }
                tablero[i] = String.valueOf(bombas).charAt(0);
            }
        }

        return tablero;
    }
    private static void mostrarTablero(char[] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            System.out.print("  " + (i + 1) + " ");
        }
        System.out.println(" ");

        for (int i = 0; i < tablero.length; i++) {
            System.out.print("+---");
        }
        System.out.println("+");

        for (int i = 0; i < tablero.length; i++) {
            System.out.print("|");
            System.out.print(" " + tablero[i] + " ");
        }
        System.out.print("|");
        System.out.println();

        for (int i = 0; i < tablero.length; i++) {
            System.out.print("+---");
        }
        System.out.println("+");

    }
    private static int pedirNumero(Scanner entrada, int min, int max, String mensaje) {
        int numero;
        do {
            System.out.print(mensaje);
            numero = entrada.nextInt();
        } while (numero < min || numero > max);

        entrada.nextLine();

        return numero;
    }

}
