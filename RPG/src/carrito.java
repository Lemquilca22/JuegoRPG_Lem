import java.util.Scanner;
public class carrito {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String [] productos = {"Pan","Huevos","Aceite","Harina","Leche"};
        int [] precio = {1,6,5,3,4};
        int [] carrito = new int[5];
        int respuesta;

        System.out.println("PRODUCTOS");
        for (int i = 0; i < 5; i++) {
            String verproductos = productos[i];
            System.out.println(i+")"+verproductos+" "+precio[i]+"€");
        }
        //BUCLE PARA AÑADIR PRODUCTOS
        boolean añadirCarrito=true;
        while (añadirCarrito) {
            System.out.println("¿Qué producto desea añadir al carrito?");
            respuesta = sc.nextInt();
            //AÑADIR PRODUCTO AL CARRITO
            switch (respuesta) {
                case 0:
                    carrito[0] += 1;
                    break;
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
            }
            System.out.println("Usted ha añadido 1 " + productos[respuesta] +" "+ " a su carrito");
            System.out.println("¿Desea añadir un producto más? (SI/NO)");
            String siNo=sc.next();
            int sumatotal=0;
            if (siNo.equalsIgnoreCase("SI")){
                añadirCarrito=true;
            } else {
                añadirCarrito=false;
                System.out.println("TICKET");
                for (int i = 0; i < 5; i++) {
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
        }

    }
}
