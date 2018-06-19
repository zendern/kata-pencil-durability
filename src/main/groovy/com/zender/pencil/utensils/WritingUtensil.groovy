package com.zender.pencil.utensils

import com.zender.pencil.surfaces.WritingSurface

interface WritingUtensil {

    boolean needsSharpened()
    void writeOn(WritingSurface writingSurface, String valueToWrite)
    void sharpen()
}