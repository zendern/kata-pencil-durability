package com.zender.pencil.utensils

import com.zender.pencil.surfaces.WritingSurface

interface WritingUtensil {

    void writeOn(WritingSurface writingSurface, String valueToWrite)
}