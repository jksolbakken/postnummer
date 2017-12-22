package jks.postnummer

sealed class LoadResult {

   class Success(val places: List<Poststed>): LoadResult()

   class Failure(val reason: String): LoadResult()

}
