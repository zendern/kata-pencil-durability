package com.zender.pencil

import spock.lang.Specification


class WriterSpec extends Specification {

    def "i write on the surface and with the utensil I have"(){
        given : "I have a writing utensil"
            WritingUtensil writingUtensil = Mock()

        and : "I have something to write on"
            WritingSurface writingSurface = Mock()

        and : "I know what I want to write"
            def expectedWrittenValue = "some text"

        when : "I write something down"
            Person writer = new Person(writingUtensil: writingUtensil, writingSurface: writingSurface)
            writer.write(expectedWrittenValue)

        then : "the utensil writes to the surface"
            1 * writingUtensil.writeOn(writingSurface, expectedWrittenValue)
    }
}