package da555;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.MatchResult;

import jssc.SerialPort;
import jssc.SerialPortList;
import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.ModbusIOException;
import net.wimpi.modbus.ModbusSlaveException;
import net.wimpi.modbus.io.ModbusSerialTransaction;
import net.wimpi.modbus.msg.DA555ReadID;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.msg.WriteMultipleRegistersRequest;
import net.wimpi.modbus.msg.WriteMultipleRegistersResponse;
import net.wimpi.modbus.msg.WriteSingleRegisterRequest;
import net.wimpi.modbus.msg.WriteSingleRegisterResponse;
import net.wimpi.modbus.net.SerialConnection;
import net.wimpi.modbus.procimg.InputRegister;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.procimg.SimpleProcessImage;
import net.wimpi.modbus.procimg.SimpleRegister;
import net.wimpi.modbus.util.SerialParameters;

public class PDA5xx_Defs {
	static List<MyMaches> myMaches = new ArrayList<MyMaches>();

	/**
	 * @param args
	 */
	private static Integer getValue(String key) {
		for (MyMaches lst : myMaches) {
			if (lst.key.equalsIgnoreCase(key))
				return lst.value;
		}
		return 0;
	}

	public static void main(String[] args) {
//		System.setProperty("net.wimpi.modbus.debug","true");
		SerialConnection con = null;
		ModbusSerialTransaction trans = null;
		ModbusRequest req = null;
		ModbusResponse rres = null;
		SimpleProcessImage spi = null;
		// Write Multiple Registers(Words)
		WriteMultipleRegistersRequest write_mreq = null;
		WriteMultipleRegistersResponse write_mres = null;
		SerialParameters params = new SerialParameters();
		String portname = null;
		// new Register[100];
		int currRegNum = 0;
		String fileName = "defs_1k.cfg";
		Integer WR_DA5XX_CC = 0;
		BufferedReader reader;
		String line;
		try {
			reader = new BufferedReader(new FileReader(fileName));

			try {
				while ((line = reader.readLine()) != null) {
					if (line.startsWith("//")) {
						// System.out.println(line);
					} else if (line.startsWith("$")) {
						System.out.println(line);
						break;
					} else if (line.startsWith("#define !")) {
						Scanner scanner = new Scanner(line);
						scanner.next();// Define
						String key = scanner.next().substring(1);
						Integer value = new Integer(scanner.next().substring(1));
						String comment = "";
						if (line.indexOf("//") > 0
								&& line.indexOf("//") < (line.length() - 3))
							comment = line.substring(line.indexOf("//"));
						scanner.close();
						myMaches.add(new MyMaches(key, value, comment));
					} else if (line.length() > 5)
						System.out.println(line);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// System.out.println("----=====DEFS=====----");
			//
			// for (MyMaches lst : myMaches) {
			// lst.Show();
			// }

			String[] ports = SerialPortList.getPortNames();
			// System.out.println("----=====PORTS=====----");
			for (String port : ports) {
				if (port.endsWith(getValue("DA5XX_COM").toString()))
					portname = port;
				// System.out.println(port);
			}

			System.out.println("DA5XX_COM=" + getValue("DA5XX_COM"));
			if (portname == null) {
				System.out.println("Port " + getValue("DA5XX_COM")
						+ " not found");
				return;
			} else
				System.out.println(portname);
			int unitid = getValue("DA5XX_ADR");
			Register[] out_registers = null;
			params.setPortName(portname);
			params.setBaudRate(SerialPort.BAUDRATE_19200);
			params.setDatabits(SerialPort.DATABITS_8);
			params.setParity(SerialPort.PARITY_NONE);
			params.setStopbits(SerialPort.STOPBITS_1);
			params.setEncoding(Modbus.SERIAL_ENCODING_RTU);
			params.setEcho(false);
			if (Modbus.debug) {
				System.out.println("Encoding [" + params.getEncoding() + "]");
			}

			// 4. Open the connection
			con = new SerialConnection(params);
			try {
				con.open();
				// /777
				System.out.println("Get DA-555 id");
				req = new DA555ReadID(100);
				req.setUnitID(unitid);
				req.setHeadless();
				trans = new ModbusSerialTransaction(con);
				trans.setRequest(req);
				trans.execute();
				rres = trans.getResponse();
				// System.out.println("Decode...");
				InputRegister[] registers = rres.getRegisters();
				int NCh = registers[0].toBytes()[0];
				System.out
						.println("NCh         = " + registers[0].toBytes()[0]);
				System.out
						.println("NInK        = " + registers[0].toBytes()[1]);// NInK:=ComBufRX[4];
				System.out
						.println("PrgrmDate   = " + registers[1].toBytes()[0]);// PrgrmDate:=ComBufRX[5];
				System.out
						.println("PrgrmMounth = " + registers[1].toBytes()[1]);// PrgrmMounth:=ComBufRX[6];
				System.out.println("PrgrmYear   = "
						+ (2000 + registers[2].toBytes()[0]));// PrgrmYear:=2000+ComBufRX[7];

				// TODO Auto-generated method stub
			} catch (Exception ex) {
				con.close();
				Logger.getLogger(MaticProDA555.class.getName()).log(
						Level.SEVERE, null, ex);
			}
			try {
				while ((line = reader.readLine()) != null) {
					if (line.startsWith("//")) {
						// System.out.println(line);
					} else if (line.startsWith("$")) {
						// System.out.println(line);
					} else if (line.startsWith("! WR_DA5XX_CC")) {
						//System.out.println(line);
						Scanner scanner = new Scanner(line);
						// scanner.delimiter(" ! ");
						scanner.next();
						scanner.next();
						scanner.next();
						WR_DA5XX_CC = new Integer(scanner.next().substring(0));
						System.out.println("WR_DA5XX_CC = " + WR_DA5XX_CC);
						out_registers = new Register[WR_DA5XX_CC];
						currRegNum = 0;
						scanner.close();
					} else if (line.startsWith("! DA5XX !")) {
//						System.out.println(line);
						Scanner scanner = new Scanner(line);
						// scanner.delimiter(" ! ");
						scanner.next();
						scanner.next();
						scanner.next();
						String fnName = scanner.next().substring(0);// System.out.println("fnName"+fnName);
						scanner.next();
						String pName = scanner.next().substring(0);// System.out.println("pName"+pName);
						scanner.next();
						Integer value = new Integer(scanner.next().substring(0));
						String comment = "";
						if (line.indexOf("//") > 0
								&& line.indexOf("//") < (line.length() - 3))
							comment = line.substring(line.indexOf("//"));
						scanner.close();
	//					System.out.println("Write fn=" + getValue(fnName)
		//						+ " param=" + getValue(pName) + " value="
			//					+ value + " comment=" + comment.substring(2));
						out_registers[currRegNum++] = new SimpleRegister(value);
//						WriteSingleRegisterRequest WriteReq = null; 
//						WriteSingleRegisterResponse WriteRes = null;
////						SimpleRegister MyReg = new SimpleRegister(1);
//						//3. Prepare the request
//						WriteReq = new WriteSingleRegisterRequest(getValue(pName),out_registers[currRegNum])	;
//						WriteReq.setUnitID( unitid );
//						WriteReq.setHeadless();
////						WriteReq.setReference(getValue(pName));  //register number
////							WriteReq.setRegister(out_registers[currRegNum]);
//
//						//4. Prepare the transaction
////						trans = new ModbusTCPTransaction(con);
//						//trans.setRequest(ReadReq);
//						trans = new ModbusSerialTransaction(con);
//						trans.setRequest(WriteReq);
//						
//						trans.setRetries(1);
//									  
//						try {
//							trans.execute();
//							System.out.println("Getting Response….");
//							WriteRes = ( WriteSingleRegisterResponse ) trans.getResponse();
//						} catch (ModbusIOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (ModbusSlaveException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (ModbusException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
						// myMaches.add(new MyMaches(key, value, comment));
					} else if (line.startsWith("! stop  !")) {
						System.out.println("-=STOP=-");
						try {
//							con.open();
							System.setProperty("net.wimpi.modbus.debug","true");
							write_mreq = new WriteMultipleRegistersRequest();
							write_mreq.setDataLength(WR_DA5XX_CC);
							write_mreq.setReference(getValue("P001"));
							write_mreq.setRegisters(out_registers);
							write_mreq.setUnitID(unitid);
							write_mreq.setHeadless();
							// Preparing Transaction
							System.out.println("Preparing Transaction….");
							trans = new ModbusSerialTransaction(con);
							trans.setRequest(write_mreq);
							trans.setRetries(1);
							// Execute Transaction
							System.out.println("Executing Transaction….");
							trans.execute();
							System.out.println(" Sent Request: "
									+ write_mreq.getHexMessage());
							// Get Response
							System.out.println(" Getting Response…. ");
							write_mres = (WriteMultipleRegistersResponse) trans
									.getResponse();
							System.out
									.println(" Response "
											+ Integer.parseInt((write_mres
													.getFunctionCode() + ""),
													16) + " msg: "
											+ write_mres.getHexMessage());
							System.out.println("Transaction Completed.");
							// Evaluate Response
							System.out.println("Evaluating Response….");
							if (write_mres.getFunctionCode() == 16) {
								// res_flag = true;
								System.out.println("Transaction Success!");
							} else {
								// res_flag = false;
								System.out.println("Transaction Failed!");
							}
						} catch (Exception e) {
							System.out
									.println(" Error in Multiple Write Operation : "
											+ e.getMessage());
							e.printStackTrace();
							// res_flag = false;
							// respons_str = "exp" ;
						}
						// return res_flag;
						// }
					}
					// }
					else if (line.length() > 5)
						System.out.println(line);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
