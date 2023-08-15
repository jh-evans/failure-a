package org.darien.tools.codegen;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		if(args.length == 0 ) {
			System.out.println("Usage: " + Main.class.getName() + " [-debug] [-outputcode] [-outputimports] [-pre17] <fully qualified classname>");
		}
		
		Map<String, Boolean> flags = new HashMap<String, Boolean>();
		flags.put("debug", false);
		flags.put("outputcode", false);
		flags.put("outputimports", false);
		flags.put("pre17", false);

		for(String arg : args) {
			if(arg.equals("-debug")) {
				flags.put("debug", true);
			}
			if(arg.equals("-outputcode")) {
				flags.put("outputcode", true);
			}
			if(arg.equals("-outputimports")) {
				flags.put("outputimports", true);
			}
			if(arg.equals("-pre17")) {
				flags.put("pre17", true);
			}
		}
		
		String classname = args[args.length - 1];
		
		if(flags.containsKey("debug") && flags.get("debug")) {
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
