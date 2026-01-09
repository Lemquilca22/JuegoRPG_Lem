import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class carrito2 {
    public static int comprobarInt() {
        Scanner sc = new Scanner(System.in);
        int respuesta = 0;
        boolean comprobar = true;
        while (comprobar) {
            try {
                respuesta = sc.nextInt();
                comprobar = false; // Mover aquí para que salga solo si es válido
            } catch (InputMismatchException e) {
                System.out.println("Error, los datos no son numericos, vuelve a intentarlo");
                sc.next();
            }
        }
        return respuesta;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] productos = {"producto invalido", "Pan", "Huevos", "Aceite", "Harina", "Leche"};
        int[] precio = {0, 1, 6, 5, 3, 4};
        int[] carrito = new int[6];
        int respuesta;

        System.out.println("PRODUCTOS");
        for (int i = 1; i < 6; i++) {
            System.out.println(i + ")" + productos[i] + " " + precio[i] + "€");
        }

        boolean añadirCarrito = true;
        while (añadirCarrito) {
            System.out.println("¿Qué producto desea añadir al carrito?");
            respuesta = comprobarInt();

            if (respuesta >= 1 && respuesta <= 5) {
                carrito[respuesta] += 1;
                System.out.println("Usted ha añadido 1 " + productos[respuesta] + " a su carrito");
                System.out.println("¿Desea añadir un producto más? (SI/NO)");
                String siNo = sc.next();

                if (!siNo.equalsIgnoreCase("SI")) {
                    añadirCarrito = false;

                    // --- INICIO DE ESCRITURA EN ARCHIVO ---
                    try (PrintWriter escritor = new PrintWriter(new FileWriter("ticket.txt"))) {
                        int sumatotal = 0;
                        escritor.println("---------- TICKET DE COMPRA ----------");

                        for (int i = 1; i < 6; i++) {
                            if (carrito[i] > 0) {
                                int subtotal = precio[i] * carrito[i];
                                // Escribe en el archivo
                                escritor.println(productos[i] + " x" + carrito[i] + " - " + subtotal + "€");
                                sumatotal += subtotal;
                            }
                        }

                        escritor.println("--------------------------------------");
                        escritor.println("TOTAL: " + sumatotal + "€");
                        escritor.println("GRACIAS POR SU COMPRA!");

                        System.out.println("El ticket se ha guardado en 'ticket.txt'");

                    } catch (IOException e) {
                        System.out.println("Error al generar el ticket: " + e.getMessage());
                    }
                    // --- FIN DE ESCRITURA EN ARCHIVO ---
                }
            } else {
                System.out.println("Opción no válida.");
            }
        }
    }
}
