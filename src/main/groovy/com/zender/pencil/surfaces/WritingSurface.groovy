package com.zender.pencil.surfaces

interface WritingSurface {

    String readCompletely()

    void write(String valueToWrite)
}