package mvc.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;

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
//import javax.swing.JMenuItem;
import javax.swing.JList;
//import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;

import mvc.PenFormMvc;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public class PenFormView extends JPanel// JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 199205026739946357L;
	private PenFormMvc mvc = null;

	// int imax=100;
	// private JFrame parientFrame;
	public JComboBox<String> comboBoxPort0 = new JComboBox<String>();
	public JComboBox<String> comboBoxPort1 = new JComboBox<String>();
	public JComboBox<String> comboBoxSpeed0 = new JComboBox<String>();
	public JComboBox<String> comboBoxSpeed1 = new JComboBox<String>();
	public JComboBox<String> comboBoxPar0 = new JComboBox<String>();
	public JComboBox<String> comboBoxPar1 = new JComboBox<String>();
	public JComboBox<String> comboBoxStopBits0 = new JComboBox<String>();
	public JComboBox<String> comboBoxStopBits1 = new JComboBox<String>();
	JSpinner Ustavka = new JSpinner();
	JSpinner StepSetting = new JSpinner();
	private final JLabel label_1 = new JLabel("Gisterezis");
	private final JSpinner Gisterezis = new JSpinner();
	private final JLabel lblNewLabel_4 = new JLabel("Period setting");
	public final JSpinner ajustPeriod = new JSpinner();
	public final DefaultListModel<String> listModel = new DefaultListModel<String>();
	public final JList<String> listOutput = new JList<String>(listModel);
	public DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	JFreeChart chart = ChartFactory.createLineChart("chart", "Ticks", "I",
			dataset);

	public final JButton btnStartStop = new JButton("Start");





	
	ChartPanel chartPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// try {
		// UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		// } catch (Throwable e) {
		// e.printStackTrace();
		// }
		// EventQueue.invokeLater(new Runnable() {
		// public void run() {
		// try {
		// PenFormView window = new PenFormView();
		// window.setVisible(true);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// });
	}

	/**
	 * Create the application.
	 */
	public PenFormView(PenFormMvc mvc) {
		this.mvc = mvc;
		initialize();
	}


	private JTabbedPane tabbedPane = null;
	private final JPanel SettingsPanel = new JPanel();
	private final JPanel IndicatorsPanel = new JPanel();
	private final JComboBox<String> comboBoxSpeed2 = new JComboBox<String>();
	private final JComboBox<String> comboBoxPar2 = new JComboBox<String>();
	private final JComboBox<String> comboBoxStopBits2 = new JComboBox<String>();
	public JSpinner windowChartWidth;


	private void initialize() {
		//
		this.setLayout(new BorderLayout());
		 tabbedPane = new JTabbedPane(JTabbedPane.BOTTOM);
		// if (mvc == null) mvc = (PenFormMvc) new JFrame();
//		GroupLayout groupLayout = new GroupLayout(this.mvc.getContentPane());

//		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
//				Alignment.LEADING).addGroup(
//				groupLayout
//						.createSequentialGroup()
//						.addContainerGap()
//						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE,
//								845, Short.MAX_VALUE)));
//		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
//				Alignment.LEADING).addComponent(tabbedPane,
//				GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE));
//
		tabbedPane.addTab("Chart", null, IndicatorsPanel, null);
		IndicatorsPanel.setLayout(new BorderLayout(0, 0));
		chartPanel = new ChartPanel(chart);
		IndicatorsPanel.add(chartPanel);
		chartPanel.setLayout(new BorderLayout(0, 0));
		JPanel LogPanel = new JPanel();
		tabbedPane.addTab("Log", null, LogPanel, null);
		LogPanel.setLayout(new BorderLayout());
		JButton btnClearLog = new JButton("Clear log...");
		LogPanel.add(btnClearLog, BorderLayout.PAGE_START);	
		LogPanel.add(listOutput, BorderLayout.NORTH);
		JScrollPane scrollPane = new JScrollPane();
		LogPanel.add(scrollPane, BorderLayout.SOUTH);
		

		btnClearLog.setVerticalAlignment(SwingConstants.TOP);
		btnClearLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
			}
		});

		tabbedPane.addTab("Settings", null, SettingsPanel, null);
		SettingsPanel.setLayout(new GridLayout(0, 6, 0, 0));
		
		SettingsPanel.add(new JLabel(""));SettingsPanel.add(new JLabel(""));

		JLabel lblLpw = new JLabel("LPW-305");
		SettingsPanel.add(lblLpw);

		JLabel lblEnergyform = new JLabel("EnergyForm");
		SettingsPanel.add(lblEnergyform);

		JLabel lblNewLabel_5 = new JLabel("DA5XXX");
		SettingsPanel.add(lblNewLabel_5);
		SettingsPanel.add(new JLabel(""));SettingsPanel.add(new JLabel(""));
		JLabel lblNewLabel = new JLabel("Port");
		SettingsPanel.add(lblNewLabel);
