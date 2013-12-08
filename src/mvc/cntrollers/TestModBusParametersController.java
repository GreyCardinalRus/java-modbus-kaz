package mvc.cntrollers;

import java.io.FileReader;
import java.util.List;

import javax.swing.JFrame;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

import mvc.models.ModbusParameter;
import mvc.models.ParameterForm;

public class TestModBusParametersController {
	JFrame panel;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
//				// TODO Auto-generated method stub
		TestModBusParametersController controller = new TestModBusParametersController();
	       controller.panel = new JFrame(); 
//		       PenFormView view = new PenFormView(controller.panel);
		//
		//
		       controller.panel.setTitle("Super puper form");
	       controller.panel.setBounds(100, 100, 700, 514);
	       controller.panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
					ModbusParameter mbp=new ModbusParameter(param);
					List<Attribute> attrbts = param.getAttributes();
					
					for (int ia = 0; ia < attrbts.size(); ++ia) {
						System.out.print(" " + attrbts.get(ia).getName()
								+ " = " + attrbts.get(ia).getValue());
						}
					
				     ParameterForm pf = new ParameterForm(mbp);
					  controller.panel.add(pf);
					System.out.println();
				}
				
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
