package droal.shareddb.cache


import com.droal.marvel.di.ContextArg
import com.squareup.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

expect fun getSqlDriver(contextArg: ContextArg? = null): SqlDriver

object DatabaseCreator {
    fun getDataBase(contextArgs: ContextArg?): Database? {
        val sqlDriver  = getSqlDriver(contextArgs)
        if (sqlDriver != null) {
            return Database(sqlDriver)
        } else {
            return null
        }
    }
}