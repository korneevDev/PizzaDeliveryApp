package github.mik0war.pizzadeliveryapp.feature.tags.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import github.mik0war.pizzadeliveryapp.feature.tags.domain.TagMapper

@Entity(tableName = "tags")
data class TagCacheModel(
    @PrimaryKey val id: Int,
    val name: String
){
    fun <T> map(mapper: TagMapper<T>) = mapper.mapSuccess(id, name, false)
}
