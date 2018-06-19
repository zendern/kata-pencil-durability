package com.zender.pencil.utensils

import com.zender.pencil.surfaces.WritingSurface
import spock.lang.Specification
import spock.lang.Unroll

class PencilSpec extends Specification {
    def "I write the value to the surface"() {
        given: "I have a surface to write on"
            WritingSurface writingSurface = Mock()

        and: "I know what I am to write"
            def expectedValueToWrite = "write me down"

        when:
            Pencil pencil = new Pencil(1_000)
            pencil.writeOn(writingSurface, expectedValueToWrite)

        then: "I should have written it to the surface"
            1 * writingSurface.write(expectedValueToWrite)
    }

    def "pencil needs sharpened when new"() {
        when: "I have a brand new pencil"
            WritingUtensil writingUtensil = new Pencil()

        then:
            writingUtensil.needsSharpened()
    }

    def "pencil can be sharp when new"() {
        when: "I have a brand new pencil that has been sharpened"
            int randomDurability = new Random().nextInt(1_000) + 1
            WritingUtensil writingUtensil = new Pencil(randomDurability)

        then:
            !writingUtensil.needsSharpened()
    }

    @Unroll
    def "writing degrades pencil durability : #type"() {
        given: "I have a pencil with a given durability"
            WritingUtensil writingUtensil = new Pencil(expectedDurailbity)

        and: "a writing surface"
            WritingSurface surface = Mock()

        when:
            writingUtensil.writeOn(surface, valueToWrite)

        then:
            writingUtensil.needsSharpened() == needSharpened

        and:
            1 * surface.write(valueWritten)

        where:
            type                   | expectedDurailbity | valueToWrite | valueWritten | needSharpened
            "lowercase-we-ran-out" | 3                  | "test"       | "tes"        | true
            "lowercase-still-good" | 5                  | "test"       | "test"       | false
            "lowercase-exact"      | 4                  | "test"       | "test"       | true

    }

}
