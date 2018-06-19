package com.zender.pencil.utensils

import com.zender.pencil.surfaces.WritingSurface
import spock.lang.Specification
import spock.lang.Unroll

class PencilSpec extends Specification {

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
            type                         | expectedDurailbity | valueToWrite         | valueWritten         | needSharpened
            "lowercase-we-ran-out"       | 3                  | "test"               | "tes"                | true
            "lowercase-still-good"       | 5                  | "test"               | "test"               | false
            "lowercase-exact"            | 4                  | "test"               | "test"               | true
            "lowercase-not-quite-enough" | 0                  | "t"                  | ""                   | true
            "uppercase-we-ran-out"       | 6                  | "TEST"               | "TES"                | true
            "uppercase-still-good"       | 9                  | "TEST"               | "TEST"               | false
            "uppercase-exact"            | 8                  | "TEST"               | "TEST"               | true
            "uppercase-not-quite-enough" | 1                  | "T"                  | ""                   | false
            "spaces-are-free"            | 1                  | "             "      | "             "      | false
            "newlines-are-free"          | 1                  | "\n\n\n\n\n\n\n\n\n" | "\n\n\n\n\n\n\n\n\n" | false
            "mixed-case"                 | 4                  | "Text"               | "Tex"                | true
            "mixed-case-and-whitespace"  | 4                  | "T e\nxt"            | "T e\nx"             | true
    }

}
