package modbusandserialtests.gui;

import java.awt.EventQueue;

import jssc.SerialPort;
import jssc.SerialPortList;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JProgressBar;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;

import java.awt.FileDialog;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.io.File;
import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextPane;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;

import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.ModbusIOException;
import net.wimpi.modbus.ModbusSlaveException;
import net.wimpi.modbus.io.ModbusSerialTransaction;
import net.wimpi.modbus.msg.DA555ReadID;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.msg.WriteMultipleRegistersRequest;
import net.wimpi.modbus.net.SerialConnection;
import net.wimpi.modbus.procimg.InputRegister;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.util.SerialParameters;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class MBTGUI  {

	private JFrame frmTestForModbus;
	private JTabbedPane tabbedPane;
	static int numPorts=8;
	JCheckBoxMenuItem mntmPort[]=new JCheckBoxMenuItem[numPorts];
	SerialParameters params[] = new SerialParameters[numPorts];
	SerialConnection con[] = new SerialConnection[numPorts];
	ModbusSerialTransaction trans = null;
	ModbusRequest req = null;
	ModbusResponse rres = null;
	WriteMultipleRegistersRequest wmreq=null;
	
	JButton btnCDPort0;
	JComboBox<String> comboBoxPort0;
	JButton btnconfigPort0;
	Timer myTimer = new Timer(); // Создаем таймер
	private final Action action = new SwingAction();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MBTGUI window = new MBTGUI();
					window.frmTestForModbus.setVisible(true);
					//window.myTimer.cancel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MBTGUI() {
		initialize();
	}
