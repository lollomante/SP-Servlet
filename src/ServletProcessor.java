import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class ServletProcessor {

	// Main method to process the incoming request and response
	public void process(Request request, Response response) {

		// Uri of the request
		String uri = request.getUri();

		// name of the servlet
		String servletName = uri.substring(uri.lastIndexOf("/") + 1);

		// check that servlet is loaded in hash table
		if (!ServletHashTable.contains(servletName)) {
			System.out.println("Error: " + servletName + " unknown");

		} else {

			// retrieve servlet from hash table
			HttpServlet servlet = ServletHashTable.get(servletName);

			try {

				// process the servlet
				servlet.service((ServletRequest) request, (ServletResponse) response);
			}
			catch (Throwable e) {
				System.out.println(e.toString());
			}
		}
	}
}
