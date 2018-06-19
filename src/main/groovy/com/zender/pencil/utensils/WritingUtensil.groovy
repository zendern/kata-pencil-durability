package com.zender.pencil.utensils

import com.zender.pencil.WritingSurface

interface WritingUtensil {

    void writeOn(WritingSurface writingSurface, String valueToWrite)
}