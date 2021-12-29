package logico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		Academico academico =  Academico.getInstance("usuario1", "12345", "jdbc:sqlserver://LAPTOP-VICTORGO;databaseName=academica");
		
		//PRUEBA SELECT
		ArrayList<ArrayList<String> > Datatabla = null;
		
		//Datatabla = academico.getAsignatura().select();
		//Datatabla = academico.getDiaSemana().select();
		//Datatabla = academico.getEstudiante().select();
		//Datatabla = academico.getGrupo().select();
		//Datatabla = academico.getGrupoHorario().select();
		//Datatabla = academico.getInscipcion().select();
		Datatabla = academico.getPeriodoAcademico().select();
		//Datatabla = academico.InformeInscEst("20171896");
		
		
		//PRUEBA SELECT WHERE
	
		//Datatabla = academico.getAsignatura().selectWhere("ITT465T");;
		//Datatabla = academico.getEstudiante().selectWhere("20171896");
		//Datatabla = academico.getGrupo().selectWhere("201620172", "ITT465T", "006");
		//Datatabla = academico.getGrupoHorario().select();
		//Datatabla = academico.getInscipcion().selectWhere("201620172","20171408","ISC300T", "009");

		
		
		for (int i = 0; i < Datatabla.size(); i++) {
			for (int j = 0; j < Datatabla.get(i).size(); j++) {
				
				System.out.println(Datatabla.get(i).get(j));
			}
			
			System.out.println("---------------------------------");
		}
		
		
		
		//PRUEBA INSERT
		/*
		academico.getAsignatura().insert("ITT465T", "Elaboracion", 4, 1, 2);
		academico.getEstudiante().insert("20171896", "Pedro", "Ramon", "Sanchez", "Ramirez", "ISC", "E","RD","Santiago");
		academico.getGrupo().insert("201620172", "ITT465T", "006", 20);
		academico.getGrupoHorario().insert("201620172", "ITT465T", "006", 2,"10:00:00", "11:00:00");
		academico.getInscipcion().insert("201620172","20171896","ITT465T", "006");
		*/

		
		//PRUEBA UPDATE
		//academico.getAsignatura().update("ITT465T", "Elaboracion", 4, 1, 5);
		//academico.getEstudiante().update("20171896", "Pedro", "Ramon", "Sanchez", "Ramirez", "ISC", "E","RD","Puerto plata");
		//academico.getGrupo().update("201620172", "ITT465T", "006", 25);
		//academico.getGrupoHorario().update("201620172", "ITT465T", "006", 2,"10:00:00", "13:00:00");
		
		//PRUEBA DELETE
		//academico.getInscipcion().delete("201620172","20171896","ITT465T", "006");
		//academico.getGrupoHorario().delete("201620172", "ITT465T", "006", 2,"10:00:00");
		//academico.getGrupo().delete("201620172", "ITT465T", "006");
		//academico.getAsignatura().delete("ITT465T");
		//academico.getEstudiante().delete("20171896");
		
		//academico.getInscipcion().delete("201620172","20170303","ISC300T", "009");
		
	}

}
