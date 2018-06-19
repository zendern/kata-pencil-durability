package com.zender.pencil.utensils

import com.zender.pencil.surfaces.WritingSurface

class Pencil implements WritingUtensil {
    private int durability

    Pencil(){
        this.durability = 0
    }

    @Override
    boolean needsSharpened(){
        durability == 0
    }

    @Override
    void writeOn(WritingSurface writingSurface, String valueToWrite) {
        writingSurface.write(valueToWrite)
    }
}
