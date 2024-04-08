package github.mik0war.pizzadeliveryapp.feature.dish.data.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import github.mik0war.pizzadeliveryapp.feature.dish.domain.DishMapper
import github.mik0war.pizzadeliveryapp.feature.dish.domain.Ingredient
import github.mik0war.pizzadeliveryapp.feature.dish.domain.IngredientMapper

@Entity(tableName = "dish")
data class DishCacheEntity(
    @PrimaryKey val dishId: String,
    @ColumnInfo(name = "dish_name")val dishName: String,
    val tag: String,
    @ColumnInfo(name = "image_url")val imageUrl: String,
    @Embedded var extendedData: ExtendedDishCacheEntity? = null
){
    fun <T> map(mapper: DishMapper<T>, ingredients: List<Ingredient>) =
        mapper.mapSuccess(dishId, dishName, tag, imageUrl, ingredients)

}

data class ExtendedDishCacheEntity(
    val area: String,
    val instructions: String,
    @ColumnInfo(name = "youtube_link") val youtubeLink: String,
    @ColumnInfo(name = "source_link") val sourceLink: String,
){
    fun <T> map(mapper: DishMapper<T>, id: String) =
        mapper.mapExtendedData(id, area, instructions, youtubeLink, sourceLink)
}

@Entity(tableName = "ingredient")
data class IngredientCacheModel(
    @ColumnInfo(name = "dish_id") val dishId: String,
    @ColumnInfo(name = "ingredient_name") val ingredientName: String,
    @ColumnInfo(name = "measure") val ingredientMeasure: String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
    fun <T> map(mapper: IngredientMapper<T>) =
        mapper.map(dishId, ingredientName, ingredientMeasure)
}

data class DishWithIngredients(
    @Embedded val dish: DishCacheEntity,
    @Relation(
        parentColumn = "dishId",
        entityColumn = "dish_id"
    )
    val ingredients: List<IngredientCacheModel>
){
    fun <T> map(dishMapper: DishMapper<T>, ingredientMapper: IngredientMapper<Ingredient>) =
        dish.map(dishMapper, ingredients.map { it.map(ingredientMapper) })
}
