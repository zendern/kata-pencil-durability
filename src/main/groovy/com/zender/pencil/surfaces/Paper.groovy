package com.zender.pencil.surfaces

class Paper implements WritingSurface {
    StringBuffer surfaceStorage = new StringBuffer()

    @Override
    String readCompletely() {
        surfaceStorage
    }

    @Override
    void write(String valueToWrite) {
        this.surfaceStorage.append(valueToWrite)
    }

    @Override
    void erase(String valueToErase) {
        def fullText = readCompletely()
        def lastIndexOf = fullText.lastIndexOf(valueToErase);
        surfaceStorage.replace(lastIndexOf, lastIndexOf + valueToErase.length(), valueToErase.collectReplacements {" "})
    }

    @Override
    void edit(String valueToEdit) {

    }
}
