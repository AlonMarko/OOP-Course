package conversion;

/**
 * converts k bytes to bytes added as package for convinience so we can
 * expand on it as needed in the future.
 */
public class KBtyesToBytes {
    /*
    constants
     */
    /**
     * a simple function that converts kbytes to bytes.
     */
    private static final int ONE_K_BYTE = 1024;

    public static double convertorBytes(double kBytes) {
        return kBytes * ONE_K_BYTE;
    }
}
