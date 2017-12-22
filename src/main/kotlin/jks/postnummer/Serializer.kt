package jks.postnummer

val template = """
    {
      "postnummer": "POSTNR",
      "poststed": "STEDET",
      "kommune": "KOMMUNEN"
    }
""".trimIndent()

fun Poststed.toJson(): String =
   template.replace("POSTNR", this.postnummer)
      .replace("STEDET", this.stedsnavn)
      .replace("KOMMUNEN", this.kommune)


