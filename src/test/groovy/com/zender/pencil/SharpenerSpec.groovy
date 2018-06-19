package com.zender.pencil

import com.zender.pencil.utensils.WritingUtensil
import spock.lang.Specification

class SharpenerSpec extends Specification {

    def "sharpener makes utensils sharp again"(){
        given :
            Sharpener sharpener = new Sharpener()

        and :
            WritingUtensil writingUtensil = Mock()

        when :
            sharpener.sharpen(writingUtensil)

        then :
            1 * writingUtensil.sharpen()
    }

}
