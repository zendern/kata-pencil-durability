package com.zender.pencil.surfaces

import spock.lang.Specification
import spock.lang.Unroll

class PaperSpec extends Specification {
    def "Something is written down and can be read back"() {
        given: "I know the value that should be on the surface"
            def expectedWrittenValue = "im going to put this here"

        when: "The value is written"
            Paper paper = new Paper()
            paper.write(expectedWrittenValue)

        then: "The value can be read back from the surface"
            paper.readCompletely() == expectedWrittenValue
    }

    def "I keep on writing on the paper and it just keeps on adding it"() {
        given: "I have multiple things that should be on the surface"
            def expectedWrittenValues = ["im going to put this here", "and this", "\n and maybe some of this"]

        when: "The value is written"
            Paper paper = new Paper()
            expectedWrittenValues.each { value ->
                paper.write(value)
            }

        then: "The value can be read back from the surface"
            paper.readCompletely() == expectedWrittenValues.join("")
    }

    def "I can erase things"() {
        given: "a writing surface"
            WritingSurface surface = new Paper()

        and:
            def valueToWrite = "How much wood would a woodchuck chuck if a woodchuck could chuck wood?"
            surface.write(valueToWrite)

        when:
            surface.erase("chuck")

        then:
            surface.readCompletely() == "How much wood would a woodchuck chuck if a woodchuck could       wood?"

        and: "we erase again"
            surface.erase("chuck")

        then:
            surface.readCompletely() == "How much wood would a woodchuck chuck if a wood      could       wood?"
    }

    @Unroll
    def "I can edit the last spot that was erased : #type"() {
        given: "a writing surface"
            WritingSurface surface = new Paper()

        and:
            surface.write(valueToWrite)

        and:
            surface.erase(valueToErase)

        when:
            surface.edit(replacementWord)

        then:
            surface.readCompletely() == expected

        where:
            type                                | valueToErase | replacementWord | valueToWrite                                                             | expected
            "same-as-erased"                    | "chuck"      | "chomp"         | "How much wood would a woodchuck chuck if a woodchuck could chuck wood?" | "How much wood would a woodchuck chuck if a woodchuck could chomp wood?"
            "smaller-erased"                    | "chuck"      | "chew"          | "How much wood would a woodchuck chuck if a woodchuck could chuck wood?" | "How much wood would a woodchuck chuck if a woodchuck could chew wood?"
            "bigger-erased-no-matches"          | "chuck"      | "gobbleup"     | "How much wood would a woodchuck chuck if a woodchuck could chuck wood?" | "How much wood would a woodchuck chuck if a woodchuck could gobble@@od?"
            "bigger-erased-with-matching-chars" | "apple"      | "artichoke"     | "An apple a day keeps the doctor away"                                   | "An artich@k@ay keeps the doctor away"
    }
}
