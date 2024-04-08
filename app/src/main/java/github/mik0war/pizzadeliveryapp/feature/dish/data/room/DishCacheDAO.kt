package github.mik0war.pizzadeliveryapp.feature.dish.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RewriteQueriesToDropUnusedColumns
import androidx.room.Transaction

@Dao
interface DishCacheDAO {

    @Transaction
    @Query("SELECT * " +
            "FROM dish")
    suspend fun getCachedData(): List<DishWithIngredients>

    @RewriteQueriesToDropUnusedColumns
    @Query("SELECT * " +
            "FROM dish " +
            "WHERE dishId=:id")
    suspend fun getExtendedData(id: String): DishCacheEntity

    @Insert
    suspend fun saveDish(dish: DishCacheEntity)

    @Insert
    suspend fun saveIngredient(ingredient: IngredientCacheModel)



    @Transaction
    suspend fun clearDishTables(){
        clearDish()
        clearIngredient()
    }


    @Query("Delete FROM dish")
    suspend fun clearDish()

    @Query("Delete FROM ingredient")
    suspend fun clearIngredient()
}