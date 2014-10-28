package leetcode.StringAndArray;

public class ValidNumber2 {
	public boolean isNumber(String s) {
		s = s.trim();
		s = s.replaceAll("e|E", "/");
		s = s.replaceAll("[a-zA-Z]", "x");
		s = s.replaceAll("/", "e");
		try {
			Double.parseDouble(s);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}


	public  boolean isNumber2(String s) {
	        int[][] transitionTable={
	            /* ¡¡0   1   2   3   4   5  */
	              {0,  1,  2,  3,  4,  0}, // 0: INVALID
	              {0,  1,  2,  3,  4,  0}, // 1: SPACE
	                {0,  0,  0,  3,  4,  0}, // 2: SIGN
	                {0,  6,  0,  3,  7,  5}, // 3: DIGIT
	                {0,  0,  0,  7,  0,  0}, // 4: DOT
	                {0,  0,  2,  8,  0,  0}, // 5: EXPONENT
	                {0,  6,  0,  0,  0,  0}, // 6: END WITH SPACE
	                {0,  6,  0,  7,  0,  5}, // 7: DOT AND DIGIT
	                {0,  6,  0,  8,  0,  0}, // 8: END WITH SPACE OR DIGIT           
	        };
	        int last=0;//invalid
	        for(int i=0;i<s.length();i++){
	            int state=0;//invalid
	            if(s.charAt(i)==' '){
	                state=1;//space
	            }else if(Character.isDigit(s.charAt(i))){
	                state=3;//digit
	            }else if(s.charAt(i)=='+'||s.charAt(i)=='-'){
	                state=2;//SIGN
	            }else if(s.charAt(i)=='.'){
	                state=4;//DOT
	            }else if(s.charAt(i)=='e'||s.charAt(i)=='E'){
	                state=5;//EXPONENT
	            }
	            last=transitionTable[last][state];
	            if(last==0) return false;
	        }
	        if(last==3||last==6||last==7||last==8)
	            return true;
	        else
	            return false;
	        
	    }
	
}
