package iul.ista.pi.snake;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.DatasetRenderingOrder;
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

public class HistogramTests extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1192139652639891377L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public HistogramTests() {
		XYBarRenderer.setDefaultShadowsVisible(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				dispose();
				if (!Snake.getInstance().isVisible())
					StartMenu.getInstance().open();
				else
					Board.getINSTANCE().pauseAndPlay();
			}
		});
		setBounds(100, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane();
		contentPane.add(tabbedPane);

		final ChartPanel tempPanel = new ChartPanel(temperatura(), true, true, true, true, true);
		tempPanel.setPreferredSize(new java.awt.Dimension(700, 270));

		final ChartPanel foodPoints = new ChartPanel(foodPoints(), true, true, true, true, true);
		foodPoints.setPreferredSize(new java.awt.Dimension(700, 270));

		final ChartPanel numberParedes = new ChartPanel(numberParedes(), true, true, true, true, true);
		foodPoints.setPreferredSize(new java.awt.Dimension(700, 270));

		final ChartPanel numberInimigos = new ChartPanel(numeroInimigos(), true, true, true, true, true);
		foodPoints.setPreferredSize(new java.awt.Dimension(700, 270));

		final ChartPanel posicaoComidas = new ChartPanel(posicaoComida(), true, true, true, true, true);
		foodPoints.setPreferredSize(new java.awt.Dimension(700, 270));
		tabbedPane.add("Pontuação da comida", foodPoints);
		tabbedPane.add("Número de paredes", numberParedes);
		tabbedPane.add("Número de inimigos", numberInimigos);
		tabbedPane.add("Posição da comida", posicaoComidas);
		tabbedPane.add("Temperatura", tempPanel);

		final ChartPanel posParedesX = new ChartPanel(posicaoParedeX(), true, true, true, true, true);
		posParedesX.setPreferredSize(new java.awt.Dimension(700, 270));
		tabbedPane.add("Posição das paredes", posParedesX);
	}

	public JFreeChart temperatura() {
		Function2D normal = new NormalDistributionFunction2D(25, 5);
		XYDataset datasetNormal = DatasetUtils.sampleFunction2D(normal, 15, 35, 100, "Normal");

		HistogramDataset datasetHistogram = new HistogramDataset();
		datasetHistogram.setType(HistogramType.SCALE_AREA_TO_1);

		datasetHistogram.addSeries("Histograma", Temperatura.getHistogramData(), 150);

		final XYItemRenderer renderer1 = new XYBarRenderer();
		renderer1.setDefaultToolTipGenerator(
				new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
						new DecimalFormat("0.000"), new DecimalFormat("0.000")));

		final NumberAxis x = new NumberAxis("X");
		x.setRange(13, 38);
		final ValueAxis y = new NumberAxis("p(x)");
		final XYPlot plot = new XYPlot(datasetHistogram, x, y, renderer1);
		plot.setDomainGridlinesVisible(false);
		plot.setRangeGridlinesVisible(false);

		final XYItemRenderer renderer2 = new StandardXYItemRenderer();
		renderer2.setDefaultToolTipGenerator(
				new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
						new DecimalFormat("0.000"), new DecimalFormat("0.000")));
		plot.setDataset(1, datasetNormal);
		plot.setRenderer(1, renderer2);

		plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

		return new JFreeChart("Temperatura Ambiente (1.000.000 amostras)", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
	}

	public JFreeChart posicaoParedeX() {
		HistogramDataset datasetHistogram = new HistogramDataset();
		datasetHistogram.setType(HistogramType.SCALE_AREA_TO_1);

		datasetHistogram.addSeries("Histograma", Parede.getHistogramDataPos(), 1000);

		final XYItemRenderer renderer1 = new XYBarRenderer(0.2);
		renderer1.setDefaultToolTipGenerator(
				new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
						new DecimalFormat("0.00000"), new DecimalFormat("0.00000")));

		final NumberAxis x = new NumberAxis("X");
		final ValueAxis y = new NumberAxis("p(x)");
		final XYPlot plot = new XYPlot(datasetHistogram, x, y, renderer1);

		final XYItemRenderer renderer2 = new StandardXYItemRenderer();
		renderer2.setDefaultToolTipGenerator(
				new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
						new DecimalFormat("0.00000"), new DecimalFormat("0.00000")));
		plot.setDataset(1, getDatasetPosParedes());
		plot.setRenderer(1, renderer2);

		plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

		return new JFreeChart("Posição das paredes em x (igual em y) (10.000.000 amostras)",
				JFreeChart.DEFAULT_TITLE_FONT, plot, true);
	}

	public XYDataset getDatasetPosParedes() {
		XYSeries serie = new XYSeries("Posição das paredes");
		for (int i = 0; i < 50; i++)
			serie.add(i, 0.008);
		for (int i = 50; i < 350; i++)
			serie.add(i, 0.000667);
		for (int i = 350; i < 400; i++)
			serie.add(i, 0.008);

		XYSeriesCollection dataset = new XYSeriesCollection(serie);

		return dataset;
	}

	public JFreeChart foodPoints() {
		HistogramDataset datasetHistogram = new HistogramDataset();
		datasetHistogram.setType(HistogramType.RELATIVE_FREQUENCY);

		datasetHistogram.addSeries("Histograma", Comida.getHistogramDataPontos(), 100000);

		final XYItemRenderer renderer1 = new XYBarRenderer();

		renderer1.setDefaultToolTipGenerator(
				new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
						new DecimalFormat("0.00000"), new DecimalFormat("0.00000")));
		final NumberAxis x = new NumberAxis("Pontuação");
		x.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		final ValueAxis y = new NumberAxis("Frequência relativa");
		final XYPlot plot = new XYPlot(datasetHistogram, x, y, renderer1);

		return new JFreeChart("Pontuação das comidas (1.000.000 de amostras)", JFreeChart.DEFAULT_TITLE_FONT, plot,
				true);
	}

	public JFreeChart numberParedes() {
		HistogramDataset datasetHistogram = new HistogramDataset();
		datasetHistogram.setType(HistogramType.RELATIVE_FREQUENCY);

		datasetHistogram.addSeries("Histograma", Parede.getHistogramDataNumber(), 1000000);

		final XYItemRenderer renderer1 = new XYBarRenderer();

		renderer1.setDefaultToolTipGenerator(
				new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
						new DecimalFormat("0.00000"), new DecimalFormat("0.00000")));
		final NumberAxis x = new NumberAxis("Número de paredes");
		x.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		final ValueAxis y = new NumberAxis("Frequência relativa");
		final XYPlot plot = new XYPlot(datasetHistogram, x, y, renderer1);

		return new JFreeChart("Número de paredes (1.000.000 de amostras)", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
	}

	public JFreeChart numeroInimigos() {
		HistogramDataset datasetHistogram = new HistogramDataset();
		datasetHistogram.setType(HistogramType.RELATIVE_FREQUENCY);

		datasetHistogram.addSeries("Histograma", Enemy.getHistogramData(), 100000);

		final XYItemRenderer renderer1 = new XYBarRenderer();

		renderer1.setDefaultToolTipGenerator(
				new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
						new DecimalFormat("0.00000"), new DecimalFormat("0.00000")));
		final NumberAxis x = new NumberAxis("Número de inimigos");
		x.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		final ValueAxis y = new NumberAxis("Frequência relativa");
		final XYPlot plot = new XYPlot(datasetHistogram, x, y, renderer1);

		return new JFreeChart("Número de inimigos (1.000.000 de amostras, situação inicial)",
				JFreeChart.DEFAULT_TITLE_FONT, plot, true);
	}

	public JFreeChart posicaoComida() {
		HistogramDataset datasetHistogram = new HistogramDataset();
		datasetHistogram.setType(HistogramType.RELATIVE_FREQUENCY);

		datasetHistogram.addSeries("Histograma", Comida.getHistogramDataPosition(), 100000);

		final XYItemRenderer renderer1 = new XYBarRenderer();

		renderer1.setDefaultToolTipGenerator(
				new StandardXYToolTipGenerator(StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
						new DecimalFormat("0.00000"), new DecimalFormat("0.00000")));
		final NumberAxis x = new NumberAxis("Posição da comida");
		x.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		final ValueAxis y = new NumberAxis("Frequência relativa");
		final XYPlot plot = new XYPlot(datasetHistogram, x, y, renderer1);

		return new JFreeChart(
				"Posição da comida em X (igual para Y) (1.000.000 de amostras) - arredondado para múltiplos de 20",
				JFreeChart.DEFAULT_TITLE_FONT, plot, true);
	}

}
