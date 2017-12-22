package jks.postnummer

import spark.Spark.*
import spark.Spark.exception
import java.lang.Exception


fun main(args: Array<String>) {

   verify(args)

   val placesMap = loadPlaces(args[0])

   notFound { _, res ->
      res.status(404)
      ""
   }

   exception(Exception::class.java) { _, _, res ->
      res.status(500)
      res.body("Oops, that didn't go so well...")
   }

   get("/isAlive") { _, _ -> "Yepp!" }

   get("/isReady") { _, _ -> "Yepp!" }

   get("/:postnr") { req, res ->
      val postnr = req.params("postnr") ?: "-1"
      res.type("application/json")
      placesMap.get(postnr)?.toJson()
   }
}

fun loadPlaces(path: String): Map<String, Poststed> {
   val result = PoststedLoader().loadFromCsv(path)
   return when (result) {
      is LoadResult.Success -> result.places.map { it.postnummer to it }.toMap()
      is LoadResult.Failure -> throw RuntimeException(
         "Error while loading poststed data: ${result.reason}")
   }
}

fun verify(args: Array<String>) {
   if (args.size != 1) {
      println("usage: java -jar postnummer.jar path/to/csv/file")
      System.exit(1)
   }
}

