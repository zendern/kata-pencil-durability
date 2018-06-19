package com.zender.pencil.surfaces

class Paper implements WritingSurface {
    String surfaceStorage

    @Override
    String readCompletely() {
        surfaceStorage
    }

    @Override
    void write(String valueToWrite) {
        this.surfaceStorage = valueToWrite
    }
}
