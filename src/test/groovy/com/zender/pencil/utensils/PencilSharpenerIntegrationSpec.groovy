package com.zender.pencil.utensils

import com.zender.pencil.surfaces.Paper
import com.zender.pencil.surfaces.WritingSurface
import spock.lang.Specification


class PencilSharpenerIntegrationSpec extends Specification {

    def "we can actually sharpen a pencil"(){
        given :
            WritingUtensil pencil = new Pencil(4)

        and :
            UtensilSharpener sharpener = new PencilSharpener()

        and :
            WritingSurface surface = new Paper()

        when : "I write some text to cause the pencil to dull"
            pencil.writeOn(surface, "abcde")

        then :
            pencil.needsSharpened()

        and : "if I sharpen"
            sharpener.sharpen(pencil)

        then : "pencil will be able to write as much as originally able"
            pencil.writeOn(surface, "abcde")

        and :
            pencil.needsSharpened()

        and : "1234 was written on surface twice"
            surface.readCompletely() == "abcdabcd"
    }

    def "pencil can only be sharpened 5 times by default"(){
        given :
            WritingUtensil pencil = new Pencil(4)

        and :
            UtensilSharpener sharpener = new PencilSharpener()

        when : "I sharpen the pencil over and over"
            (1..5).each {
                sharpener.sharpen(pencil)
            }

        then : "pencil will seize to exist"
            pencil.isEmpty()
    }

    def "pencil can only be sharpened so many times"(){
        given :
            def expectedLength = 2
            WritingUtensil pencil = new Pencil(4, expectedLength)

        and :
            UtensilSharpener sharpener = new PencilSharpener()

        when : "I sharpen the pencil over and over"
            (1..expectedLength).each {
                sharpener.sharpen(pencil)
            }

        then : "pencil will seize to exist"
            pencil.isEmpty()
    }


}