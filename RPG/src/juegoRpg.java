import java.util.Random;
import java.util.Scanner;

public class juegoRpg {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //==== Generador de numeros random ====
        Random generador= new Random();
        //==== Arrays predeterminados ====
        String [] enemigos = {"Ogro","Goblin","Mago oscuro","Slime"};
        String [] recompensas = {"Pocion de vida","Pocion magica","Pocion de daño","Pocion de daño extremo"};
        String [] inventario = new String[10];
        String [] interaccion = {"normal", "normal","normal","esquiva","critico"};
        boolean start = true;
        //==== Estadisticas ====
        int ps ;
        int pm ;
        double multiplicadordaño;
        double dañoFisicio;
        double dañoMagico;
        int oro;

        //Aquí irá un While!!!

        //Establecemos aqui para que las estats se restablezcan
        ps = 100;
        pm = 50;
        multiplicadordaño= 1.0;
        dañoFisicio= 15;
        dañoMagico=35;
        oro=0;
        //---- Creación de personaje ----
        boolean creacion = true;
        while (creacion) {
            System.out.println("CREACIÓN DEL PERSONAJE");
            System.out.println("¿Cuál quieres que sea el nombre de tu personaje?");
            String nomp1 = sc.nextLine();
            System.out.println("¿Qué clase quieres que tenga tu personaje?");
            System.out.println("CLASES:\n- Mago\n- Guerrero\n- Druida\n- Elfo");
            String clasp1 = sc.next();
            System.out.println("¿Entonces quieres permanecer como '" + nomp1 + "' de 'clase' " + clasp1 + "?");
            System.out.println("S/N");
            String modificar = sc.next();
            if (modificar.equalsIgnoreCase("n")) {
                creacion = true;
                System.out.println("Esta bien, vamos al inicio!");
                sc.nextLine();
            }
            if (modificar.equalsIgnoreCase("s")){
                break;
            }
        }
        boolean rpg=true;
        boolean combate=true;
        while (rpg){
            int numAleatorio = generador.nextInt(5);
            int vidaMAXEnemigo=60;
            int vidaAleatoriaEnemigo= generador.nextInt(vidaMAXEnemigo)+40;
            System.out.println("Mientras caminabas por las mazmorras un " +enemigos[numAleatorio]+ " se cruzó ante ti!");
            System.out.println("Stats enemigo"+"\nVida: "+vidaAleatoriaEnemigo);
            while (combate) {
                System.out.println("COMIENZA EL COMBATE!!");
                //COMBATE
                System.out.println("MENU DE OPCIONES");
                System.out.println("1) Atacar\n2) Ataque Magico\n3) Objetos\n4) Rendirse");
                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        //P1 - NOSOTROS
                        int ataquep1 = generador.nextInt(5);
                        String attackP1 = interaccion[ataquep1];
                        if (attackP1.equalsIgnoreCase("normal")) {
                            double dañoReal = dañoFisicio * multiplicadordaño;
                            vidaAleatoriaEnemigo -= dañoReal;
                            System.out.println("Haz hecho un ataque normal");
                        } else if (attackP1.equalsIgnoreCase("esquiva")) {
                            System.out.println("El enemigo ha esquivado tu ataque");
                        } else if (attackP1.equalsIgnoreCase("critico")) {
                            double dañocritico = (dañoFisicio * multiplicadordaño) * 1.5;
                            vidaAleatoriaEnemigo -= dañocritico;
                            System.out.println("Haz hecho un ataque critico");
                        }
                        System.out.println("Vida restante de " + enemigos[numAleatorio] + " " + vidaAleatoriaEnemigo);
                        //ENEMIGO ATACA
                        int dañoEnemigo = generador.nextInt(10) + 10;
                        int ataqueEnemigo = generador.nextInt(5);
                        String attackEnemy = interaccion[ataqueEnemigo];
                        if (attackEnemy.equalsIgnoreCase("normal")) {
                            ps -= dañoEnemigo;
                        } else if (attackEnemy.equalsIgnoreCase("esquiva")) {
                            System.out.println("Haz esquivado el ataque de tu enemigo");
                        } else if (attackP1.equalsIgnoreCase("critico")) {
                            double dañocriticoEnemigo = (dañoEnemigo) * 2;
                            ps -= dañocriticoEnemigo;
                            System.out.println("Haz recibido un ataque critico");
                        }
                        System.out.println("Vida restante de xx " + ps);
                        System.out.println();
                        break;
                    case 2:
                        break;
                }
            }


        }

        }
    }

