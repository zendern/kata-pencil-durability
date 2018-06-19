package com.zender.pencil

import com.zender.pencil.surfaces.Paper
import com.zender.pencil.surfaces.WritingSurface
import com.zender.pencil.utensils.Pencil
import com.zender.pencil.utensils.PencilSharpener
import com.zender.pencil.utensils.UtensilSharpener
import com.zender.pencil.utensils.WritingUtensil
import spock.lang.Specification

class WriterIntegrationSpec extends Specification {


    def "happy path"(){
        given : "I have all my tools"
            WritingUtensil pencil = new Pencil(16)
            WritingSurface surface = new Paper()
            UtensilSharpener sharpener = new PencilSharpener()

        and :
            Writer writer = new Writer(writingUtensil: pencil, writingSurface: surface, sharpener: sharpener)

        when : "I want to write something down"
            writer.write("big gulps huh well alright then")

        then : "I can read what I wrote"
            writer.read() == "big gulps huh well a"

        and : "I did not finish my thought b/c my pencil ran out so i sharpen it"
            writer.sharpenUtensil()

        and : "then I write some more"
            writer.write("lright then bye")

        and : "then I read that thought"
            writer.read() == "big gulps huh well alright then bye"

        and : "then I erase that last part"
            writer.erase("bye")

        and : "and proof read one more time"
            writer.read() == "big gulps huh well alright then    "

        and : "then i erase until my eraser is gone"
            writer.erase("big gulps huh well")

        and : "then read the non sense that is left"
            writer.read() == "big gu             alright then    "
    }

}