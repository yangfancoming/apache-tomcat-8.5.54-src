
package jsp2.examples.el;

import java.util.Locale;

/**
 * Defines the functions for the jsp2 example tag library.
 *
 * <p>Each function is defined as a static method.</p>
 */
public class Functions {
    public static String reverse( String text ) {
        return new StringBuilder( text ).reverse().toString();
    }

    public static int numVowels( String text ) {
        String vowels = "aeiouAEIOU";
        int result = 0;
        for( int i = 0; i < text.length(); i++ ) {
            if( vowels.indexOf( text.charAt( i ) ) != -1 ) {
                result++;
            }
        }
        return result;
    }

    public static String caps( String text ) {
        return text.toUpperCase(Locale.ENGLISH);
    }
}
