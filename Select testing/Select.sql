SELECT *
FROM Asignatura

DELETE Asignatura
WHERE [Cod Asignatura] = 'ICC102T'


SELECT *
FROM DiaSemana

SELECT *
FROM Estudiante

SELECT *
FROM Grupo

SELECT *
FROM GrupoHorario

SELECT *
FROM Inscripcion

SELECT *
FROM [Periodo Academico]


SELECT Estudiante.matricula,dbo.FormatName(Estudiante.nombre1,Estudiante.nombre2,Estudiante.apellido1,Estudiante.apellido2) AS [Nombre Estudiante],Inscripcion.CodPeridoAcad,Inscripcion.[Cod Asignatura],Inscripcion.NumGrupo,Grupo.Horario
FROM Estudiante,Inscripcion,Grupo
WHERE Estudiante.matricula = Inscripcion.Matricula AND Inscripcion.CodPeridoAcad =Grupo.CodPeriodoAcad
AND Inscripcion.[Cod Asignatura] = Grupo.[Cod Asignatura] AND Inscripcion.NumGrupo = Grupo.NumGrupo
AND Estudiante.matricula = '20171896'


EXEC dbo.InformeInscEst @matricula = '20171896';

--BEGIN TRAN
--ROLLBACK
--COMMIT