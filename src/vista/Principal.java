package vista;
    import java.util.Scanner;
    import java.util.regex.Matcher;
    import java.util.regex.Pattern;
    import java.util.ArrayList;
    import modelo.Grupo;
    import modelo.Horario;
    import controlador.GrupoDAO;
    import controlador.HorarioDAO;
    import java.sql.SQLException;
    import java.util.Arrays;

/**
 *
 * @author Fer
 */
public class Principal {
    private static String url;

    //Método main de la clase Principal, se gestiona E/S y llamadas a los métodos
    public static void main(String[] args) {
        String inputMainMenu = "1";
        String dias[] = {"DILLUNS", "DIMARTS", "DIMECRES", "DIJOUS", "DIVENDRES"};
        url = "jdbc:mariadb://localhost:3307/horaris?user=root&password=0Castorp0";
        
        Scanner input = new Scanner(System.in); //Se instancia Scanner

        //Este bucle while hace que el programa se mantenga corriendo mientras en el menu principal no se escoja la opción salir
        while ((Integer.parseInt(inputMainMenu) >= 1) & (Integer.parseInt(inputMainMenu) < 4)){
            Pattern patMain = Pattern.compile("[1-4]*"); //Se establece regla para que el input sea un número de una cifra entre 1 y 4
            System.out.println("Escoja la acción que quiera realizar: ");
            System.out.println("1.  Mostrar la lista de grupos.");
            System.out.println("2.  Mostrar el horario de un grupo.");
            System.out.println("3.  Modificar la hora de tutoría de un grupo a otro día.");
            System.out.println("4.  Salir del programa.");
            System.out.println("Introduzca una de las opciones de la lista anterior para comenzar):");
            inputMainMenu = input.nextLine();
            System.out.println("La opción escogida es:" + inputMainMenu);
            Matcher matMain = patMain.matcher(inputMainMenu); //Compara la entrada con la regla REGEX
            while(!matMain.matches()){ //Bucle condicional que itera mientras la entrada no tenga el formato definido en variable patMain
                System.out.println("Escoja la acción que quiera realizar: ");
                System.out.println("1.  Mostrar la lista de grupos.");
                System.out.println("2.  Mostrar el horario de un grupo.");
                System.out.println("3.  Modificar la hora de tutoría de un grupo a otro día.");
                System.out.println("4.  Salir del programa.");
                System.out.println("Introduzca una de las opciones de la lista anterior para comenzar):");
                inputMainMenu = input.nextLine();
                matMain = patMain.matcher(inputMainMenu);
            }
            
            //Estructura de control para las opciones del menú principal
            switch(Integer.parseInt(inputMainMenu)){
                case 1:
                    try{
                        GrupoDAO grupObj = new GrupoDAO(url);
                        ArrayList<Grupo> recibirLista = grupObj.getListaGrupos();
                        for (Grupo grupo : recibirLista){
                            System.out.println(grupo.getDesGrupo());
                        }
                    }
                    catch(SQLException exc){
                    }
                    break;
                case 2:
                    try{
                        System.out.println("Introduzca el nombre del grupo para obtener su horario: ");
                        String grupoBuscado = input.nextLine().toUpperCase();
                        Pattern patGrupo = Pattern.compile("M1I|M2I|S1I|S1P|S1W|S2I|S2P|S2W");//Se establece regex para la entrada por teclado del grupo
                        Matcher matGrupo = patGrupo.matcher(grupoBuscado);//Compara la entrada con la regla REGEX
                        while (!matGrupo.matches()){
                            System.out.println("El grupo introducido no es correcto, por favor introduzca un grupo válido de la lista (M1I, M2I, S1I, S1P, S1W, S2I, S2P, S2W):");
                            grupoBuscado = input.nextLine().toUpperCase();
                            matGrupo = patGrupo.matcher(grupoBuscado);
                        }
                        HorarioDAO grupObj = new HorarioDAO(url);
                        ArrayList<Horario> recibirHorario = grupObj.getHorario(grupoBuscado);
                        GrupoDAO grupObj2 = new GrupoDAO(url);
                        String desgrupBuscado = grupObj2.devolverDesgrupBuscado(grupoBuscado);
                        
                        System.out.printf("%n %n %-10s %s %n", grupoBuscado, desgrupBuscado);
                        System.out.printf("%13s %10s %10s %10s %10s %n", dias);
                        
                        String matrizHorario [][] = new String[14][6];

                        for(Horario k : recibirHorario){ //dentro de este bucle, cojo indice dia e indice hora y lo escribo en esos indices del array, el resto deben ser 0s
                            int dia = k.getDia();
                            int hora = k.getHora();
                            matrizHorario[hora][dia] = k.getAsignatura();
                        }
                        
                        for (int i = 1; i<14; i++){
                            System.out.printf("%2d", i);                            
                            for (int j = 1; j<6; j++){
                                if (matrizHorario[i][j] == null){
                                    System.out.printf("%11s", " ");
                                }else{
                                    System.out.printf("%11s", matrizHorario[i][j]);                                    
                                }
                            }
                            System.out.printf("%n");                                    
                        }
                        System.out.printf("%n %n");                                    
                    }
                    catch(SQLException exc){
                    }
                    break;
                case 3:
                    try{
                        System.out.println("Introduzca el nombre del grupo para modificar tutoría: ");
                        String grupoBuscado = input.nextLine().toUpperCase();
                        Pattern patGrupo = Pattern.compile("M1I|M2I|S1I|S1P|S1W|S2I|S2P|S2W");
                        Matcher matGrupo = patGrupo.matcher(grupoBuscado);
                        while (!matGrupo.matches()){
                            System.out.println("El grupo introducido no es correcto, por favor introduzca un grupo válido de la lista (M1I, M2I, S1I, S1P, S1W, S2I, S2P, S2W):");
                            grupoBuscado = input.nextLine().toUpperCase();
                            matGrupo = patGrupo.matcher(grupoBuscado);
                        }
                        HorarioDAO grupObj = new HorarioDAO(url);
                        Horario tutoria = grupObj.getHorarioTut(grupoBuscado);
                        int diaNumTutoria = tutoria.getDia();

                        String diaTextTutoria = dias[diaNumTutoria];
                        
                        System.out.println("El día ACTUAL de tutoría del grupo " + grupoBuscado + " es: " + diaTextTutoria);                        
                        System.out.printf("%n");
                        System.out.println("Introduzca el nuevo día de la semana para tutoría de " + tutoria.getAsignatura() + ": ");
                        diaTextTutoria = input.nextLine().toUpperCase();

                        Pattern patDia = Pattern.compile("DILLUNS|DIMARTS|DIMECRES|DIJOUS|DIVENDRES");
                        Matcher matDia = patDia.matcher(diaTextTutoria);
                        while (!matDia.matches()){
                            System.out.println("El día de la semana introducido no es correcto, por favor introduzca un día válido de la lista (DILLUNS, DIMARTS, DIMECRES, DIJOUS, DIVENDRES):");
                            diaTextTutoria = input.nextLine().toUpperCase();
                            matDia = patDia.matcher(diaTextTutoria);
                        }

                        diaNumTutoria = Arrays.asList(dias).indexOf(diaTextTutoria);
                        grupObj.setHorarioTut (grupoBuscado, diaNumTutoria);

                        //Se comprueba que el día de tutoría se ha modificado correctamente volviendo a hacer la misma bísqueda en la base de datos
                        tutoria = grupObj.getHorarioTut(grupoBuscado);
                        diaNumTutoria = tutoria.getDia();
                        diaTextTutoria = dias[diaNumTutoria];
                        System.out.println("El día de tutoría del grupo " + grupoBuscado + " AHORA es: " + diaTextTutoria);                        
                        System.out.printf("%n");
                    }
                    catch(SQLException exc){
                    }
                    break;
            }
        }
    }
}