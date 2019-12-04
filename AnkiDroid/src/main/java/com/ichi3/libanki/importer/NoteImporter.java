package com.ichi3.libanki.importer;

import com.ichi3.libanki.Collection;

/**
 * This class is a stub. Nothing is implemented yet.
 */
public class NoteImporter extends Importer {
    public NoteImporter(Collection col, String file) {
        super(col, file);
    }

    @Override
    public void run() {
        // do nothing
    }

    public int getTotal() {
        return mTotal;
    }
}
