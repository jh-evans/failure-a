package org.darien.tools.codegen;

import java.util.Objects;

public class ReturnInvocation {
	public final String type;
	public final String method_name;
	public final String method_args;
	public final String method_return_type;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Objects.hash(this.simpleTypeName());

		return result;
	}

	@Override
	public boolean equals(Object obj) {			
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ReturnInvocation)) {
			return false;
		}
		
		ReturnInvocation other = (ReturnInvocation) obj;
		
		return Objects.equals(this.simpleTypeName(), other.simpleTypeName());
	}
			
	public ReturnInvocation(String type, String method_signature) {
		this.type = type.replace('/', '.');
		String[] m_sig = method_signature.split(" ");
		this.method_name = m_sig[0];
		String[] m_args = m_sig[1].split("\\)");
		this.method_args = m_args[0] + ")";
		
		if (m_args[1].charAt(0) == 'L') {
			String m_arg = m_args[1].substring(1, m_args[1].length() - 1);
			this.method_return_type = m_arg.replace('/', '.');
		} else {
			this.method_return_type = m_args[1];
		}
	}
	
	public String simpleTypeName() {
		if(method_return_type.charAt(0) != 'V') {
			if(method_return_type.contains(".")) {
				String[] components = method_return_type.split("\\.");
				String str = components[components.length - 1];
				
				if(str.endsWith(";")) {
					return str.substring(0, str.length() - 1);
				} else {
					return str;
				}
			} else {
				return type;
			}
		} else {
			if(type.contains(".")) {
				String[] components = type.split("\\.");
				return components[components.length - 1];
			} else {
				return type;
			}
		}
	}
	
	public boolean isSuccessType(Class<?> s_cls) {
		return s_cls.getName().equals(this.type);
	}
	
	public String toString() {
		return type + " " + method_name + " " + method_args + " " + method_return_type;
	}
}