package org.darien.tools.codegen;

import java.util.HashMap;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		if(args.length == 0 ) {
			System.err.println("Usage: " + Main.class.getName() + " [-v] [-outputcode] [-outputimports] [-pre17] <fully qualified classname>");
			System.err.println("  -v switch on verbose output. No need for -v true. -v is enough.");
			System.err.println("  -outputcode outputs generated code. Generall, you always want this on.");
			System.err.println("  -outputimports outputs the imports you need to use the generated code.");
			System.err.println("  -pre17 The code generated is not reliant on a Java 17 type switch preview feature");
			System.err.println("  <fully qualified classname> The class to be found on the classpath that returns org.darien.types.S\n" +
	                           "    This tool will generate the Java source code for you to call this code.");
			System.err.println("See https://darien-project.readthedocs.io/en/latest/ for more information");
			System.exit(99);
		}
		
		Map<String, Boolean> flags = new HashMap<String, Boolean>();
		flags.put("v", false);
		flags.put("outputcode", false);
		flags.put("outputimports", false);
		flags.put("pre17", false);
		
		for(int i = 0; i < args.length - 1; i++) {
			String arg = args[i];
			
			if(arg.equals("-v")) {
				flags.put("v", true);
			} else {
				if(arg.equals("-outputcode")) {
					flags.put("outputcode", true);
				} else {
					if(arg.equals("-outputimports")) {
						flags.put("outputimports", true);
					} else {
						if(arg.equals("-pre17")) {
							flags.put("pre17", true);
						} else {
							System.err.println("<" + arg + "> is an unknown flag. Exiting.");
							System.exit(101);
						}
					}
				}
			}
			
			if(flags.containsKey("v") && flags.get("v")) {
				System.out.println("Processing arg <" + arg +">");
			}
		}
		
		String classname = args[args.length - 1];
		
		if(flags.containsKey("v") && flags.get("v")) {
			System.out.println("Running with these flags:");
			for(Map.Entry<String, Boolean> e : flags.entrySet()) {
				System.out.println("  -" + e.getKey() + "=" + e.getValue());
			}
			System.out.println("Classname to process is:");
			System.out.println("  " + classname);
		}
		
		CodeGen codegen = new CodeGen();
		codegen.generate(classname, flags);
	}
}