//~MBTGUI(){};
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		myTimer.schedule(new TimerTask() { // Определяем задачу
		    @Override
		    public void run() {
		    	///System.out.println("Times ap");
		    	if(null!=con[0] && con[0].isOpen())
		    	{
				tabbedPane.setBackgroundAt(1, Color.GREEN);
				btnCDPort0.setText("Disconnect");
				btnconfigPort0.setEnabled(false);
		    		
		    	}else{
					tabbedPane.setBackgroundAt(1, Color.RED);
					btnCDPort0.setText("Connect");
					btnconfigPort0.setEnabled(true);
		    	}
		        //final String result = doLongAndComplicatedTask();
		        //uiHandler.post(new Runnable() {
		        //    @Override
		         //   public void run() {
		         //       txtResult.setText(result);
		          //  }
		      //  });
		    };//);
		}, 1000L, 1L * 1000); // интервал - 60000 миллисекунд, 0 миллисекунд до первого запуска.
		frmTestForModbus = new JFrame();
		frmTestForModbus.setTitle("Test for MODBUS");
		frmTestForModbus.setBounds(100, 100, 450, 300);
		frmTestForModbus.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String[] ports = SerialPortList.getPortNames();
		JMenuBar menuBar = new JMenuBar();
		
		frmTestForModbus.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmTestForModbus.setVisible(false);
			}
		});
		
		JMenuItem mntmfileOpen = new JMenuItem("Open file...");
		mnFile.add(mntmfileOpen);
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnSettings = new JMenu("Ports");
		mnSettings.setEnabled(false);
		menuBar.add(mnSettings);
		
		JCheckBoxMenuItem mntmPort = new JCheckBoxMenuItem("Port0");
		mnSettings.add(mntmPort);
		
		JCheckBoxMenuItem mntmPort_1 = new JCheckBoxMenuItem("Port1");
		mnSettings.add(mntmPort_1);
		
		JCheckBoxMenuItem mntmPort_2 = new JCheckBoxMenuItem("Port2");
		mnSettings.add(mntmPort_2);
		
		JCheckBoxMenuItem mntmPort_3 = new JCheckBoxMenuItem("Port3");
		mnSettings.add(mntmPort_3);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutDialog dialogAbout = new AboutDialog();
				dialogAbout.setVisible(true);
			}
		});
		mnHelp.add(mntmAbout);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmTestForModbus.getContentPane().add(tabbedPane, BorderLayout.NORTH);
		
		JPanel Tests = new JPanel();
		tabbedPane.addTab("Tests", null, Tests, null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cd,fn;
				//Create a file chooser
				JFrame jp;
				jp = (JFrame)(((JButton)e.getSource()).getParent().getParent().getParent().getParent().getParent().getParent());
			    FileDialog fDialog = new FileDialog(jp, "Open ...", FileDialog.LOAD);
		        fDialog.setVisible(true);
		        // Get the file name chosen by the user
		        String name = fDialog.getFile();

		    // If user canceled file selection, return without doing anything.
		        if(name == null)
		            return;
		        System.out.println(fDialog.getDirectory() + name);
		        if(null!=con[0] && con[0].isOpen())
		        {
		        int unitid = 1;
				int ref = 2;
				System.out.println("Get DA-555 id");
				req  = new DA555ReadID(ref);
				req.setUnitID(unitid);
				req.setHeadless();
				trans = new ModbusSerialTransaction(con[0]);
				trans.setRequest(req);
				try {
					trans.execute();
				} catch (ModbusIOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ModbusSlaveException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ModbusException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				rres =  trans.getResponse();
//				System.out.println("Decode...");
				InputRegister[]  registers = new Register[50];
				registers =   rres.getRegisters();
				int NCh = registers[0].toBytes()[0];
				System.out.println("NCh         = "+registers[0].toBytes()[0]);
				System.out.println("NInK        = "+registers[0].toBytes()[1]);//	          NInK:=ComBufRX[4];
				System.out.println("PrgrmDate   = "+registers[1].toBytes()[0]);//	          PrgrmDate:=ComBufRX[5];
				System.out.println("PrgrmMounth = "+registers[1].toBytes()[1]);//	          PrgrmMounth:=ComBufRX[6];
				System.out.println("PrgrmYear   = "+(2000+registers[2].toBytes()[0]));//	          PrgrmYear:=2000+ComBufRX[7];
		        }
		        else {
		        	System.out.println("Port 0 not open!");
		        }
			}
		});
		Tests.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		Tests.add(btnStop);
		
		JTextPane textPane = new JTextPane();
		Tests.add(textPane);
		
		JFormattedTextField frmtdtxtfldFsffFdFs = new JFormattedTextField();
		frmtdtxtfldFsffFdFs.setText("fsff fd fs f df df s");
		Tests.add(frmtdtxtfldFsffFdFs);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setMinimum(1);
		progressBar.setMaximum(100);
		progressBar.setValue(77);
		Tests.add(progressBar);
		
		JPanel Port0 = new JPanel();
		Port0.setForeground(Color.RED);
		tabbedPane.addTab("Port0", null, Port0, null);
		tabbedPane.setBackgroundAt(1, Color.RED);
		GridBagLayout gbl_Port0 = new GridBagLayout();
		gbl_Port0.columnWidths = new int[]{0, 0, 74, 0, 0, 145, 145, 0};
		gbl_Port0.rowHeights = new int[]{31, 0, 0};
		gbl_Port0.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_Port0.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		Port0.setLayout(gbl_Port0);
			
				
				comboBoxPort0 = new JComboBox<String>();  
				GridBagConstraints gbc_comboBox = new GridBagConstraints();
				gbc_comboBox.gridwidth = 5;
				gbc_comboBox.insets = new Insets(0, 0, 5, 5);
				gbc_comboBox.fill = GridBagConstraints.BOTH;
				gbc_comboBox.gridx = 0;
				gbc_comboBox.gridy = 0;
				Port0.add(comboBoxPort0, gbc_comboBox);
			
			btnCDPort0 = new JButton("Connect");
			btnCDPort0.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(tabbedPane.getBackgroundAt(1)== Color.GREEN)
					{
						//tabbedPane.setBackgroundAt(1, Color.RED);
						//buttonPort0CD.setText("Connect");
						con[0].close();
					}
					else
					{
						//JComboBox cb = (JComboBox)e.getSource();
						//String petName = (String)cb.getSelectedItem();
						params[0].setPortName((String)comboBoxPort0.getSelectedItem());
						con[0] = new SerialConnection(params[0]);
						try {
							con[0].open();
							//tabbedPane.setBackgroundAt(1, Color.GREEN);
						//buttonPort0CD.setText("Disconnect");
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							///e1.printStackTrace();
						}
						
					}
				}
			});
			GridBagConstraints gbc_button = new GridBagConstraints();
			gbc_button.insets = new Insets(0, 0, 5, 5);
			gbc_button.gridx = 5;
			gbc_button.gridy = 0;
			Port0.add(btnCDPort0, gbc_button);
			btnconfigPort0 = new JButton("Config");
			GridBagConstraints gbc_btnPort0config = new GridBagConstraints();
			gbc_btnPort0config.insets = new Insets(0, 0, 5, 0);
			gbc_btnPort0config.gridx = 6;
			gbc_btnPort0config.gridy = 0;
			Port0.add(btnconfigPort0, gbc_btnPort0config);
			for(String port : ports){
        	comboBoxPort0.addItem(port);
        }	
	
		JPanel Port1 = new JPanel();
		tabbedPane.addTab("Port1", null, Port1, null);
		tabbedPane.setBackgroundAt(2, Color.RED);
		GridBagLayout gbl_Port1 = new GridBagLayout();
		gbl_Port1.columnWidths = new int[]{0, 0, 74, 145, 145, 0};
		gbl_Port1.rowHeights = new int[]{31, 0};
		gbl_Port1.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_Port1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		Port1.setLayout(gbl_Port1);
		
		JButton button_2 = new JButton("Connect");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			if(tabbedPane.getBackgroundAt(2)== Color.GREEN)
			{
				tabbedPane.setBackgroundAt(2, Color.RED);
			}
			else
			{
				tabbedPane.setBackgroundAt(2, Color.GREEN);
			}

			}
		});
		GridBagConstraints gbc_button_2 = new GridBagConstraints();
		gbc_button_2.insets = new Insets(0, 0, 0, 5);
		gbc_button_2.gridx = 0;
		gbc_button_2.gridy = 0;
		Port1.add(button_2, gbc_button_2);
		
		JButton button_3 = new JButton("Disconnect");
		GridBagConstraints gbc_button_3 = new GridBagConstraints();
		gbc_button_3.insets = new Insets(0, 0, 0, 5);
		gbc_button_3.gridx = 1;
		gbc_button_3.gridy = 0;
		Port1.add(button_3, gbc_button_3);
		
		JButton button_1 = new JButton("Config");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.insets = new Insets(0, 0, 0, 5);
		gbc_button_1.gridx = 2;
		gbc_button_1.gridy = 0;
		Port1.add(button_1, gbc_button_1);
		for(int i=0;i<numPorts;i++){
		params[i] = new SerialParameters();
		//params[i].setPortName(portname);
		params[i].setBaudRate(SerialPort.BAUDRATE_19200);
		params[i].setDatabits(SerialPort.DATABITS_8);
		params[i].setParity(SerialPort.PARITY_NONE);
		params[i].setStopbits(SerialPort.STOPBITS_1);
		params[i].setEncoding(Modbus.SERIAL_ENCODING_RTU);
		params[i].setEcho(false);
		}

	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
