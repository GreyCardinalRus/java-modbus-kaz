package mvc.cntrollers;

import java.io.FileReader;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import mvc.models.ModbusParameter;
import mvc.models.ParameterForm;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;

public class TestModBusParametersController {
	JFrame panel;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
//				// TODO Auto-generated method stub
		TestModBusParametersController controller = new TestModBusParametersController();
	       JFrame frame = new JFrame();
	       controller.panel = frame; 
	       
//		       PenFormView view = new PenFormView(controller.panel);
		//
		//
		       controller.panel.setTitle("Super puper form");
	       controller.panel.setBounds(100, 100, 700, 514);
	       controller.panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       JPanel pPanel = new JPanel();
//		       
	   	try {
			SAXBuilder parser = new SAXBuilder();
			FileReader fr = new FileReader("modbusParameters.xml");
			Document rDoc = parser.build(fr);

			List<Element> groups = rDoc.getRootElement().getChildren();
			for (int ig = 0; ig < groups.size(); ++ig) {
				// System.out.println(temp.get(i).getName());
				System.out.println(groups.get(ig)
						.getAttributeValue("GroupName"));
				List<Element> params = groups.get(ig).getChildren();
				for (int ic = 0; ic < params.size(); ++ic) {
					Element param = params.get(ic);
					// System.out.println(temp.get(i).getName());
					//ModbusParameter mbp=;
					List<Attribute> attrbts = param.getAttributes();
					
					for (int ia = 0; ia < attrbts.size(); ++ia) {
						System.out.print(" " + attrbts.get(ia).getName()
								+ " = " + attrbts.get(ia).getValue());
						}
					
				     //ParameterForm pf = ;
					//controller.panel.add(new JSeparator());
//					JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
//					frame.getContentPane().add(internalFrame, BorderLayout.NORTH);
//					internalFrame.setVisible(true);
//					
//					internalFrame.getContentPane().add(new ParameterForm(new ModbusParameter(param)));
					//controller.panel.getContentPane().add(new ParameterForm(new ModbusParameter(param),pPanel));
					new ParameterForm(new ModbusParameter(param),pPanel);
					System.out.println();
				}
				
				controller.panel.getContentPane().add(pPanel);	
			}
		} catch (Exception ex) {
			// file not exist!
			System.out.println(ex.getMessage());
		}

		       controller.panel.setVisible(true);
		// //      view.main(args);
//			}
	}

}