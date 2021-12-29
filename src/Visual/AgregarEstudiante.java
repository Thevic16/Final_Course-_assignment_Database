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

public class AgregarEstudiante extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JPanel buttonPane;
	private JButton btnAgregar;
	private Academico academico;
	private JTextFieldLimit textMatricula;
	private JTextFieldLimit textPrimerNombre;
	private JTextFieldLimit textSegundoNombre;
	private JTextFieldLimit textPrimerApellido;
	private JTextFieldLimit textSegundoApellido;
	private JTextFieldLimit textCarrera;
	private JTextFieldLimit textPago;
	private JTextFieldLimit textNacionalidad;
	private JTextFieldLimit textDireccion;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Academico academico =  Academico.getInstance("usuario1", "12345", "jdbc:sqlserver://LAPTOP-VICTORGO;databaseName=academica");
			AgregarEstudiante dialog = new AgregarEstudiante(academico);
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
	public AgregarEstudiante(Academico academico) {
		this.academico = academico;
		setResizable(false);
		setLocationRelativeTo(null);		
		setTitle("Agregar estudiante");
		setBounds(100, 100, 438, 376);
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
				JLabel lblMatricula = new JLabel("M\u00E1tricula:");
				lblMatricula.setBounds(10, 29, 112, 14);
				panel.add(lblMatricula);
			}
			{
				JLabel lblPrimerNombre = new JLabel("Primer nombre:");
				lblPrimerNombre.setBounds(10, 79, 112, 14);
				panel.add(lblPrimerNombre);
			}
			{
				textMatricula = new JTextFieldLimit(8);
				textMatricula.setBounds(10, 48, 60, 20);
				panel.add(textMatricula);
				textMatricula.setColumns(10);
			}
			{
				textPrimerNombre = new JTextFieldLimit(30);
				textPrimerNombre.setBounds(10, 97, 173, 20);
				panel.add(textPrimerNombre);
				textPrimerNombre.setColumns(10);
			}
			{
				textSegundoNombre = new JTextFieldLimit(35);
				textSegundoNombre.setColumns(10);
				textSegundoNombre.setBounds(10, 146, 173, 20);
				panel.add(textSegundoNombre);
			}
			{
				JLabel lblSegundoNombre = new JLabel("Segundo nombre:");
				lblSegundoNombre.setBounds(10, 128, 130, 14);
				panel.add(lblSegundoNombre);
			}
			{
				textPrimerApellido = new JTextFieldLimit(25);
				textPrimerApellido.setColumns(10);
				textPrimerApellido.setBounds(10, 195, 173, 20);
				panel.add(textPrimerApellido);
			}
			{
				JLabel lblPrimerApellido = new JLabel("Primer apellido:");
				lblPrimerApellido.setBounds(10, 177, 147, 14);
				panel.add(lblPrimerApellido);
			}
			{
				textSegundoApellido = new JTextFieldLimit(25);
				textSegundoApellido.setColumns(10);
				textSegundoApellido.setBounds(10, 244, 173, 20);
				panel.add(textSegundoApellido);
			}
			{
				JLabel lblSegundoApellido = new JLabel("Segundo apellido:");
				lblSegundoApellido.setBounds(10, 226, 112, 14);
				panel.add(lblSegundoApellido);
			}
			{
				textCarrera = new JTextFieldLimit(4);
				textCarrera.setColumns(10);
				textCarrera.setBounds(231, 47, 40, 20);
				panel.add(textCarrera);
			}
			{
				JLabel lblCarrera = new JLabel("Carrera:");
				lblCarrera.setBounds(231, 29, 74, 14);
				panel.add(lblCarrera);
			}
			{
				textPago = new JTextFieldLimit(3);
				textPago.setColumns(10);
				textPago.setBounds(231, 97, 30, 20);
				panel.add(textPago);
			}
			{
				JLabel lblPago = new JLabel("Pago:");
				lblPago.setBounds(231, 79, 74, 14);
				panel.add(lblPago);
			}
			{
				textNacionalidad = new JTextFieldLimit(3);
				textNacionalidad.setColumns(10);
				textNacionalidad.setBounds(231, 146, 30, 20);
				panel.add(textNacionalidad);
			}
			{
				JLabel lblNacionalidad = new JLabel("Nacionalidad:");
				lblNacionalidad.setBounds(231, 128, 96, 14);
				panel.add(lblNacionalidad);
			}
			{
				textDireccion = new JTextFieldLimit(256);
				textDireccion.setColumns(10);
				textDireccion.setBounds(231, 195, 173, 20);
				panel.add(textDireccion);
			}
			{
				JLabel lblDireccion = new JLabel("Direcci\u00F3n:");
				lblDireccion.setBounds(231, 177, 74, 14);
				panel.add(lblDireccion);
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
							AgregarEstudiante.this.academico.getEstudiante().insert(textMatricula.getText(), textPrimerNombre.getText(),textSegundoNombre.getText(),
									textPrimerApellido.getText(),textSegundoApellido.getText(),textCarrera.getText(),textPago.getText(),
									textNacionalidad.getText(),textDireccion.getText());
							JOptionPane.showMessageDialog(null, "Estudiante agregado", "Información", JOptionPane.INFORMATION_MESSAGE);
							Clean();
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Error al agregar el estudiante", "Información", JOptionPane.ERROR_MESSAGE);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, "Error al agregar el estudiante", "Información", JOptionPane.ERROR_MESSAGE);
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
		textMatricula.setText(" ");
		textPrimerNombre.setText(" ");
		textSegundoNombre.setText(" ");
		textPrimerApellido.setText(" ");
		textSegundoApellido.setText(" ");
		textCarrera.setText(" ");
		textPago.setText(" ");
		textNacionalidad.setText(" ");
		textDireccion.setText(" ");
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
