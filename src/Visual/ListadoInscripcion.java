package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import logico.Academico;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class ListadoInscripcion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] fila;
	private Academico academico;
	private JTextFieldLimit textCodAsignatura;
	private JButton btnmModificar;
	private JButton EliminarButton;
	
	private int selectedRow = -1; // parte de seleccionar
	private JTextFieldLimit textCodPeriodoAcad;
	private JTextFieldLimit textNumGrupo;
	private JTextFieldLimit textMatricula;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Academico academico =  Academico.getInstance("usuario1", "12345", "jdbc:sqlserver://LAPTOP-VICTORGO;databaseName=academica");
			ListadoInscripcion dialog = new ListadoInscripcion(academico);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoInscripcion(Academico academico) {
		this.academico = academico;
		setTitle("Listado Inscripci\u00F3n(es)");
		setBounds(100, 100, 748, 425);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(5, 55, 717, 287);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane);
				{
					String[] columnas = {"Código periodo Aca.","Mátricula","Código asignatura","Número grupo"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(columnas);
					table = new JTable(){
				        private static final long serialVersionUID = 1L;

				        public boolean isCellEditable(int row, int column) {                
				                return false;               
				        };
				    };
				    
					table.setBackground(Color.LIGHT_GRAY);
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							selectedRow = table.getSelectedRow();
							if(selectedRow>-1) {
								EliminarButton.setEnabled(true);
							}
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}
			
			
		}
		
		JLabel lblFiltrar = new JLabel("Filtrar:");
		lblFiltrar.setBounds(23, 24, 46, 14);
		contentPanel.add(lblFiltrar);
		
		textCodAsignatura = new JTextFieldLimit(7);
		textCodAsignatura.setBounds(324, 21, 60, 20);
		contentPanel.add(textCodAsignatura);
		textCodAsignatura.setColumns(10);
		
		JLabel lblCodAsignatura = new JLabel("C\u00F3digo asignatura:");
		lblCodAsignatura.setBounds(324, 0, 115, 14);
		contentPanel.add(lblCodAsignatura);
		{
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.setBackground(Color.LIGHT_GRAY);
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						loadListadoFiltrado(academico,textCodPeriodoAcad.getText(),textMatricula.getText(),textCodAsignatura.getText(),textNumGrupo.getText());
						EliminarButton.setEnabled(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Error al cargar la asignatura", "Información", JOptionPane.ERROR_MESSAGE);
						EliminarButton.setEnabled(false);
						e1.printStackTrace();
					}
				}
			});
			btnBuscar.setBounds(550, 20, 77, 23);
			contentPanel.add(btnBuscar);
		}
		{
			JButton btnRecargar = new JButton("Recargar");
			btnRecargar.setBackground(Color.LIGHT_GRAY);
			btnRecargar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						loadListado(academico);
						EliminarButton.setEnabled(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch bloc
						EliminarButton.setEnabled(false);
						JOptionPane.showMessageDialog(null, "Error al cargar las asignaturas", "Información", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			});
			btnRecargar.setBounds(633, 19, 89, 23);
			contentPanel.add(btnRecargar);
		}
		
		textCodPeriodoAcad = new JTextFieldLimit(9);
		textCodPeriodoAcad.setColumns(10);
		textCodPeriodoAcad.setBounds(85, 21, 75, 20);
		contentPanel.add(textCodPeriodoAcad);
		
		JLabel lblCodAsignatura_1 = new JLabel("C\u00F3digo periodo Acad.:");
		lblCodAsignatura_1.setBounds(70, 0, 124, 14);
		contentPanel.add(lblCodAsignatura_1);
		
		textNumGrupo = new JTextFieldLimit(3);
		textNumGrupo.setColumns(10);
		textNumGrupo.setBounds(449, 21, 30, 20);
		contentPanel.add(textNumGrupo);
		
		JLabel lblNumGrupo = new JLabel("N\u00FAmero Grupo:");
		lblNumGrupo.setBounds(449, 0, 99, 14);
		contentPanel.add(lblNumGrupo);
		{
			textMatricula = new JTextFieldLimit(8);
			textMatricula.setBounds(214, 21, 65, 20);
			contentPanel.add(textMatricula);
			textMatricula.setColumns(10);
		}
		{
			JLabel lblMatricula = new JLabel("M\u00E1tricula:");
			lblMatricula.setBounds(214, 0, 67, 14);
			contentPanel.add(lblMatricula);
		}
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAgregar = new JButton("Agregar");
				btnAgregar.setBackground(Color.LIGHT_GRAY);
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AgregarInscripcion agregarGrupo = new AgregarInscripcion(academico);
						agregarGrupo.setModal(true);
						agregarGrupo.setVisible(true);
						
						try {
							loadListado(academico);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Error al cargar las asignaturas", "Información", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
					
					}
				});
				buttonPane.add(btnAgregar);
			}
			{
				btnmModificar = new JButton("Modificar");
				btnmModificar.setBackground(Color.LIGHT_GRAY);
				btnmModificar.setEnabled(false);
				buttonPane.add(btnmModificar);
			}
			{
				EliminarButton = new JButton("Eliminar");
				EliminarButton.setBackground(Color.LIGHT_GRAY);
				EliminarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				EliminarButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int option = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que desea eliminar la inscripción? ", "Eliminar", JOptionPane.WARNING_MESSAGE);
						if(option == 0) {
							try {
								ListadoInscripcion.this.academico.getInscipcion().delete((String) table.getModel().getValueAt(selectedRow, 0),(String) table.getModel().getValueAt(selectedRow, 1),
										(String) table.getModel().getValueAt(selectedRow, 2),(String) table.getModel().getValueAt(selectedRow, 3));
								
								JOptionPane.showMessageDialog(null, "Operación Realizada", "Información", JOptionPane.INFORMATION_MESSAGE);

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "Error al eliminar la grupo", "Información", JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							}
						}
						
						try {
							loadListado(academico);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Error al cargar los grupos", "Información", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						EliminarButton.setEnabled(false);
					}
				});
				EliminarButton.setEnabled(false);
				EliminarButton.setActionCommand("OK");
				buttonPane.add(EliminarButton);
				getRootPane().setDefaultButton(EliminarButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setBackground(Color.LIGHT_GRAY);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		try {
			loadListado(this.academico);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error al cargar las asignaturas", "Información", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		//System.out.println(table.getModel().getValueAt(1, 1));
	}
	
	public static void loadListado(Academico academico) throws SQLException {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		ArrayList<ArrayList<String> > Datatabla = academico.getInscipcion().select();
		
		for (int i = 0; i < Datatabla.size(); i++) {
			for (int j = 0; j < 1; j++) {
				fila[0] = Datatabla.get(i).get(j);
				fila[1] = Datatabla.get(i).get(j+1);
				fila[2] = Datatabla.get(i).get(j+2);
				fila[3] = Datatabla.get(i).get(j+3);
				model.addRow(fila);
			}
		}
	}
	
	public static void loadListadoFiltrado(Academico academico,String codPeriodoAcad,String matricula, String codAsignatura,String numGrupo) throws SQLException {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		ArrayList<ArrayList<String> > Datatabla = academico.getInscipcion().selectWhere(codPeriodoAcad,matricula,codAsignatura, numGrupo);
		
		for (int i = 0; i < Datatabla.size(); i++) {
			for (int j = 0; j < 1; j++) {
				fila[0] = Datatabla.get(i).get(j);
				fila[1] = Datatabla.get(i).get(j+1);
				fila[2] = Datatabla.get(i).get(j+2);
				fila[3] = Datatabla.get(i).get(j+3);
				model.addRow(fila);
			}
		}
	}
	
	public class JTextFieldLimit extends JTextField {
	       private int limit;

	       public JTextFieldLimit(int limit) {
	           super();
	           this.limit = limit;
	       }

	       @Override
	       protected Document createDefaultModel() {
	           return new LimitDocument();
	       }

	       private class LimitDocument extends PlainDocument {

	           @Override
	           public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
	               if (str == null) return;

	               if ((getLength() + str.length()) <= limit) {
	                   super.insertString(offset, str, attr);
	               }
	           }       

	       }

	}
}
