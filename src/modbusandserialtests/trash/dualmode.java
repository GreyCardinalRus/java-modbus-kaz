package modbusandserialtests.trash;

import java.awt.EventQueue;
import jssc.SerialPort;
import jssc.SerialPortList;


import javax.swing.JFrame;

import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.io.ModbusSerialTransaction;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;
import net.wimpi.modbus.net.SerialConnection;
import net.wimpi.modbus.util.SerialParameters;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JInternalFrame;

public class dualmode {

	private JFrame frmDualmode;
	SerialConnection SerialCon = null;
	SerialParameters SerialParams = new SerialParameters();
	
	SerialConnection ModBuscon = null;
	SerialParameters ModBusParams = new SerialParameters();
	ModbusSerialTransaction trans = null;
	ReadInputRegistersRequest req = null;
	ReadInputRegistersResponse res = null;

	int unitid = 0;
	int ref = 0;
	int count = 0;
	int repeat = 1;
	
	private final Action action = new SwingAction();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dualmode window = new dualmode();
					window.frmDualmode.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public dualmode() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDualmode = new JFrame();
		frmDualmode.setTitle("DualMode");
		frmDualmode.setBounds(100, 100, 450, 300);
		frmDualmode.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_serial = new JPanel();
		panel_serial.setToolTipText("Serial");
		frmDualmode.getContentPane().add(panel_serial, BorderLayout.NORTH);
	    String[] ports = SerialPortList.getPortNames();
		
		JLabel lblNewLabel = new JLabel("New label");
		panel_serial.add(lblNewLabel);
		
		JPanel panel_ModBus = new JPanel();
		frmDualmode.getContentPane().add(panel_ModBus, BorderLayout.SOUTH);
		
		final JButton btnSerialConnectDisconnect = new JButton("Connect");
		frmDualmode.getContentPane().add(btnSerialConnectDisconnect, BorderLayout.CENTER);
		
		final JButton buttonSettingSerial = new JButton();
		frmDualmode.getContentPane().add(buttonSettingSerial, BorderLayout.EAST);
		buttonSettingSerial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

//				        DialogSettings dialogSettings = new DialogSettings(frmDualmode, portName, baudRate, dataBits, stopBits, parity);
				    }//GEN-LAST:event_jButtonSettingsActionPerformed

			
		});
		buttonSettingSerial.setAction(action);
		buttonSettingSerial.setText("Settings");
		buttonSettingSerial.setPreferredSize(new Dimension(75, 28));
		buttonSettingSerial.setFont(new Font("Dialog", Font.PLAIN, 11));
		buttonSettingSerial.setEnabled(true);
		
		JComboBox jComboBoxPortName = new JComboBox();
		frmDualmode.getContentPane().add(jComboBoxPortName, BorderLayout.WEST);
		
				btnSerialConnectDisconnect.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(SerialCon == null ) {
							//
							SerialCon = new SerialConnection(SerialParams);
							try {
								SerialCon.open();
								btnSerialConnectDisconnect.setText("Disconnect");
								buttonSettingSerial.setEnabled(false);
		
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							}
							else
							{
								SerialCon.close();
								SerialCon = null;
								btnSerialConnectDisconnect.setText("Connect");
								buttonSettingSerial.setEnabled(true);
							}
					}
				});
 //       String[] ports = SerialPortList.getPortNames();
        if(ports.length > 0){
        	SerialParams.setPortName(ports[0]);//portName = ports[0];
        }
        SerialParams.setBaudRate(SerialPort.BAUDRATE_19200);
        SerialParams.setDatabits(SerialPort.DATABITS_8);
        SerialParams.setParity(SerialPort.PARITY_NONE);
        SerialParams.setStopbits(SerialPort.STOPBITS_1);
        SerialParams.setEncoding(Modbus.SERIAL_ENCODING_RTU);
        SerialParams.setEcho(false);
        for(String port : ports){
            jComboBoxPortName.addItem(port);
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
