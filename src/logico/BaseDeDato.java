package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public abstract class BaseDeDato {
	
	public BaseDeDato(Usuario usurario, String url) {
		super();
		this.usurario = usurario;
		this.url = url;
	}
	
	private Usuario usurario;
	private String url;
	
	public Usuario getUsurario() {
		return usurario;
	}
	public void setUsurario(Usuario usurario) {
		this.usurario = usurario;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public ArrayList<ArrayList<String>> select(String tabla) throws SQLException {
		ResultSet resultSet = null;
		ArrayList<ArrayList<String> > Datatabla = null;
		
			Connection connection = DriverManager.getConnection(this.url, this.usurario.getNombreUsuario(), this.usurario.getContrasena());
		    System.out.println("La conexión con Microsoft SQL Server ha sido exitosa.");
		    
			String sql ="SELECT * FROM "+tabla;
			
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
