package github.mik0war.pizzadeliveryapp.feature.tags.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.mik0war.pizzadeliveryapp.feature.tags.data.retrofit.TagService
import github.mik0war.pizzadeliveryapp.feature.tags.data.room.TagDatabase
import github.mik0war.pizzadeliveryapp.feature.tags.domain.TagDataModel
import github.mik0war.pizzadeliveryapp.feature.tags.domain.TagMapper
import github.mik0war.pizzadeliveryapp.feature.tags.presentation.TagUIModel
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object TagProvidesModule {

    @Provides
    fun provideTagService(retrofit: Retrofit): TagService = retrofit.create(TagService::class.java)

    @Provides
    fun provideTagDB(@ApplicationContext context: Context) : TagDatabase =
        Room.databaseBuilder(context, TagDatabase::class.java, "tags").build()

    @Provides
    fun provideTagDAO(db: TagDatabase) = db.tagDAO()

    @Provides
    fun provideMapperToTagDataModel(): TagMapper<TagDataModel> =
        object : TagMapper<TagDataModel>{
            override fun mapSuccess(id: Int, name: String, isMain: Boolean) = TagDataModel(id, name)
        }

    @Provides
    fun provideMapperToTagUIModel(): TagMapper<TagUIModel> =
        object : TagMapper<TagUIModel>{
            override fun mapSuccess(id: Int, name: String, isMain: Boolean) = TagUIModel.Common(id, name, isMain)
        }
}