package github.mik0war.pizzadeliveryapp.feature.dish.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [DishCacheEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DishDataBase : RoomDatabase(){
    abstract fun provideCacheDishDao(): DishCacheDAO
}