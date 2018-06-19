package com.zender.pencil

import com.zender.pencil.surfaces.WritingSurface
import com.zender.pencil.utensils.UtensilSharpener
import com.zender.pencil.utensils.WritingUtensil
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
            Writer writer = new Writer(writingUtensil: writingUtensil, writingSurface: writingSurface)
            writer.write(expectedWrittenValue)

        then : "the utensil writes to the surface"
            1 * writingUtensil.writeOn(writingSurface, expectedWrittenValue)
    }

    def "after i have written on the surface I can read it back to myself"(){
        given : "I have a writing utensil"
            WritingUtensil writingUtensil = Mock()

        and : "I have something to write on"
            WritingSurface writingSurface = Mock()

        and : "I know what I want to write"
            def expectedWrittenValue = "some text"

        and : "I write something down"
            Writer writer = new Writer(writingUtensil: writingUtensil, writingSurface: writingSurface)
            writer.write(expectedWrittenValue)

        when : "I want to read it back to myself"
            def actualValue = writer.read()

        then : "the surface returns what was written"
            1 * writingSurface.readCompletely() >> expectedWrittenValue

        and: "the value return is exactly what I wrote"
            actualValue == expectedWrittenValue
    }

    def "as a writer I want to be able to sharpen my utensil"(){
        given : "I have a writing utensil"
            WritingUtensil writingUtensil = Mock()

        and : "I have a sharpener"
            UtensilSharpener sharpener = Mock()

        and :
            Writer writer = new Writer(writingUtensil : writingUtensil, sharpener : sharpener)

        when : "I want sharpen my utensil"
            writer.sharpenUtensil()

        then :
            1 * sharpener.sharpen(writingUtensil)
    }

    def "as a writer I want to be able to erase some text"(){
        given : "I have a writing utensil"
            WritingUtensil writingUtensil = Mock()

        and : "I have a the surface that I wrote on"
            WritingSurface writingSurface = Mock()

        and :
            Writer writer = new Writer(writingUtensil : writingUtensil, writingSurface: writingSurface)

        and :
            def valueToErase = "text to erase"

        when : "I want erase something"
            writer.erase(valueToErase)

        then :
            1 * writingUtensil.erase(writingSurface, valueToErase)
    }

    def "as a writer I want to be able to do some editing"(){
        given : "I have a writing utensil"
            WritingUtensil writingUtensil = Mock()

        and : "I have a the surface that I wrote on"
            WritingSurface writingSurface = Mock()

        and :
            Writer writer = new Writer(writingUtensil : writingUtensil, writingSurface: writingSurface)

        and :
            def valueToEdit = "new value"

        when : "I want edit something"
            writer.edit(valueToEdit)

        then :
            1 * writingUtensil.edit(writingSurface, valueToEdit)
    }

}