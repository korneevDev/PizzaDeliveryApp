package github.mik0war.pizzadeliveryapp.dish.di

import dagger.Binds
import dagger.Module
import github.mik0war.entity.ExceptionHandler
import github.mik0war.entity.StringResourceProvider
import github.mik0war.pizzadeliveryapp.core.DataMapper
import github.mik0war.pizzadeliveryapp.core.dishDataModel.DishDataModel
import github.mik0war.pizzadeliveryapp.core.dishDataModel.DishModel
import github.mik0war.pizzadeliveryapp.core.dishDataModel.DishUIModel
import github.mik0war.pizzadeliveryapp.dish.data.cache.DishCacheEntity
import github.mik0war.pizzadeliveryapp.dish.data.cache.DishCacheGetDataListDataSource
import github.mik0war.pizzadeliveryapp.dish.data.cloud.DishCloudGetDataListDataSource
import github.mik0war.pizzadeliveryapp.dish.data.cloud.DishServerModel
import github.mik0war.pizzadeliveryapp.dish.domain.CategoryExceptionHandler
import github.mik0war.pizzadeliveryapp.recycler_list.data.CacheGetDataListDataSource
import github.mik0war.pizzadeliveryapp.recycler_list.data.GetDataListDataSource
import github.mik0war.pizzadeliveryapp.recycler_list.data.GetDataListRepositoryImpl
import github.mik0war.pizzadeliveryapp.recycler_list.domain.GetDataListRepository
import github.mik0war.pizzadeliveryapp.recycler_list.presentation.GetDataListLiveData
import github.mik0war.pizzadeliveryapp.recycler_list.presentation.GetDataListViewModel
import github.mik0war.pizzadeliveryapp.recycler_list.domain.GetDataListInteractor

@Module
abstract class DishDataBindsModule {
    @Binds
    abstract fun bindVM(vm: GetDataListViewModel.Base<DishModel, DishUIModel>):
            GetDataListViewModel<DishUIModel>

    @Binds
    abstract fun bindLiveData(liveData: GetDataListLiveData.Base<DishUIModel>):
            GetDataListLiveData<DishUIModel>

    @Binds
    abstract fun bindMapperDishDataModelDishUIModel(mapper: DataMapper.Base<DishDataModel, DishUIModel>):
            DataMapper<DishDataModel, DishUIModel>

    @Binds
    abstract fun bindInteractor(interactor: GetDataListInteractor.Base<DishDataModel, DishModel>):
            GetDataListInteractor<DishModel>

    @Binds
    abstract fun bindExceptionHandler(exceptionHandler: CategoryExceptionHandler):
            ExceptionHandler<DishModel>

    @Binds
    abstract fun bindMapperDishDataModelDishModel(mapper: DataMapper.Base<DishDataModel, DishModel>):
            DataMapper<DishDataModel, DishModel>

    @Binds
    abstract fun bindMapperDishModelDishUIModel(mapper: DataMapper.Base<DishModel, DishUIModel>):
            DataMapper<DishModel, DishUIModel>

    @Binds
    abstract fun bindStringResourceProvider(provider: StringResourceProvider.Base): StringResourceProvider

    @Binds
    abstract fun bindRepo(repo: GetDataListRepositoryImpl<DishDataModel>): GetDataListRepository<DishDataModel>

    @Binds
    abstract fun bindCacheDataSource(source: DishCacheGetDataListDataSource): CacheGetDataListDataSource<DishDataModel>

    @Binds
    abstract fun bindCloudDataSource(source: DishCloudGetDataListDataSource): GetDataListDataSource<DishDataModel>

    @Binds
    abstract fun bindMapperDishServerModelDishDataModel(mapper: DataMapper.Base<DishServerModel, DishDataModel>):
            DataMapper<DishServerModel, DishDataModel>

    @Binds
    abstract fun bindMapperDishCacheEntityDishDataModel(mapper: DataMapper.Base<DishCacheEntity, DishDataModel>):
            DataMapper<DishCacheEntity, DishDataModel>

    @Binds
    abstract fun bindMapperDishDataModelDishCacheEntity(mapper: DataMapper.Base<DishDataModel, DishCacheEntity>):
            DataMapper<DishDataModel, DishCacheEntity>
}