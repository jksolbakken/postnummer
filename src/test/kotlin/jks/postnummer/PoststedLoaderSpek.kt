package jks.postnummer

import org.amshove.kluent.*
import org.jetbrains.spek.api.*
import org.jetbrains.spek.api.dsl.*

object PoststedLoaderSpek : Spek({

   val loader = PoststedLoader()

   describe("when loading csv poststed data") {
      given("a properly formatted file") {
         it("will be parsed without errors") {
            val result = loader.loadFromCsv("src/test/resources/postnummer.txt")
            result `should be instance of` LoadResult.Success::class
            when (result) {
               is LoadResult.Success -> result.places.size `should equal` 4855
            }
         }
      }

      given("a tab separated line") {
         it ("extracts a Poststed") {
            val line = "8651\tMOSJØEN\t1824\tVEFSN\tP"
            val expected = Poststed("8651", "MOSJØEN", "VEFSN")
            val actual = loader.parse(line)
            actual `should equal` expected
         }
      }
   }
})
