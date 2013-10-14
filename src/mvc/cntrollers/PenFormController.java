package mvc.cntrollers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import sun.org.mozilla.javascript.internal.Context;
import sun.org.mozilla.javascript.internal.ContextFactory.Listener;
import javax.swing.JOptionPane;

import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.ModbusIOException;
import net.wimpi.modbus.ModbusSlaveException;
import net.wimpi.modbus.io.ModbusSerialTransaction;
import net.wimpi.modbus.msg.ReadMultipleRegistersRequest;

import mvc.PenFormMvc;
import mvc.views.*;

public class PenFormController implements Listener, ActionListener, ListSelectionListener{
	// root panel
	JFrame panel;
	 private PenFormMvc mvc = null;	
		Timer myTimer = null;// new Timer(taskDelay); // Создаем таймер
		int workTime = 0;
		int isSimulation = 0;
	/**
	 * @param args
	 */
     public PenFormController(PenFormMvc mvc) {
     	this.mvc = mvc;
   //          initialize(parientFrame);
     }
 	
     void mainwork() {
		// Тут чтото делаем очень важное!
		if (isSimulation > 0) {

			// for (int i = 0; i < 1000; i += 1) {
			// series.add(i, Math.sin(i));
			// series_cos.add(i, Math.cos(i));
			this.mvc.getView().dataset.addValue(Math.sin(isSimulation * .1 + Math.random() * 0.1),
					"Tok 1 ", " " + (isSimulation - 1));
			this.mvc.getView().dataset.addValue(
					Math.cos(isSimulation * .1 + Math.random() * 0.2 - 0.1),
					"Series cos", " " + (isSimulation - 1));
			this.mvc.getView().dataset.addValue(Math.cos(isSimulation * .1 + Math.PI / 10),
					"Series Ideal", " " + (isSimulation - 1));
			if (isSimulation >= ((Integer) this.mvc.getView().windowChartWidth.getValue())) {
				this.mvc.getView().dataset.removeColumn(" "
						+ (isSimulation - ((Integer) this.mvc.getView().windowChartWidth
								.getValue())));
				// System.out.println("Delete "+i);
			}
			this.mvc.getView().listModel.addElement("Word " + isSimulation + "=" + isSimulation);
			int index = this.mvc.getView().listModel.size() - 1;
			this.mvc.getView().listOutput.setSelectedIndex(index);
			this.mvc.getView().listOutput.ensureIndexIsVisible(index);
			if (this.mvc.getView().listModel.size() > 20)
				this.mvc.getView().listModel.clear();

			isSimulation++;
		} else {
			this.mvc.getModel().mainwork();
	
		}
	}
 	/**
 	 * Initialize the contents of the frame.
 	 */
 	ActionListener taskPerformer = new ActionListener() {
 		public void actionPerformed(ActionEvent evt) {
 			// ...Perform a task...
 			// System.out.println("Times ap");
// 			this.mvc.getView().btnStartStop.setText(((isSimulation > 0) ? "Simulation... "
// 					: "Work... ") + (++workTime) + " cikles. Stop?");
 			mainwork();
 		}
 	};

//	 public static void main(String[] args) {
//		// TODO Auto-generated method stub
//       PenFormController controller = new PenFormController();
//       controller.panel = new JFrame();  
//       PenFormView view = new PenFormView(controller.panel);
//
//
//       controller.panel.setTitle("Super puper form");
//       controller.panel.setBounds(100, 100, 700, 514);
//       controller.panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//       
//       controller.panel.add(view);
//       controller.panel.setVisible(true);
// //      view.main(args);
//	}
	public void contextCreated(Context arg0) {
		// TODO Auto-generated method stub
		
	}
	public void contextReleased(Context arg0) {
		// TODO Auto-generated method stub
		
	}

	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
        
