package com.zender.pencil.utensils

import com.zender.pencil.surfaces.WritingSurface
import spock.lang.Specification
import spock.lang.Unroll

class PencilSpec extends Specification {

    def "pencil can erase from surface"() {
        given:
            WritingUtensil writingUtensil = new Pencil(100)

        and:
            WritingSurface surface = Mock()

        and:
            def expectedValueToErase = "valueToErase"

        when:
            writingUtensil.erase(surface, expectedValueToErase)


        then:
            1 * surface.erase(expectedValueToErase)
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

    @Unroll
    def "we can see the state of the eraser : #hasEraserLeft"() {
        when: "I have a brand new pencil that has been sharpened"
            WritingUtensil writingUtensil = new Pencil(10, 5, eraserDurability)

        then:
            writingUtensil.hasEraserLeft() == hasEraserLeft

        where:
            eraserDurability              | hasEraserLeft
            0                             | false
            new Random().nextInt(100) + 1 | true
    }

    @Unroll
    def "erasing degrades pencil's eraser durability : #type"() {
        given: "I have a pencil with a given durability"
            WritingUtensil writingUtensil = new Pencil(1_000, 6, expectedDurailbity)

        and: "a writing surface"
            WritingSurface surface = Mock()

        when:
            writingUtensil.erase(surface, valueToErase)

        then:
            writingUtensil.hasEraserLeft() == hasEraserLeft

        and:
            if (valueErased != "") {
                1 * surface.erase(valueErased)
            }

        where:
            type                         | expectedDurailbity | valueToErase | valueErased | hasEraserLeft
            "cant-erase-if-nothing-left" | 0                  | "t"          | ""          | false
            "lowercase-we-ran-out"       | 3                  | "test"       | "est"       | false
            "lowercase-still-good"       | 5                  | "test"       | "test"      | true
            "lowercase-exact"            | 4                  | "test"       | "test"      | false
            "uppercase-we-ran-out"       | 3                  | "TEST"       | "EST"       | false
            "uppercase-still-good"       | 5                  | "TEST"       | "TEST"      | true
            "uppercase-exact"            | 4                  | "TEST"       | "TEST"      | false
            "mixed-case"                 | 4                  | "Text"       | "Text"      | false
            "mixed-case-and-whitespace"  | 6                  | "T e\nxt"    | "T e\nxt"   | false
            "numbers"                    | 6                  | "12345678"    | "345678"   | false
    }


}
