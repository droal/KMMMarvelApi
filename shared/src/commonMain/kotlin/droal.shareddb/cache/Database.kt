package droal.shareddb.cache


//import com.droal.marvel.datasource.network.model.Character
//import com.droal.marvel.datasource.network.model.Thumbnail
//import droal.shareddb.Character
import com.droal.marvel.domain.Character
import droal.shareddb.Character_Entity
import droal.shareddb.MarvelDatabase
import droal.shareddb.SelectAllCharacters
//import droal.shareddb.Thumbnail

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = MarvelDatabase(databaseDriverFactory.createDriver())
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
                    path= character.thumbnailPath,
                    extension= character.thumbnailPath
                )
            }
        }
    }

/*    internal fun saveCharacters(characters: List<Character>) {
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
                    path= character.thumbnail.path,
                    extension= character.thumbnail.extension
                )
            }
        }
    }*/


   /* internal fun getAllCharacters(): List<Character>{
        return dbQuery.selectAllCharacters(::mapingCharacter).executeAsList()
    }

    private fun mapingCharacter(
        id: String,
        name: String,
        description: String?,
        modified: String?,
        resourceURI: String?,
        thumbnailId: String,
        idThumb: String,
        path: String,
        extension: String
    ): Character {
        return Character(
            id = id.toInt(),
            name = name,
            description = description,
            modified = modified,
            resourceURI = resourceURI,
            thumbnail = Thumbnail(
                path = path,
                extension = extension
            ),
            urls = "",
            comics = "",
            stories = "",
            events = "",
            series = ""
        )
    }


    */
}