SettingsPanel.add(comboBoxPort0);
SettingsPanel.add(comboBoxPort1);

		JComboBox<String> comboBoxPort2 = new JComboBox<String>();
		SettingsPanel.add(comboBoxPort2);
SettingsPanel.add(new JLabel(""));
		SettingsPanel.add(new JLabel(""));
		JLabel lblSpeed = new JLabel("Speed");
		SettingsPanel.add(lblSpeed);
SettingsPanel.add(comboBoxSpeed0);
comboBoxSpeed0.setModel(new javax.swing.DefaultComboBoxModel<String>(
		new String[] { "110", "300", "600", "1200", "4800", "9600",
				"14400", "19200", "38400", "57600", "115200" }));
comboBoxSpeed0.setSelectedIndex(7);
SettingsPanel.add(comboBoxSpeed1);
comboBoxSpeed1.setModel(new javax.swing.DefaultComboBoxModel<String>(
		new String[] { "110", "300", "600", "1200", "4800", "9600",
				"14400", "19200", "38400", "57600", "115200" }));
comboBoxSpeed1.setSelectedIndex(7);
comboBoxSpeed2.setModel(new DefaultComboBoxModel(new String[] { "110",
		"300", "600", "1200", "4800", "9600", "14400", "19200",
		"38400", "57600", "115200" }));
comboBoxSpeed2.setSelectedIndex(7);

		SettingsPanel.add(comboBoxSpeed2);
SettingsPanel.add(new JLabel(""));
		SettingsPanel.add(new JLabel(""));
		JLabel label = new JLabel("Parity");
		SettingsPanel.add(label);
SettingsPanel.add(comboBoxPar0);
comboBoxPar0.setModel(new javax.swing.DefaultComboBoxModel<String>(
		new String[] { "None", "Odd", "Even", "Mark", "Space" }));
SettingsPanel.add(comboBoxPar1);
comboBoxPar1.setModel(new DefaultComboBoxModel(new String[] { "None",
		"Odd", "Even", "Mark", "Space" }));
comboBoxPar2.setModel(new DefaultComboBoxModel(new String[] { "None",
		"Odd", "Even", "Mark", "Space" }));

		SettingsPanel.add(comboBoxPar2);
SettingsPanel.add(new JLabel(""));
		SettingsPanel.add(new JLabel(""));
		JLabel lblNewLabel_1 = new JLabel("StopBits");
		SettingsPanel.add(lblNewLabel_1);
SettingsPanel.add(comboBoxStopBits0);
comboBoxStopBits0
		.setModel(new javax.swing.DefaultComboBoxModel<String>(
				new String[] { "1", "1.5", "2" }));
SettingsPanel.add(comboBoxStopBits1);
comboBoxStopBits1
		.setModel(new javax.swing.DefaultComboBoxModel<String>(
				new String[] { "1", "1.5", "2" }));
comboBoxStopBits2.setModel(new DefaultComboBoxModel(new String[] { "1",
		"1.5", "2" }));

		SettingsPanel.add(comboBoxStopBits2);
SettingsPanel.add(new JLabel(""));
								
SettingsPanel.add(new JLabel(""));

						
								JLabel lblNewLabel_2 = new JLabel("Ustavka");
								SettingsPanel.add(lblNewLabel_2);
						SettingsPanel.add(Ustavka);
						Ustavka.setValue(5);
						SettingsPanel.add(label_1);
						SettingsPanel.add(Gisterezis);
						Gisterezis.setValue(1000);
						SettingsPanel.add(new JLabel(""));
						SettingsPanel.add(new JLabel(""));


				
						JLabel lblNewLabel_3 = new JLabel("Step setting");
						SettingsPanel.add(lblNewLabel_3);
				SettingsPanel.add(StepSetting);
				StepSetting.setValue(100);
SettingsPanel.add(lblNewLabel_4);
SettingsPanel.add(ajustPeriod);
ajustPeriod.setValue(100);
SettingsPanel.add(new JLabel(""));
SettingsPanel.add(new JLabel(""));
		JLabel lblNewLabel_6 = new JLabel("chart width (dots)");
		SettingsPanel.add(lblNewLabel_6);

		windowChartWidth = new JSpinner();
		windowChartWidth.setModel(new SpinnerNumberModel(new Integer(100),
				new Integer(0), null, new Integer(1)));
		SettingsPanel.add(windowChartWidth);
/////		this.mvc.getContentPane().setLayout(groupLayout);
		
