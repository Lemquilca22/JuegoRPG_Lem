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
        double dañoFisico;
        double dañoMagico;
        int oro;

        //Aquí irá un While!!!

        //Establecemos aqui para que las estats se restablezcan
        ps = 100;
        pm = 50;
        multiplicadordaño= 1.0;
        dañoFisico= 15;
        dañoMagico=35;
        oro=0;
        //Inventario al inicio del Juego para que siempre que iniciemos este sea el default
        int pocionVida=1;
        int pocionMagica=1;
        int pocionDaño=1;
        int pocionDañoextremo=1;
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

        while (rpg){
            boolean combate=true;
            int numAleatorio = generador.nextInt(5);
            int vidaMAXEnemigo=60;
            int vidaAleatoriaEnemigo= generador.nextInt(vidaMAXEnemigo)+40;
            System.out.println("Mientras caminabas por las mazmorras un " +enemigos[numAleatorio]+ " se cruzó ante ti!");
            System.out.println("Stats enemigo"+"\nVida: "+vidaAleatoriaEnemigo);
            System.out.println("COMIENZA EL COMBATE!!");
            int contadorInventario=0;
            while (combate) {
                //COMBATE
                int dañoEnemigo = generador.nextInt(10) + 10;
                int ataqueEnemigo = generador.nextInt(5);
                String attackEnemy = interaccion[ataqueEnemigo];
                //^^^EL CODIGO DE ARRIBA ES ATAQUE ALEATORIO DEL ENEMIGO^^^
                //RECOMPENSAS ALEATORIOS DESPUES DEL COMBATE
                int vidaRecuperada;
                int oroAleatorio;
                int objetoAleatorio;

                //CODIGO DE MENU SE MUESTRA SIEMPRE DESPUES DE CADA INTERACCION
                System.out.println("STATS: \nPS: "+ps+" PM: "+pm+" Multiplicador de daño: "+multiplicadordaño+" \nORO: "+oro);
                System.out.println("MENU DE OPCIONES");
                System.out.println("1) Atacar\n2) Ataque Magico\n3) Objetos\n4) Rendirse");
                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        //P1 - NOSOTROS
                        int ataquep1 = generador.nextInt(5);
                        String attackP1 = interaccion[ataquep1];
                        if (attackP1.equalsIgnoreCase("normal")) {
                            double dañoReal = dañoFisico * multiplicadordaño;
                            vidaAleatoriaEnemigo -= dañoReal;
                            System.out.println("Haz hecho un ataque normal");
                        } else if (attackP1.equalsIgnoreCase("esquiva")) {
                            System.out.println("El enemigo ha esquivado tu ataque");
                        } else if (attackP1.equalsIgnoreCase("critico")) {
                            double dañocritico = (dañoFisico * multiplicadordaño) * 1.5;
                            vidaAleatoriaEnemigo -= dañocritico;
                            System.out.println("Haz hecho un ataque critico");
                        }
                        System.out.println("Vida restante de " + enemigos[numAleatorio] + " " + vidaAleatoriaEnemigo);
                        if (vidaAleatoriaEnemigo<=0){
                            System.out.println("Has derrotado a "+enemigos[numAleatorio]);
                            oroAleatorio=generador.nextInt(5)+1;
                            oro+=oroAleatorio;
                            System.out.println("Has recibido "+oro+" de oro");
                            vidaRecuperada=generador.nextInt(25)+25;
                            ps+=vidaRecuperada;
                            objetoAleatorio=generador.nextInt(4);
                            inventario[contadorInventario]=recompensas[objetoAleatorio];
                            System.out.println("Has recibido una "+recompensas[objetoAleatorio]);
                            System.out.println("Has recuperado "+vidaRecuperada+" puntos de salud");
                            System.out.println("Vida actual: "+ps);
                            //HACER UN CONDICIONAL SI ES QUE ESTA EN VALOR NULL Y SI ESTA LLENO QUE PASE AL SIGUIENTE PARA GUARDAR LA INFORMACION
                            contadorInventario++;
                            combate=false;
                            break;
                        }
                        //ENEMIGO ATACA

                        if (attackEnemy.equalsIgnoreCase("normal")) {
                            ps -= dañoEnemigo;
                            System.out.println("Has recibido ataque normal");
                        } else if (attackEnemy.equalsIgnoreCase("esquiva")) {
                            System.out.println("Has esquivado el ataque de tu enemigo");
                        } else if (attackEnemy.equalsIgnoreCase("critico")) {
                            double dañocriticoEnemigo = (dañoEnemigo) * 2;
                            ps -= dañocriticoEnemigo;
                            System.out.println("Has recibido un ataque critico");
                        }
//                        System.out.println("Vida restante de xx " + ps);
                        System.out.println();
                        if (ps<=0){
                            System.out.println("Has muerto");
                            System.out.println("GAME OVER");
                            combate=false;
                            rpg=false;
                        }
                        break;
                    case 2: //ATAQUE MAGICO
                        //ATAQUE DEL P1
                        if (pm>=25){
                            pm-=25;
                            double dañomagicoreal=dañoMagico*multiplicadordaño;
                            vidaAleatoriaEnemigo-=dañomagicoreal;
                            System.out.println("Has realizado un ataque magico que ha restado "+dañomagicoreal+" ps de tu enemigo");
                            if (vidaAleatoriaEnemigo<=0){
                                System.out.println("Has derrotado a "+enemigos[numAleatorio]);
                                oroAleatorio=generador.nextInt(5)+1;
                                oro+=oroAleatorio;
                                System.out.println("Has recibido "+oro+" de oro");
                                vidaRecuperada=generador.nextInt(25)+25;
                                ps+=vidaRecuperada;
                                objetoAleatorio=generador.nextInt(4);
                                inventario[contadorInventario]=recompensas[objetoAleatorio];
                                System.out.println("Has recibido una "+recompensas[objetoAleatorio]);
                                System.out.println("Has recuperado "+vidaRecuperada+" puntos de salud");
                                System.out.println("Vida actual: "+ps);
                                contadorInventario++;
                                combate=false;
                                break;
                            }
                            System.out.println("Vida restante de " + enemigos[numAleatorio] + " " + vidaAleatoriaEnemigo);
                            //ATAQUE DEL ENEMIGO
                            if (attackEnemy.equalsIgnoreCase("normal")) {
                                ps -= dañoEnemigo;
                                System.out.println("Has recibido ataque normal");
                            } else if (attackEnemy.equalsIgnoreCase("esquiva")) {
                                System.out.println("Has esquivado el ataque de tu enemigo");
                            } else if (attackEnemy.equalsIgnoreCase("critico")) {
                                double dañocriticoEnemigo = (dañoEnemigo) * 2;
                                ps -= dañocriticoEnemigo;
                                System.out.println("Has recibido un ataque critico");
                            }

//                            System.out.println("Vida restante de xx " + ps);
                            System.out.println();
                            if (ps<=0){
                                System.out.println("Has muerto");
                                System.out.println("GAME OVER");
                                combate=false;
                                rpg=false;
                            }
                        } else {
                            System.out.println("Puntos de magia insuficientes");
                        }

                        break;
                    case 3: //OBJETOS MAGICOS
                        //MODIFICAR INVENTARIO
                        //CUANDO USE UN OBJETO DEBO DEJAR VACIO EL ESPACIO EN DONDE SE ENCUENTRA.
                        System.out.println("INVENTARIO");
                        for (int i = 0; i < 10; i++) {
                            System.out.println(i+" "+inventario[i]);
                        }
                        System.out.println("¿Qué eliges?");
                        int respInventario= sc.nextInt();
                        switch (respInventario){
                            case 1:
                                pocionVida-=1;
                                ps+=25;
                                System.out.println("Has recuperado 25 puntos de salud");
                                break;

                            case 2:
                                pocionMagica-=1;
                                pm+=25;
                                System.out.println("Has recuperado 25 puntos de magia");
                                break;

                            case 3:
                                pocionDaño-=1;
                                dañoFisico+=5;
                                System.out.println("Tu daño fisico ha aumentado permanentemente en 5");
                                System.out.println("Tu daño fisico actual es: "+dañoFisico);
                                break;

                            case 4:
                                pocionDañoextremo-=1;
                                multiplicadordaño+=0.05;
                                System.out.println("Tu multiplicador de daño  ha aumentado permanentemente en 0.05");
                                System.out.println("Tu multiplicador de daño es: "+multiplicadordaño);
                                break;

                            case 5:
                                break;
                        }
                        break;

                    case 4:
                        oro-=5;
                        if (oro<=0){
                            System.out.println("No tienes suficiente oro, Has muerto");
                            combate=false;
                            rpg=false;
                        } else {
                            combate=false;
                            break;
                        }
                        break;

                }
                if (pm<=90){
                    pm+=10;
                }

            }
            //AQUI ES LA SIGUIENTE PARTE DE LA TIENDA
            System.out.println("Tras una larga batalla, el héroe ve a lo lejos una taberna"+"\n¿Entrar? (SI/NO)");
            String rptataberna=sc.next();
            if (rptataberna.equalsIgnoreCase("si")){
                for (int i = 0; i < 3; i++) {
                    int objetoTaberna= generador.nextInt(4);
                    int preciorandom= generador.nextInt(3)+1;
                    System.out.println(recompensas[objetoTaberna]+" Precio: "+preciorandom);
                }
                System.out.println("¿Quieres comprar algo? (SI/NO)");
                String comprar=sc.next();
                if (comprar.equalsIgnoreCase("si")) {
                    System.out.println("¿Qué vas a comprar?");
                    sc.nextLine();
                    String palabrabuscada=sc.nextLine();
                    for (String palabra : recompensas) {
                        if (palabra.equals(palabrabuscada)) {
                            inventario[contadorInventario]=palabrabuscada;
                        }
                    }
                } else if (comprar.equalsIgnoreCase("NO")) {
                    rpg=true;
                }


            } else if (rptataberna.equalsIgnoreCase("no")) {
                rpg=true;
            }
            System.out.println("EL heroe sale de la taberna...");
        }

        }
    }

