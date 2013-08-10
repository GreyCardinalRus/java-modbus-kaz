package modbusandserialtests.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
//import java.util.TimerTask;

import jssc.SerialPort;
import jssc.SerialPortList;
import javax.swing.JSpinner;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;

public class PenForm {
    //final int taskDelay=1000;
	private JFrame frame;
	JComboBox<String> comboBoxPort0 = new JComboBox<String>();
	JComboBox<String> comboBoxPort1 = new JComboBox<String>();
	JComboBox<String> comboBoxSpeed0 = new JComboBox<String>();
	JComboBox<String> comboBoxSpeed1 = new JComboBox<String>();
	JComboBox<String> comboBoxPar0 = new JComboBox<String>();
	JComboBox<String> comboBoxPar1 = new JComboBox<String>();
	JComboBox<String> comboBoxStopBits0 = new JComboBox<String>();
	JComboBox<String> comboBoxStopBits1 = new JComboBox<String>();
	JSpinner Ustavka = new JSpinner();
	JSpinner StepSetting = new JSpinner();
	private final JLabel label_1 = new JLabel("Гистерезис");
	private final JSpinner Gisterezis = new JSpinner();
	private final JLabel lblNewLabel_4 = new JLabel("Период настройки");
	private final JSpinner ajustPeriod = new JSpinner();
	private final JMenu mnNewMenu = new JMenu("Помощь нужна?");
	final DefaultListModel<String> listModel = new DefaultListModel<String>();
	final JList<String> listOutput = new JList<String>(listModel);
	final JButton btnStartStop = new JButton("Start");
	Timer myTimer = null;//new Timer(taskDelay); // Создаем таймер
	int workTime = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PenForm window = new PenForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PenForm() {
		initialize();
	}

