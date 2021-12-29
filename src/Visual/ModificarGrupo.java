package Visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;


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

public class ModificarGrupo extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JSpinner spnCupoGrupo;
	private JButton btnModificar;
	private Academico academico;
	private JComboBox comboBoxCodPeriAcad;
	private JComboBox comboBoxCodAsig;
	private JTextField textNumGrupo;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Academico academico =  Academico.getInstance("usuario1", "12345", "jdbc:sqlserver://LAPTOP-VICTORGO;databaseName=academica");
			ModificarGrupo dialog = new ModificarGrupo(academico,"201620172","ISC300T","009");
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
	public ModificarGrupo(Academico academico, String codPeriodoAcad,String codAsignatura,String numGrupo) {
		this.academico = academico;
		setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Modificar grupo");
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
				lblCodigoPeriodoAcas.setBounds(10, 29, 112, 14);
				panel.add(lblCodigoPeriodoAcas);
			}
			{
				JLabel lblNumGrupo = new JLabel("N\u00FAmero grupo:");
				lblNumGrupo.setBounds(10, 128, 106, 14);
				panel.add(lblNumGrupo);
			}
			{
				spnCupoGrupo = new JSpinner();
				spnCupoGrupo.setModel(new SpinnerNumberModel(1, 1, 31, 1));
				spnCupoGrupo.setBounds(248, 48, 74, 20);
				panel.add(spnCupoGrupo);
			}
			{
				JLabel lblCupoGrupo = new JLabel("Cupo grupo:");
				lblCupoGrupo.setBounds(248, 29, 97, 14);
				panel.add(lblCupoGrupo);
			}
			{
				JLabel lblCodAsignatura = new JLabel("C\u00F3digo asignatura:");
				lblCodAsignatura.setBounds(10, 79, 127, 14);
				panel.add(lblCodAsignatura);
			}
			
			comboBoxCodPeriAcad = new JComboBox();
			comboBoxCodPeriAcad.addItem(codPeriodoAcad);
			comboBoxCodPeriAcad.setBounds(10, 47, 138, 22);
			panel.add(comboBoxCodPeriAcad);
			
			comboBoxCodAsig = new JComboBox();
			comboBoxCodAsig.addItem(codAsignatura);
			comboBoxCodAsig.setBounds(10, 96, 138, 22);
			panel.add(comboBoxCodAsig);
			{
				textNumGrupo = new JTextField();
				textNumGrupo.setText(numGrupo);
				textNumGrupo.setEditable(false);
				textNumGrupo.setBounds(10, 144, 86, 20);
				panel.add(textNumGrupo);
				textNumGrupo.setColumns(10);
			}
		}
		{
			buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.setBackground(Color.LIGHT_GRAY);
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							ModificarGrupo.this.academico.getGrupo().update((String)comboBoxCodPeriAcad.getSelectedItem(),(String)comboBoxCodAsig.getSelectedItem(),textNumGrupo.getText(), Integer.valueOf( spnCupoGrupo.getValue().toString()));
							JOptionPane.showMessageDialog(null, "Grupo modificado", "Información", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error al modificar el grupo", "Información", JOptionPane.ERROR_MESSAGE);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Error al modificar el grupo", "Información", JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}

					}

				});
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
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

		
	}
	
	private void Clean() {
		// TODO Auto-generated method stub
		spnCupoGrupo.setValue(Integer.valueOf("1"));
	}
	

}