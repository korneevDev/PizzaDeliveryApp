package github.mik0war.pizzadeliveryapp.feature.tags.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TagDAO {

    @Query("SELECT * FROM tags")
    suspend fun getTagsList(): List<TagCacheModel>

    @Query("DELETE FROM tags")
    suspend fun clearTagsTable()

    @Insert
    suspend fun saveTag(tag: TagCacheModel)
}