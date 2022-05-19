package controlador;
    import java.util.ArrayList;
    import java.sql.SQLException;
    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.Statement;
    import java.sql.ResultSet;
    import modelo.Grupo;
/**
 * Clase que sirve para realizar operaciones con la tabla Grupos de la BDD
 * @author Fer
 */
public class GrupoDAO {
    protected String url;
   
   //Método constructor 
   public GrupoDAO (String url){
       this.url = url;
   }
   
   //método que devuelve una lista cons los grupos de la BDD
   public ArrayList<Grupo> getListaGrupos() throws SQLException{
       ArrayList listaGrupos = new ArrayList<>();
       Connection connection = DriverManager.getConnection(url);
       Statement statement = connection.createStatement();
       ResultSet result = statement.executeQuery("SELECT * FROM grups");
       
       System.out.println("result length: " + result);
       while (result.next()){
           Grupo grup = new Grupo(result.getString("grup"), result.getString("desgrup"));
           listaGrupos.add(grup);
       }

       connection.close();

       return listaGrupos;
   }
   
   //Método que devuelve el nombre del grupo buscado a la clase principal
   public String devolverDesgrupBuscado (String aux) throws SQLException{
       Connection connection = DriverManager.getConnection(url);
       Statement statement = connection.createStatement();
       String desgrupBuscado = new String();

       ResultSet result = statement.executeQuery("SELECT desgrup FROM grups WHERE grup='" + aux + "';");
       while (result.next()){
           desgrupBuscado = result.getString("desgrup");
       }
       
       connection.close();
       return desgrupBuscado;
   }
}