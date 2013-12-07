package trash;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class HomeMeteostation {
public static void main(String args[]) {
for (Weather weather : WeatherBuilder.getWeather()) {
		System.out.println(weather);
        }
    }
}

class Weather {
    private Date date;
    private int maxPressure, minPressure;
    private int maxTemperature, minTemperature;
 
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
 
    public void setDate(Date date) {
        this.date = date;
    }
 
    public void setMaxPressure(int pressure) {
        this.maxPressure = pressure;
    }
 
    public void setMinPressure(int pressure) {
        this.minPressure = pressure;
    }
 
    public void setMaxTemperature(int temperature) {
        this.maxTemperature = temperature;
    }
 
    public void setMinTemperature(int temperature) {
        this.minTemperature = temperature;
    }
 
    public String toString() {
        return String.format("%s: %d-%dC %d-%dmm",
                                dateFormat.format(date),
                                minTemperature,
                                maxTemperature,
                                minPressure,
                                maxPressure);
    }
}
 
class WeatherBuilder {
 
    public static Weather buildWeather(Node weatherNode) {
        Weather weather = new Weather();
 
        NamedNodeMap attributes = weatherNode.getAttributes();
 
        Calendar c = Calendar.getInstance();
 
        c.set(Calendar.YEAR, Integer.parseInt(attributes.getNamedItem("year").getNodeValue()));
        c.set(Calendar.MONTH, Integer.parseInt(attributes.getNamedItem("month").getNodeValue()) - 1);
        c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(attributes.getNamedItem("day").getNodeValue()));
        c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(attributes.getNamedItem("hour").getNodeValue()));
        c.set(Calendar.MINUTE, 0);
 
        weather.setDate(c.getTime());
 
        NodeList parameters = weatherNode.getChildNodes();
 
        for (int i = 0; i < parameters.getLength(); i++) {
            Node parameter = parameters.item(i);
 
            if ("PRESSURE".equals(parameter.getNodeName())) {
                String max = parameter.getAttributes().getNamedItem("max").getNodeValue();
                String min = parameter.getAttributes().getNamedItem("min").getNodeValue();
 
                weather.setMaxPressure(Integer.parseInt(max));
                weather.setMinPressure(Integer.parseInt(min));
            }
 
            if ("TEMPERATURE".equals(parameter.getNodeName())) {
                String max = parameter.getAttributes().getNamedItem("max").getNodeValue();
                String min = parameter.getAttributes().getNamedItem("min").getNodeValue();
 
                weather.setMaxTemperature(Integer.parseInt(max));
                weather.setMinTemperature(Integer.parseInt(min));
            }
        }
 
        return weather;
    }
 
    public static List<Weather> getWeather() {
        List<Weather> resList = new ArrayList<Weather>();
 
        try {
            URL url = new URL("http://xml.meteoservice.ru/export/gismeteo/point/37.xml");
            DOMParser p = new DOMParser();
            p.parse(new InputSource(url.openStream()));
            Document doc = p.getDocument();
 
            NodeList days = doc.getElementsByTagName("FORECAST");
 
            for (int i = 0; i < days.getLength(); i++) {
                resList.add(buildWeather(days.item(i)));
            }
 
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
 
        return resList;
    }
 
}