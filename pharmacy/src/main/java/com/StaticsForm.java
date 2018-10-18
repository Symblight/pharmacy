package com;

import javax.swing.*;

import java.awt.*;
import java.util.List;

import javax.swing.JPanel;

import com.dataaccess.PreparationDaoImpl;
import com.datamodel.Preparation;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class StaticsForm extends JFrame {
    private static List<Preparation> preparationList;
    private JPanel rootPanel;
    private JComboBox comboBox1;
    private JPanel chartPanel;

    public StaticsForm() {
        super("Диаграмма");

        this.getContentPane().add(rootPanel);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(true);

    //    chartPanel.add(createDemoPanel());
        chartPanel.validate();

        initialState();

       setContentPane(createDemoPanel());

    }

    private void initialState() {
        PreparationDaoImpl preparationDao = new PreparationDaoImpl(App.statement);
        preparationList = preparationDao.list();

        JPanel jPanel1 = new JPanel();
        jPanel1.setLayout(new java.awt.BorderLayout());
    }

    private static PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for(int i = 0; i < preparationList.size(); i++ ) {
            dataset.setValue(preparationList.get(i).getTitle(), new Double(preparationList.get(i).getCount()));
        }
        return dataset;
    }

    /**
     * Creates a chart.
     *
     * @param dataset  the dataset.
     *
     * @return A chart.
     */
    private static JFreeChart createChart(PieDataset dataset) {

        JFreeChart chart = ChartFactory.createPieChart(
                "Наличие лекарственных средств",  // chart title
                dataset,             // data
                true,               // include legend
                true,
                false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        plot.setCircular(false);
        plot.setLabelGap(0.02);
        return chart;

    }

    public static JPanel createDemoPanel() {
        JFreeChart chart = createChart(createDataset());
        return new ChartPanel(chart);
    }
}
