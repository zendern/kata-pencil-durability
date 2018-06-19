package com.zender.pencil.utensils

class Sharpener implements UtensilSharpener {
    @Override
    void sharpen(WritingUtensil writingUtensil) {
        writingUtensil.sharpen()
    }
}
