package com.zender.pencil.utensils

import com.zender.pencil.surfaces.WritingSurface

class Pencil implements WritingUtensil {
    @Override
    void writeOn(WritingSurface writingSurface, String valueToWrite) {
        writingSurface.write(valueToWrite)
    }
}
