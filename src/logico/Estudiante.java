package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Estudiante extends BaseDeDato {

	public Estudiante(Usuario usurario, String url) {
		super(usurario, url);
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<ArrayList<String>> select() throws SQLException {
		return super.select("Estudiante");
	}
	
	public void insert(String matricula,String nombre1, String nombre2,
			String apellido1, String apellido2, String carrera, String pago,
			String nacionalidad, String direccion) throws SQLException {
		
			Connection connection = DriverManager.getConnection(this.getUrl(), this.getUsurario().getNombreUsuario(), this.getUsurario().getContrasena());
			
			String sql ="INSERT INTO Estudiante VALUES (?,?,?,?,?,?,?,?,?);";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1,matricula);
			statement.setString(2,nombre1);
			statement.setString(3,nombre2);
			statement.setString(4,apellido1);
			statement.setString(5,apellido2);
			statement.setString(6,carrera);
			statement.setString(7,pago);
			statement.setString(8,nacionalidad);
			statement.setString(9,direccion);
			
			
			int row = statement.executeUpdate();
			
			if(row >0) {
				System.out.println("La fila ha sido insertada.");
			}

			
			connection.close();
	}
	
	public void update(String matricula,String nombre1, String nombre2,
			String apellido1, String apellido2, String carrera, String pago,
			String nacionalidad, String direccion) throws SQLException {
		
			Connection connection = DriverManager.getConnection(this.getUrl(), this.getUsurario().getNombreUsuario(), this.getUsurario().getContrasena());
			
			String sql ="UPDATE Estudiante SET matricula= ?,Nombre1=?,Nombre2=?,Apellido1=?,Apellido2=?,Carrera=?,Pago=?,Nacionalidad=?,Dirección=? WHERE matricula= ? ;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1,matricula);
			statement.setString(2,nombre1);
			statement.setString(3,nombre2);
			statement.setString(4,apellido1);
			statement.setString(5,apellido2);
			statement.setString(6,carrera);
			statement.setString(7,pago);
			statement.setString(8,nacionalidad);
			statement.setString(9,direccion);
			statement.setString(10,matricula);
			
			
			int row = statement.executeUpdate();
			
			if(row >0) {
				System.out.println("La fila ha sido modificado.");
			}

			
			connection.close();
	}
	
	
	public void delete(String matricula) throws SQLException {
			Connection connection = DriverManager.getConnection(this.getUrl(), this.getUsurario().getNombreUsuario(), this.getUsurario().getContrasena());
			
			String sql ="DELETE Estudiante WHERE matricula= ? ;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1,matricula);

						
			int row = statement.executeUpdate();
			
			if(row >0) {
				System.out.println("La fila ha sido eliminada.");
			}
			connection.close();
	}
	
	
	public ArrayList<ArrayList<String>> selectWhere(String matricula) throws SQLException {
		ResultSet resultSet = null;
		ArrayList<ArrayList<String> > Datatabla = null;
		
			Connection connection = DriverManager.getConnection(this.getUrl(), this.getUsurario().getNombreUsuario(), this.getUsurario().getContrasena());
		    System.out.println("La conexión con Microsoft SQL Server ha sido exitosa.");
		    
			String sql ="SELECT * FROM Estudiante WHERE matricula ='"+matricula+"';";
			
			Statement statement = connection.createStatement();
			
			resultSet= statement.executeQuery(sql);
			
			Datatabla = resultSetToArrayList(resultSet);
			
			connection.close();
			
			
		return Datatabla;
	}
	
	
	public ArrayList<ArrayList<String>> resultSetToArrayList(ResultSet rs) throws SQLException {
		
	    ResultSetMetaData md = rs.getMetaData();
	    int columnas = md.getColumnCount();
	    
	    ArrayList<ArrayList<String> > tabla = new ArrayList<ArrayList<String> >(columnas); 
		
			while(rs.next()) {
				ArrayList<String> fila = new ArrayList<String>();
				
			    for (int i = 1; i < columnas+1; i++) { 	
			    	fila.add(rs.getString(i));
			    }
				tabla.add(fila);
			}
	    return tabla;		
	}

}
