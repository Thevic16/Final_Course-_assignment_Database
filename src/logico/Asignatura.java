package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Asignatura extends BaseDeDato {

	public Asignatura(Usuario usurario, String url) {
		super(usurario, url);
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<ArrayList<String>> select() throws SQLException {
		return super.select("Asignatura");
	}
	
	public void insert(String codAsignatura, String nombre, int creditos, int horasTecnicas,int horasPracticas) throws SQLException {
		
			Connection connection = DriverManager.getConnection(this.getUrl(), this.getUsurario().getNombreUsuario(), this.getUsurario().getContrasena());
		
			String sql ="INSERT INTO Asignatura VALUES (?,?,?,?,?);";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1,codAsignatura);
			statement.setString(2,nombre);
			statement.setInt(3,creditos);
			statement.setInt(4,horasTecnicas);
			statement.setInt(5,horasPracticas);
			
			int row = statement.executeUpdate();
			
			if(row >0) {
				System.out.println("La fila ha sido insertada.");
			}

			connection.close();
			
	}
	
	public void update(String codAsignatura, String nombre, int creditos, int horasTecnicas,int horasPracticas) throws SQLException
	{
		
			Connection connection = DriverManager.getConnection(this.getUrl(), this.getUsurario().getNombreUsuario(), this.getUsurario().getContrasena());
			
			String sql ="UPDATE Asignatura SET [Cod Asignatura] = ?,Nombre = ?,Creditos = ?, HorasTeoricas = ?, HorasPracticas = ? "
					+ "WHERE [Cod Asignatura] = ?;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1,codAsignatura);
			statement.setString(2,nombre);
			statement.setInt(3,creditos);
			statement.setInt(4,horasTecnicas);
			statement.setInt(5,horasPracticas);
			statement.setString(6,codAsignatura);
			
			int row = statement.executeUpdate();
			
			if(row >0) {
				System.out.println("La fila ha sido modificada.");
			}

			
			connection.close();
			
	}
	
	public void delete(String codAsignatura) throws SQLException {
		
			Connection connection = DriverManager.getConnection(this.getUrl(), this.getUsurario().getNombreUsuario(), this.getUsurario().getContrasena());
			
			String sql ="DELETE Asignatura WHERE [Cod Asignatura] = ?;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1,codAsignatura);

			int row = statement.executeUpdate();
			
			if(row >0) {
				System.out.println("La fila ha sido eliminada.");
			}

			
			connection.close();	
	}
	
	
	public ArrayList<ArrayList<String>> selectWhere(String codAsignatura) throws SQLException {
		ResultSet resultSet = null;
		ArrayList<ArrayList<String> > Datatabla = null;
		
			Connection connection = DriverManager.getConnection(this.getUrl(), this.getUsurario().getNombreUsuario(), this.getUsurario().getContrasena());
		    System.out.println("La conexión con Microsoft SQL Server ha sido exitosa.");
		    
			String sql ="SELECT * FROM Asignatura WHERE [Cod Asignatura] = '"+codAsignatura+"';";
			
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
