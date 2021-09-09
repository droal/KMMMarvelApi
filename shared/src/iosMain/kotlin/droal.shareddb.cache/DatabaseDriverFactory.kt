package droal.shareddb.cache

import com.droal.marvel.di.ContextArg
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import droal.shareddb.MarvelDatabase

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(MarvelDatabase.Schema, "test.db")
    }
}

actual fun getSqlDriver(contextArg: ContextArg?): SqlDriver {
    return NativeSqliteDriver(MarvelDatabase.Schema, "test.db")
}