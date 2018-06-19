package com.zender.pencil

import com.zender.pencil.utensils.WritingUtensil

class Person {
    WritingSurface writingSurface
    WritingUtensil writingUtensil

    void write(String value) {
        writingUtensil.writeOn(writingSurface, value)
    }

    String read() {
        writingSurface.readCompletely()
    }
}
