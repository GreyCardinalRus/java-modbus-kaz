package modbusandserialtests.gui;

import java.util.Random;

import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class jFreeChartSample {
	public static void main(String[] args) {
		int imax=100;
		JFrame frame = new JFrame("Hi, Shishka");
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		JFreeChart chart = ChartFactory.createLineChart("Shishka", "x", "y",
				dataset);
		// Помещаем график на фрейм
		frame.getContentPane().add(new ChartPanel(chart));
		frame.setSize(400, 300);
		frame.setVisible(true);//2*Math.PI
		for (int i = 0; i < 1000; i += 1) {
			// series.add(i, Math.sin(i));
			// series_cos.add(i, Math.cos(i));
			dataset.addValue(Math.sin(i*.1+Math.random()*0.1), "Series sin", " " + i);
			dataset.addValue(Math.cos(i*.1+Math.random()*0.2-0.1), "Series cos", " " + i);
			dataset.addValue(Math.cos(i*.1+Math.PI/10), "Series Ideal", " " + i);
			if(i>=imax){
				dataset.removeColumn(" " + (i-imax));
				//System.out.println("Delete "+i);
			}
			try {
				Thread.sleep(100); // спать 1000 милисекунд.
			} catch (Exception e) {
				System.out
						.println("Что-то пошло не так, но бог здесь не причём, ибо его нет. Это просто какой-то косяк, который можно объяснить.");
			}
		}
	}
}