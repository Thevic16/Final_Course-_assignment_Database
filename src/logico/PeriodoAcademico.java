package logico;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PeriodoAcademico extends BaseDeDato {

	public PeriodoAcademico(Usuario usurario, String url) {
		super(usurario, url);
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<ArrayList<String>> select() throws SQLException {
		return super.select("[Periodo Academico]");
	}
	
}
