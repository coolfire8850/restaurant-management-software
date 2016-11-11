package com.bindong.view;
/**
 * Pour les statistiques
 * 
 */
import java.awt.Font;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;


public class Chart {
    public static void main(String args[]){
    	
    	JFrame frame=new JFrame("Java数据统计图");
    	frame.add(new Chart().getChartPanel());    //添加折线图
    	frame.setBounds(50, 50, 800, 600);
    	frame.setVisible(true);
    	
    }
    
    ChartPanel frame1;
	public Chart(){
		XYDataset xydataset = createDataset();
		JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Monthly turnover and Monthly cost", "Date", "Million",xydataset, true, true, true);
		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		DateAxis dateaxis = (DateAxis) xyplot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("MMM-yyyy"));
        frame1=new ChartPanel(jfreechart,true);
        dateaxis.setLabelFont(new Font("黑体",Font.BOLD,14));         //水平底部标题
        dateaxis.setTickLabelFont(new Font("宋体",Font.BOLD,12));  //垂直标题
        ValueAxis rangeAxis=xyplot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        jfreechart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        jfreechart.getTitle().setFont(new Font("宋体",Font.BOLD,20));//设置标题字体

	} 
	 private static XYDataset createDataset() {  //这个数据集有点多，但都不难理解
	        TimeSeries timeseries = new TimeSeries("Monthly turnover",
	                org.jfree.data.time.Month.class);
	        timeseries.add(new Month(2, 2015), 12.80000000000001D);
	        timeseries.add(new Month(3, 2015), 13.30000000000001D);
	        timeseries.add(new Month(4, 2015), 13.80000000000001D);
	        timeseries.add(new Month(5, 2015), 15.59999999999999D);
	        timeseries.add(new Month(6, 2015), 16.80000000000001D);
	        timeseries.add(new Month(7, 2015), 17.30000000000001D);
	        timeseries.add(new Month(8, 2015), 18.90000000000001D);
	        timeseries.add(new Month(9, 2015), 15.69999999999999D);
	        timeseries.add(new Month(10, 2015), 14.2D);
	        timeseries.add(new Month(11, 2015), 13.80000000000001D);
	        timeseries.add(new Month(12, 2015), 13.59999999999999D);
	        timeseries.add(new Month(1, 2016), 13.90000000000001D);
	        timeseries.add(new Month(2, 2016), 13.69999999999999D);
	        timeseries.add(new Month(3, 2016), 14.30000000000001D);
	        timeseries.add(new Month(4, 2016), 13.90000000000001D);
	        timeseries.add(new Month(5, 2016), 15.80000000000001D);
	        timeseries.add(new Month(6, 2016), 16.43400034300344D);
	        timeseries.add(new Month(7, 2016), 17.80000000000001D);
	        TimeSeries timeseries1 = new TimeSeries("Monthly Cost",
	                org.jfree.data.time.Month.class);
	        timeseries1.add(new Month(2, 2015), 9.59999999999999D);
	        timeseries1.add(new Month(3, 2015), 11.2432423432443D);
	        timeseries1.add(new Month(4, 2015), 11.2D);
	        timeseries1.add(new Month(5, 2015), 12.49999999999999D);
	        timeseries1.add(new Month(6, 2015), 14.59999999999999D);
	        timeseries1.add(new Month(7, 2015), 15.2D);
	        timeseries1.add(new Month(8, 2015), 16.5D);
	        timeseries1.add(new Month(9, 2015), 13.7D);
	        timeseries1.add(new Month(10, 2015), 12.5D);
	        timeseries1.add(new Month(11, 2015), 11.09999999999999D);
	        timeseries1.add(new Month(12, 2015), 11.33432432423444D);
	        timeseries1.add(new Month(1, 2016), 11.7D);
	        timeseries1.add(new Month(2, 2016), 10.3123213D);
	        timeseries1.add(new Month(3, 2016), 12.59999999999999D);
	        timeseries1.add(new Month(4, 2016), 10.3221321D);
	        timeseries1.add(new Month(5, 2016), 14.59999999999999D);
	        timeseries1.add(new Month(6, 2016), 13.8D);
	        timeseries1.add(new Month(7, 2016), 15.59999999999999D);
	        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection();
	        timeseriescollection.addSeries(timeseries);
	        timeseriescollection.addSeries(timeseries1);
	        return timeseriescollection;
	    }
	  public ChartPanel getChartPanel(){
	    	return frame1;
	    	
	    }
}
