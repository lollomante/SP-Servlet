import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;

import javax.servlet.http.HttpServlet;

public class ManagementConsole extends Thread {

	private String command = " ";
	private static final String[] admittedCommands =  {"help", "load", "remove", "list", "quit"};

	//constructor
	ManagementConsole(){

	}

	// main method:
	public void run() {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

		while (!command.equals("quit")){

			// print a welcome message
			System.out.print("Type a command or 'help': ");

			// read input
			try {
				command  = bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			// execute the command
			executeCommand(command);
		}

		// terminate program
		Shutdown.flag=true;
	}


	// parse and execute a command
	void executeCommand(String command) {
		// command = help
		if (firstWord(command).equals(admittedCommands[0])) {

			System.out.println("Possible Commands: ");
			System.out.println("1) 'help' prints all admitted commands");
			System.out.println("2) 'load <servletName>' loads a servlet in repository");
			System.out.println("3) 'remove <servletName>' removes a servlet from repository");
			System.out.println("4) 'list' lists all servlets");
			System.out.println("5) 'quit' quits the program");
		}

		// command = load
		else if (firstWord(command).equals(admittedCommands[1])) {
			loadServlet(secondWord(command));
		}

		// command = remove
		else if (firstWord(command).equals(admittedCommands[2])) {
			removeServlet(secondWord(command));
		}

		// command = list
		else if (firstWord(command).equals(admittedCommands[3])) {
			listServlets();
		}

		// command = quit
		else if (firstWord(command).equals(admittedCommands[4])) {
			return;
		}

		// unrecognised command
		else {
			System.out.print("Command unknown: ");
		}
	}


	// loads a servlet
	void loadServlet(String servletName){

		// check that servlet is not already loaded
		if (ServletHashTable.contains(servletName)) {
			System.out.println("Servlet " + servletName + " already in the servlet repository");
			return;
		}

		// directory of the servlet
		String servletRepository = (HttpServer.ServletDirectory + File.separator + servletName).trim();

		// check that folder exists
		File f = new File(servletRepository);
		if (!(f.exists() && f.isDirectory())) {
			System.out.println("Directory " + servletRepository + " does not exists");
			return;
		}

		// read metadata file
		String servletClassName = null;
		FileReader fr=null;
		String metadataFile = servletRepository + File.separator + "metadata.txt";
		try	{
			BufferedReader reader = new BufferedReader(new FileReader(metadataFile));
			String command = reader.readLine();
			while (command != null) {
				if (command.contains("=")){
					int index = command.indexOf("=");
					servletClassName = command.substring(index+1);
				}
				command = reader.readLine();
			}
		}
		catch (FileNotFoundException fe){
			System.out.println("File not found");
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		// load the servlet
		URLClassLoader loader = null;
		try {
			URL[] urls = new URL[1];
			urls[0] = new URL("file:" + servletRepository + File.separator);
			loader = new URLClassLoader(urls);
		}
		catch (IOException e) {
			System.out.println(e.toString() );
			return;
		}

		Class myClass = null;
		try {
			myClass = loader.loadClass(servletClassName);
		}
		catch (ClassNotFoundException e) {
			System.out.println(e.toString());
			return;
		}
		HttpServlet servlet = null;
		try {
			servlet = (HttpServlet) myClass.newInstance();
		}
		catch (Exception e) {
			System.out.println(e.toString());
			return;
		}

		// add class to servlet to hash table and print a success message
		ServletHashTable.put(servletName, servlet);
		System.out.println("Servlet " + servletName + " added");
	}


	// unloads a servlet
	void removeServlet(String servletInternalName){
		if (!ServletHashTable.contains(servletInternalName)) {
			System.out.println("Servlet " + servletInternalName + " not in the servlet repository");
		} else {
			ServletHashTable.remove(servletInternalName);
			System.out.println("Servlet " + servletInternalName + " removed");
		}
	}

	// lists all servlets
	private void listServlets() {
		System.out.println(ServletHashTable.getServletNames());
	}

	// get first word of the command
	String firstWord(String command) {
		if (command.contains(" ")){
			int index = command.indexOf(" ");
			return command.substring(0, index);
		} else {
			return command;
		}
	}

	// get second word of the command
	String secondWord(String command) {
		if (command.contains(" ")) {
			int index = command.indexOf(" ");
			return command.substring(index+1);
		} else {
			return command;
		}
	}









}
