package com.zender.pencil.utensils

import com.zender.pencil.surfaces.WritingSurface

class Pencil implements WritingUtensil {
    private int durability
    private int originalDurability
    private int length

    Pencil(int durability, int length = 5){
        this.durability = durability
        this.originalDurability = durability
        this.length = length
    }

    @Override
    boolean needsSharpened(){
        durability == 0
    }

    @Override
    void writeOn(WritingSurface writingSurface, String valueToWrite) {
        StringBuilder writeBuffer = new StringBuilder()
        for (char character in valueToWrite) {
            if (durability == 0) {
                break
            }
            if(character.isWhitespace()){
                writeBuffer.append(character)
            }else if(character.isLowerCase() && durability >= 1){
                durability = durability - 1
                writeBuffer.append(character)
            }else if(character.isUpperCase() && durability >= 2){
                durability = durability - 2
                writeBuffer.append(character)
            }
        }
        writingSurface.write(writeBuffer.toString())
    }

    @Override
    boolean isEmpty() {
        this.length == 0
    }

    protected void sharpen() {
        this.length = this.length - 1
        this.durability = this.originalDurability
    }
}
