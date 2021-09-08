package droal.shareddb.cache


import com.droal.marvel.api.data.character.Character
import com.droal.marvel.api.data.character.Thumbnail
import droal.shareddb.MarvelDatabase

internal class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = MarvelDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.marvelDatabaseQueries

    internal fun clearDatabase(){
        dbQuery.transaction {
            dbQuery.removeAllThumbnail()
            dbQuery.removeAllCharacters()
        }
    }

    internal fun getAllCharacters(): List<Character>{
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
    ): Character{
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


    internal fun saveCharacters(characters: List<Character>) {
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
    }
}