package com.zender.pencil.utensils

class PencilSharpener implements UtensilSharpener {
    @Override
    void sharpen(WritingUtensil writingUtensil) {
        if(!(writingUtensil instanceof Pencil)){
            throw new IllegalArgumentException("Pencil sharpeners are only for pencils sorry try again.")
        }
        if(writingUtensil.isEmpty()){
            throw new IllegalStateException("Pencil is no more....what do you want me to do??")
        }

        writingUtensil.sharpen()
    }
}
