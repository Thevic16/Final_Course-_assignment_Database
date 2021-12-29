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
import java.awt.event.MouseEvent;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class AgregarAsignatura extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JSpinner spnCreditos;
	private JSpinner spnHoraTeoricas;
	private JSpinner spnHoraPractica;
	private JButton btnAgregar;
	private Academico academico;
	private JTextFieldLimit textCodAsignatura;
	private JTextFieldLimit textNombre;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Academico academico =  Academico.getInstance("usuario1", "12345", "jdbc:sqlserver://LAPTOP-VICTORGO;databaseName=academica");
			AgregarAsignatura dialog = new AgregarAsignatura(academico);
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
	public AgregarAsignatura(Academico academico) {
		this.academico = academico;
		setResizable(false);
		setLocationRelativeTo(null);		
		setTitle("Agregar asignatura");
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
				JLabel lblCodigoAsignatura = new JLabel("C\u00F3digo asignatura:");
				lblCodigoAsignatura.setBounds(10, 29, 112, 14);
				panel.add(lblCodigoAsignatura);
			}
			{
				JLabel lblCreditos = new JLabel("Creditos:");
				lblCreditos.setBounds(10, 128, 106, 14);
				panel.add(lblCreditos);
			}
			{
				spnHoraTeoricas = new JSpinner();
				spnHoraTeoricas.setModel(new SpinnerNumberModel(1, 1, 31, 1));
				spnHoraTeoricas.setBounds(248, 48, 45, 20);
				panel.add(spnHoraTeoricas);
			}
			{
				JLabel lblHorasTeoricas = new JLabel("Horas te\u00F3ricas:");
				lblHorasTeoricas.setBounds(248, 29, 97, 14);
				panel.add(lblHorasTeoricas);
			}
			{
				spnHoraPractica = new JSpinner();
				spnHoraPractica.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
				spnHoraPractica.setBounds(248, 97, 45, 20);
				panel.add(spnHoraPractica);
			}
			{
				JLabel lblNombre = new JLabel("Nombre:");
				lblNombre.setBounds(10, 79, 74, 14);
				panel.add(lblNombre);
			}
			{
				JLabel lblSaldoIniciar = new JLabel("Horas pr\u00E1cticas:");
				lblSaldoIniciar.setBounds(248, 79, 97, 14);
				panel.add(lblSaldoIniciar);
			}
			{
				spnCreditos = new JSpinner();
				spnCreditos.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
				spnCreditos.setBounds(10, 145, 45, 20);
				panel.add(spnCreditos);
			}
			{
				textCodAsignatura = new JTextFieldLimit(7);
				textCodAsignatura.setBounds(10, 48, 59, 20);
				panel.add(textCodAsignatura);
				textCodAsignatura.setColumns(10);
			}
			{
				textNombre = new JTextFieldLimit(60);
				textNombre.setBounds(10, 97, 173, 20);
				panel.add(textNombre);
				textNombre.setColumns(10);
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
				btnAgregar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						try {
							AgregarAsignatura.this.academico.getAsignatura().insert(textCodAsignatura.getText(), textNombre.getText(), Integer.valueOf( spnCreditos.getValue().toString()), Integer.valueOf( spnHoraTeoricas.getValue().toString()), Integer.valueOf( spnHoraPractica.getValue().toString()));
							JOptionPane.showMessageDialog(null, "Asignatura agregada", "Información", JOptionPane.INFORMATION_MESSAGE);
							Clean();
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error al agregar la asignatura", "Información", JOptionPane.ERROR_MESSAGE);
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Error al agregar la asignatura", "Información", JOptionPane.ERROR_MESSAGE);
						
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
	}
	
	private void Clean() {
		// TODO Auto-generated method stub
		spnCreditos.setValue(Integer.valueOf("1"));
		spnHoraTeoricas.setValue(Integer.valueOf("1"));
		spnHoraPractica.setValue(Integer.valueOf("1"));
		textCodAsignatura.setText(" ");
		textNombre.setText(" ");
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



