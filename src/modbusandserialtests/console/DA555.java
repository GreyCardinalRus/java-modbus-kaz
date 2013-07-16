package modbusandserialtests.console;
/***
 * Copyright 2002-2010 jamod development team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ***/


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

/**
 * Class that implements a simple commandline tool for reading an analog input.
 * 
 * @author Dieter Wimberger
 * @version @version@ (@date@)
 */
public class DA555 {
	static int NCh=0;
	private static int NMenu[] = // Количество параметров для разных конфигураций
		    {
		    30, // 1 канал
		    30, // 2 канала НЕТ!!!
		    37, // 3 канала
		    37, // 4 канала НЕТ!!!
		    43  // 5 канала
		    };
		  // Количество входных регистров для разных конфигураций
	private static int	NInReg[] = // Массив количества входных регистров для разных конфигураций
		    {
		    ((9*2+1)*1+1),
		    ((9*2+1)*2+1),
		    ((9*2+1)*3+1),
		    ((9*2+1)*4+1),
		    ((9*2+1)*5+1)
		    };
//		  StartInReg=2;						            // Начальный адрес входных регистров
//		  StartHldReg=100;						        // Начальный адрес регистров хранениЯ

	public static void main(String[] args) {

		SerialConnection con = null;
		ModbusSerialTransaction trans = null;
		ModbusRequest req = null;
		ModbusResponse rres = null;
		WriteMultipleRegistersRequest wmreq=null;

		
		String portname = null;
		int unitid = 1;
		int ref = 2;
		int count = ((9*2+1)*1+1);
		int repeat = 1;
		InputRegister[]  registers = new Register[count];
		try {

			// 1. Setup the parameters
			if (args.length < 4) {
				printUsage();
		
				String[] ports = SerialPortList.getPortNames();
		        if(ports.length > 0)    	portname=ports[0];
		        System.out.println("Use portname= "+portname+"; Unit Address = "+ unitid +"; register = "+ref+"; wordcount = "+count);
		 
				////System.exit(1);
			} else {
				try {
					portname = args[0];
					unitid = Integer.parseInt(args[1]);
					ref = Integer.parseInt(args[2]);
					count = Integer.parseInt(args[3]);
					if (args.length == 5) {
						repeat = Integer.parseInt(args[4]);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					printUsage();
					System.exit(1);
				}
			}

			// 2. Set slave identifier for master response parsing
			ModbusCoupler.getReference().setUnitID(unitid);

			System.out.println("net.wimpi.modbus.debug set to: "
					+ System.getProperty("net.wimpi.modbus.debug"));

			// 3. Setup serial parameters
			SerialParameters params = new SerialParameters();
			params.setPortName(portname);
			params.setBaudRate(SerialPort.BAUDRATE_19200);
			params.setDatabits(SerialPort.DATABITS_8);
			params.setParity(SerialPort.PARITY_NONE);
			params.setStopbits(SerialPort.STOPBITS_1);
			params.setEncoding(Modbus.SERIAL_ENCODING_RTU);
			params.setEcho(false);
			if (Modbus.debug)
				System.out.println("Encoding [" + params.getEncoding() + "]");

			// 4. Open the connection
			con = new SerialConnection(params);
			con.open();
///777
			System.out.println("Get DA-555 id");
			req  = new DA555ReadID(ref);
			req.setUnitID(unitid);
			req.setHeadless();
			trans = new ModbusSerialTransaction(con);
			trans.setRequest(req);
			trans.execute();
			rres =  trans.getResponse();
//			System.out.println("Decode...");
			registers =   rres.getRegisters();
			NCh = registers[0].toBytes()[0];
			System.out.println("NCh         = "+registers[0].toBytes()[0]);
			System.out.println("NInK        = "+registers[0].toBytes()[1]);//	          NInK:=ComBufRX[4];
			System.out.println("PrgrmDate   = "+registers[1].toBytes()[0]);//	          PrgrmDate:=ComBufRX[5];
			System.out.println("PrgrmMounth = "+registers[1].toBytes()[1]);//	          PrgrmMounth:=ComBufRX[6];
			System.out.println("PrgrmYear   = "+(2000+registers[2].toBytes()[0]));//	          PrgrmYear:=2000+ComBufRX[7];
			count = NMenu[NCh-1];
//			for (int n = 0; n < rres.getWordCount(); n++) {
//				System.out.println("Word " + n + "="
//						+ rres.getRegisterValue(n));
//////				 //(rres.getRegisterValue(n));
//			}
			
			// 5. Prepare a request
			req = new ReadInputRegistersRequest(ref, count);
			req.setUnitID(unitid);
			req.setHeadless();
			//if (Modbus.debug)
			//	System.out.println("Req.: " + req.getHexMessage());
			// 6. Prepare the transaction
			trans = new ModbusSerialTransaction(con);
			trans.setRequest(req);

			// 7. Execute the transaction repeat times
			int k = 0;
			do {
				trans.execute();

				rres =  trans.getResponse();
//				registers =  (Register[]) rres.getRegisters();
				//if (Modbus.debug)
				//	System.out.println("Response: " + res.getHexMessage());
				for (int n = 0; n < rres.getWordCount(); n++) {
					System.out.println("Word " + n + "="
						+ rres.getRegisterValue(n));
////					 //(rres.getRegisterValue(n));
				}
				k++;
			} while (k < repeat);
            // 8. modify
			
			//Register reg = new Regi;
			//registers[0].setValue(5);
//			wmreq = new WriteMultipleRegistersRequest(ref, registers);
//			wmreq.setUnitID(unitid);
//			wmreq.setHeadless();
//			
//			trans.setRequest(wmreq);
//			trans.execute();
//			wmres = (WriteMultipleRegistersResponse) trans.getResponse();
//			for (int n = 0; n < wres.getWordCount(); n++) {
//				System.out.println("Word " + n + "="
//						+ wres.getRegisterValue(n));
				//registers[n].setValue(rres.getRegisterValue(n));
//			}

			// 9. read again
			
			trans.setRequest(req);

			// 7. Execute the transaction repeat times
			k = 0; 
			do {
				trans.execute();

				rres = (ReadInputRegistersResponse) trans.getResponse();
				//if (Modbus.debug)
				//	System.out.println("Response: " + res.getHexMessage());
//				for (int n = 0; n < rres.getWordCount(); n++) {
//					System.out.println("Word " + n + "="
//							+ rres.getRegisterValue(n));
////					registers[n].setValue(rres.getRegisterValue(n));
//				}
				k++;
			} while (k < repeat);
			
			// 10. restore
			// 11. Close the connection
			con.close();

		} catch (Exception ex) {
			ex.printStackTrace();
			// Close the connection
			con.close();
		}
	}// main

	private static void printUsage() {
		System.out
				.println("java DA555 <portname [String]>  <Unit Address [int8]> <register [int16]> <wordcount [int16]> {<repeat [int]>}");
	}// printUsage

}// class SerialAITest
