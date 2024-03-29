import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Calendar;

@SuppressWarnings("serial")
public class OpenedServlet extends HttpServlet {
	
	static final String TEMPLATE = "<html>" +
			"<head><title>Prog Academy</title></head>" +
			"<body><h1>Today we are:</h1><h2>%s</h2></body></html>";
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_WEEK);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		boolean opened = ((day == Calendar.MONDAY) && (hour >= 10) && (hour <= 18));
		
		if (opened)
			resp.getWriter().println(String.format(TEMPLATE, "Opened"));
		else
			resp.getWriter().println(String.format(TEMPLATE, "Closed"));
	}
}
