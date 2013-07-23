package modbusandserialtests.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.util.ResourceBundle;
import javax.swing.JButton;

import com.sun.prism.paint.Stop;

import modbusandserialtests.trash.DialogAbout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;

import jssc.SerialPort;
import jssc.SerialPortList;
import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.io.ModbusSerialTransaction;
import net.wimpi.modbus.msg.DA555ReadID;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;
import net.wimpi.modbus.msg.WriteMultipleRegistersRequest;
import net.wimpi.modbus.net.SerialConnection;
import net.wimpi.modbus.procimg.InputRegister;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.util.SerialParameters;

public class DA555GUI {
	static int numPorts=8;
	JCheckBoxMenuItem mntmPort[]=new JCheckBoxMenuItem[numPorts];
	SerialParameters params[] = new SerialParameters[numPorts];
	SerialConnection con[] = new SerialConnection[numPorts];
	private JFrame frmDaSpecailFor;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DA555GUI window = new DA555GUI();
					window.frmDaSpecailFor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DA555GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDaSpecailFor = new JFrame();
		frmDaSpecailFor.setTitle(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.frmDaSpecailFor.title")); //$NON-NLS-1$ //$NON-NLS-2$
		frmDaSpecailFor.setBounds(100, 100, 450, 300);
		frmDaSpecailFor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmDaSpecailFor.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmNewMenuItem.text")); //$NON-NLS-1$ //$NON-NLS-2$
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmExit = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmExit.text")); //$NON-NLS-1$ //$NON-NLS-2$
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDaSpecailFor.setVisible(false);	
			}
		});
		mnNewMenu.add(mntmExit);
		
		JButton btnConnect = new JButton(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.btnConnect.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JMenu mnSettings = new JMenu(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mnSettings.text")); //$NON-NLS-1$ //$NON-NLS-2$
		menuBar.add(mnSettings);
		
		JMenu mnNewMenu_2 = new JMenu(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mnNewMenu_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
		mnSettings.add(mnNewMenu_2);
		
		JCheckBoxMenuItem mntmNewMenuItem_2 = new JCheckBoxMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmNewMenuItem_2.text"));
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		for(int i=0; i < numPorts;i++) 
		{
			mntmPort[i] = new JCheckBoxMenuItem("Port_"+i); //$NON-NLS-1$ //$NON-NLS-2$
			mnSettings.add(mntmPort[i]);
			params[i] = new SerialParameters();
			//params[i].setPortName(portname);
			params[i].setBaudRate(SerialPort.BAUDRATE_19200);
			params[i].setDatabits(SerialPort.DATABITS_8);
			params[i].setParity(SerialPort.PARITY_NONE);
			params[i].setStopbits(SerialPort.STOPBITS_1);
			params[i].setEncoding(Modbus.SERIAL_ENCODING_RTU);
			params[i].setEcho(false);
			
			mntmPort[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("i="+mntmPort[1].getState());
					System.out.println(e.getID());
				}
			});
		}
			
//		JMenuItem mntmPort_0 = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmPort_0.text")); //$NON-NLS-1$ //$NON-NLS-2$
//		mnSettings.add(mntmPort_0);
//		
//		JMenuItem mntmPort_1 = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmPort_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
//		mnSettings.add(mntmPort_1);
//		
//		JMenuItem mntmPort_2 = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmPort_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
//		mnSettings.add(mntmPort_2);
//		
//		JMenuItem mntmPort_3 = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmPort_3.text")); //$NON-NLS-1$ //$NON-NLS-2$
//		mnSettings.add(mntmPort_3);
//		
//		JMenuItem mntmPort_4 = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmPort_4.text")); //$NON-NLS-1$ //$NON-NLS-2$
//		mnSettings.add(mntmPort_4);
//		
//		JMenuItem mntmPort_5 = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmPort_5.text")); //$NON-NLS-1$ //$NON-NLS-2$
//		mnSettings.add(mntmPort_5);
//		
//		JMenuItem mntmPort_6 = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmPort_6.text")); //$NON-NLS-1$ //$NON-NLS-2$
//		mnSettings.add(mntmPort_6);
//		
//		JMenuItem mntmPort_7 = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmPort_7.text")); //$NON-NLS-1$ //$NON-NLS-2$
//		mnSettings.add(mntmPort_7);
		
		JMenu mnNewMenu_1 = new JMenu(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mnNewMenu_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmNewMenuItem_1.text"));
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JMenuItem mntmAbout = new JMenuItem(ResourceBundle.getBundle("modbusandserialtests.gui.messages").getString("DA555.mntmAbout.text")); //$NON-NLS-1$ //$NON-NLS-2$
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AboutDialog dialogAbout = new AboutDialog();
				dialogAbout.setVisible(true);
			}
		});
		mnNewMenu_1.add(mntmAbout);
		menuBar.add(btnConnect);
	}

}
