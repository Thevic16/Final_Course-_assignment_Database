package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import logico.Academico;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class AgregarInscripcion extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JButton btnAgregar;
	private Academico academico;
	private JComboBox comboBoxCodPeriAcad;
	private JComboBox comboBoxCodAsig;
	private JTextFieldLimit textNumGrupo;
	private JTextFieldLimit textMatricula;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Academico academico =  Academico.getInstance("usuario1", "12345", "jdbc:sqlserver://LAPTOP-VICTORGO;databaseName=academica");
			AgregarInscripcion dialog = new AgregarInscripcion(academico);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @param miBanco 
	 */
	public AgregarInscripcion(Academico academico) {
		this.academico = academico;
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Agregar inscripci\u00F3n");
		setBounds(100, 100, 379, 262);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(Color.WHITE);

			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Informaci\u00F3n requerida", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblCodigoPeriodoAcas = new JLabel("C\u00F3digo Periodo Acad.:");
				lblCodigoPeriodoAcas.setBounds(10, 29, 138, 14);
				panel.add(lblCodigoPeriodoAcas);
			}
			{
				JLabel lblNumGrupo = new JLabel("N\u00FAmero grupo:");
				lblNumGrupo.setBounds(10, 128, 106, 14);
				panel.add(lblNumGrupo);
			}
			{
				JLabel lblMatricula = new JLabel("M\u00E1tricula:");
				lblMatricula.setBounds(241, 29, 97, 14);
				panel.add(lblMatricula);
			}
			{
				JLabel lblCodAsignatura = new JLabel("C\u00F3digo asignatura:");
				lblCodAsignatura.setBounds(10, 79, 127, 14);
				panel.add(lblCodAsignatura);
			}
			
			comboBoxCodPeriAcad = new JComboBox();
			comboBoxCodPeriAcad.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(comboBoxCodAsig.getSelectedIndex() != 0 && comboBoxCodPeriAcad.getSelectedIndex() != 0 ) {
						btnAgregar.setEnabled(true);
					}
					else {
						btnAgregar.setEnabled(false);
					}
				}
			});

			comboBoxCodPeriAcad.setBounds(10, 47, 138, 22);
			panel.add(comboBoxCodPeriAcad);
			
			comboBoxCodAsig = new JComboBox();
			comboBoxCodAsig.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(comboBoxCodAsig.getSelectedIndex() != 0 && comboBoxCodPeriAcad.getSelectedIndex() != 0 ) {
						btnAgregar.setEnabled(true);
					}
					else {
						btnAgregar.setEnabled(false);
					}
				}
			});

			comboBoxCodAsig.setBounds(10, 96, 138, 22);
			panel.add(comboBoxCodAsig);
			{
				textNumGrupo = new JTextFieldLimit(3);
				textNumGrupo.setBounds(10, 144, 30, 20);
				panel.add(textNumGrupo);
				textNumGrupo.setColumns(10);
			}
			{
				textMatricula = new JTextFieldLimit(8);
				textMatricula.setBounds(241, 48, 65, 20);
				panel.add(textMatricula);
				textMatricula.setColumns(10);
			}
		}
		{
			buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAgregar = new JButton("Agregar");
				btnAgregar.setBackground(Color.LIGHT_GRAY);
				btnAgregar.setEnabled(false);
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							AgregarInscripcion.this.academico.getInscipcion().insert((String)comboBoxCodPeriAcad.getSelectedItem(),textMatricula.getText(),(String)comboBoxCodAsig.getSelectedItem(),textNumGrupo.getText());
							JOptionPane.showMessageDialog(null, "Inscripción agregada", "Información", JOptionPane.INFORMATION_MESSAGE);
							Clean();
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error al agregar la inscripción", "Información", JOptionPane.ERROR_MESSAGE);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Error al agregar la inscripción", "Información", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}

					}

				});
				btnAgregar.setActionCommand("OK");
				buttonPane.add(btnAgregar);
				getRootPane().setDefaultButton(btnAgregar);
			}
			{
				JButton btnCancelar = new JButton("Cancelar");
				btnCancelar.setBackground(Color.LIGHT_GRAY);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btnCancelar.setActionCommand("Cancel");
				buttonPane.add(btnCancelar);
			}
		}
		try {
			loadCodigoAsig(academico);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al agregar los codigos asignatura.", "Información", JOptionPane.ERROR_MESSAGE);

		}
		
		try {
			loadPeriodoAcad(academico);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al agregar los codigos periodo academico.", "Información", JOptionPane.ERROR_MESSAGE);

		}
		
	}
	
	private void Clean() {
		// TODO Auto-generated method stub
		textNumGrupo.setText("");
		textMatricula.setText(" ");
		comboBoxCodPeriAcad.setSelectedIndex(0);
		comboBoxCodAsig.setSelectedIndex(0);
	}
	
	private void loadPeriodoAcad(Academico academico) throws SQLException {
		ArrayList<ArrayList<String> > Datatabla = academico.getPeriodoAcademico().select();
		comboBoxCodPeriAcad.removeAllItems();
		for (int i = 0; i < Datatabla.size(); i++) {
			comboBoxCodPeriAcad.addItem(Datatabla.get(i).get(0));
		}
		comboBoxCodPeriAcad.insertItemAt(new String("<Seleccione>"),0);
		comboBoxCodPeriAcad.setSelectedIndex(0);
	}
	
	private void loadCodigoAsig(Academico academico) throws SQLException {
		ArrayList<ArrayList<String> > Datatabla = academico.getAsignatura().select();
		comboBoxCodAsig.removeAllItems();
		for (int i = 0; i < Datatabla.size(); i++) {
			comboBoxCodAsig.addItem(Datatabla.get(i).get(0));
		}
		comboBoxCodAsig.insertItemAt(new String("<Seleccione>"),0);
		comboBoxCodAsig.setSelectedIndex(0);
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
