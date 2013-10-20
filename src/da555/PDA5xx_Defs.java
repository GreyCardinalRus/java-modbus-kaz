package da555;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jssc.SerialPortList;
import net.wimpi.modbus.io.ModbusSerialTransaction;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.msg.WriteMultipleRegistersRequest;
import net.wimpi.modbus.net.SerialConnection;
import net.wimpi.modbus.util.SerialParameters;

public class PDA5xx_Defs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SerialConnection con = null;
		ModbusSerialTransaction trans = null;
		ModbusRequest req = null;
		ModbusResponse rres = null;
		WriteMultipleRegistersRequest wmreq = null;
		SerialParameters params = new SerialParameters();
		String portname = null;
		// String stopWord = "$10 за лабу"
		List<String> strings = new ArrayList<String>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader("defs_1k.cfg"));
			String line;
			try {
				while ((line = reader.readLine()) != null) {
					if (!line.startsWith("//")) {
						System.out.println(line);
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] ports = SerialPortList.getPortNames();
		if (ports.length > 0) {
			portname = ports[0];
		}
		params.setPortName(portname);
		// TODO Auto-generated method stub

	}

}