        if(source==mvc.getView().btnStartStop) {
            JOptionPane.showMessageDialog(mvc, "Нажата кнопка Start");
			//btnStartStop.addActionListener(new ActionListener() {
			//	public void actionPerformed(ActionEvent e) {
					// final JButton jb = (JButton) (e.getSource());
	
					if ("Start".equals(mvc.getView().btnStartStop.getText())) {
						if (myTimer == null) {
							myTimer = new Timer((Integer) mvc.getView().ajustPeriod.getValue(),
									taskPerformer);
						}
						myTimer.setDelay((Integer) mvc.getView().ajustPeriod.getValue());
						if (null == ((String) mvc.getView().comboBoxPort0.getSelectedItem())) {
							isSimulation = 1;
						} else isSimulation = mvc.getModel().mainwork();
//							paramsCom0 = new SerialParameters();
//	
//							paramsCom0.setPortName((String) mvc.getView().comboBoxPort0
//									.getSelectedItem());
//							paramsCom0.setBaudRate(SerialPort.BAUDRATE_19200);
//							paramsCom0.setDatabits(SerialPort.DATABITS_8);
//							paramsCom0.setParity(SerialPort.PARITY_NONE);
//							paramsCom0.setStopbits(SerialPort.STOPBITS_1);
//							paramsCom0.setEncoding(Modbus.SERIAL_ENCODING_RTU);
//							paramsCom0.setEcho(false);
//							// Open the connection
//							conCom0 = new SerialConnection(paramsCom0);
//							try {
//								conCom0.open();
//							} catch (Exception e2) {
//								// TODO Auto-generated catch block
//								e2.printStackTrace();
//								if (null != paramsCom0)
//									conCom0.close();
//								paramsCom0 = null;
//								isSimulation = 1;
//	
//								return;
//							}
//							System.out.println("Get DA-555 id");
//							req = new DA555ReadID(2);
//							req.setUnitID(unitid);
//							req.setHeadless();
//							trans = new ModbusSerialTransaction(conCom0);
//							trans.setRequest(req);
//							try {
//								trans.execute();
//	
//							} catch (ModbusIOException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//								if (null != paramsCom0)
//									conCom0.close();
//								paramsCom0 = null;
//								isSimulation = 1;
//								// return;
//							} catch (ModbusSlaveException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//								if (null != paramsCom0)
//									conCom0.close();
//								paramsCom0 = null;
//								isSimulation = 1;
//								// return;
//	
//							} catch (ModbusException e1) {
//								// TODO Auto-generated catch block
//								e1.printStackTrace();
//								if (null != paramsCom0)
//									conCom0.close();
//								paramsCom0 = null;
//								isSimulation = 1;
//								// return;
//	
//							}
//							if (paramsCom0 != null) {
//								rres = trans.getResponse();
//								InputRegister[] registers = new Register[50];
//								registers = rres.getRegisters();
//								System.out.println("NCh         = "
//										+ registers[0].toBytes()[0]);
//								System.out.println("NInK        = "
//										+ registers[0].toBytes()[1]);// NInK:=ComBufRX[4];
//								System.out.println("PrgrmDate   = "
//										+ registers[1].toBytes()[0]);// PrgrmDate:=ComBufRX[5];
//								System.out.println("PrgrmMounth = "
//										+ registers[1].toBytes()[1]);// PrgrmMounth:=ComBufRX[6];
//								System.out.println("PrgrmYear   = "
//										+ (2000 + registers[2].toBytes()[0]));// PrgrmYear:=2000+ComBufRX[7];
//							}
//						}
							mvc.getView().btnStartStop.setText("Starting... Stop?");
						myTimer.start();
					} else {
						mvc.getView().btnStartStop.setText("Start");
//						if (null != paramsCom0)
//							conCom0.close();
//						paramsCom0 = null;
						isSimulation = 0;
						myTimer.stop();
					}
			//	}
			//});

        }
//        else if(source==mvc.getView().cancelButton) {
//            JOptionPane.showMessageDialog(mvc, "Нажата кнопка CANCEL");
//        }
        else System.out.println("ActionPerformed");		
	}

}
