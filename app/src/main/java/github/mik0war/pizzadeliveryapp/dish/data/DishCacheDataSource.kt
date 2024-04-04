package github.mik0war.pizzadeliveryapp.dish.data

import github.mik0war.pizzadeliveryapp.core.CacheDataSource
import github.mik0war.pizzadeliveryapp.dish.data.room.DishCacheDAO
import github.mik0war.pizzadeliveryapp.dish.data.room.DishCacheEntity
import github.mik0war.pizzadeliveryapp.dish.data.room.ExtendedDishCacheEntity
import github.mik0war.pizzadeliveryapp.dish.data.room.IngredientCacheModel
import github.mik0war.pizzadeliveryapp.dish.domain.DishDataModel
import github.mik0war.pizzadeliveryapp.dish.domain.DishMapper
import github.mik0war.pizzadeliveryapp.dish.domain.Ingredient
import github.mik0war.pizzadeliveryapp.dish.domain.IngredientMapper
import javax.inject.Inject

typealias FullDishData = Pair<DishDataModel, DishDataModel>
class DishCacheDataSource @Inject constructor(
    private val cacheDAO: DishCacheDAO,
    private val baseMapperToDB: DishMapper<DishCacheEntity>,
    private val extendedMapperToDB: DishMapper<ExtendedDishCacheEntity>,
    private val mapperFromDB: DishMapper<DishDataModel>,
    private val ingredientMapperToDB: IngredientMapper<IngredientCacheModel>,
    private val ingredientMapperFromDB: IngredientMapper<Ingredient>
) : CacheDataSource.Abstract<DishDataModel>() {
    override suspend fun saveListData(
        lists: List<FullDishData>
    ) {
        cacheDAO.clearDishTables()

        lists.forEach { fullData ->
            val cacheModel = fullData.first.map(baseMapperToDB).also {
                it.extendedData = fullData.second.map(extendedMapperToDB)

            }
            cacheDAO.saveDish(cacheModel)

            (fullData.first as DishDataModel.Base).getIngredientsList().forEach{
                cacheDAO.saveIngredient(it.map(ingredientMapperToDB, cacheModel.dishId))
            }
        }
    }

    override suspend fun getBaseData(): List<DishDataModel> =
        cacheDAO.getCachedData().map { it.map(mapperFromDB, ingredientMapperFromDB) }


    override suspend fun getExtendedData(id: String): DishDataModel =
        cacheDAO.getExtendedData(id).extendedData!!.map(mapperFromDB, id)

}