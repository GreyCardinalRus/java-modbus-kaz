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
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;

public class ParameterForm //extends JPanel 
{
	/**
	 * 
	 */
//	private static final long serialVersionUID = 2660363798598403037L;
	private JLabel label_Access;
	private JPanel panel;
    ModbusParameter  modbusParameter=null;
	/**
	 * Create the panel.
	 */
	public ParameterForm( ModbusParameter  mbp, JPanel panelParient) {
		panel =panelParient;// new JPanel();
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
		
		JSeparator separator = new JSeparator();
		GroupLayout groupLayout = new GroupLayout(panel);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(11)
					.addComponent(label_Access)
					.addGap(12)
					.addComponent(label_ID)
					.addGap(10)
					.addComponent(lblNameparameterVery, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
					.addGap(3)
					.addComponent(spinner_Value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(9)
					.addComponent(label_UoM)
					.addGap(10)
					.addComponent(button_default)
					.addGap(48))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addGap(31)
							.addComponent(separator, GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(9)
							.addComponent(slider_Value, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)))
					.addGap(37))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_Access)
							.addGap(13))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_ID)
							.addGap(13))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNameparameterVery)
							.addGap(13))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(spinner_Value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(10))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label_UoM)
							.addGap(11))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(button_default)
							.addGap(7)))
					.addComponent(slider_Value, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(18))
		);
		panel.setLayout(groupLayout);

	}
}
