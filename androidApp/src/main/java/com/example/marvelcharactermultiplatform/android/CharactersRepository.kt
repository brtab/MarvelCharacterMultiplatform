package ar.edu.unlam.marvelcharacters

interface CharactersRepository {

    suspend fun getCharacters(timestamp: Long, md5: String): List<Character>

}
