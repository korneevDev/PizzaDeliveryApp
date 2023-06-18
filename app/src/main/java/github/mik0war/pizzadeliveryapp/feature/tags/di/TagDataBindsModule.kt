package github.mik0war.pizzadeliveryapp.feature.tags.di

import dagger.Binds
import dagger.Module
import github.mik0war.entity.ExceptionHandler
import github.mik0war.pizzadeliveryapp.core.DataMapper
import github.mik0war.pizzadeliveryapp.feature.tags.data.cache.TagCacheEntity
import github.mik0war.pizzadeliveryapp.feature.tags.data.cache.TagCacheGetDataListDataSource
import github.mik0war.pizzadeliveryapp.feature.tags.data.cloud.TagCloudGetDataListDataSource
import github.mik0war.pizzadeliveryapp.feature.tags.data.cloud.TagServerModel
import github.mik0war.pizzadeliveryapp.feature.tags.dataModel.Tag
import github.mik0war.pizzadeliveryapp.feature.tags.dataModel.TagDataModel
import github.mik0war.pizzadeliveryapp.feature.tags.domain.TagExceptionHandler
import github.mik0war.pizzadeliveryapp.feature.tags.domain.TagInteractor
import github.mik0war.pizzadeliveryapp.feature.tags.presentation.TagsLiveData
import github.mik0war.pizzadeliveryapp.feature.tags.presentation.TagsViewModel
import github.mik0war.pizzadeliveryapp.recycler_list.data.CacheGetDataListDataSource
import github.mik0war.pizzadeliveryapp.recycler_list.data.GetDataListDataSource
import github.mik0war.pizzadeliveryapp.recycler_list.data.GetDataListRepositoryImpl
import github.mik0war.pizzadeliveryapp.recycler_list.domain.GetDataListRepository

@Module
abstract class TagDataBindsModule {

    @Binds
    abstract fun bindTagsVM(vm: TagsViewModel.Base): TagsViewModel

    @Binds
    abstract fun bindTagsLiveData(liveData: TagsLiveData.Base): TagsLiveData

    @Binds
    abstract fun bindTagsInteractor(interactor: TagInteractor.Base):
            TagInteractor

    @Binds
    abstract fun bindTagsRepository(repo: GetDataListRepositoryImpl<TagDataModel>):
            GetDataListRepository<TagDataModel>

    @Binds
    abstract fun bindTagsMapperTagDataModelTag(mapper: DataMapper.Base<TagDataModel, Tag>):
            DataMapper<TagDataModel, Tag>

    @Binds
    abstract fun bindExceptionHandler(handler: TagExceptionHandler):
            ExceptionHandler<Tag>

    @Binds
    abstract fun bindTagCacheDataSource(source: TagCacheGetDataListDataSource): CacheGetDataListDataSource<TagDataModel>

    @Binds
    abstract fun bindTagCloudDataSource(source: TagCloudGetDataListDataSource): GetDataListDataSource<TagDataModel>

    @Binds
    abstract fun bindMapperTagServerModelTagDataModel(mapper: DataMapper.Base<TagServerModel, TagDataModel>):
            DataMapper<TagServerModel, TagDataModel>

    @Binds
    abstract fun bindMapperTagCacheEntityTagDataModel(mapper: DataMapper.Base<TagCacheEntity, TagDataModel>):
            DataMapper<TagCacheEntity, TagDataModel>

    @Binds
    abstract fun bindMapperTagDataModelTagCacheEntity(mapper: DataMapper.Base<TagDataModel, TagCacheEntity>):
            DataMapper<TagDataModel, TagCacheEntity>

}