package org.darien.use.five;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.darien.types.*;
import org.darien.types.impl.*;
import org.darien.types.utils.FailureUtils;

// write code to take away exceptions having to be thrown, to keep flow easier

public class Main {
	public S getFile(Path filename) {
		if(FailureUtils.oneIsNull(filename)) {
			return FailureUtils.theNull(filename);
		}

		File file = filename.toFile();
		if(!file.exists()) {
			return FailureUtils.theFalse(file.exists());
		}
		
		String line;
		StringBuilder resultStringBuilder = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
	        while ((line = br.readLine()) != null) {
	            resultStringBuilder.append(line).append("\n");
	        }
	    } catch (IOException e) {
			return new FExp(e);
		}
	
	    return new Success(resultStringBuilder.toString());
	}
	
	// Special version that creates a FailingBufferedReader to induce an IOException
	public S getFailingFile(Path filename) {
		if(FailureUtils.oneIsNull(filename)) {
			return FailureUtils.theNull(filename);
		}
		
		// write code to take away exceptions having to be thrown, to keep flow easier
		File file = filename.toFile();
		if(!file.exists()) {
			return FailureUtils.theFalse(file.exists());
		}
		
		String line;
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new FailingBufferedReader(new InputStreamReader(new FileInputStream(file)))) {
	        while ((line = br.readLine()) != null) {
	            resultStringBuilder.append(line).append("\n");
	        }
	    } catch (IOException e) {
			return new FExp(e);
		}
	
	    return new Success(resultStringBuilder.toString());
	}
	

	public S getFileSlowly(Path filename) {
		if(FailureUtils.oneIsNull(filename)) {
			return FailureUtils.theNull(filename);
		}
		
		// write code to take away exceptions having to be thrown, to keep flow easier
		File file = filename.toFile();
		if(!file.exists()) {
			return FailureUtils.theFalse(file.exists());
		}
		
		String line;
		StringBuilder resultStringBuilder = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
        	try {
				Thread.sleep(10000); // If filename is on a removable media and during this delay
			} catch (InterruptedException e) { // the media is removed, IOException is thrown
				e.printStackTrace();
			}
	    	while ((line = br.readLine()) != null) {
	            resultStringBuilder.append(line).append("\n");
	        }
	    } catch (IOException e) {
			return new FExp(e);
	    }
	
	    return new Success(resultStringBuilder.toString());
	}

	public static void main(String[] argv) {
		Main m = new Main();
		
		Path inputfile = Paths.get("E:", "pom.xml");

		System.out.println("In");
		S obj = m.getFileSlowly(inputfile);
		System.out.println("Out");
		
		if(obj.eval()) {
			String pom = (String) obj.unwrap();
			System.out.println("Success");
		} else {
			switch(obj) {
			case FailureArgIsNull fain -> System.out.println(fain.getLocation());
			case FailureArgIsFalse faif -> System.out.println(faif.getLocation());
			case FailureException fe -> System.out.println(fe.getLocation());
			default -> System.out.println("As written, cannot happen");
			}
		}
		
		inputfile = Paths.get(System.getProperty("user.dir"), "org.darian.use.five", "tamoshanter");
		
		obj = m.getFile(inputfile);
		if(obj.eval()) {
			String pom = (String) obj.unwrap();
			System.out.println("Success");
		} else {
			switch(obj) {
			case FailureArgIsNull fain -> System.out.println(fain.getLocation());
			case FailureArgIsFalse faif -> System.out.println(faif.getLocation());
			case FailureException fe -> System.out.println(fe.getLocation());
			default -> System.out.println("As written, cannot happen");
			}
		}
		
		// Arg is null
		
		obj = m.getFile(null);
		if(obj.eval()) {
			String pom = (String) obj.unwrap();
			System.out.println(pom);
		} else {
			switch(obj) {
			case FailureArgIsNull fain -> System.out.println(fain.getLocation());
			case FailureArgIsFalse faif -> System.out.println(faif.getLocation());
			case FailureException fe -> System.out.println(fe.getLocation());
			default -> System.out.println("As written, cannot happen");
			}
		}
		
		// Arg is false
		inputfile = Paths.get(System.getProperty("user.dir"), "org.darian.use.five", "filedoesnotexist");
		
		obj = m.getFile(inputfile);
		if(obj.eval()) {
			String pom = (String) obj.unwrap();
			System.out.println(pom);
		} else {
			switch(obj) {
			case FailureArgIsNull fain -> System.out.println(fain.getLocation());
			case FailureArgIsFalse faif -> System.out.println(faif.getLocation());
			case FailureException fe -> System.out.println(fe.getLocation());
			default -> System.out.println("As written, cannot happen");
			}
		}
		
		// FailureException is returned
		inputfile = Paths.get(System.getProperty("user.dir"), "org.darian.use.five", "tamoshanter");
		
		obj = m.getFailingFile(inputfile);
		if(obj.eval()) {
			String pom = (String) obj.unwrap();
			System.out.println(pom);
		} else {
			switch(obj) {
			case FailureArgIsNull fain -> System.out.println(fain.getLocation());
			case FailureArgIsFalse faif -> System.out.println(faif.getLocation());
			case FailureException fe -> System.out.println("FE output starts here: " + fe.getLocation());
			default -> System.out.println("As written, cannot happen");
			}
		}
	}
	
	private class FailingBufferedReader extends BufferedReader {
		public FailingBufferedReader(Reader in) {
			super(in);
		}
		
		@Override
		public String readLine() throws IOException {
			throw new IOException();
		}
	}
}
