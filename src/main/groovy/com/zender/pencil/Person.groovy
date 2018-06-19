package com.zender.pencil

import com.zender.pencil.surfaces.WritingSurface
import com.zender.pencil.utensils.WritingUtensil

class Person {
    WritingSurface writingSurface
    WritingUtensil writingUtensil
    UtensilSharpener sharpener

    void write(String value) {
        writingUtensil.writeOn(writingSurface, value)
    }

    String read() {
        writingSurface.readCompletely()
    }

    void sharpenUtensil() {
        sharpener.sharpen(writingUtensil)
    }
}
