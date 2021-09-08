package droal.shareddb.cache

import android.content.Context
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import droal.shareddb.MarvelDatabase

actual class DatabaseDriverFactory(private val context: Context){
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(MarvelDatabase.Schema, context, "test.db")
    }

}