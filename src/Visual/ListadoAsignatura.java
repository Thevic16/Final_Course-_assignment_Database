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

public class ListadoAsignatura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] fila;
	private Academico academico;
	private JTextFieldLimit textCodAsignatura;
	private JButton btnmModificar;
	private JButton EliminarButton;
	
	private int selectedRow = -1; // parte de seleccionar

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Academico academico =  Academico.getInstance("usuario1", "12345", "jdbc:sqlserver://LAPTOP-VICTORGO;databaseName=academica");
			ListadoAsignatura dialog = new ListadoAsignatura(academico);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListadoAsignatura(Academico academico) {
		this.academico = academico;
		setTitle("Listado asignatura(s)");
		setBounds(100, 100, 668, 425);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(5, 55, 637, 287);
			contentPanel.add(panel);
			panel.setLayout(new BorderLayout(0, 0));
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				panel.add(scrollPane);
				{
					String[] columnas = {"C�digo asignatura","Nombre","Creditos","Hora te�ricas","Hora pr�cticas"};
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
		
		textCodAsignatura = new JTextFieldLimit(7);
		textCodAsignatura.setBounds(358, 21, 60, 20);
		contentPanel.add(textCodAsignatura);
		textCodAsignatura.setColumns(10);
		
		JLabel lblCodAsignatura = new JLabel("C\u00F3digo asignatura:");
		lblCodAsignatura.setBounds(246, 24, 111, 14);
		contentPanel.add(lblCodAsignatura);
		{
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.setBackground(Color.LIGHT_GRAY);
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						loadListadoFiltrado(academico,textCodAsignatura.getText());
						btnmModificar.setEnabled(false);
						EliminarButton.setEnabled(false);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Error al cargar la asignatura", "Informaci�n", JOptionPane.ERROR_MESSAGE);
						btnmModificar.setEnabled(false);
						EliminarButton.setEnabled(false);
						e1.printStackTrace();
					}
				}
			});
			btnBuscar.setBounds(470, 20, 77, 23);
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
						JOptionPane.showMessageDialog(null, "Error al cargar las asignaturas", "Informaci�n", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			});
			btnRecargar.setBounds(553, 19, 89, 23);
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
						AgregarAsignatura agregarAsignatura = new AgregarAsignatura(academico);
						agregarAsignatura.setModal(true);
						agregarAsignatura.setVisible(true);
						
						try {
							loadListado(academico);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Error al cargar las asignaturas", "Informaci�n", JOptionPane.ERROR_MESSAGE);
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
						ModificarAsignatura modificarAsignatura = new ModificarAsignatura(academico,(String) table.getModel().getValueAt(selectedRow, 0), (String) table.getModel().getValueAt(selectedRow, 1));
						modificarAsignatura.setModal(true);
						modificarAsignatura.setVisible(true);
						
						try {
							loadListado(academico);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Error al cargar las asignaturas", "Informaci�n", JOptionPane.ERROR_MESSAGE);
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
				EliminarButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				EliminarButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int option = JOptionPane.showConfirmDialog(null, "�Esta seguro de que desea eliminar la asignatura? ", "Eliminar", JOptionPane.WARNING_MESSAGE);
						if(option == 0) {
							try {
								ListadoAsignatura.this.academico.getAsignatura().delete((String) table.getModel().getValueAt(selectedRow, 0));
								JOptionPane.showMessageDialog(null, "Operaci�n Realizada", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "Error al eliminar la asignatura", "Informaci�n", JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							}
						}
						try {
							loadListado(academico);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Error al cargar las asignaturas", "Informaci�n", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, "Error al cargar las asignaturas", "Informaci�n", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		//System.out.println(table.getModel().getValueAt(1, 1));
	}
	
	public static void loadListado(Academico academico) throws SQLException {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		ArrayList<ArrayList<String> > Datatabla = academico.getAsignatura().select();
		
		for (int i = 0; i < Datatabla.size(); i++) {
			for (int j = 0; j < 1; j++) {
				fila[0] = Datatabla.get(i).get(j);
				fila[1] = Datatabla.get(i).get(j+1);
				fila[2] = Datatabla.get(i).get(j+2);
				fila[3] = Datatabla.get(i).get(j+3);
				fila[4] = Datatabla.get(i).get(j+4);
				model.addRow(fila);
			}
		}
	}
	
	public static void loadListadoFiltrado(Academico academico, String codAsignatura) throws SQLException {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		ArrayList<ArrayList<String> > Datatabla = academico.getAsignatura().selectWhere(codAsignatura);
		
		for (int i = 0; i < Datatabla.size(); i++) {
			for (int j = 0; j < 1; j++) {
				fila[0] = Datatabla.get(i).get(j);
				fila[1] = Datatabla.get(i).get(j+1);
				fila[2] = Datatabla.get(i).get(j+2);
				fila[3] = Datatabla.get(i).get(j+3);
				fila[4] = Datatabla.get(i).get(j+4);
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
