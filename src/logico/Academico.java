package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Academico {
	private Usuario usuario;
	private Asignatura asignatura;
	private DiaSemana diaSemana;
	private Estudiante estudiante;
	private Grupo grupo;
	private GrupoHorario grupoHorario;
	private Inscripcion inscipcion;
	private PeriodoAcademico periodoAcademico; 
	
	private static Academico academico; // primer paso singleton. 
	
	private Academico(String nombreUsuario, String contrasena, String url) {
		super();
		this.usuario = new Usuario(nombreUsuario, contrasena);
		this.asignatura = new Asignatura(this.usuario, url);
		this.diaSemana = new DiaSemana(this.usuario, url);
		this.estudiante = new Estudiante(this.usuario, url);
		this.grupo = new Grupo(this.usuario, url);
		this.grupoHorario = new GrupoHorario(this.usuario, url);
		this.inscipcion = new Inscripcion(this.usuario, url);
		this.periodoAcademico = new PeriodoAcademico(this.usuario, url);
		
	}
	
	public static Academico getInstance(String nombreUsuario, String contrasena, String url) { // tercer paso, tiene que ser estatica para que no dependa de la clase.
		if(academico  ==null) {
			academico = new Academico(nombreUsuario,contrasena,url);
		}
		return academico;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public DiaSemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(DiaSemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public GrupoHorario getGrupoHorario() {
		return grupoHorario;
	}

	public void setGrupoHorario(GrupoHorario grupoHorario) {
		this.grupoHorario = grupoHorario;
	}

	public Inscripcion getInscipcion() {
		return inscipcion;
	}

	public void setInscipcion(Inscripcion inscipcion) {
		this.inscipcion = inscipcion;
	}

	public PeriodoAcademico getPeriodoAcademico() {
		return periodoAcademico;
	}

	public void setPeriodoAcademico(PeriodoAcademico periodoAcademico) {
		this.periodoAcademico = periodoAcademico;
	}
	
	public ArrayList<ArrayList<String>> InformeInscEst(String matricula) throws SQLException{
		
		ResultSet resultSet = null;

		Connection connection = DriverManager.getConnection(this.asignatura.getUrl(), this.usuario.getNombreUsuario(), this.usuario.getContrasena());
	    System.out.println("La conexión con Microsoft SQL Server ha sido exitosa.");
		
		String sql ="EXEC dbo.InformeInscEst @matricula = "+matricula+" ;";
		
		Statement statement = connection.createStatement();
		
		resultSet= statement.executeQuery(sql);
		
		ArrayList<ArrayList<String>> informe = resultSetToArrayList(resultSet);
		
		connection.close();
		
		return informe;
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
