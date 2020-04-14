
package javax.el;

public class TesterFunctions {

    private static StringBuilder calls = new StringBuilder();

    public static String getCallList() {
        return calls.toString();
    }

    public static void resetCallList() {
        calls = new StringBuilder();
    }

    public static void doIt() {
        calls.append('A');
    }

    public static void doIt(@SuppressWarnings("unused") int a) {
        calls.append('B');
    }

    public static void doIt(@SuppressWarnings("unused") Integer a) {
        calls.append('C');
    }

    public static void doIt(@SuppressWarnings("unused") int[] a) {
        calls.append('D');
    }

    public static void doIt(@SuppressWarnings("unused") int[][] a) {
        calls.append('E');
    }

    public static void doIt(@SuppressWarnings("unused") Integer[] a) {
        calls.append('F');
    }

    public static void doIt(@SuppressWarnings("unused") Integer[][] a) {
        calls.append('G');
    }

    public static void doIt(@SuppressWarnings("unused") long... a) {
        calls.append('H');
    }

    public static void doIt(@SuppressWarnings("unused") Object... a) {
        calls.append('I');
    }
}
