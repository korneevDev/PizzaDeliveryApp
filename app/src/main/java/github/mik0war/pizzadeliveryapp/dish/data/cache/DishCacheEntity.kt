package github.mik0war.pizzadeliveryapp.dish.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import github.mik0war.pizzadeliveryapp.core.dishDataModel.DishUIModel
import github.mik0war.pizzadeliveryapp.core.mapper.DishMapperTo
import github.mik0war.pizzadeliveryapp.core.mapper.MapperTo
import github.mik0war.pizzadeliveryapp.core.Entity as ProjectEntity

@Entity(tableName = "dish")
data class DishCacheEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "image_url") val image_url: String,
) : ProjectEntity {
    override fun <R : MapperTo<T>, T> map(mapper: R): T =
        (mapper as DishMapperTo<T>).mapSuccess(id, name, description, price, image_url)
}