//		this.mvc.getContentPane().add(tabbedPane);
		JMenuBar menuBar = new JMenuBar();
		this.mvc.setJMenuBar(menuBar);
//		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
//		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(btnStartStop, BorderLayout.PAGE_START);
		btnStartStop.setVerticalAlignment(SwingConstants.TOP);
		// все кнопки переведем события на контроллер!
		btnStartStop.addActionListener(mvc.getController());
		
//				btnStartStop.addActionListener(new ActionListener() {
//					public void actionPerformed(ActionEvent e) {
//						// final JButton jb = (JButton) (e.getSource());
//		
//						if ("Start".equals(btnStartStop.getText())) {
//							if (myTimer == null) {
//								myTimer = new Timer((Integer) ajustPeriod.getValue(),
//										taskPerformer);
//							}
//							myTimer.setDelay((Integer) ajustPeriod.getValue());
//							if (null == ((String) comboBoxPort0.getSelectedItem())) {
//								isSimulation = 1;
//							} else if (null == paramsCom0) {
//								paramsCom0 = new SerialParameters();
//		
//								paramsCom0.setPortName((String) comboBoxPort0
//										.getSelectedItem());
//								paramsCom0.setBaudRate(SerialPort.BAUDRATE_19200);
//								paramsCom0.setDatabits(SerialPort.DATABITS_8);
//								paramsCom0.setParity(SerialPort.PARITY_NONE);
//								paramsCom0.setStopbits(SerialPort.STOPBITS_1);
//								paramsCom0.setEncoding(Modbus.SERIAL_ENCODING_RTU);
//								paramsCom0.setEcho(false);
//								// Open the connection
//								conCom0 = new SerialConnection(paramsCom0);
//								try {
//									conCom0.open();
//								} catch (Exception e2) {
//									// TODO Auto-generated catch block
//									e2.printStackTrace();
//									if (null != paramsCom0)
//										conCom0.close();
//									paramsCom0 = null;
//									isSimulation = 1;
//		
//									return;
//								}
//								System.out.println("Get DA-555 id");
//								req = new DA555ReadID(2);
//								req.setUnitID(unitid);
//								req.setHeadless();
//								trans = new ModbusSerialTransaction(conCom0);
//								trans.setRequest(req);
//								try {
//									trans.execute();
//		
//								} catch (ModbusIOException e1) {
//									// TODO Auto-generated catch block
//									e1.printStackTrace();
//									if (null != paramsCom0)
//										conCom0.close();
//									paramsCom0 = null;
//									isSimulation = 1;
//									// return;
//								} catch (ModbusSlaveException e1) {
//									// TODO Auto-generated catch block
//									e1.printStackTrace();
//									if (null != paramsCom0)
//										conCom0.close();
//									paramsCom0 = null;
//									isSimulation = 1;
//									// return;
//		
//								} catch (ModbusException e1) {
//									// TODO Auto-generated catch block
//									e1.printStackTrace();
//									if (null != paramsCom0)
//										conCom0.close();
//									paramsCom0 = null;
//									isSimulation = 1;
//									// return;
//		
//								}
//								if (paramsCom0 != null) {
//									rres = trans.getResponse();
//									InputRegister[] registers = new Register[50];
//									registers = rres.getRegisters();
//									System.out.println("NCh         = "
//											+ registers[0].toBytes()[0]);
//									System.out.println("NInK        = "
//											+ registers[0].toBytes()[1]);// NInK:=ComBufRX[4];
//									System.out.println("PrgrmDate   = "
//											+ registers[1].toBytes()[0]);// PrgrmDate:=ComBufRX[5];
//									System.out.println("PrgrmMounth = "
//											+ registers[1].toBytes()[1]);// PrgrmMounth:=ComBufRX[6];
//									System.out.println("PrgrmYear   = "
//											+ (2000 + registers[2].toBytes()[0]));// PrgrmYear:=2000+ComBufRX[7];
//								}
//							}
//							btnStartStop.setText("Starting... Stop?");
//							myTimer.start();
//						} else {
//							btnStartStop.setText("Start");
//							if (null != paramsCom0)
//								conCom0.close();
//							paramsCom0 = null;
//							isSimulation = 0;
//							myTimer.stop();
//						}
//					}
//				});
		this.add(tabbedPane,BorderLayout.CENTER);
		String[] ports = SerialPortList.getPortNames();
		for (String port : ports) {
			comboBoxPort2.addItem(port);
			comboBoxPort1.addItem(port);
			comboBoxPort0.addItem(port);
		}
		// comboBoxPort0.setSelectedIndex(0);
		// comboBoxPort1.setSelectedIndex(1);
		// comboBoxPort2.setSelectedIndex(2);
//		 this.mvc.getContentPane().add(this);
	}
}
