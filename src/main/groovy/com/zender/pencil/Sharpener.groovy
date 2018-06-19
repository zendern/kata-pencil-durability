package com.zender.pencil

import com.zender.pencil.utensils.WritingUtensil

class Sharpener implements UtensilSharpener {
    @Override
    void sharpen(WritingUtensil writingUtensil) {
        writingUtensil.sharpen()
    }
}
