package com.zender.pencil.surfaces

class Paper implements WritingSurface {
    private StringBuffer surfaceStorage = new StringBuffer()

    private int indexOfLastErasedWord = -1
    private int lengthOfLastErasedWord = 0

    @Override
    String readCompletely() {
        surfaceStorage
    }

    @Override
    void write(String valueToWrite) {
        this.surfaceStorage.append(valueToWrite)
    }

    @Override
    void erase(String valueToErase) {
        def fullText = readCompletely()
        this.indexOfLastErasedWord = fullText.lastIndexOf(valueToErase);
        this.lengthOfLastErasedWord = valueToErase.length()
        surfaceStorage.replace(this.indexOfLastErasedWord, this.indexOfLastErasedWord + this.lengthOfLastErasedWord, valueToErase.collectReplacements {
            " "
        })
    }

    @Override
    void edit(String valueToEdit) {
        if(lengthOfLastErasedWord < valueToEdit.length()){
            for(int i = indexOfLastErasedWord; i < indexOfLastErasedWord + valueToEdit.length(); i ++){
                def currentCharOnSurface = surfaceStorage.getAt(i)
                def wantedEditedChar = valueToEdit.charAt(i - indexOfLastErasedWord)
                if(currentCharOnSurface == " "){
                    surfaceStorage.replace(i, i+1, wantedEditedChar as String)
                }else if(currentCharOnSurface != wantedEditedChar){
                    surfaceStorage.replace(i, i+1, "@")
                }
            }
        }else{
            surfaceStorage.replace(this.indexOfLastErasedWord, this.indexOfLastErasedWord + this.lengthOfLastErasedWord, valueToEdit)
        }
    }
}
