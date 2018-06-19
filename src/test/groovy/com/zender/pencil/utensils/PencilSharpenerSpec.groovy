package com.zender.pencil.utensils

import spock.lang.Specification

class PencilSharpenerSpec extends Specification {

    def "sharpener makes utensils sharp again"(){
        given :
            PencilSharpener sharpener = new PencilSharpener()

        and :
            Pencil writingUtensil = Mock()

        when :
            sharpener.sharpen(writingUtensil)

        then :
            1 * writingUtensil.sharpen()
    }

    def "pencil sharpener cannot sharpen anything but pencils"(){
        given :
            PencilSharpener sharpener = new PencilSharpener()

        and :
            WritingUtensil writingUtensil = Mock()

        when :
            sharpener.sharpen(writingUtensil)

        then :
            def ex = thrown(IllegalArgumentException)
            ex.message == "Pencil sharpeners are only for pencils sorry try again."
    }

}
