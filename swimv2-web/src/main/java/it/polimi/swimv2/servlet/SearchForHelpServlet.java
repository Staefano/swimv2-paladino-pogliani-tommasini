package it.polimi.swimv2.servlet;

import it.polimi.swimv2.entity.User;
import it.polimi.swimv2.session.remote.SearchBeanRemote;
import it.polimi.swimv2.webutils.Controller;
import it.polimi.swimv2.webutils.Navigation;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchForHelpServlet extends Controller {

	private static final long serialVersionUID = 1103591206119674852L;

	@EJB
	private SearchBeanRemote bean;

	private static final String RESULTS_JSP = "WEB-INF/searchHelpResults.jsp";
	
	@Override
	protected void get(Navigation nav) throws IOException, ServletException {
		String queryString = nav.getParam("abilities");
		String scope = nav.getParam("scope");
		List<User> results;
		List<String> abilities = generateListOfAbilities(queryString);
		if("friends".equals(scope) && nav.getLoggedUser() != null) {
			results = bean.searchForHelpAmongFriends(nav.getLoggedUser(), abilities);
		} else {
			results = bean.searchForHelp(abilities);
		}
		nav.setAttribute("abilities",  nav.getParam("abilities")); // revalidate and rebuild the string?
		if (results == null || results.size() == 0) {
			nav.setAttribute("found", false);
		} else {
			nav.setAttribute("found", true);
			nav.setAttribute("results", results);
		}
		nav.fwd(RESULTS_JSP);
	}
	
	private List<String> generateListOfAbilities(String queryString) {
		List<String> abilities = new ArrayList<String>();
		String[] ab = queryString.split(",");
		for(String a : ab) {
			abilities.add(a.trim());
		}
		return abilities;
	}
}