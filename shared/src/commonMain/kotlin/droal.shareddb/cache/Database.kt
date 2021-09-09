package droal.shareddb.cache

import com.droal.marvel.domain.Character
import com.squareup.sqldelight.db.SqlDriver
import droal.shareddb.MarvelDatabase
import droal.shareddb.SelectAllCharacters

class Database(sqlDriver: SqlDriver) {

    private val database = MarvelDatabase(sqlDriver)
    private val dbQuery = database.marvelDatabaseQueries

    fun clearDatabase(){
        dbQuery.transaction {
            dbQuery.removeAllThumbnail()
            dbQuery.removeAllCharacters()
        }
    }

    fun getAllCharacters(): List<SelectAllCharacters> {
        return dbQuery.selectAllCharacters().executeAsList()
    }

    fun insertCharacters(characters: List<Character>){
        dbQuery.transaction {
            characters.forEach { character ->
                dbQuery.insertCharacter(
                    id = character.id.toString(),
                    name= character.name,
                    description= character.description,
                    modified= character.modified,
                    resourceURI= character.resourceURI,
                    thumbnailId=character.id.toString()
                )
                dbQuery.insertThumbnail(
                    idThumb= character.id.toString(),
                    path= character.thumbnailPath
                )
            }
        }
    }

}