package droal.shareddb.cache

import android.content.Context
import com.droal.marvel.di.ContextArg
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import droal.shareddb.MarvelDatabase

actual class DatabaseDriverFactory(private val context: Context){
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(MarvelDatabase.Schema, context, "test.db")
    }

}

actual fun getSqlDriver(contextArg: ContextArg?): SqlDriver {
    return AndroidSqliteDriver(MarvelDatabase.Schema, contextArg?.context!!, "test.db")
}
