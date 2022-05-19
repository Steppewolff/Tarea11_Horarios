package controlador;
    import java.util.ArrayList;
    import java.sql.SQLException;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.Statement;
    import java.sql.ResultSet;
    import modelo.Horario;
/**
 * Clase que sirve para realizar operaciones con la tabla Horaris de la BDD
 * @author Fer
 */
public class HorarioDAO {
    protected String url;
    
    //método constructor
    public HorarioDAO(String url){
        this.url = url;
    }
    
    //método para obtener el horario de una asignatura y pasarla como objeto a Clase Principal
    public ArrayList<Horario> getHorario (String aux) throws SQLException{
        ArrayList<Horario> listaHorarios = new ArrayList<>(); 
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT dia, hora, assignatura FROM horaris WHERE grup='" + aux + "' ORDER BY hora, dia;");

        while (result.next()){
            Horario horario = new Horario(result.getInt("dia"), result.getInt("hora"), result.getString("assignatura"));
            listaHorarios.add(horario);
        }
        
        connection.close();
        return listaHorarios;
    }

    //Método que devuelve a clase principal la información de una asignatura de tutoría
    public Horario getHorarioTut (String aux) throws SQLException{
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();
        Horario horario = new Horario();
        ResultSet result = statement.executeQuery("SELECT dia, hora, assignatura FROM horaris WHERE grup='" + aux + "' AND assignatura LIKE '%TUT' ORDER BY hora, dia;");

        while (result.next()){
            horario = new Horario(result.getInt("dia"), result.getInt("hora"), result.getString("assignatura"));
        }
        
        connection.close();
        return horario;
    }
    
    //Escribe en la BDD el nuevo día para la tutoría de la asignatura
    public void setHorarioTut (String aux, int aux_num) throws SQLException{
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE horaris SET dia='" + aux_num + "' WHERE grup='" + aux + "' AND assignatura LIKE '%TUT'");
        connection.close();
    }    
}