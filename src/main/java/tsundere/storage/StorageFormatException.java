package tsundere.storage;

import tsundere.TsundereException;

/**
 * Thrown if the storage data is corrupted.
 */
public class StorageFormatException extends TsundereException {
    public StorageFormatException() {
        super("Storage data seems to be corrupted.\n");
    }
}
