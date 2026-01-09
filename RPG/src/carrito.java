import java.util.InputMismatchException;
import java.util.Scanner;
public class carrito {
    public static int comprobarInt(){
        Scanner sc = new Scanner(System.in);
        int respuesta = 0;
        boolean comprobar=true;
        while (comprobar) {
            try {
                respuesta = sc.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Error, los datos no son numericos, vuelve a intentarlo");
                sc.next();
            } catch (Exception e) {
                System.out.println(e);
            }
            comprobar=false;
        }
        return respuesta;
    }
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String [] productos = {"producto invalido","Pan","Huevos","Aceite","Harina","Leche"};
        int [] precio = {0,1,6,5,3,4};
        int [] carrito = new int[6];
        int respuesta;

        System.out.println("PRODUCTOS");
        for (int i = 1; i < 6; i++) {
            String verproductos = productos[i];
            System.out.println(i+")"+verproductos+" "+precio[i]+"€");
        }
        //BUCLE PARA AÑADIR PRODUCTOS
        boolean añadirCarrito=true;
        while (añadirCarrito) {
            System.out.println("¿Qué producto desea añadir al carrito?");
            respuesta=comprobarInt();
            //AÑADIR PRODUCTO AL CARRITO
            switch (respuesta) {
                case 1:
                    carrito[1] += 1;
                    break;
                case 2:
                    carrito[2] += 1;
                    break;
                case 3:
                    carrito[3] += 1;
                    break;
                case 4:
                    carrito[4] += 1;
                    break;
                case 5:
                    carrito[5] += 1;
                    break;
            }
            if (respuesta!=0){
                System.out.println("Usted ha añadido 1 " + productos[respuesta] +" "+ " a su carrito");
                System.out.println("¿Desea añadir un producto más? (SI/NO)");
                String siNo=sc.next();
                int sumatotal=0;
                if (siNo.equalsIgnoreCase("SI")){
                    añadirCarrito=true;
                } else {
                    añadirCarrito=false;
                    System.out.println("TICKET");
                    for (int i = 1; i < 6; i++) {
                        int sumaprecio=0;
                        if (carrito[i]>0){
                            System.out.println(productos[i]+" x"+carrito[i]+" "+precio[i]+"€");
                            //OPERACION DE SUMA DEL PRECIO
                            sumaprecio = precio[i]*carrito[i];
                            sumatotal+=sumaprecio;

                        }
                    }
                    System.out.println("TOTAL: "+sumatotal+"€");
                    System.out.println("GRACIAS POR SU COMPRA!");
                }
            } else {
                añadirCarrito=true;
            }

        }

    }
}
