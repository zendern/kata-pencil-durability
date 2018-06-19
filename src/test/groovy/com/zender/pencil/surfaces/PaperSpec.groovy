package com.zender.pencil.surfaces

import spock.lang.Specification

class PaperSpec extends Specification {
    def "Something is written down and can be read back"(){
        given: "I know the value that should be on the surface"
            def expectedWrittenValue = "im going to put this here"

        when : "The value is written"
            Paper paper = new Paper()
            paper.write(expectedWrittenValue)

        then : "The value can be read back from the surface"
            paper.readCompletely() == expectedWrittenValue
    }
}
