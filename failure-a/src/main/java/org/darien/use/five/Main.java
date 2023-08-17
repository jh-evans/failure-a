package org.darien.use.five;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.darien.types.*;
import org.darien.types.impl.*;
import org.darien.types.utils.FailureUtils;

public class Main {
	public S getFile(String filename) {
		if(FailureUtils.oneIsNull(filename)) {
			return FailureUtils.theNull(filename);
		}
		
		// write code to take away exceptions having to be thrown, to keep flow easier
		File file = new File(filename);
		if(!file.exists()) {
			return FailureUtils.theFalse(file.exists());
		}
		
		String line;
		StringBuilder resultStringBuilder = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
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
		
		String filename = "C:\\Users\\jheva\\eclipse-workspace\\failure-a\\failure-a\\pom.xm";
		
		S obj = m.getFile(filename);
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
	        
	}
}
