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

public class InformeInscripcion extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static DefaultTableModel model;
	private static Object[] fila;
	private Academico academico;
	
	private int selectedRow = -1; // parte de seleccionar
	private JTextFieldLimit textMatricula;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Academico academico =  Academico.getInstance("usuario1", "12345", "jdbc:sqlserver://LAPTOP-VICTORGO;databaseName=academica");
			InformeInscripcion dialog = new InformeInscripcion(academico);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InformeInscripcion(Academico academico) {
		this.academico = academico;
		setTitle("Informe Inscripci\u00F3n(es)");
		setBounds(100, 100, 748, 425);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
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
					String[] columnas = {"Mátricula","Nombre estudiante","Código periodo Aca.","Código asignatura","Número grupo","Horario"};
					model = new DefaultTableModel();
					model.setColumnIdentifiers(columnas);
					table = new JTable(){
				        private static final long serialVersionUID = 1L;

				        public boolean isCellEditable(int row, int column) {                
				                return false;               
				        };
				    };
				    
					table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
					table.setBackground(Color.LIGHT_GRAY);
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							selectedRow = table.getSelectedRow();
						}
					});
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setModel(model);
					scrollPane.setViewportView(table);
				}
			}
			
			
		}
		
		JLabel lblFiltrar = new JLabel("Seleccionar:");
		lblFiltrar.setBounds(437, 25, 77, 14);
		contentPanel.add(lblFiltrar);
		{
			JButton btnBuscar = new JButton("Buscar");
			btnBuscar.setBackground(Color.LIGHT_GRAY);
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						loadListadoFiltrado(academico,textMatricula.getText());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Error al cargar la asignatura", "Información", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				}
			});
			btnBuscar.setBounds(645, 20, 77, 23);
			contentPanel.add(btnBuscar);
		}
		{
			textMatricula = new JTextFieldLimit(8);
			textMatricula.setBounds(524, 22, 65, 20);
			contentPanel.add(textMatricula);
			textMatricula.setColumns(10);
		}
		{
			JLabel lblMatricula = new JLabel("M\u00E1tricula estudiante:");
			lblMatricula.setBounds(512, 0, 125, 14);
			contentPanel.add(lblMatricula);
		}
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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

		
		//System.out.println(table.getModel().getValueAt(1, 1));
	}
	
	
	public static void loadListadoFiltrado(Academico academico,String matricula) throws SQLException {
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		ArrayList<ArrayList<String> > Datatabla = academico.InformeInscEst(matricula);
		for (int i = 0; i < Datatabla.size(); i++) {
			for (int j = 0; j < 1; j++) {
				fila[0] = Datatabla.get(i).get(j);
				fila[1] = Datatabla.get(i).get(j+1);
				fila[2] = Datatabla.get(i).get(j+2);
				fila[3] = Datatabla.get(i).get(j+3);
				fila[4] = Datatabla.get(i).get(j+4);
				fila[5] = Datatabla.get(i).get(j+5);
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
