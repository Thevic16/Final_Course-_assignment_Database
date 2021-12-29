package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Inscripcion extends BaseDeDato {

	public Inscripcion(Usuario usurario, String url) {
		super(usurario, url);
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<ArrayList<String>> select() throws SQLException {
		return super.select("Inscripcion");
	}
	
	public void insert(String codPeriodoAcad, String matricula,
			String codAsignatura, String numGrupo) throws SQLException {
		
			Connection connection = DriverManager.getConnection(this.getUrl(), this.getUsurario().getNombreUsuario(), this.getUsurario().getContrasena());
			
			String sql ="INSERT INTO Inscripcion VALUES (?,?,?,?);";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1,codPeriodoAcad);
			statement.setString(2,matricula);
			statement.setString(3,codAsignatura);
			statement.setString(4,numGrupo);

			int row = statement.executeUpdate();
			
			if(row >0) {
				System.out.println("La fila ha sido insertada.");
			}
			connection.close();
	}
	
	public void delete(String codPeriodoAcad, String matricula,
			String codAsignatura, String numGrupo) throws SQLException {
		
			Connection connection = DriverManager.getConnection(this.getUrl(), this.getUsurario().getNombreUsuario(), this.getUsurario().getContrasena());
		
			String sql ="DELETE  Inscripcion WHERE CodPeriodoAcad = ? AND Matricula = ? AND [Cod Asignatura] = ? AND NumGrupo = ?;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1,codPeriodoAcad);
			statement.setString(2,matricula);
			statement.setString(3,codAsignatura);
			statement.setString(4,numGrupo);
			
			int row = statement.executeUpdate();
			
			if(row >0) {
				System.out.println("La fila ha sido eliminada.");
			}
			connection.close();
	}
	
	public ArrayList<ArrayList<String>> selectWhere(String codPeriodoAcad, String matricula,
			String codAsignatura, String numGrupo) throws SQLException {
		ResultSet resultSet = null;
		ArrayList<ArrayList<String> > Datatabla = null;
		
			Connection connection = DriverManager.getConnection(this.getUrl(), this.getUsurario().getNombreUsuario(), this.getUsurario().getContrasena());
		    System.out.println("La conexión con Microsoft SQL Server ha sido exitosa.");
		    
			String sql ="SELECT * FROM Inscripcion WHERE CodPeriodoAcad = '"+codPeriodoAcad+"' AND [Cod Asignatura] ='"+codAsignatura+"' AND NumGrupo='"+numGrupo+"' AND Matricula = '"+matricula+"';";
			
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
