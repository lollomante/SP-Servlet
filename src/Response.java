import java.io.*;
import java.util.Collection;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class Response implements HttpServletResponse {

	private static final int BUFFER_SIZE = 1024;
	public Request request;
	public OutputStream output;
	public PrintWriter writer;

	private static final String headerHttpBeforeLength = "HTTP/1.1 200 OK"+"\r\n"+"Content-Type: text/html"+"\r\n"+"Content-Length: ";
	private static final String headerHttpAfterLength = "\r\n" + "\r\n";

	// constructor
	public Response(OutputStream output) {
		this.output = output;
	}
	public void setRequest(Request request) {
		this.request = request;
	}


	/* This method is used to serve static pages */
	public void sendStaticResource() throws IOException {

		try {
			// load and send the static page
			String fileName = HttpServer.StaticContentDirectory+request.getUri();
			SendHTMLMessage(fileName, output);
		}

		// If the file is not found, return a 404 response.
		catch (FileNotFoundException e) {
			String link = HttpServer.StaticContentDirectory + File.separator + HttpServer.Error404Link;
			SendHTMLMessage(link, output);
		}
	}

	// send a file as a html message
	private void SendHTMLMessage(String link, OutputStream output) throws IOException {
		FileReader fileReader = new FileReader(link);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		StringBuilder stringBuilder = new StringBuilder();
		String line;

		// Read each line from the file and append it to the StringBuilder.
		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line);
		}

		// Close the BufferedReader after reading the file.
		bufferedReader.close();

		// Get the length of the content.
		int len = stringBuilder.toString().length();

		// Combine headers and file content into the final output message.
		String outMsg = headerHttpBeforeLength + Integer.toString(len) + headerHttpAfterLength + stringBuilder.toString();

		// Write the response to the output stream.
		output.write(outMsg.getBytes());
	}
	
	/** implementation of ServletResponse */
	public void flushBuffer() throws IOException { }
	public int getBufferSize() {
		return 0;
	}
	public String getCharacterEncoding() {
		return null;
	}
	public Locale getLocale() {
		return null;
	}
	public ServletOutputStream getOutputStream() throws IOException {
		return null;
	}
	public PrintWriter getWriter() throws IOException {
		// autoflush is true, println() will flush,
		// but print() will not.
		writer = new PrintWriter(output, true);
		return writer;
	}
	public boolean isCommitted() {
		return false;
	}
	public void reset() { }
	public void resetBuffer() { }
	public void setBufferSize(int size) { }
	public void setContentLength(int length) { }
	public void setContentType(String type) { }
	public void setLocale(Locale locale) { }
	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setCharacterEncoding(String arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCookie(Cookie cookie) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean containsHeader(String name) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String encodeURL(String url) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String encodeRedirectURL(String url) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String encodeUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String encodeRedirectUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void sendError(int sc, String msg) throws IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sendError(int sc) throws IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sendRedirect(String location) throws IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDateHeader(String name, long date) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addDateHeader(String name, long date) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setHeader(String name, String value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addHeader(String name, String value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setIntHeader(String name, int value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addIntHeader(String name, int value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setStatus(int sc) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setStatus(int sc, String sm) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection<String> getHeaders(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection<String> getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}
}
