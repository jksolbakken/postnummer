package jks.postnummer

import java.io.*

class PoststedLoader {

   fun loadFromCsv(path: String): LoadResult {
      val file = File(path)

      if (!file.exists()) {
         return LoadResult.Failure("The file ${path} doesn't exist")
      }

      val places = mutableListOf<Poststed>()
      file.forEachLine {
         if (it.isNotBlank()) places.add(parse(it))
      }
      return LoadResult.Success(places)
   }

   fun parse(line: String): Poststed {
      val splitted = line.split("\t")
      return Poststed(splitted[0], splitted[1], splitted[3])
   }
}



