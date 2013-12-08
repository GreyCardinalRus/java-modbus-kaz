package mvc.views;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.SpinnerNumberModel;

public class jif extends JInternalFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					jif frame = new jif();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public jif() {
		setBounds(100, 100, 450, 100);
		
		JLabel lblId = new JLabel("P.002");
		
		JLabel lblName = new JLabel("NameParameter");
		
		JLabel lblAccess = new JLabel("RW");
		
		JLabel lblUoM = new JLabel("%");
		
		JSpinner spinnerValue = new JSpinner();
		spinnerValue.setModel(new SpinnerNumberModel(new Integer(23), null, null, new Integer(1)));
		
		JButton btnDefault = new JButton("default");
		
		JSlider sliderValue = new JSlider();
		sliderValue.setValue(23);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblAccess)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblId)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerValue, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblUoM)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnDefault)
							.addGap(14))
						.addComponent(sliderValue, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
					.addGap(7))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAccess)
						.addComponent(lblId)
						.addComponent(lblName)
						.addComponent(spinnerValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUoM)
						.addComponent(btnDefault))
					.addGap(6)
					.addComponent(sliderValue, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		getContentPane().setLayout(groupLayout);

	}
}
