package mvc.views;

import java.awt.Color;

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

import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.ModbusIOException;
import net.wimpi.modbus.ModbusSlaveException;
import net.wimpi.modbus.io.ModbusSerialTransaction;
import net.wimpi.modbus.msg.DA555ReadID;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.WriteMultipleRegistersRequest;
import net.wimpi.modbus.net.SerialConnection;
import net.wimpi.modbus.procimg.InputRegister;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.util.SerialParameters;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class PenFormView {

	final int unitid = 1;
	int imax=100;
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
	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	JFreeChart chart = ChartFactory.createLineChart("Графики", "Тики", "Ток", dataset);

	final JButton btnStartStop = new JButton("Start");
	Timer myTimer = null;// new Timer(taskDelay); // Создаем таймер
	int workTime = 0;

	SerialParameters paramsCom0 = null;
	SerialConnection conCom0 = null;

	SerialParameters paramsCom1 = null;
	SerialConnection conCom1 = null;

	ModbusSerialTransaction trans = null;
	ModbusRequest req = null;
	ModbusResponse rres = null;
	WriteMultipleRegistersRequest wmreq = null;
	ChartPanel chartPanel;
	int isSimulation = 0;

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
					PenFormView window = new PenFormView();
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
	public PenFormView() {
		initialize();
	}

	void mainwork() {
		// Тут чтото делаем очень важное!
		if (isSimulation>0) {
			
			//for (int i = 0; i < 1000; i += 1) {
				// series.add(i, Math.sin(i));
				// series_cos.add(i, Math.cos(i));
				dataset.addValue(Math.sin(isSimulation*.1+Math.random()*0.1), "Series sin", " " + (isSimulation-1));
				dataset.addValue(Math.cos(isSimulation*.1+Math.random()*0.2-0.1), "Series cos", " " + (isSimulation-1));
				dataset.addValue(Math.cos(isSimulation*.1+Math.PI/10), "Series Ideal", " " + (isSimulation-1));
				if(isSimulation>=imax){
					dataset.removeColumn(" " + (isSimulation-imax));
					//System.out.println("Delete "+i);
				}
				try {
					Thread.sleep(100); // спать 1000 милисекунд.
				} catch (Exception e) {
					System.out
							.println("Что-то пошло не так, но бог здесь не причём, ибо его нет. Это просто какой-то косяк, который можно объяснить.");
				}
			//}
				isSimulation++;
		} else {
			// TODO Читаем регистры
			// 5. Prepare a request
			// откуда сколько
			// com1_bf_tx[2] = 0x04;
			// com1_bf_tx[3] = 0x4B; // 0xE8;
			// com1_bf_tx[4] = 0x00;
			// com1_bf_tx[5] = 0x06;
			int ref = 0x004B, count = 0x06;
			req = new ReadMultipleRegistersRequest(ref, count);
			req.setUnitID(unitid);
			req.setHeadless();

			// 6. Prepare the transaction
			trans = new ModbusSerialTransaction(conCom0);
			trans.setRequest(req);
			System.out.println("---");
			// 7. Execute the transaction repeat times
			try {
				trans.execute();
			} catch (ModbusIOException e) {
				// TODO Auto-generated catch block
				System.out.println("ModbusIOException");
				e.printStackTrace();
				return;
			} catch (ModbusSlaveException e) {
				// TODO Auto-generated catch block
				System.out.println("ModbusSlaveException");
				e.printStackTrace();
				return;
			} catch (ModbusException e) {
				// TODO Auto-generated catch block
				System.out.println("ModbusSlaveException");
				e.printStackTrace();
				return;
			}

			rres = trans.getResponse();
			for (int n = 0; n < rres.getWordCount(); n++) {
				listModel.addElement("Word " + n + "="
						+ rres.getRegisterValue(n));
				int index = listModel.size() - 1;
				listOutput.setSelectedIndex(index);
				listOutput.ensureIndexIsVisible(index);
				// System.out.println("Word " + n + "="
				// + rres.getRegisterValue(n));
			}
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	ActionListener taskPerformer = new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
			// ...Perform a task...
			// System.out.println("Times ap");
			btnStartStop.setText(((isSimulation>0)?"Имитация... ":"Работа... ") + (++workTime)
					+ " циклов. Остановить?");
			mainwork();
		}
	};
	private final JPanel SettingsPanel = new JPanel();
	private final JPanel IndicatorsPanel = new JPanel();

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Супер пупер форма");
		frame.setBounds(100, 100, 700, 514);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE,
								845, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE));

		tabbedPane.addTab("Индикаторы", null, IndicatorsPanel, null);
		IndicatorsPanel.setLayout(new BorderLayout(0, 0));
		chartPanel = new ChartPanel(chart);
		IndicatorsPanel.add(chartPanel);
		chartPanel.setLayout(new BorderLayout(0, 0));
		JPanel MainPanel = new JPanel();
		tabbedPane.addTab("Лог", null, MainPanel, null);
		MainPanel.add(listOutput);

		JScrollPane scrollPane = new JScrollPane();
		MainPanel.add(scrollPane);

		tabbedPane.addTab("Настройки", null, SettingsPanel, null);
		SettingsPanel.setLayout(null);

		JLabel lblLpw = new JLabel("LPW-305");
		lblLpw.setBounds(190, 9, 60, 15);
		SettingsPanel.add(lblLpw);

		JLabel label = new JLabel("Четность");
		label.setBounds(48, 132, 65, 15);
		SettingsPanel.add(label);
		comboBoxStopBits0.setBounds(124, 172, 137, 24);
		SettingsPanel.add(comboBoxStopBits0);
		comboBoxStopBits0
				.setModel(new javax.swing.DefaultComboBoxModel<String>(
						new String[] { "1", "1.5", "2" }));

		JLabel lblNewLabel_1 = new JLabel("StopBits");
		lblNewLabel_1.setBounds(34, 167, 60, 15);
		SettingsPanel.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Port");
		lblNewLabel.setBounds(49, 45, 30, 15);
		SettingsPanel.add(lblNewLabel);
		comboBoxPort0.setBounds(124, 40, 168, 24);
		SettingsPanel.add(comboBoxPort0);

		JLabel lblSpeed = new JLabel("Speed");
		lblSpeed.setBounds(49, 92, 45, 15);
		SettingsPanel.add(lblSpeed);
		comboBoxSpeed0.setBounds(124, 87, 168, 24);
		SettingsPanel.add(comboBoxSpeed0);
		comboBoxSpeed0.setModel(new javax.swing.DefaultComboBoxModel<String>(
				new String[] { "110", "300", "600", "1200", "4800", "9600",
						"14400", "19200", "38400", "57600", "115200" }));
		comboBoxSpeed0.setSelectedIndex(7);
		comboBoxPort1.setBounds(330, 40, 143, 24);
		SettingsPanel.add(comboBoxPort1);
		comboBoxPar0.setBounds(124, 123, 168, 24);
		SettingsPanel.add(comboBoxPar0);
		comboBoxPar0.setModel(new javax.swing.DefaultComboBoxModel<String>(
				new String[] { "None", "Odd", "Even", "Mark", "Space" }));

		JLabel lblNewLabel_2 = new JLabel("Уставка");
		lblNewLabel_2.setBounds(31, 269, 67, 15);
		SettingsPanel.add(lblNewLabel_2);
		Ustavka.setBounds(110, 264, 79, 20);
		SettingsPanel.add(Ustavka);
		Ustavka.setValue(5);

		JLabel lblNewLabel_3 = new JLabel("Шаг настройки");
		lblNewLabel_3.setBounds(34, 308, 107, 15);
		SettingsPanel.add(lblNewLabel_3);
		StepSetting.setBounds(160, 310, 73, 20);
		SettingsPanel.add(StepSetting);
		StepSetting.setValue(100);
		label_1.setBounds(322, 269, 79, 15);
		SettingsPanel.add(label_1);
		Gisterezis.setBounds(475, 266, 78, 22);
		SettingsPanel.add(Gisterezis);
		Gisterezis.setValue(1000);
		lblNewLabel_4.setBounds(322, 312, 132, 15);
		SettingsPanel.add(lblNewLabel_4);
		ajustPeriod.setBounds(478, 308, 75, 24);
		SettingsPanel.add(ajustPeriod);
		ajustPeriod.setValue(100);
		comboBoxPar1.setBounds(330, 127, 137, 24);
		SettingsPanel.add(comboBoxPar1);
		comboBoxPar1.setModel(new javax.swing.DefaultComboBoxModel<String>(
				new String[] { "None", "Odd", "Even", "Mark", "Space" }));
		comboBoxStopBits1.setBounds(340, 172, 132, 24);
		SettingsPanel.add(comboBoxStopBits1);
		comboBoxStopBits1
				.setModel(new javax.swing.DefaultComboBoxModel<String>(
						new String[] { "1", "1.5", "2" }));
		comboBoxSpeed1.setBounds(326, 87, 143, 24);
		SettingsPanel.add(comboBoxSpeed1);
		comboBoxSpeed1.setModel(new javax.swing.DefaultComboBoxModel<String>(
				new String[] { "110", "300", "600", "1200", "4800", "9600",
						"14400", "19200", "38400", "57600", "115200" }));
		comboBoxSpeed1.setSelectedIndex(5);

		JLabel lblEnergyform = new JLabel("EnergyForm");
		lblEnergyform.setBounds(339, 9, 160, 15);
		SettingsPanel.add(lblEnergyform);
		frame.getContentPane().setLayout(groupLayout);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		menuBar.add(mnNewMenu);
		menuBar.add(btnStartStop);

		JButton btnClearLog = new JButton("Почистим лог...");
		menuBar.add(btnClearLog);
		btnClearLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
			}
		});

		btnStartStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// final JButton jb = (JButton) (e.getSource());

				if ("Start".equals(btnStartStop.getText())) {
					if (myTimer == null) {
						myTimer = new Timer((Integer) ajustPeriod.getValue(),
								taskPerformer);
					}
					myTimer.setDelay((Integer) ajustPeriod.getValue());
					if (null == ((String) comboBoxPort0.getSelectedItem())) {
						isSimulation = 1;
					} else if (null == paramsCom0) {
						paramsCom0 = new SerialParameters();

						paramsCom0.setPortName((String) comboBoxPort0
								.getSelectedItem());
						paramsCom0.setBaudRate(SerialPort.BAUDRATE_19200);
						paramsCom0.setDatabits(SerialPort.DATABITS_8);
						paramsCom0.setParity(SerialPort.PARITY_NONE);
						paramsCom0.setStopbits(SerialPort.STOPBITS_1);
						paramsCom0.setEncoding(Modbus.SERIAL_ENCODING_RTU);
						paramsCom0.setEcho(false);
						// Open the connection
						conCom0 = new SerialConnection(paramsCom0);
						try {
							conCom0.open();
						} catch (Exception e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
							return;
						}
						System.out.println("Get DA-555 id");
						req = new DA555ReadID(2);
						req.setUnitID(unitid);
						req.setHeadless();
						trans = new ModbusSerialTransaction(conCom0);
						trans.setRequest(req);
						try {
							trans.execute();
						} catch (ModbusIOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							return;
						} catch (ModbusSlaveException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							return;
						} catch (ModbusException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							return;
						}
						rres = trans.getResponse();
						InputRegister[] registers = new Register[50];
						registers = rres.getRegisters();
						System.out.println("NCh         = "
								+ registers[0].toBytes()[0]);
						System.out.println("NInK        = "
								+ registers[0].toBytes()[1]);// NInK:=ComBufRX[4];
						System.out.println("PrgrmDate   = "
								+ registers[1].toBytes()[0]);// PrgrmDate:=ComBufRX[5];
						System.out.println("PrgrmMounth = "
								+ registers[1].toBytes()[1]);// PrgrmMounth:=ComBufRX[6];
						System.out.println("PrgrmYear   = "
								+ (2000 + registers[2].toBytes()[0]));// PrgrmYear:=2000+ComBufRX[7];
					}
					btnStartStop.setText("Starting... Stop?");
					myTimer.start();
				} else {
					btnStartStop.setText("Start");
					if (null != paramsCom0) conCom0.close();
					paramsCom0 = null;
					isSimulation = 0;
					myTimer.stop();
				}
			}
		});
		String[] ports = SerialPortList.getPortNames();
		for (String port : ports) {
			comboBoxPort1.addItem(port);
			comboBoxPort0.addItem(port);
		}
	}
}