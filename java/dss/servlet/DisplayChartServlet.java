package dss.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import dss.dto.CategoryInterest;

/**
 
 * 
 * 
 * Servlet implementation class DisplayChartServlet
 */
public class DisplayChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		List<CategoryInterest> catInterstList =  (List<CategoryInterest>) session.getAttribute("categoryInterestList");
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for(CategoryInterest categoryInterest : catInterstList)
	
		{
			String categoryName = categoryInterest.getCategoryName();
			float interestPercent = categoryInterest.getInterest();
			dataset.addValue(interestPercent, categoryName, "");	
		}
		
		JFreeChart chart = ChartFactory.createBarChart("Your Interest in Different Engineering Streams", "Branch", "Percentage", dataset, PlotOrientation.VERTICAL, true, true, false);
		chart.setBorderVisible(true);
		if (chart != null) {
            int width = 600;
            int height = 400;
            response.setContentType("image/jpeg");
            OutputStream out = response.getOutputStream();
            ChartUtils.writeChartAsJPEG(out, chart, width, height);
    }
	}

}
