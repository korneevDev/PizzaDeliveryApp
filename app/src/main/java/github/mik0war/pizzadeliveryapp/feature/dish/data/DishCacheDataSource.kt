package github.mik0war.pizzadeliveryapp.feature.dish.data

import github.mik0war.pizzadeliveryapp.core.NoCachedDataException
import github.mik0war.pizzadeliveryapp.feature.dish.data.room.DishCacheDAO
import github.mik0war.pizzadeliveryapp.feature.dish.data.room.DishCacheEntity
import github.mik0war.pizzadeliveryapp.feature.dish.data.room.ExtendedDishCacheEntity
import github.mik0war.pizzadeliveryapp.feature.dish.data.room.IngredientCacheModel
import github.mik0war.pizzadeliveryapp.feature.dish.domain.DishDataModel
import github.mik0war.pizzadeliveryapp.feature.dish.domain.DishMapper
import github.mik0war.pizzadeliveryapp.feature.dish.domain.Ingredient
import github.mik0war.pizzadeliveryapp.feature.dish.domain.IngredientMapper
import javax.inject.Inject

typealias FullDishData = Pair<DishDataModel, DishDataModel>

class DishCacheDataSource @Inject constructor(
    private val cacheDAO: DishCacheDAO,
    private val baseMapperToDB: DishMapper<DishCacheEntity>,
    private val extendedMapperToDB: DishMapper<ExtendedDishCacheEntity>,
    private val mapperFromDB: DishMapper<DishDataModel>,
    private val ingredientMapperToDB: IngredientMapper<IngredientCacheModel>,
    private val ingredientMapperFromDB: IngredientMapper<Ingredient>
) {
    suspend fun saveListData(
        lists: List<FullDishData>
    ) {
        cacheDAO.clearDishTables()

        lists.forEach { fullData ->
            val cacheModel = fullData.first.map(baseMapperToDB).also {
                it.extendedData = fullData.second.map(extendedMapperToDB)

            }
            cacheDAO.saveDish(cacheModel)

            (fullData.first as DishDataModel.Base).getIngredientsList().forEach {
                cacheDAO.saveIngredient(it.map(ingredientMapperToDB, cacheModel.dishId))
            }
        }
    }

    suspend fun getBaseListData(): List<DishDataModel> =
        cacheDAO.getCachedData().map { it.map(mapperFromDB, ingredientMapperFromDB) }
            .ifEmpty { throw NoCachedDataException() }


    suspend fun getExtendedData(id: String): DishDataModel =
        cacheDAO.getExtendedData(id).extendedData!!.map(mapperFromDB, id)

}