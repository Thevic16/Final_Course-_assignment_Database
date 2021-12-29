package Visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import logico.Academico;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import java.awt.Font;

public class Principal extends JFrame {

	private JPanel contentPane;
	private Dimension dim = null; // para ajustar el tamaño de la ventana
	private Academico academico =  Academico.getInstance("usuario1", "12345", "jdbc:sqlserver://LAPTOP-VICTORGO;databaseName=academica");
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] fila;
	
	private static DefaultTableModel model2;
	private static Object[] fila2;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/Imagenes/academico (1).png")));
		setBackground(Color.WHITE);
		setResizable(false);
		setTitle("DeskSISE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		
		//tamano de la ventana.
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width, (dim.height-50));
		setLocationRelativeTo(null);

		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		setJMenuBar(menuBar);
		
		JMenu mnAsignatura = new JMenu("Asignatura");
		mnAsignatura.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/cuaderno.png")));
		menuBar.add(mnAsignatura);
		
		JMenuItem mntmAsignaturaListar = new JMenuItem("Listar");
		mntmAsignaturaListar.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/lista (2).png")));
		mntmAsignaturaListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoAsignatura listadoAsignatura = new ListadoAsignatura(academico);
				listadoAsignatura.setModal(true);
				listadoAsignatura.setVisible(true);
			}
		});
		mnAsignatura.add(mntmAsignaturaListar);
		
		JMenu mnGrupo = new JMenu("Grupo");
		mnGrupo.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/grupo.png")));
		menuBar.add(mnGrupo);
		
		JMenuItem mntmGrupoListar = new JMenuItem("Listar");
		mntmGrupoListar.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/lista (2).png")));
		mntmGrupoListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoGrupo listadoGrupo = new ListadoGrupo(academico);
				listadoGrupo.setModal(true);
				listadoGrupo.setVisible(true);
			}
		});
		mnGrupo.add(mntmGrupoListar);
		
		JMenu mnGrupoHorario = new JMenu("Grupo Horario");
		mnGrupoHorario.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/paso-del-tiempo.png")));
		menuBar.add(mnGrupoHorario);
		
		JMenuItem mntmGrupoHListar = new JMenuItem("Listar");
		mntmGrupoHListar.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/lista (2).png")));
		mntmGrupoHListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoGrupoHorario listadoGrupoHorario = new ListadoGrupoHorario(academico);
				listadoGrupoHorario.setModal(true);
				listadoGrupoHorario.setVisible(true);
			}
		});
		mnGrupoHorario.add(mntmGrupoHListar);
		
		JMenu mnEstudiante = new JMenu("Estudiante");
		mnEstudiante.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/libro-de-lectura.png")));
		menuBar.add(mnEstudiante);
		
		JMenuItem mntmEstudianteListar = new JMenuItem("Listar");
		mntmEstudianteListar.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/lista (2).png")));
		mntmEstudianteListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoEstudiante listadoEstudiante = new ListadoEstudiante(academico);
				listadoEstudiante.setModal(true);
				listadoEstudiante.setVisible(true);
			}
		});
		mnEstudiante.add(mntmEstudianteListar);
		
		JMenu mnInscripcion = new JMenu("Inscripci\u00F3n");
		mnInscripcion.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/escritura.png")));
		menuBar.add(mnInscripcion);
		
		JMenuItem mntmInscripcionListar = new JMenuItem("Listar");
		mntmInscripcionListar.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/lista (2).png")));
		mntmInscripcionListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListadoInscripcion listadoInscripcion = new ListadoInscripcion(academico);
				listadoInscripcion.setModal(true);
				listadoInscripcion.setVisible(true);
			}
		});
		mnInscripcion.add(mntmInscripcionListar);
		
		JMenuItem mntmInforme = new JMenuItem("Informe");
		mntmInforme.setIcon(new ImageIcon(Principal.class.getResource("/Imagenes/informe-de-datos.png")));
		mntmInforme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformeInscripcion informeInscripcion = new InformeInscripcion(academico);
				informeInscripcion.setModal(true);
				informeInscripcion.setVisible(true);
			}
		});
		mnInscripcion.add(mntmInforme);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);
			panel.setBounds(22, 111, 1305, 216);
			contentPane.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane);
				{
					String[] columnas = {"Código periodo Académico","Descripción","Fecha inicio","Fecha Fin","Fecha inicio clases",
							"Fecha fin clases","Fecha límite de pago","Fecha límite prematrícula","Fecha límite retiro","Fecha límite publicación"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(columnas);
					table = new JTable(){
				        private static final long serialVersionUID = 1L;

				        public boolean isCellEditable(int row, int column) {                
				                return false;               
				        };
				    };
					table.setBackground(Color.LIGHT_GRAY);

					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}
		
		}
		
		JLabel lblBienvenido = new JLabel("Bienvenido");
		lblBienvenido.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblBienvenido.setBounds(22, 11, 338, 47);
		contentPane.add(lblBienvenido);
		
		JLabel lblInformacionSobreLos = new JLabel("Informaci\u00F3n sobre lo(s) periodo(s) acad\u00E9mico(s):");
		lblInformacionSobreLos.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblInformacionSobreLos.setBounds(22, 69, 662, 31);
		contentPane.add(lblInformacionSobreLos);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(22, 426, 623, 120);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		table_1 = new JTable(){
	        private static final long serialVersionUID = 1L;

	        public boolean isCellEditable(int row, int column) {                
	                return false;               
	        };
	    };
		table_1.setBackground(Color.LIGHT_GRAY);
		//panel.add(table_1, BorderLayout.NORTH);
		String[] columnas1 = {"Día","Nombre"};
		model2 = new DefaultTableModel();
		model2.setColumnIdentifiers(columnas1);
		
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setModel(model2);
		scrollPane.setViewportView(table_1);
		
		JLabel lblInformacinSobreLos = new JLabel("Informaci\u00F3n sobre los d\u00EDas semana:");
		lblInformacinSobreLos.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblInformacinSobreLos.setBounds(22, 384, 662, 31);
		contentPane.add(lblInformacinSobreLos);

		
		
		try {
			loadListadoPeriodoAcad(academico);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error al cargar lo(s) periodo(s) academico(s)", "Información", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		
		
		try {
			loadListadoDiaSemana(academico);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error al cargar los dias semana", "Información", JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}
		
	}
	
	
	public static void loadListadoPeriodoAcad(Academico academico) throws SQLException {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		ArrayList<ArrayList<String> > Datatabla = academico.getPeriodoAcademico().select();
		
		for (int i = 0; i < Datatabla.size(); i++) {
			for (int j = 0; j < 1; j++) {
				fila[0] = Datatabla.get(i).get(j);
				fila[1] = Datatabla.get(i).get(j+1);
				fila[2] = Datatabla.get(i).get(j+2);
				fila[3] = Datatabla.get(i).get(j+3);
				fila[4] = Datatabla.get(i).get(j+4);
				fila[5] = Datatabla.get(i).get(j+5);
				fila[6] = Datatabla.get(i).get(j+6);
				fila[7] = Datatabla.get(i).get(j+7);
				fila[8] = Datatabla.get(i).get(j+8);
				fila[9] = Datatabla.get(i).get(j+9);
				model.addRow(fila);
			}
		}
	}
	
	public static void loadListadoDiaSemana(Academico academico) throws SQLException {
		model2.setRowCount(0);
		fila = new Object[model2.getColumnCount()];
		ArrayList<ArrayList<String> > Datatabla = academico.getDiaSemana().select();
		
		for (int i = 0; i < Datatabla.size(); i++) {
			for (int j = 0; j < 1; j++) {
				fila[0] = Datatabla.get(i).get(j);
				fila[1] = Datatabla.get(i).get(j+1);
				model2.addRow(fila);
			}
		}
	}
	
}