	void mainwork()	{
		// Тут чтото делаем очень важное!
		System.out.println("main cicle!");
	}
	/**
	 * Initialize the contents of the frame.
	 */   
	ActionListener taskPerformer = new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	            //...Perform a task...
//				System.out.println("Times ap");
					btnStartStop.setText("Working... "+(++workTime)+" sec. Stop?");
                 mainwork();
	            }
	        };

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Супер пупер форма");
		frame.setBounds(100, 100, 859, 438);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblNewLabel = new JLabel("Port");

		JLabel lblLpw = new JLabel("LPW-305");

		JLabel lblEnergyform = new JLabel("EnergyForm");

		JLabel lblSpeed = new JLabel("Speed");

		JLabel label = new JLabel("Четность");

	
	      
		btnStartStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//final JButton jb = (JButton) (e.getSource());

				if (btnStartStop.getText() == "Start") {
					if(myTimer == null){
					myTimer = new Timer((Integer) ajustPeriod.getValue(),taskPerformer);
					}
					btnStartStop.setText("Starting... Stop?");
					myTimer.start();
				} else {
					btnStartStop.setText("Start");
					myTimer.stop();
				}
			}
		});

		JLabel lblNewLabel_1 = new JLabel("StopBits");

		JLabel lblNewLabel_2 = new JLabel("Уставка");

		JLabel lblNewLabel_3 = new JLabel("Шаг настройки");

		JScrollPane scrollPane = new JScrollPane();

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout.createSequentialGroup()
										.addComponent(btnStartStop)
										.addContainerGap(787, Short.MAX_VALUE))
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING,
																false)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(95)
																		.addComponent(
																				lblLpw))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addComponent(
																								lblNewLabel)
																						.addComponent(
																								lblSpeed))
																		.addGap(38)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								comboBoxSpeed0,
																								GroupLayout.PREFERRED_SIZE,
																								141,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								comboBoxPort0,
																								GroupLayout.PREFERRED_SIZE,
																								141,
																								GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addComponent(
																								label)
																						.addComponent(
																								lblNewLabel_1))
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												comboBoxStopBits0,
																												GroupLayout.PREFERRED_SIZE,
																												133,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED))
																						.addComponent(
																								comboBoxPar0,
																								0,
																								GroupLayout.DEFAULT_SIZE,
																								Short.MAX_VALUE))))
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(33)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												comboBoxStopBits1,
																												GroupLayout.PREFERRED_SIZE,
																												133,
																												GroupLayout.PREFERRED_SIZE)
																										.addContainerGap())
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGroup(
																												groupLayout
																														.createParallelGroup(
																																Alignment.TRAILING,
																																false)
																														.addComponent(
																																comboBoxPar1,
																																Alignment.LEADING,
																																0,
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)
																														.addComponent(
																																comboBoxSpeed1,
																																Alignment.LEADING,
																																0,
																																GroupLayout.DEFAULT_SIZE,
																																Short.MAX_VALUE)
																														.addComponent(
																																comboBoxPort1,
																																Alignment.LEADING,
																																0,
																																475,
																																Short.MAX_VALUE))
																										.addContainerGap())))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(221)
																		.addComponent(
																				lblEnergyform)
																		.addContainerGap())))
						.addGroup(
								Alignment.LEADING,
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																scrollPane,
																GroupLayout.PREFERRED_SIZE,
																829,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				lblNewLabel_2)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				Ustavka,
																				GroupLayout.PREFERRED_SIZE,
																				79,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				lblNewLabel_3)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				StepSetting,
																				GroupLayout.PREFERRED_SIZE,
																				73,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				label_1)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				Gisterezis,
																				GroupLayout.PREFERRED_SIZE,
																				78,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				lblNewLabel_4)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				ajustPeriod,
																				GroupLayout.PREFERRED_SIZE,
																				98,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(24, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblLpw)
														.addComponent(
																lblEnergyform))
										.addGap(5)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblNewLabel)
														.addComponent(
																comboBoxPort0,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																comboBoxPort1,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(lblSpeed)
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				comboBoxSpeed0,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				comboBoxSpeed1,
																				GroupLayout.PREFERRED_SIZE,
																				23,
																				GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				comboBoxPar0,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addComponent(
																				label))
														.addComponent(
																comboBoxPar1,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								comboBoxStopBits0,
																								GroupLayout.PREFERRED_SIZE,
																								GroupLayout.DEFAULT_SIZE,
																								GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								lblNewLabel_1))
																		.addGap(30)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.TRAILING)
																						.addGroup(
																								groupLayout
																										.createParallelGroup(
																												Alignment.BASELINE)
																										.addComponent(
																												Ustavka,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												lblNewLabel_3)
																										.addComponent(
																												StepSetting,
																												GroupLayout.PREFERRED_SIZE,
																												GroupLayout.DEFAULT_SIZE,
																												GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												label_1)
																										.addComponent(
																												Gisterezis,
																												GroupLayout.PREFERRED_SIZE,
																												22,
																												GroupLayout.PREFERRED_SIZE)
																										.addComponent(
																												lblNewLabel_4))
																						.addComponent(
																								lblNewLabel_2)))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				comboBoxStopBits1,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(39)
																		.addComponent(
																				ajustPeriod,
																				GroupLayout.PREFERRED_SIZE,
																				15,
																				GroupLayout.PREFERRED_SIZE)))
										.addGap(18)
										.addComponent(scrollPane,
												GroupLayout.DEFAULT_SIZE, 130,
												Short.MAX_VALUE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(btnStartStop)));
		scrollPane.setViewportView(listOutput);
		frame.getContentPane().setLayout(groupLayout);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		menuBar.add(mnNewMenu);
		String[] ports = SerialPortList.getPortNames();
		for (String port : ports) {
			comboBoxPort1.addItem(port);
			comboBoxPort0.addItem(port);
		}
		comboBoxPar0.setModel(new javax.swing.DefaultComboBoxModel<String>(
				new String[] { "None", "Odd", "Even", "Mark", "Space" }));
		comboBoxPar1.setModel(new javax.swing.DefaultComboBoxModel<String>(
				new String[] { "None", "Odd", "Even", "Mark", "Space" }));
		comboBoxSpeed0.setModel(new javax.swing.DefaultComboBoxModel<String>(
				new String[] { "110", "300", "600", "1200", "4800", "9600",
						"14400", "19200", "38400", "57600", "115200" }));
		comboBoxSpeed1.setModel(new javax.swing.DefaultComboBoxModel<String>(
				new String[] { "110", "300", "600", "1200", "4800", "9600",
						"14400", "19200", "38400", "57600", "115200" }));
		comboBoxStopBits0
				.setModel(new javax.swing.DefaultComboBoxModel<String>(
						new String[] { "1", "1.5", "2" }));
		comboBoxStopBits1
				.setModel(new javax.swing.DefaultComboBoxModel<String>(
						new String[] { "1", "1.5", "2" }));
		comboBoxSpeed0.setSelectedIndex(7);
		comboBoxSpeed1.setSelectedIndex(5);
		Ustavka.setValue(5);
		StepSetting.setValue(100);
		Gisterezis.setValue(1000);
		ajustPeriod.setValue(1000);
		for (int i = 0; i < 25; i++) {
			listModel.addElement("Элемент списка " + i);
			int index = listModel.size() - 1;
			listOutput.setSelectedIndex(index);
			listOutput.ensureIndexIsVisible(index);
		}
	}
}
