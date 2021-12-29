package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GrupoHorario extends BaseDeDato {

	public GrupoHorario(Usuario usurario, String url) {
		super(usurario, url);
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<ArrayList<String>> select() throws SQLException {
		return super.select("GrupoHorario");
	}
	
	public void insert(String codPeriodoAcad,String codAsignatura,
			String numGrupo,int dia, String horaInicial,String horaFinal) throws SQLException
	{
			Connection connection = DriverManager.getConnection(this.getUrl(), this.getUsurario().getNombreUsuario(), this.getUsurario().getContrasena());
			
			String sql ="INSERT INTO GrupoHorario VALUES (?,?,?,?,?,?);";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1,codPeriodoAcad);
			statement.setString(2,codAsignatura);
			statement.setString(3,numGrupo);
			statement.setInt(4,dia);
			statement.setString(5,horaInicial);
			statement.setString(6,horaFinal);
			
			int row = statement.executeUpdate();
			
			if(row >0) {
				System.out.println("La fila ha sido insertada.");
			}

			connection.close();
	}
	
	public void delete(String codPeriodoAcad,String codAsignatura,
			String numGrupo,int dia, String horaInicial) throws SQLException {
		
			Connection connection = DriverManager.getConnection(this.getUrl(), this.getUsurario().getNombreUsuario(), this.getUsurario().getContrasena());
			
			String sql ="DELETE GrupoHorario WHERE CodPeriodoAcad=? AND CodAsignatura=? AND NumGrupo=? AND dia=? AND HoraInicial = ?;";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1,codPeriodoAcad);
			statement.setString(2,codAsignatura);
			statement.setString(3,numGrupo);
			statement.setInt(4,dia);
			statement.setString(5,horaInicial);

			
			int row = statement.executeUpdate();
			
			if(row >0) {
				System.out.println("La fila ha sido eliminada.");
			}

			
			connection.close();
	}
	
	public void update(String codPeriodoAcad,String codAsignatura,
			String numGrupo,int dia, String horaInicial,String horaFinal) throws SQLException {	
		this.delete(codPeriodoAcad, codAsignatura, numGrupo, dia, horaInicial);
		this.insert(codPeriodoAcad, codAsignatura, numGrupo, dia, horaInicial, horaFinal);
	
	}
	
	public ArrayList<ArrayList<String>> selectWhere(String codPeriodoAcad,String codAsignatura, 
			String numGrupo) throws SQLException {
		ResultSet resultSet = null;
		ArrayList<ArrayList<String> > Datatabla = null;
		
			Connection connection = DriverManager.getConnection(this.getUrl(), this.getUsurario().getNombreUsuario(), this.getUsurario().getContrasena());
		    System.out.println("La conexión con Microsoft SQL Server ha sido exitosa.");
		    
			String sql ="SELECT * FROM GrupoHorario WHERE CodPeriodoAcad='"+codPeriodoAcad+"' AND CodAsignatura = '"+codAsignatura+"' AND NumGrupo= '"+numGrupo+"';";
			
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
