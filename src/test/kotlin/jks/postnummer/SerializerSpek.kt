package jks.postnummer

import org.amshove.kluent.*
import org.jetbrains.spek.api.*
import org.jetbrains.spek.api.dsl.*

object SerializerSpek : Spek({

   given("a Poststed") {
      it("will be serialized to JSON") {
         val place = Poststed("5555", "Trillebårnes", "Gokk")
         val expected = """
{
  "postnummer": "5555",
  "poststed": "Trillebårnes",
  "kommune": "Gokk"
}
         """.trimIndent()
         val actual = place.toJson()
         actual `should equal` expected
      }
   }

})
