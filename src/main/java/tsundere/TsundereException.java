package tsundere;

/**
 * Checked exception thrown by the tsundere.Tsundere class.
 */
public class TsundereException extends Exception {
    /**
     * Creates a new checked exception with a message.
     * @param message Details to be printed to the user.
     */
    public TsundereException(String message) {
        super(message);
    }
}
