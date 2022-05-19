package modelo;

/**
 * Recibe la información de los registros de la tabla Grupos de la BDD
 * @author Fer
 */
public class Grupo {
    private String grupo;
    private String desGrupo;
    
    //Método constructor con todos los atributos
    public Grupo(String grupo, String desGrupo){
        this.grupo = grupo;
        this.desGrupo = desGrupo;            
    }

    //Método get de variable Grupo
    public String getGrupo(){
        return grupo;
    }

    //Método set de variable Grupo
    public void setGrupo(String grupo){
        this.grupo = grupo;
    }

    //Método get de variable descripcioón del Grupo
    public String getDesGrupo(){
        return desGrupo;
    }

    //Método set de variable descripcioón del Grupo
    public void setDesGrupo(String desGrupo){
        this.desGrupo = desGrupo;
    }
}