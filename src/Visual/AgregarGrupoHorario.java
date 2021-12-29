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

public class AgregarGrupoHorario extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JButton btnAgregar;
	private Academico academico;
	private JComboBox comboBoxCodPeriAcad;
	private JComboBox comboBoxCodAsig;
	private JTextFieldLimit textNumGrupo;
	private JComboBox comboBoxDia;
	private JSpinner spnHoraInicialHora;
	private JSpinner spnHoraInicialMinuto;
	private JSpinner spnHoraInicialSegu;
	private JSpinner spnHoraFinallHora;
	private JSpinner spnHoraFinalMinuto;
	private JSpinner spnHoraFinalSegund;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Academico academico =  Academico.getInstance("usuario1", "12345", "jdbc:sqlserver://LAPTOP-VICTORGO;databaseName=academica");
			AgregarGrupoHorario dialog = new AgregarGrupoHorario(academico);
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
	public AgregarGrupoHorario(Academico academico) {
		this.academico = academico;
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Agregar grupo horario");
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
				lblCodigoPeriodoAcas.setBounds(10, 29, 151, 14);
				panel.add(lblCodigoPeriodoAcas);
			}
			{
				JLabel lblNumGrupo = new JLabel("N\u00FAmero grupo:");
				lblNumGrupo.setBounds(10, 128, 106, 14);
				panel.add(lblNumGrupo);
			}
			{
				JLabel lblDia = new JLabel("D\u00EDa:");
				lblDia.setBounds(218, 29, 97, 14);
				panel.add(lblDia);
			}
			{
				JLabel lblCodAsignatura = new JLabel("C\u00F3digo asignatura:");
				lblCodAsignatura.setBounds(10, 79, 127, 14);
				panel.add(lblCodAsignatura);
			}
			
			comboBoxCodPeriAcad = new JComboBox();
			comboBoxCodPeriAcad.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if(comboBoxCodAsig.getSelectedIndex() != 0 && comboBoxCodPeriAcad.getSelectedIndex() != 0 && comboBoxDia.getSelectedIndex() != 0 ) {
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
					if(comboBoxCodAsig.getSelectedIndex() != 0 && comboBoxCodPeriAcad.getSelectedIndex() != 0 && comboBoxDia.getSelectedIndex() != 0 ) {
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
				textNumGrupo.setBounds(10, 144, 32, 20);
				panel.add(textNumGrupo);
				textNumGrupo.setColumns(10);
			}
			{
				comboBoxDia = new JComboBox();
				comboBoxDia.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						if(comboBoxCodAsig.getSelectedIndex() != 0 && comboBoxCodPeriAcad.getSelectedIndex() != 0 && comboBoxDia.getSelectedIndex() != 0 ) {
							btnAgregar.setEnabled(true);
						}
						else {
							btnAgregar.setEnabled(false);
						}
					}
				});
				comboBoxDia.setBounds(218, 47, 106, 22);
				panel.add(comboBoxDia);
			}
			{
				JLabel lblHoraInicial = new JLabel("Hora inicial:");
				lblHoraInicial.setBounds(218, 79, 97, 14);
				panel.add(lblHoraInicial);
			}
			{
				spnHoraInicialHora = new JSpinner();
				spnHoraInicialHora.setModel(new SpinnerNumberModel(0, 0, 24, 1));
				spnHoraInicialHora.setBounds(218, 97, 41, 20);
				panel.add(spnHoraInicialHora);
			}
			{
				JLabel lblNewLabel = new JLabel(":");
				lblNewLabel.setBounds(261, 100, 14, 14);
				panel.add(lblNewLabel);
			}
			{
				spnHoraInicialMinuto = new JSpinner();
				spnHoraInicialMinuto.setModel(new SpinnerNumberModel(0, 0, 60, 1));
				spnHoraInicialMinuto.setBounds(271, 97, 36, 20);
				panel.add(spnHoraInicialMinuto);
			}
			{
				spnHoraInicialSegu = new JSpinner();
				spnHoraInicialSegu.setModel(new SpinnerNumberModel(0, 0, 60, 1));
				spnHoraInicialSegu.setBounds(317, 97, 36, 20);
				panel.add(spnHoraInicialSegu);
			}
			{
				JLabel lblHoraFinal = new JLabel("Hora final:");
				lblHoraFinal.setBounds(218, 128, 97, 14);
				panel.add(lblHoraFinal);
			}
			{
				spnHoraFinallHora = new JSpinner();
				spnHoraFinallHora.setModel(new SpinnerNumberModel(0, 0, 24, 1));
				spnHoraFinallHora.setBounds(218, 146, 41, 20);
				panel.add(spnHoraFinallHora);
			}
			{
				JLabel lblNewLabel = new JLabel(":");
				lblNewLabel.setBounds(261, 149, 14, 14);
				panel.add(lblNewLabel);
			}
			{
				spnHoraFinalMinuto = new JSpinner();
				spnHoraFinalMinuto.setModel(new SpinnerNumberModel(0, 0, 60, 1));
				spnHoraFinalMinuto.setBounds(271, 146, 36, 20);
				panel.add(spnHoraFinalMinuto);
			}
			{
				JLabel spnHoraInicialSegu = new JLabel(":");
				spnHoraInicialSegu.setBounds(311, 147, 8, 14);
				panel.add(spnHoraInicialSegu);
			}
			{
				spnHoraFinalSegund = new JSpinner();
				spnHoraFinalSegund.setModel(new SpinnerNumberModel(0, 0, 60, 1));
				spnHoraFinalSegund.setBounds(317, 144, 36, 20);
				panel.add(spnHoraFinalSegund);
			}
			{
				JLabel lblNewLabel_1 = new JLabel(":");
				lblNewLabel_1.setBounds(311, 100, 4, 14);
				panel.add(lblNewLabel_1);
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
							AgregarGrupoHorario.this.academico.getGrupoHorario().insert((String)comboBoxCodPeriAcad.getSelectedItem(),(String)comboBoxCodAsig.getSelectedItem(),textNumGrupo.getText(),
									Integer.valueOf((String) comboBoxDia.getSelectedItem()),spnHoraInicialHora.getValue().toString()+":"+spnHoraInicialMinuto.getValue().toString()+":"+spnHoraInicialSegu.getValue().toString(),
									spnHoraFinallHora.getValue().toString()+":"+spnHoraFinalMinuto.getValue().toString()+":"+spnHoraFinalSegund.getValue().toString());
							JOptionPane.showMessageDialog(null, "Grupo horario agregado", "Información", JOptionPane.INFORMATION_MESSAGE);
							Clean();
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error al agregar el grupo horario", "Información", JOptionPane.ERROR_MESSAGE);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Error al agregar el grupo horario", "Información", JOptionPane.ERROR_MESSAGE);
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
		
		try {
			loadDia(academico);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al agregar los dias de la semana.", "Información", JOptionPane.ERROR_MESSAGE);

		}
		
	}
	
	private void Clean() {
		// TODO Auto-generated method stub
		textNumGrupo.setText("");
		comboBoxDia.setSelectedIndex(0);
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
	
	private void loadDia(Academico academico) throws SQLException {
		ArrayList<ArrayList<String> > Datatabla = academico.getDiaSemana().select();
		comboBoxDia.removeAllItems();
		for (int i = 0; i < Datatabla.size(); i++) {
			comboBoxDia.addItem(Datatabla.get(i).get(0));
		}
		comboBoxDia.insertItemAt(new String("<Seleccione>"),0);
		comboBoxDia.setSelectedIndex(0);
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
