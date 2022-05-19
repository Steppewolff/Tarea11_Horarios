package modelo;

/**
 * Recibe la información de los registros de la tabla Horarios de la BDD
 * @author Fer
 */
public class Horario {
    private int idHorario;
    private String grupo;
    private int dia;
    private int hora;
    private String asignatura;
    
    //Método constructor con todos los atributos
    public Horario(int idHorario, String grupo, int dia, int hora, String asignatura){
        this.idHorario = idHorario;
        this.grupo = grupo;
        this.dia = dia;
        this.hora = hora;
        this.asignatura = asignatura;            
    }

    //Método constructor con 3 atributos: día, hora y nombre asignatura
    public Horario(int dia, int hora, String asignatura){
        this.idHorario = idHorario;
        this.grupo = grupo;
        this.dia = dia;
        this.hora = hora;
        this.asignatura = asignatura;            
    }

    //Método constructor sin atributos
    public Horario(){
            
    }
    
    //Método get para IdHorario
    public int getIdHorario(){
        return idHorario;
    }

    //Método set para IdHorario
    public void setIdHorario(int idClient){
        this.idHorario = idClient;
    }

    //Método get para Grupo
    public String getGrupo(){
        return grupo;
    }

    //Método set para Grupo
    public void setGrupo(String grupo){
        this.grupo = grupo;
    }

    //Método get para dia
    public int getDia(){
        return dia;
    }

    //Método set para dia
    public void setDia(int dia){
        this.dia = dia;
    }

    //Método get para hora
    public int getHora(){
        return hora;
    }

    //Método set para hora
    public void setHora(int hora){
        this.hora = hora;
    }

    //Método get para Asignatura
    public String getAsignatura(){
        return asignatura;
    }

    //Método set para Asignatura
    public void setAsignatura(String asignatura){
        this.asignatura = asignatura;
    }
}