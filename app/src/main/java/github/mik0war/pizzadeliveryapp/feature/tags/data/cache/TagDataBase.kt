package github.mik0war.pizzadeliveryapp.feature.tags.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TagCacheEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TagDataBase : RoomDatabase(){
    abstract fun provideCacheTagDao(): TagCacheDAO
}