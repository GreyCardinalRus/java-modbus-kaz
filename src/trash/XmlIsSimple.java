package trash;

import org.jdom2.*;
import org.jdom2.output.*;
import org.jdom2.input.*;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class XmlIsSimple {

	public static void main(String[] args) {
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
					// System.out.println(temp.get(i).getName());
					List<Attribute> attrbts = params.get(ic).getAttributes();
					for (int ia = 0; ia < attrbts.size(); ++ia) {
						System.out.print(" " + attrbts.get(ia).getName()
								+ " = " + attrbts.get(ia).getValue());
					}
					System.out.println();
				}
				
			}
		} catch (Exception ex) {
			// file not exist!
			System.out.println(ex.getMessage());
		}
		NumberFormat nf3 = new DecimalFormat("#000");
		Element rootElement = new Element("ModBusParameters");
		rootElement.addContent(new Comment("This is just comment!"));
		Document doc = new Document(rootElement);
		Element groopElement = new Element("pInt");

		groopElement.setAttribute("GroupName",
				"Основные- пусть будут целые тут");
		rootElement.addContent(groopElement);
        int offSet = 0;
		for (int IdParam = 0; IdParam < 3; IdParam++) {
			Element elParameter = new Element("P." + nf3.format(offSet));
			elParameter.setAttribute("id", "" + offSet);
			elParameter.setAttribute("Name", "Vorona_" + offSet);
			elParameter.setAttribute("Description", "Описание параметра "
					+ offSet);
			elParameter.setAttribute("ParamType", "Integer");
			elParameter.setAttribute("MinValue", "0");
			elParameter.setAttribute("MaxValue", "100");
			elParameter.setAttribute("defaultValue", "5");
			elParameter.setAttribute("UoM", "V");
			elParameter.setAttribute("Access", "RW");
			// elAsus.addContent("Asus Eee PC");
			groopElement.addContent(elParameter);
			offSet++;
		}
	
		rootElement.addContent(new Comment("Тут будут булевые!!"));
		
		groopElement = new Element("pBool");

		groopElement.setAttribute("GroupName",
				"Основные- пусть будут логические тут");
		rootElement.addContent(groopElement);

		for (int IdParam = 0; IdParam < 3; IdParam++) {
			Element elParameter = new Element("P." + nf3.format(offSet));
			elParameter.setAttribute("id", "" + offSet);
			elParameter.setAttribute("Name", "Vorona_" + offSet);
			elParameter.setAttribute("Description", "Описание параметра "
					+ offSet);
			elParameter.setAttribute("ParamType", "Boolean");
			//elParameter.setAttribute("MinValue", "0");
			//elParameter.setAttribute("MaxValue", "100");
			elParameter.setAttribute("defaultValue", "False");
			//elParameter.setAttribute("UoM", "V");
			elParameter.setAttribute("Access", "RW");
			elParameter.setAttribute("ListValues", "Ложь, Истина");
			// elAsus.addContent("Asus Eee PC");
			groopElement.addContent(elParameter);
			offSet++;
		}
		rootElement.addContent(new Comment("Тут будут строковые!!"));
		
		groopElement = new Element("pString");

		groopElement.setAttribute("GroupName",
				"Основные- пусть будут строковые тут");
		rootElement.addContent(groopElement);

		for (int IdParam = 0; IdParam < 3; IdParam++) {
			Element elParameter = new Element("P." + nf3.format(offSet));
			elParameter.setAttribute("id", "" + offSet);
			elParameter.setAttribute("Name", "Vorona_" + offSet);
			elParameter.setAttribute("Description", "Описание параметра "
					+ offSet);
			elParameter.setAttribute("ParamType", "String");
			//elParameter.setAttribute("MinValue", "0");
			//elParameter.setAttribute("MaxValue", "100");
			elParameter.setAttribute("defaultValue", "Выбор2");
			//elParameter.setAttribute("UoM", "V");
			elParameter.setAttribute("Access", "RW");
			elParameter.setAttribute("ListValues", "Выбор1,Выбор2,Выбор3");
			// elAsus.addContent("Asus Eee PC");
			groopElement.addContent(elParameter);
			offSet++;
		}
	
		

		try {
			XMLOutputter outputter = new XMLOutputter();
			outputter.setFormat(Format.getPrettyFormat());
			FileWriter fw = new FileWriter("modbusParameters.xml");
			outputter.output(doc, fw);
			fw.close();
		} catch (Exception ex1) {
			System.out.println(ex1.getMessage());
		}
	}
}
