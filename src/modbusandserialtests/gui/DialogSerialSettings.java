package modbusandserialtests.gui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class DialogSerialSettings extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2973131651319842796L;

	/**
	 * Create the panel.
	 */
	/**
	 * 
	 */
	public DialogSerialSettings() {
		
		JLabel label = new JLabel();
		label.setText("Port name:");
		label.setPreferredSize(new Dimension(120, 14));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Dialog", Font.PLAIN, 11));
		
//		JLabel label_1 = new JLabel();
//		label_1.setText("Baudrate:");
//		label_1.setPreferredSize(new Dimension(90, 14));
///		label_1.setHorizontalAlignment(SwingConstants.CENTER);
//		label_1.setFont(new Font("Dialog", Font.PLAIN, 11));
		
//		JLabel label_2 = new JLabel();
//		label_2.setText("Data bits:");
//		label_2.setPreferredSize(new Dimension(70, 14));
//		label_2.setHorizontalAlignment(SwingConstants.CENTER);
//		label_2.setFont(new Font("Dialog", Font.PLAIN, 11));
		
//		JLabel label_3 = new JLabel();
//		label_3.setText("Stop bits:");
//		label_3.setPreferredSize(new Dimension(70, 14));
//		label_3.setHorizontalAlignment(SwingConstants.CENTER);
//		label_3.setFont(new Font("Dialog", Font.PLAIN, 11));
		
//		JLabel label_4 = new JLabel();
//		label_4.setText("Parity:");
//		label_4.setPreferredSize(new Dimension(90, 14));
//		label_4.setHorizontalAlignment(SwingConstants.CENTER);
//		label_4.setFont(new Font("Dialog", Font.PLAIN, 11));
		
//		JComboBox comboBox = new JComboBox();
//		comboBox.setPreferredSize(new Dimension(120, 28));
//		comboBox.setFont(new Font("Dialog", Font.PLAIN, 11));
//		
//		JComboBox comboBox_1 = new JComboBox();
//		comboBox_1.setPreferredSize(new Dimension(90, 28));
//		comboBox_1.setFont(new Font("Dialog", Font.PLAIN, 11));
//		
//		JComboBox comboBox_2 = new JComboBox();
//		comboBox_2.setPreferredSize(new Dimension(70, 28));
//		comboBox_2.setFont(new Font("Dialog", Font.PLAIN, 11));
//		
//		JComboBox comboBox_3 = new JComboBox();
//		comboBox_3.setPreferredSize(new Dimension(70, 28));
//		comboBox_3.setFont(new Font("Dialog", Font.PLAIN, 11));
//		
//		JComboBox comboBox_4 = new JComboBox();
//		comboBox_4.setSelectedIndex(0);
//		comboBox_4.setPreferredSize(new Dimension(90, 28));
//		comboBox_4.setFont(new Font("Dialog", Font.PLAIN, 11));
//		
//		JButton button = new JButton();
//		button.setText("Save");
//		button.setPreferredSize(new Dimension(90, 28));
//		button.setFont(new Font("Dialog", Font.PLAIN, 11));
//		
//		JButton button_1 = new JButton();
//		button_1.setText("Cancel");
//		button_1.setPreferredSize(new Dimension(90, 28));
//		button_1.setFont(new Font("Dialog", Font.PLAIN, 11));
		GroupLayout groupLayout = new GroupLayout(this);
//		groupLayout.setHorizontalGroup(
//			groupLayout.createParallelGroup(Alignment.LEADING)
//				.addGroup(groupLayout.createSequentialGroup()
//					.addContainerGap()
//					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
//						.addGroup(groupLayout.createSequentialGroup()
//							.addComponent(label, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
//							.addGap(6)
//							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
//							.addGap(6)
//							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//							.addGap(6)
//							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//							.addGap(6)
//							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
//						.addGroup(groupLayout.createSequentialGroup()
//							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
//							.addGap(6)
//							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
//							.addGap(6)
//							.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
//							.addGap(6)
//							.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
//							.addGap(6)
//							.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
//						.addGroup(groupLayout.createSequentialGroup()
//							.addGap(284)
//							.addComponent(button, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
//							.addGap(6)
//							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))
//					.addContainerGap(-32, Short.MAX_VALUE))
//		);
//		groupLayout.setVerticalGroup(
//			groupLayout.createParallelGroup(Alignment.LEADING)
//				.addGroup(groupLayout.createSequentialGroup()
//					.addContainerGap()
//					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
//						.addComponent(label, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
//						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
//						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
//						.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
//						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
//					.addGap(1)
//					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
//						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
//						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
//						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
//						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
//						.addComponent(comboBox_4, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
//					.addGap(14)
//					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
//						.addComponent(button, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
//						.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)))
//		);
//		setLayout(groupLayout);

	}
}
