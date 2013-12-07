package trash;
import org.jdom2.*;
import org.jdom2.output.*;
import org.jdom2.input.*;
import java.io.*;
import java.util.List;
 
public class XmlIsSimple {
 
    public static void main(String[] args) {
        try {
            SAXBuilder parser = new SAXBuilder();
            FileReader fr = new FileReader("myoutput.xml");
            Document rDoc = parser.build(fr);
            System.out.println(rDoc.getRootElement().getName());
            List<Element> temp = rDoc.getRootElement().getChildren();
            for (int i = 0; i < temp.size(); ++i) {
                System.out.println(temp.get(i).getName());
            }
        }
        catch (Exception ex) {
        	// file not exist!
            System.out.println(ex.getMessage());
            Element rootElement = new Element("notebooks");
            Document doc = new Document(rootElement);
            rootElement.setAttribute("color", "black");
            rootElement.setAttribute(new Attribute("size", "13.3"));	
            //rootElement.addContent(new Element("acer"));
            //	rootElement.addContent(new Element("acer").setAttribute("price", "cheap"));
            rootElement.addContent(new Element("acer").
                    setAttribute("price", "cheap").
                    setAttribute("quality", "bad"));
            
            Element elAsus = new Element("asus");
            elAsus.setAttribute("price", "normal");
            elAsus.setAttribute("quality", "normal");
            rootElement.addContent(elAsus);
            Element elSony = new Element("sony");
            elSony.setAttribute("price", "expensive");
            elSony.setAttribute("quality", "good");
            rootElement.addContent(elSony);	
            
//            Element elAcer = rootElement.getChild("acer");
//            elAcer.getAttribute("quality").setValue("normal");
  //          Если нам необходимо получить доступ к какому либо элемент, а ссылка на него утеряна, можно сделать следующее:

            	Element elAcer = rootElement.getChild("acer");
            	elAcer.getAttribute("quality").setValue("normal");
//	            	Кроме элементов, а так же их атрибутов можно добавлять просто текст или комментарии:
            	elAsus.addContent("Asus Eee PC");
            	rootElement.addContent(new Comment("This is just comment!"));
 //           	Теперь когда у нас есть готовый XML документ, было бы не плохо сохранить его в файл. Для этого нужно воспользоватся классом XMLOutputter. Вот пример сохранения созданного документа:

             		try {
            		    XMLOutputter outputter = new XMLOutputter();
            		    outputter.setFormat(Format.getPrettyFormat());
            		    FileWriter fw = new FileWriter("myoutput.xml");
            		    outputter.output(doc, fw);
            		    fw.close();
            		}
            		catch (Exception ex1) {
            		    System.out.println(ex1.getMessage());
            		}
        }
    }
}