package org.darien.use.four;

import java.util.ArrayList;
import java.util.function.BiFunction;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.MutablePair;

public class Main {
	
	private interface SCI {
		public Boolean apply();
	}
	
	private class SC_Equals implements SCI {
		private String[] args;
		
		public SC_Equals(String[] args) {
			this.args = args;
		}
		public Boolean apply() {
			return args[0].equals(args[1]);
		}
	}
	
	private class SC_Contains implements SCI {
		private String[] args;
		
		public SC_Contains(String[] args) {
			this.args = args;
		}
		
		public Boolean apply() {
			return args[0].contains(args[1]);
		}
	}

	private interface SCT extends BiFunction<String, String, Boolean> {
	}
	
	private class StringCompare implements SCT {
		private String[] args;
		private SCT fn;
		
		public StringCompare(SCT fn, String[] args) {
			this.args = args;
			this.fn = fn;
		}
		
		@Override
		public Boolean apply(String t, String u) {
			return fn.apply(args[0], args[1]);
		}
		
		public Boolean apply() {
			return fn.apply(args[0], args[1]);
		}
	}
	
	private void one() {
		MutablePair<BiFunction<String, String, Boolean>, String[]> pair = new MutablePair<BiFunction<String, String, Boolean>, String[]>(
				(s1,s2) -> s1.equals(s2),
				new String[] {"S", "S"});
		System.out.println(pair.left.apply(pair.right[0], pair.right[1]));
	}
	
	private void two() {
		SCT seq = (s1,s2) -> s1.equals(s2);
		String[] args = new String[] {"S", "S"};
		var pair = new MutablePair<BiFunction<String, String, Boolean>, String[]>(seq, args);
		System.out.println(pair.left.apply(pair.right[0], pair.right[1]));
	}
	
	private void three() {
		SCT seq = (s1,s2) -> s1.equals(s2);
		String[] args = new String[] {"S", "S"};
		var pair = new StringCompare(seq, args);
		System.out.println(pair.apply());
	}
	
	private void four() {
		SCT str_equals = (s1,s2) -> s1.equals(s2); // the type is the point of abstraction, as long as the item on the right of the -> generates a Boolean, we don't care what the code is
        SCT str_contains = (s1,s2) -> s1.contains(s2); // but we are aware of the implementation, but that the code is trivial an it depends on nothing else and nothing else depends on it

        String[] fn_args = new String[] {"S", "S"};
		
		var seq = new MutablePair<BiFunction<String, String, Boolean>, String[]>(str_equals, fn_args);
		System.out.println("(" + fn_args[0] + ", " + fn_args[1] + ") evaluated via MutablePair: " + seq.left.apply(seq.right[0],  seq.right[1]));

		seq.left = str_contains;
		seq.right[0] = "Hello world";
		seq.right[1] = "ello";
		System.out.println("(" + fn_args[0] + ", " + fn_args[1] + ") evaluated via MutablePair: " + seq.left.apply(seq.right[0],  seq.right[1]));
		
		StringCompare sc = new StringCompare(str_equals, fn_args); // fn_args[0] is now "Hello world", fn_args[1] is "ello"
		System.out.println("(" + fn_args[0] + ", " + fn_args[1] + ") evaluated via StringCompare: " + sc.apply());
	}
	
	public static void main(String[] args) {
		Main m = new Main();
		
		m.one();
		m.two();
		m.three();
		m.four();
		
		/*
		   a = ["S", "S"] # lists, tuples, basic value types, dictionaries
		   
		   class Node
		       data
		       children = [] // children has no type or list of something. Code in between here 3 lines below _could_ have more operations applied to it as less specific type
		       
		       addChild(Node n)
		           children.append(n) // children is now a list of Nodes
		 */
	}
}
