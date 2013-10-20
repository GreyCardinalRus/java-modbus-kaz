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
import net.wimpi.modbus.io.ModbusSerialTransaction;
import net.wimpi.modbus.msg.DA555ReadID;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.msg.WriteMultipleRegistersRequest;
import net.wimpi.modbus.net.SerialConnection;
import net.wimpi.modbus.procimg.InputRegister;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.util.SerialParameters;


public class PDA5xx_Defs {
	static List<MyMaches> myMaches = new ArrayList<MyMaches>();
	/**
	 * @param args
	 */
	private static Integer getValue(String key)
	{
		for (MyMaches lst : myMaches) {
			if (lst.key.equalsIgnoreCase(key)) return lst.value;
		}
		return 0;
	}
	public static void main(String[] args) {
		SerialConnection con = null;
		ModbusSerialTransaction trans = null;
		ModbusRequest req = null;
		ModbusResponse rres = null;
		WriteMultipleRegistersRequest wmreq = null;
		SerialParameters params = new SerialParameters();
		String portname = null;
        InputRegister[] registers = new Register[100];
		String fileName = "defs_1k.cfg";
		// String stopWord = "$10 за лабу"

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
				         String key = scanner.next().substring(1);
//				         System.out.print(key);
//				         System.out.print("=");
				         Integer value = new Integer(scanner.next().substring(1));
//				         System.out.print(value);
				         String comment = "";
				         if(line.indexOf("//")>0 && line.indexOf("//")<(line.length()-3))comment=line.substring(line.indexOf("//"));
//				         else System.out.println();
				         scanner.close();
				         myMaches.add(new MyMaches(key, value,comment));
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
		System.out.println("----=====DEFS=====----");  

		for (MyMaches lst : myMaches) {
	            lst.Show();
	        }
	
	        String[] ports = SerialPortList.getPortNames();
			System.out.println("----=====PORTS=====----");  
			for (String port : ports) {	 
				if(port.endsWith(getValue("DA5XX_COM").toString())) portname = port;
				System.out.println(port);
			}

 		System.out.println("DA5XX_COM="+getValue("DA5XX_COM"));	
 		if(portname ==null) {
        	System.out.println("Port "+getValue("DA5XX_COM")+" not found");
        	return ;
        }
        else System.out.println(portname);	
 		int unitid = getValue("DA5XX_ADR");
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
            ///777
            System.out.println("Get DA-555 id");
            req = new DA555ReadID(100);
            req.setUnitID(unitid);
            req.setHeadless();
            trans = new ModbusSerialTransaction(con);
            trans.setRequest(req);
            trans.execute();
            rres = trans.getResponse();
//			System.out.println("Decode...");
            registers = rres.getRegisters();
            int NCh = registers[0].toBytes()[0];
            System.out.println("NCh         = " + registers[0].toBytes()[0]);
            System.out.println("NInK        = " + registers[0].toBytes()[1]);//	          NInK:=ComBufRX[4];
            System.out.println("PrgrmDate   = " + registers[1].toBytes()[0]);//	          PrgrmDate:=ComBufRX[5];
            System.out.println("PrgrmMounth = " + registers[1].toBytes()[1]);//	          PrgrmMounth:=ComBufRX[6];
            System.out.println("PrgrmYear   = " + (2000 + registers[2].toBytes()[0]));//	          PrgrmYear:=2000+ComBufRX[7];

        // TODO Auto-generated method stub
	     } catch (Exception ex) {
        con.close();
        Logger.getLogger(MaticProDA555.class.getName()).log(Level.SEVERE, null, ex);
    }
	}

}
