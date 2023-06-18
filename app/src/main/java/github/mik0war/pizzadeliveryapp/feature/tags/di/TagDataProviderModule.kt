package github.mik0war.pizzadeliveryapp.feature.tags.di

import dagger.Module
import dagger.Provides
import github.mik0war.pizzadeliveryapp.core.mapper.MapperTo
import github.mik0war.pizzadeliveryapp.core.mapper.TagMapperTo
import github.mik0war.pizzadeliveryapp.feature.tags.data.cache.TagCacheEntity
import github.mik0war.pizzadeliveryapp.feature.tags.data.cache.TagDataBase
import github.mik0war.pizzadeliveryapp.feature.tags.data.cloud.TagService
import github.mik0war.pizzadeliveryapp.feature.tags.dataModel.Tag
import github.mik0war.pizzadeliveryapp.feature.tags.dataModel.TagDataModel
import retrofit2.Retrofit

@Module
class TagDataProviderModule {
    @Provides
    fun provideTagService(retrofit: Retrofit): TagService =
        retrofit.create(TagService::class.java)

    @Provides
    fun provideDAO(db: TagDataBase) = db.provideCacheTagDao()

    @Provides
    fun provideMapperToTagDataModel(): MapperTo<TagDataModel> =
        object : TagMapperTo<TagDataModel> {
            override fun mapSuccess(id: Int, name: String) = TagDataModel(id, name)

            override fun mapError(errorName: String) = throw IllegalStateException()

        }

    @Provides
    fun provideMapperToTagModel(): MapperTo<Tag> =
        object : TagMapperTo<Tag>{

            override fun mapSuccess(id: Int, name: String) = Tag.Common(id, name)

            override fun mapError(errorName: String): Tag = Tag.Error(errorName)
        }

    @Provides
    fun provideMapperToTagCacheModel(): MapperTo<TagCacheEntity> =
        object : TagMapperTo<TagCacheEntity>{

            override fun mapSuccess(id: Int, name: String) = TagCacheEntity(id, name)

            override fun mapError(errorName: String) = throw IllegalStateException()
        }
}