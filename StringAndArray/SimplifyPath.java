package leetcode.StringAndArray;

import java.util.Stack;

public class SimplifyPath {
	/**
	 * Given an absolute path for a file (Unix-style), simplify it.
	 * 
	 * For example, path = "/home/", => "/home" path = "/a/./b/../../c/", =>
	 * "/c" click to show corner cases.
	 * 
	 * Corner Cases: Did you consider the case where path = "/../"? In this
	 * case, you should return "/". Another corner case is the path might
	 * contain multiple slashes '/' together, such as "/home//foo/". In this
	 * case, you should ignore redundant slashes and return "/home/foo".
	 * 
	 * @param path
	 * @return
	 */
	public static String simplifyPath(String path) {
		String[] tokens = path.split("/");
		Stack<String> stack = new Stack<String>();
		for (int i = 0; i < tokens.length; i++) {
			if (!tokens[i].isEmpty()) {
				if (tokens[i].equals("..")) {
					if (!stack.isEmpty()) {
						stack.pop();
					}
				} else if (!tokens[i].equals(".")) {
					stack.push(tokens[i]);
				}
			}
		}

		StringBuffer sb = new StringBuffer();
		
		for (String token : stack) {
			sb.append("/");
			sb.append(token);			
		}
		if(sb.length()>0)
			return sb.toString();
		else 
			return "/";
	}
}
