package dss.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dss.dto.CategoryInterest;
import dss.service.locator.ServiceLocator;

/**
 * Servlet implementation class EvaluationServlet
 */
public class EvaluationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EvaluationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map<Integer,List<String>>catIdQuestionIdsMap = new HashMap<>();
		Map<String,String[]>requestMap = request.getParameterMap();
		Collection<String[]>valueList = requestMap.values();
		for (String[] strings : valueList) {
			String value = strings[0];
			String[] valArr =  value.split("_");
			String optionId = valArr[0];
			String quesId = valArr[1];
			String catId = valArr[2];
			Integer catIdInt = Integer.parseInt(catId);
			List<String>quesIdsList = catIdQuestionIdsMap.get(catIdInt);
			if(quesIdsList==null) {
				quesIdsList = new ArrayList<>();
				quesIdsList.add(quesId+"_"+optionId);
			}
			else {
				quesIdsList.add(quesId+"_"+optionId);
			}
			catIdQuestionIdsMap.put(catIdInt, quesIdsList);
		}
		List<CategoryInterest>listCatInterest = null;
		try {
			listCatInterest = ServiceLocator.getDssService().calculateCategoryInterest(catIdQuestionIdsMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		HttpSession httpSession = request.getSession();
		
		httpSession.setAttribute("categoryInterestList", listCatInterest);
		RequestDispatcher rd = request.getRequestDispatcher("showInterest.jsp");
		rd.forward(request, response);
		
	}

}
