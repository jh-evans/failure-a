package org.darien.use.five;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.darien.types.S;
import org.darien.types.impl.Success;
import org.darien.types.utils.FailureUtils;

public class ReadFile {
	/* Original code
	public String getFile(String filename) throws FileNotFoundException {
		String line;
		StringBuilder resultStringBuilder = new StringBuilder();
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
	        while ((line = br.readLine()) != null) {
	            resultStringBuilder.append(line).append("\n");
	        }
	    } catch (IOException e) {
			e.printStackTrace();
		}
	
	    return resultStringBuilder.toString();
	}
    */
	
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
			e.printStackTrace();
		}
	
	    return new Success(resultStringBuilder.toString());
	}
}
