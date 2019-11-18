package iul.ista.pi.snake;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;
import javax.xml.crypto.dsig.keyinfo.X509Data;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.NormalDistributionFunction2D;
import org.jfree.data.general.DatasetUtils;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.data.statistics.HistogramType;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class HistogramTests extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1192139652639891377L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HistogramTests frame = new HistogramTests();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HistogramTests() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane();
		contentPane.add(tabbedPane);

		final ChartPanel tempPanel = new ChartPanel(temperatura(), true, true, true, true, true);
		tempPanel.setPreferredSize(new java.awt.Dimension(500, 270));

		final ChartPanel posParedesX = new ChartPanel(posicaoParedeX(), true, true, true, true, true);
		posParedesX.setPreferredSize(new java.awt.Dimension(500, 270));
		
		final ChartPanel posParedesY = new ChartPanel(posicaoParedeY(), true, true, true, true, true);
		posParedesX.setPreferredSize(new java.awt.Dimension(500, 270));
		
		final ChartPanel foodPoints = new ChartPanel (foodPoints(), true, true, true, true, true);
		foodPoints.setPreferredSize(new java.awt.Dimension(500, 270));
		
		JPanel posParedesPanel = new JPanel(new GridLayout(2, 1));
		
		posParedesPanel.add(posParedesX);
		posParedesPanel.add(posParedesY);

		tabbedPane.add("Temperatura", tempPanel);
		tabbedPane.add("Posição das paredes", posParedesPanel);
		tabbedPane.add("Pontuação da Comida", foodPoints);
	}

	public JFreeChart temperatura() {
		Function2D normal = new NormalDistributionFunction2D(25, 5);
		XYDataset datasetNormal = DatasetUtils.sampleFunction2D(normal, 15, 35, 100, "Normal");

		HistogramDataset datasetHistogram = new HistogramDataset();
		datasetHistogram.setType(HistogramType.SCALE_AREA_TO_1);

		datasetHistogram.addSeries("Histograma", Temperatura.getHistogramData(), 150);

		final XYItemRenderer renderer1 = new XYBarRenderer(0.2);
		renderer1.setDefaultToolTipGenerator(new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
				new DecimalFormat("0.000"), new DecimalFormat("0.000")));

		final NumberAxis x = new NumberAxis("X");
		x.setRange(13, 38);
		final ValueAxis y = new NumberAxis("Y");
		final XYPlot plot = new XYPlot(datasetHistogram, x, y, renderer1);

		final XYItemRenderer renderer2 = new StandardXYItemRenderer();
		renderer2.setDefaultToolTipGenerator(new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
				new DecimalFormat("0.000"), new DecimalFormat("0.000")));
		plot.setDataset(1, datasetNormal);
		plot.setRenderer(1, renderer2);

		plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

		return new JFreeChart("Temperatura Ambiente", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
	}

	public JFreeChart posicaoParedeX() {
		HistogramDataset datasetHistogram = new HistogramDataset();
		datasetHistogram.setType(HistogramType.SCALE_AREA_TO_1);

		datasetHistogram.addSeries("Histograma", Parede.getHistogramData(), 1000);
		
		final XYItemRenderer renderer1 = new XYBarRenderer(0.2);
		renderer1.setDefaultToolTipGenerator(new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
				new DecimalFormat("0.00000"), new DecimalFormat("0.00000")));

		final NumberAxis x = new NumberAxis("X");
		final ValueAxis y = new NumberAxis("p(x)");
		final XYPlot plot = new XYPlot(datasetHistogram, x, y, renderer1);

		final XYItemRenderer renderer2 = new StandardXYItemRenderer();
		renderer2.setDefaultToolTipGenerator(new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
				new DecimalFormat("0.00000"), new DecimalFormat("0.00000")));
		plot.setDataset(1, getDatasetPosParedes());
		plot.setRenderer(1, renderer2);

		plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

		return new JFreeChart("Posição das paredes em x", JFreeChart.DEFAULT_TITLE_FONT, plot, true);

//		JFreeChart chart = ChartFactory.createHistogram("Posição das paredes em X", "x", "p(x)", datasetHistogram,
//				PlotOrientation.VERTICAL, true, true, true);
//
//		return chart;
	}
	
	public JFreeChart posicaoParedeY() {
		HistogramDataset datasetHistogram = new HistogramDataset();
		datasetHistogram.setType(HistogramType.SCALE_AREA_TO_1);

		datasetHistogram.addSeries("Histograma", Parede.getHistogramData(), 50);
		
		final XYItemRenderer renderer1 = new XYBarRenderer(0.2);
		renderer1.setDefaultToolTipGenerator(new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
				new DecimalFormat("0.00000"), new DecimalFormat("0.00000")));
		final NumberAxis x = new NumberAxis("Y");
		final ValueAxis y = new NumberAxis("p(y)");
		final XYPlot plot = new XYPlot(datasetHistogram, x, y, renderer1);
		
		final XYItemRenderer renderer2 = new StandardXYItemRenderer();
		renderer2.setDefaultToolTipGenerator(new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
				new DecimalFormat("0.00000"), new DecimalFormat("0.00000")));
		plot.setDataset(1, getDatasetPosParedes());
		plot.setRenderer(1, renderer2);

		plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
		
		return new JFreeChart("Posição das paredes em y", JFreeChart.DEFAULT_TITLE_FONT, plot, true);

		// JFreeChart chart = ChartFactory.createHistogram("Posição das paredes em Y", "y", "p(y)", datasetHistogram,
			// PlotOrientation.VERTICAL, true, true, true);

		// return chart;
	}
	
	public JFreeChart foodPoints() {
		HistogramDataset datasetHistogram = new HistogramDataset();
		datasetHistogram.setType(HistogramType.SCALE_AREA_TO_1);

		datasetHistogram.addSeries("Histograma", Comida.getHistogramData(), 500);
		
		final XYItemRenderer renderer1 = new XYBarRenderer(0.2);
		renderer1.setDefaultToolTipGenerator(new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
				new DecimalFormat("0.000"), new DecimalFormat("0.000")));
		final NumberAxis x = new NumberAxis("X");
		final ValueAxis y = new NumberAxis("Y");
		final XYPlot plot = new XYPlot(datasetHistogram, x, y, renderer1);
		
		return new JFreeChart("Pontuação da comida", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
	}
	
//	public XYDataset getDatasetFood() {
//		XYSeries serie = new XYSeries("Pontuação da comida");
//		for(int i=0; i<10; i++) {
//			if(i==10)
//				serie.add(i,0.05);
//			else if(i==6)
//				serie.add(i, 0.2);
//			else if(i==3)
//				serie.add(i,0.3);
//			else if(i==1)
//				serie.add(i,0.45);
//		}
		
//		XYSeriesCollection dataset = new XYSeriesCollection(serie);
//		return dataset;
//	}
//	
	public XYDataset getDatasetPosParedes(){
		XYSeries serie = new XYSeries("Posição das paredes");
		for (int i=0; i<50; i++)
			serie.add(i,0.008);
		for (int i=50; i<350; i++)
			serie.add(i,0.000667);
		for (int i=350; i<400; i++)
			serie.add(i,0.008);
		
		XYSeriesCollection dataset = new XYSeriesCollection(serie);
		
		return dataset;
	}

}
