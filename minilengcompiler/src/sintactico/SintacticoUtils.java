package sintactico;

import main.Token;

public class SintacticoUtils {
	
	public static void error_sintactico(Token currentToken, int[][] expectedTokenSequences,
            						String[] tokenImage) {
			String eol = System.getProperty("line.separator", "\n");
			StringBuffer expected = new StringBuffer();
			int maxSize = 0;
			for (int i = 0; i < expectedTokenSequences.length; i++) {
				if (maxSize < expectedTokenSequences[i].length) {
					maxSize = expectedTokenSequences[i].length;
				}
				for (int j = 0; j < expectedTokenSequences[i].length; j++) {
					expected.append(tokenImage[expectedTokenSequences[i][j]]).append(' ');
				}
				/*
				if (expectedTokenSequences[i][expectedTokenSequences[i].length - 1] != 0) {
				expected.append("...");
				}*/
				if (i < (expectedTokenSequences.length - 1)) {
					expected.append(" || ");
				}
			}
			String retval = "ERROR SÍNTACTICO (" + currentToken.next.beginLine   + ", " + currentToken.next.beginColumn + "): ";
			Token tok = currentToken.next;
			
			for (int i = 0; i < maxSize; i++) {
			if (i != 0) retval += " ";
			if (tok.kind == 0) {
				retval += tokenImage[0];
				break;
			}
				//retval += " " + tokenImage[tok.kind];
				
				retval += "se ha encontrado el símbolo \"";
				retval += add_escapes(tok.image);
				retval += "\"";
				tok = tok.next;
			}
			
			if (expectedTokenSequences.length == 1) {
				retval += " se esperaba el símbolo: ";
			} else {
				retval += " se esperaba uno de los siguientes símbolos: " + eol;
			}
			retval += expected.toString();
			System.out.println(retval);
		}
	
	private static String add_escapes(String str) {
	      StringBuffer retval = new StringBuffer();
	      char ch;
	      for (int i = 0; i < str.length(); i++) {
	        switch (str.charAt(i))
	        {
	           case 0 :
	              continue;
	           case '\b':
	              retval.append("\\b");
	              continue;
	           case '\t':
	              retval.append("\\t");
	              continue;
	           case '\n':
	              retval.append("\\n");
	              continue;
	           case '\f':
	              retval.append("\\f");
	              continue;
	           case '\r':
	              retval.append("\\r");
	              continue;
	           case '\"':
	              retval.append("\\\"");
	              continue;
	           case '\'':
	              retval.append("\\\'");
	              continue;
	           case '\\':
	              retval.append("\\\\");
	              continue;
	           default:
	              if ((ch = str.charAt(i)) < 0x20 || ch > 0x7e) {
	                 String s = "0000" + Integer.toString(ch, 16);
	                 retval.append("\\u" + s.substring(s.length() - 4, s.length()));
	              } else {
	                 retval.append(ch);
	              }
	              continue;
	        }
	      }
	      return retval.toString();
	   }
	
	public static boolean tokenEsperado(Token t, int [][] expectedTokenSequences) {
		boolean tokenEsp = false;
		int i = 0;
		while (i < expectedTokenSequences.length && !tokenEsp) {
			if (t.kind == expectedTokenSequences[i][0]) tokenEsp = true;
			i++;
		}
		return tokenEsp;
	}
}
