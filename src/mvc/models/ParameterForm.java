package mvc.models;
import mvc.models.*;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;

public class ParameterForm extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2660363798598403037L;
	private JLabel label_Access;
    ModbusParameter  modbusParameter=null;
	/**
	 * Create the panel.
	 */
	public ParameterForm( ModbusParameter  mbp) {
		modbusParameter=mbp;
		label_Access = new JLabel(mbp.getAccess());
		label_Access.setHorizontalAlignment(SwingConstants.LEFT);
		
		JLabel label_ID = new JLabel("P."+mbp.getId());
		
		JLabel lblNameparameterVery = new JLabel(mbp.getDescription());
		
		JSpinner spinner_Value = new JSpinner();
		spinner_Value.setModel(new SpinnerNumberModel(22, 0, 100, 1));
		
		JLabel label_UoM = new JLabel(mbp.getUoM());
		
		JButton button_default = new JButton("default");
		
		JSlider slider_Value = new JSlider();
		slider_Value.setValue(23);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(label_Access)
					.addGap(12)
					.addComponent(label_ID)
					.addGap(10)
					.addComponent(lblNameparameterVery, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(3)
					.addComponent(spinner_Value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addComponent(label_UoM)
					.addGap(10)
					.addComponent(button_default)
					.addGap(48))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(9)
					.addComponent(slider_Value, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
					.addGap(37))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(15)
							.addComponent(label_Access)
							.addGap(13))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(label_ID)
							.addGap(13))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNameparameterVery)
							.addGap(13))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(spinner_Value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(label_UoM)
							.addGap(11))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(button_default)
							.addGap(7)))
					.addComponent(slider_Value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(17, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

}
