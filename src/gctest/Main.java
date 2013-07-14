package gctest;

import java.io.IOException;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.io.ModbusSerialTransaction;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;
import net.wimpi.modbus.net.SerialConnection;
import net.wimpi.modbus.util.SerialParameters;
/**
 * 
 * @author scream3r
 */
public class Main {
	private static SerialPort serialPortMaster = null, serialPortSlaver = null;

    
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		// Method getPortNames() returns an array of strings. Elements of the
		// array is already sorted.
		char ch ;
		int code ;
		String[] portNames = SerialPortList.getPortNames();
		if (portNames.length == 0) {
			System.out.println("No serial port found");
		} else if (portNames.length == 1) {
			System.out.println("Found 1 serial port");
			for (int i = 0; i < portNames.length; i++) {
				System.out.println(portNames[i]);

				SerialPort serialPort = new SerialPort(portNames[i]);
				try {
					System.out.println("Port opened: " + serialPort.openPort());
					System.out.println("Params setted: "
							+ serialPort.setParams(SerialPort.BAUDRATE_9600,
									SerialPort.DATABITS_8,
									SerialPort.STOPBITS_1,
									SerialPort.PARITY_NONE));
					System.out
							.println("\"Hello World!!!\" successfully writen to port: "
									+ serialPort.writeBytes("Hello World!!!"
											.getBytes()));
					System.out
							.println("Port closed: " + serialPort.closePort());
				} catch (SerialPortException ex) {
					System.out.println(ex);
				}
			}

		} else if (portNames.length == 2) {
			System.out.println("Found 2 serial port");
			for (int i = 0; i < portNames.length; i++) {
				//System.out.print(portNames[i]);

				if (0 == i) {
					serialPortMaster = new SerialPort(portNames[i]);
					try {
						System.out.print(portNames[i] +"  Port opened: "
								+ serialPortMaster.openPort());
						System.out.println("  Params setted: "
								+ serialPortMaster.setParams(9600, 8, 1, 0));
//						// Включаем аппаратное управление потоком
//						serialPortMaster
//								.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN
//										| SerialPort.FLOWCONTROL_RTSCTS_OUT);
						System.out
								.println("\"Hello World,serialPortMaster!!!\" successfully writen to port: "
										+ serialPortMaster
												.writeBytes("Hello World!!!"
														.getBytes()));
						// Устанавливаем ивент лисенер и маску
						serialPortMaster.addEventListener(new PortReader(),
								SerialPort.MASK_RXCHAR);
						// System.out
						// .println("Port closed: " +
						// serialPortMaster.closePort());
					} catch (SerialPortException ex) {
						System.out.println(ex);
					}
				} else {
					serialPortSlaver = new SerialPort(portNames[i]);
					try {
						System.out.print(portNames[i] +"  Port opened: "
								+ serialPortSlaver.openPort());
						System.out.println("  Params setted: "
								+ serialPortSlaver.setParams(9600, 8, 1, 0));
//						// Включаем аппаратное управление потоком
//						serialPortSlaver
//								.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN
//										| SerialPort.FLOWCONTROL_RTSCTS_OUT);
						System.out
								.println("\"Hello World, serialPortSlaver!!!\" successfully writen to port: "
										+ serialPortSlaver
												.writeBytes("Hello World!!!"
														.getBytes()));
						// Устанавливаем ивент лисенер и маску
						// serialPortMaster.addEventListener(new PortReader(),
						// SerialPort.MASK_RXCHAR);
						// System.out
						// .println("Port closed: " +
						// serialPortMaster.closePort());
					} catch (SerialPortException ex) {
						System.out.println(ex);
					}
				}
			}
			try {
				while ( -1 != (code = System.in.read ()) )
				{
					try {
						System.out
						.println("\"Hello World,serialPortMaster!!!\" successfully writen to port: "
								+ serialPortMaster
										.writeBytes("Hello World!!!"
												.getBytes()));
					} catch (SerialPortException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ch = (char) code ;
				    System.out.println ( "you pressed: '" + ch + "'\n" ) ;

				    // выйти когда нажато 'q'
				    if ( 'q' == ch )
				    {
				        break;//System.exit ( 0 ) ;
				    }
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (null != serialPortMaster)
				try {
					System.out.println("Port serialPortMaster closed: "
							+ serialPortMaster.closePort());
				} catch (SerialPortException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (null != serialPortSlaver)
				try {
					System.out.println("Port serialPortSlaver closed: "
							+ serialPortSlaver.closePort());
				} catch (SerialPortException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		}
	}

	private static class PortReader implements SerialPortEventListener {

		public void serialEvent(SerialPortEvent event) {
			if (event.isRXCHAR() && event.getEventValue() > 0) {
				try {
					// Получаем ответ от устройства, обрабатываем данные и т.д.
					String data = serialPortMaster.readString(event
							.getEventValue());
					System.out.println("Read: "+data);
					// И снова отправляем запрос
					serialPortMaster.writeString("Get data");
				} catch (SerialPortException ex) {
					System.out.println(ex);
				}
			}
		}
	}

}