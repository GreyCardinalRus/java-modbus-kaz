package da555;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;

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
		String fileName = "defs_1k.cfg";
		// String stopWord = "$10 за лабу"
		List<String> strings = new ArrayList<String>();
//	    try {
//
//	         File file = new File(fileName);
//
//	         Scanner scanner = new Scanner(file);
//
//	         while (scanner.hasNext()) {
//
//	           System.out.println(scanner.next());
//
//	         }
//
//	         scanner.close();
//
//	       } catch (FileNotFoundException e) {
//
//	         e.printStackTrace();
//
//	       }
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line;
			try {
				while ((line = reader.readLine()) != null) {
					if (line.startsWith("//")) {
						//System.out.println(line);
					}else if (line.startsWith("#define !")) {	
//						System.out.println(line);
				         Scanner scanner = new Scanner(line);
				         scanner.next();// Define
//				         scanner.hasNext();// Define
//				         scanner.hasNext();
				         System.out.print(scanner.next().substring(1));
//				         scanner.hasNext();
				         System.out.print("=");
				         System.out.print(scanner.next().substring(1));
				         if(line.indexOf("//")>0 && line.indexOf("//")<(line.length()-3))System.out.println("   "+line.substring(line.indexOf("//")));
				         else System.out.println();
//				         while (scanner.hasNext()) {
//
//				           System.out.println(scanner.next());
//
//				         }
                     
				         scanner.close();
//						 Scanner s = new Scanner(line);
//						 s.findInLine("!");
//						 MatchResult result = s.match();
//						 for (int i=1; i<=result.groupCount(); i++)
//							 System.out.println("-=D=-"+result.group(i));
//

						//System.out.println("-=DEF=- "+line.substring(9,line.indexOf('!',10))+"=");
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
