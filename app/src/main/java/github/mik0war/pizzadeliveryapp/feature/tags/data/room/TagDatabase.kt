package github.mik0war.pizzadeliveryapp.feature.tags.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    version = 1,
    entities = [TagCacheModel::class],
    exportSchema = false
)
abstract class TagDatabase : RoomDatabase() {
    abstract fun tagDAO(): TagDAO
}