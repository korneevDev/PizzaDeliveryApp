package github.mik0war.pizzadeliveryapp.feature.tags.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TagCacheDAO {

    @Query("SELECT * FROM tag")
    fun getCachedData(): List<TagCacheEntity>

    @Insert
    fun saveDish(tag: TagCacheEntity)

    @Query("Delete FROM tag")
    fun clearTable()
}