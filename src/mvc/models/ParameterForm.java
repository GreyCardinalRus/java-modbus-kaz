package mvc.models;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//import org.omg.CORBA.Object;

public class ParameterForm extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2660363798598403037L;
	private JLabel label_Access;
	// private JPanel panel;
	ModbusParameter modbusParameter = null;
	// private JTextField textField;
	private Component comboBox;

	/**
	 * Create the panel.
	 */
	public ParameterForm(ModbusParameter mbp, JPanel panelParient) {
		// BooleanParameterForm( mbp, panelParient);
		// StringParameterForm( mbp, panelParient);
		if (mbp.getFieldType().equals("Integer")) {
			IntegerParameterForm(mbp, panelParient);
		}
		if (mbp.getFieldType().equals("Boolean")) {
			BooleanParameterForm(mbp, panelParient);
		}
		if (mbp.getFieldType().equals("String")
				|| mbp.getFieldType().equals("Integer")
				&& !mbp.ListValuesEmpty()) {
			StringParameterForm(mbp, panelParient);
		}
	}

	private void IntegerParameterForm(ModbusParameter mbp, JPanel panelParient) {

		modbusParameter = mbp;
		label_Access = new JLabel(mbp.getAccess());
		label_Access.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel label_ID = new JLabel("P." + mbp.getId());

		JLabel lblNameparameterVery = new JLabel(mbp.getDescription());

		JSpinner spinner_Value = new JSpinner();
		spinner_Value.setModel(new SpinnerNumberModel(22, 0, 100, 1));

		JLabel label_UoM = new JLabel(mbp.getUoM());

		JButton button_default = new JButton("default");

		JSlider slider_Value = new JSlider();
		slider_Value.setValue(23);

		JSeparator separator = new JSeparator();
		GroupLayout groupLayout = new GroupLayout(this);

		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(11)
										.addComponent(label_Access)
										.addGap(12)
										.addComponent(label_ID)
										.addGap(10)
										.addComponent(lblNameparameterVery,
												GroupLayout.DEFAULT_SIZE, 94,
												Short.MAX_VALUE)
										.addGap(3)
										.addComponent(spinner_Value,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addGap(9).addComponent(label_UoM)
										.addGap(10)
										.addComponent(button_default)
										.addGap(48))
						.addGroup(
								Alignment.TRAILING,
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																Alignment.LEADING,
																groupLayout
																		.createSequentialGroup()
																		.addGap(31)
																		.addComponent(
																				separator,
																				GroupLayout.DEFAULT_SIZE,
																				448,
																				Short.MAX_VALUE))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(9)
																		.addComponent(
																				slider_Value,
																				GroupLayout.DEFAULT_SIZE,
																				470,
																				Short.MAX_VALUE)))
										.addGap(37)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				label_Access)
																		.addGap(13))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				label_ID)
																		.addGap(13))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblNameparameterVery)
																		.addGap(13))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				spinner_Value,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(10))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				label_UoM)
																		.addGap(11))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				button_default)
																		.addGap(7)))
										.addComponent(slider_Value,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(separator,
												GroupLayout.PREFERRED_SIZE, 2,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)));

		this.setLayout(groupLayout);

	}

	private void BooleanParameterForm(ModbusParameter mbp, JPanel panelParient) {
		// panel =panelParient;
		// if(null==panel)
		// panel =new JPanel();
		modbusParameter = mbp;
		label_Access = new JLabel(mbp.getAccess());
		label_Access.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel label_ID = new JLabel("P." + mbp.getId());

		JLabel lblNameparameterVery = new JLabel(mbp.getDescription());

		JButton button_default = new JButton("default");

		JSeparator separator = new JSeparator();

		JRadioButton rdbtnNewRadioButton = new JRadioButton("False");
		if (!mbp.ListValuesEmpty())
			rdbtnNewRadioButton.setText(mbp.getListValues().get(0));
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("True");
		if (!mbp.ListValuesEmpty() && mbp.getListValues().size() > 1)
			rdbtnNewRadioButton_1.setText(mbp.getListValues().get(1));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addGap(11)
								.addComponent(label_Access)
								.addGap(12)
								.addComponent(label_ID)
								.addGap(10)
								.addComponent(lblNameparameterVery,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(rdbtnNewRadioButton).addGap(6)
								.addComponent(rdbtnNewRadioButton_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_default).addGap(48))
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addGap(31)
								.addComponent(separator,
										GroupLayout.DEFAULT_SIZE, 590,
										Short.MAX_VALUE).addGap(37)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap(18, Short.MAX_VALUE)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING,
																false)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				label_Access)
																		.addGap(13))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				label_ID)
																		.addGap(13))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblNameparameterVery)
																		.addGap(13))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								button_default)
																						.addComponent(
																								rdbtnNewRadioButton)
																						.addComponent(
																								rdbtnNewRadioButton_1))
																		.addGap(7)))
										.addGap(22)
										.addComponent(separator,
												GroupLayout.PREFERRED_SIZE, 2,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)));

		this.setLayout(groupLayout);

	}

	private void StringParameterForm(ModbusParameter mbp, JPanel panelParient) {
		// panel =panelParient;
		// if(null==panel)
		// panel =new JPanel();
		modbusParameter = mbp;
		label_Access = new JLabel(mbp.getAccess());
		label_Access.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel label_ID = new JLabel("P." + mbp.getId());

		JLabel lblNameparameterVery = new JLabel(mbp.getDescription());

		JLabel label_UoM = new JLabel(mbp.getUoM());

		JButton button_default = new JButton("default");

		JSeparator separator = new JSeparator();

		if (mbp.ListValuesEmpty()) {

			comboBox = new JTextField();
			((JTextField) comboBox).setColumns(10);
		} else {
			comboBox = new JComboBox<String>();
			for (int i = 0; i < mbp.getListValues().size(); i++) {
				((JComboBox<String>) comboBox).addItem(mbp.getListValues().get(
						i));
			}
		}

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addGap(11)
								.addComponent(label_Access)
								.addGap(12)
								.addComponent(label_ID)
								.addGap(10)
								.addComponent(lblNameparameterVery,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGap(13)
								.addComponent(comboBox,
										GroupLayout.PREFERRED_SIZE, 119,
										GroupLayout.PREFERRED_SIZE)
								.addGap(11)
								.addComponent(label_UoM,
										GroupLayout.PREFERRED_SIZE, 44,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(button_default).addGap(21))
				.addGroup(
						groupLayout
								.createSequentialGroup()
								.addGap(31)
								.addComponent(separator,
										GroupLayout.DEFAULT_SIZE, 448,
										Short.MAX_VALUE).addGap(37))

		);
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap(
																				13,
																				Short.MAX_VALUE)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING,
																								false)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												label_Access)
																										.addGap(13))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												label_ID)
																										.addGap(13))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												lblNameparameterVery)
																										.addGap(13))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.BASELINE)
																														.addComponent(
																																button_default)
																														.addComponent(
																																label_UoM))
																										.addGap(11)))
																		.addGap(22))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(13)
																		.addComponent(
																				comboBox,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(12)

																		.addGap(4)))
										.addComponent(separator,
												GroupLayout.PREFERRED_SIZE, 2,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)));

		this.setLayout(groupLayout);

	}
}
