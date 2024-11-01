import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.AsyncContext;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

public class Request implements HttpServletRequest {

	// constructor
	public Request(InputStream input) {
		this.input = input;
	}

	// input stream
	private final InputStream input;

	// URl parsed from the request
	private String uri;

	// return URL
	public String getUri() {
		return uri;
	}

	// Helper method to parse the URI from the request string.
	private String parseUri(String requestString) {
		int index1, index2;

		// Find first space in request line
		index1 = requestString.indexOf(' ');
		if (index1 != -1) {
			// Find second space in request line
			index2 = requestString.indexOf(' ', index1 + 1);

			if (index2 > index1) {
				// Extract substring as URI
				return requestString.substring(index1 + 1, index2);
			}
		}
		// Return null if URI can't be parsed
		return null;
	}


	// Method to parse the request from the input stream and extract the URI.
	public void parse() {
		// Buffer to store request data
		StringBuilder request = new StringBuilder(2048);
		int i;

		// Temporary buffer to read data
		byte[] buffer = new byte[2048];
		try {
			// Read from input stream into buffer
			i = input.read(buffer);

			// Append each character to the request buffer.
			for (int j=0; j<i; j++) {
				request.append((char) buffer[j]);
			}

			// Parse URI from the request string.
			uri = parseUri(request.toString());
		} catch (Exception e) {
			uri=null;
		}
	}

	public Object getAttribute(String attribute) {
		return null;
	}
	public Enumeration getAttributeNames() {
		return null;
	}
	public String getRealPath(String path) {
		return null;
	}
	public RequestDispatcher getRequestDispatcher(String path) {
		return null;
	}
	public boolean isSecure() {
		return false;
	}
	public String getCharacterEncoding() {
		return null;
	}
	public int getContentLength() {
		return 0;
	}
	public String getContentType(){
		return null;
	}
	public ServletInputStream getInputStream() throws IOException {
		return null;
	}
	public Locale getLocale() {
		return null;
	}
	public Enumeration getLocales() {
		return null;
	}
	public String getParameter(String name) {
		return null;
	}
	public Map getParameterMap() {
		return null;
	}
	public Enumeration getParameterNames() {
		return null;
	}
	public String[] getParameterValues(String parameter) {
		return null;
	}
	public String getProtocol() {
		return null;
	}
	public BufferedReader getReader() throws IOException {
		return null;
	}
	public String getRemoteAddr() {
		return null;
	}
	public String getRemoteHost() {
		return null;
	}
	public String getScheme() {
		return null;
	}
	public String getServerName() {
		return null;
	}
	public int getServerPort() {
		return 0;
	}
	public void removeAttribute(String attribute) { }
	public void setAttribute(String key, Object value) { }
	public void setCharacterEncoding(String encoding)
	throws UnsupportedEncodingException { }

	@Override
	public int getRemotePort() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getLocalName() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getLocalAddr() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getLocalPort() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public ServletContext getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AsyncContext startAsync() throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse)
			throws IllegalStateException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isAsyncStarted() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAsyncSupported() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public AsyncContext getAsyncContext() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public DispatcherType getDispatcherType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getAuthType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Cookie[] getCookies() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long getDateHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Enumeration<String> getHeaders(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Enumeration<String> getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getIntHeader(String name) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getMethod() {
		// TODO Auto-generated method stub
		String s = "GET";
		return s;
	}
	@Override
	public String getPathInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPathTranslated() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getContextPath() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getQueryString() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRemoteUser() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isUserInRole(String role) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Principal getUserPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRequestedSessionId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRequestURI() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public StringBuffer getRequestURL() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getServletPath() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HttpSession getSession(boolean create) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HttpSession getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRequestedSessionIdValid() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isRequestedSessionIdFromCookie() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isRequestedSessionIdFromURL() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isRequestedSessionIdFromUrl() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean authenticate(HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void login(String username, String password) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void logout() throws ServletException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Collection<Part> getParts() throws IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Part getPart(String name) throws IOException, ServletException {
		// TODO Auto-generated method stub
		return null;
	}
}
