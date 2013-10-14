package mvc.models;

import jssc.SerialPort;
import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.ModbusIOException;
import net.wimpi.modbus.ModbusSlaveException;
import net.wimpi.modbus.io.ModbusSerialTransaction;
import net.wimpi.modbus.msg.DA555ReadID;
import net.wimpi.modbus.msg.ModbusRequest;
import net.wimpi.modbus.msg.ModbusResponse;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
import net.wimpi.modbus.msg.WriteMultipleRegistersRequest;
import net.wimpi.modbus.net.SerialConnection;
import net.wimpi.modbus.procimg.InputRegister;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.util.SerialParameters;
import mvc.PenFormMvc;
//import mvc.cntrollers.DA555ReadID;
//import mvc.cntrollers.InputRegister;
//import mvc.cntrollers.Register;
import net.wimpi.modbus.Modbus;
//import net.wimpi.modbus.ModbusIOException;
//import net.wimpi.modbus.ModbusSlaveException;
//import net.wimpi.modbus.io.ModbusSerialTransaction;
//import net.wimpi.modbus.msg.DA555ReadID;
//import net.wimpi.modbus.msg.ModbusRequest;
//import net.wimpi.modbus.msg.ModbusResponse;
////import net.wimpi.modbus.msg.ReadInputRegistersRequest;
//import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;
//import net.wimpi.modbus.msg.WriteMultipleRegistersRequest;
//import net.wimpi.modbus.net.SerialConnection;
//import net.wimpi.modbus.procimg.InputRegister;
//import net.wimpi.modbus.procimg.Register;
//import net.wimpi.modbus.util.SerialParameters;
public class PenFormModel {
	 private PenFormMvc mvc = null;	
		SerialParameters paramsCom0 = null;
		SerialConnection conCom0 = null;

		SerialParameters paramsCom1 = null;
		SerialConnection conCom1 = null;
		
		SerialParameters paramsCom2 = null;
		SerialConnection conCom2 = null;
		
		ModbusSerialTransaction trans = null;
		ModbusRequest req = null;
		ModbusResponse rres = null;
		WriteMultipleRegistersRequest wmreq = null;
		final int unitid = 1;
		
		public int mainwork(){
			if (null == paramsCom0) {
				paramsCom0 = new SerialParameters();

				paramsCom0.setPortName((String) mvc.getView().comboBoxPort0
						.getSelectedItem());
				paramsCom0.setBaudRate(SerialPort.BAUDRATE_19200);
				paramsCom0.setDatabits(SerialPort.DATABITS_8);
				paramsCom0.setParity(SerialPort.PARITY_NONE);
				paramsCom0.setStopbits(SerialPort.STOPBITS_1);
				paramsCom0.setEncoding(Modbus.SERIAL_ENCODING_RTU);
				paramsCom0.setEcho(false);
				// Open the connection
				conCom0 = new SerialConnection(paramsCom0);
				try {
					conCom0.open();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
					if (null != paramsCom0)
						conCom0.close();
					paramsCom0 = null;
//					isSimulation = 1;

					return 1;
				}
				System.out.println("Get DA-555 id");
				req = new DA555ReadID(2);
				req.setUnitID(unitid);
				req.setHeadless();
				trans = new ModbusSerialTransaction(conCom0);
				trans.setRequest(req);
				try {
					trans.execute();

				} catch (ModbusIOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					if (null != paramsCom0)
						conCom0.close();
					paramsCom0 = null;
					return 1;
					// return;
				} catch (ModbusSlaveException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					if (null != paramsCom0)
						conCom0.close();
					paramsCom0 = null;
					return 1;
					// return;

				} catch (ModbusException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					if (null != paramsCom0)
						conCom0.close();
					paramsCom0 = null;
					return 1;
					// return;

				}
				if (paramsCom0 != null) {
					rres = trans.getResponse();
					InputRegister[] registers = new Register[50];
					registers = rres.getRegisters();
					System.out.println("NCh         = "
							+ registers[0].toBytes()[0]);
					System.out.println("NInK        = "
							+ registers[0].toBytes()[1]);// NInK:=ComBufRX[4];
					System.out.println("PrgrmDate   = "
							+ registers[1].toBytes()[0]);// PrgrmDate:=ComBufRX[5];
					System.out.println("PrgrmMounth = "
							+ registers[1].toBytes()[1]);// PrgrmMounth:=ComBufRX[6];
					System.out.println("PrgrmYear   = "
							+ (2000 + registers[2].toBytes()[0]));// PrgrmYear:=2000+ComBufRX[7];
				}
			}
			// TODO Читаем регистры
			// 5. Prepare a request
			// откуда сколько
			// com1_bf_tx[2] = 0x04;
			// com1_bf_tx[3] = 0x4B; // 0xE8;
			// com1_bf_tx[4] = 0x00;
			// com1_bf_tx[5] = 0x06;
			int ref = 0x004B, count = 0x06;
			req = new ReadMultipleRegistersRequest(ref, count);
			req.setUnitID(unitid);
			req.setHeadless();

			// 6. Prepare the transaction
			trans = new ModbusSerialTransaction(conCom0);
			trans.setRequest(req);
			System.out.println("---");
			// 7. Execute the transaction repeat times
			try {
				trans.execute();
			} catch (ModbusIOException e) {
				// TODO Auto-generated catch block
				System.out.println("ModbusIOException");
				e.printStackTrace();
				return 1;
			} catch (ModbusSlaveException e) {
				// TODO Auto-generated catch block
				System.out.println("ModbusSlaveException");
				e.printStackTrace();
				return 1;
			} catch (ModbusException e) {
				// TODO Auto-generated catch block
				System.out.println("ModbusSlaveException");
				e.printStackTrace();
				return 1;
			}

			rres = trans.getResponse();
			for (int n = 0; n < rres.getWordCount(); n++) {
				this.mvc.getView().listModel.addElement("Word " + n + "="
						+ rres.getRegisterValue(n));
				int index = this.mvc.getView().listModel.size() - 1;
				this.mvc.getView().listOutput.setSelectedIndex(index);
				this.mvc.getView().listOutput.ensureIndexIsVisible(index);
				// System.out.println("Word " + n + "="
				// + rres.getRegisterValue(n));
			}
			return 0;
			
		}
	/**
	 * @param args
	 */
     public PenFormModel(PenFormMvc mvc) {
      	this.mvc = mvc;
    //          initialize(parientFrame);
      }


}
