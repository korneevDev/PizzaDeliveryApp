package github.mik0war.pizzadeliveryapp.feature.tags.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import github.mik0war.pizzadeliveryapp.core.mapper.MapperTo
import github.mik0war.pizzadeliveryapp.core.mapper.TagMapperTo
import github.mik0war.pizzadeliveryapp.core.Entity as ProjectEntity

@Entity(tableName = "tag")
data class TagCacheEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String
) : ProjectEntity {
    override fun <R : MapperTo<T>, T> map(mapper: R): T =
        (mapper as TagMapperTo<T>).mapSuccess(id, name)
}