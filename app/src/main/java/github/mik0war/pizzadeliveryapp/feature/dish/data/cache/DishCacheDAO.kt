package github.mik0war.pizzadeliveryapp.feature.dish.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DishCacheDAO {

    @Query("SELECT * FROM dish")
    fun getCachedData(): List<DishCacheEntity>

    @Insert
    fun saveDish(dish:DishCacheEntity)

    @Query("Delete FROM dish")
    fun clearTable()
}