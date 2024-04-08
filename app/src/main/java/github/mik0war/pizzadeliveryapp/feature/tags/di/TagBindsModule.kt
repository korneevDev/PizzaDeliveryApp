package github.mik0war.pizzadeliveryapp.feature.tags.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import github.mik0war.pizzadeliveryapp.core.Communication
import github.mik0war.pizzadeliveryapp.feature.tags.data.TagRepositoryImpl
import github.mik0war.pizzadeliveryapp.feature.tags.domain.TagRepository
import github.mik0war.pizzadeliveryapp.feature.tags.presentation.TagUIModel

@Module
@InstallIn(SingletonComponent::class)
abstract class TagBindsModule {
    @Binds
    abstract fun bindTagRepo(repository: TagRepositoryImpl) : TagRepository

    @Binds
    abstract fun bindCommunicationTag(com: Communication.Base<TagUIModel>): Communication<TagUIModel>
}