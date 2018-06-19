package com.zender.pencil.utensils

import com.zender.pencil.surfaces.WritingSurface

class Pencil implements WritingUtensil {
    private int durability

    Pencil(){
        this.durability = 0
    }

    Pencil(int durability){
        this.durability = durability
    }

    @Override
    boolean needsSharpened(){
        durability == 0
    }

    @Override
    void writeOn(WritingSurface writingSurface, String valueToWrite) {
        for (char character in valueToWrite) {
            if (durability == 0) {
                break
            }
            durability = durability - 1
        }
        writingSurface.write(valueToWrite)
    }
}
