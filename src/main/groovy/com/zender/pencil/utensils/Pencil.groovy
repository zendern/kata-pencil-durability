package com.zender.pencil.utensils

import com.zender.pencil.WritingSurface

class Pencil implements WritingUtensil {
    @Override
    void writeOn(WritingSurface writingSurface, String valueToWrite) {
        writingSurface.write(valueToWrite)
    }
}
