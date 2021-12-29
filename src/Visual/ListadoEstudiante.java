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

public class ListadoEstudiante extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] fila;
	private Academico academico;
	private JTextFieldLimit textMatricula;
	private JButton btnmModificar;
	private JButton EliminarButton;
	
	private int selectedRow = -1; // parte de seleccionar

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Academico academico =  Academico.getInstance("usuario1", "12345", "jdbc:sqlserver://LAPTOP-VICTORGO;databaseName=academica");
			ListadoEstudiante dialog = new ListadoEstudiante(academico);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoEstudiante(Academico academico) {
		this.academico = academico;
		setTitle("Listado estudiante(s)");
		setBounds(100, 100, 955, 425);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(5, 55, 924, 287);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane);
				{
					String[] columnas = {"Mátricula","Primer nombre","Segundo nombre","Primer apellido","Segundo apellido","Carrera","Pago","Nacionalidad","Dirección"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(columnas);
					table = new JTable(){
				        private static final long serialVersionUID = 1L;

				        public boolean isCellEditable(int row, int column) {                
				                return false;               
				        };
				    };
				    
					table.setBackground(Color.LIGHT_GRAY);
					table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							selectedRow = table.getSelectedRow();
							if(selectedRow>-1) {
								btnmModificar.setEnabled(true);
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
		lblFiltrar.setBounds(590, 25, 46, 14);
		contentPanel.add(lblFiltrar);
		
		textMatricula = new JTextFieldLimit(8);
		textMatricula.setBounds(645, 22, 65, 20);
		contentPanel.add(textMatricula);
		textMatricula.setColumns(10);
		
		JLabel lblCodAsignatura = new JLabel("M\u00E1tricula:");
		lblCodAsignatura.setBounds(645, 1, 111, 14);
		contentPanel.add(lblCodAsignatura);
		{
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.setBackground(Color.LIGHT_GRAY);
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						loadListadoFiltrado(academico,textMatricula.getText());
						btnmModificar.setEnabled(false);
						EliminarButton.setEnabled(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Error al cargar la asignatura", "Información", JOptionPane.ERROR_MESSAGE);
						btnmModificar.setEnabled(false);
						EliminarButton.setEnabled(false);
						e1.printStackTrace();
					}
				}
			});
			btnBuscar.setBounds(757, 21, 77, 23);
			contentPanel.add(btnBuscar);
		}
		{
			JButton btnRecargar = new JButton("Recargar");
			btnRecargar.setBackground(Color.LIGHT_GRAY);
			btnRecargar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						loadListado(academico);
						btnmModificar.setEnabled(false);
						EliminarButton.setEnabled(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						btnmModificar.setEnabled(false);
						EliminarButton.setEnabled(false);
						JOptionPane.showMessageDialog(null, "Error al cargar las asignaturas", "Información", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			});
			btnRecargar.setBounds(840, 20, 89, 23);
			contentPanel.add(btnRecargar);
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
						AgregarEstudiante agregarEstudiante = new AgregarEstudiante(academico);
						agregarEstudiante.setModal(true);
						agregarEstudiante.setVisible(true);
						
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
				btnmModificar.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						ModificarEstudiante modificarEstudiante = new ModificarEstudiante(academico,(String) table.getModel().getValueAt(selectedRow, 0),
								(String) table.getModel().getValueAt(selectedRow, 1),(String) table.getModel().getValueAt(selectedRow, 2),
										(String) table.getModel().getValueAt(selectedRow, 3),(String) table.getModel().getValueAt(selectedRow, 4),
										(String) table.getModel().getValueAt(selectedRow, 5),(String) table.getModel().getValueAt(selectedRow, 6),
										(String) table.getModel().getValueAt(selectedRow, 7),(String) table.getModel().getValueAt(selectedRow, 8));
						modificarEstudiante.setModal(true);
						modificarEstudiante.setVisible(true);
						
						try {
							loadListado(academico);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Error al cargar las asignaturas", "Información", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						btnmModificar.setEnabled(false);
						EliminarButton.setEnabled(false);
					}
				});
				btnmModificar.setEnabled(false);
				buttonPane.add(btnmModificar);
			}
			{
				EliminarButton = new JButton("Eliminar");
				EliminarButton.setBackground(Color.LIGHT_GRAY);
				EliminarButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int option = JOptionPane.showConfirmDialog(null, "¿Esta seguro de que desea eliminar el estudiante? ", "Eliminar", JOptionPane.WARNING_MESSAGE);
						if(option == 0) {
							try {
								ListadoEstudiante.this.academico.getEstudiante().delete((String) table.getModel().getValueAt(selectedRow, 0));
								JOptionPane.showMessageDialog(null, "Operación Realizada", "Información", JOptionPane.INFORMATION_MESSAGE);

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "Error al eliminar el estudiante.", "Información", JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							}
						}
						
						try {
							loadListado(academico);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Error al cargar las asignaturas", "Información", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						
						btnmModificar.setEnabled(false);
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
			JOptionPane.showMessageDialog(null, "Error al cargar los estudiantes", "Información", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		//System.out.println(table.getModel().getValueAt(1, 1));
	}
	
	public static void loadListado(Academico academico) throws SQLException {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		ArrayList<ArrayList<String> > Datatabla = academico.getEstudiante().select();
		
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
				model.addRow(fila);
			}
		}
	}
	
	public static void loadListadoFiltrado(Academico academico, String matricula) throws SQLException {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		ArrayList<ArrayList<String> > Datatabla = academico.getEstudiante().selectWhere(matricula);
		
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

