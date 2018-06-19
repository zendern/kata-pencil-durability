package com.zender.pencil

class Person {
    WritingSurface writingSurface
    WritingUtensil writingUtensil

    void write(String value) {
        writingUtensil.writeOn(writingSurface, value)
    }
}
