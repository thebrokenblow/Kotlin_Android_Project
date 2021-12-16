package classInput.loopInputIndexOfList

import classValidate.ValidateIndexOfList
import dagger.Binds
import dagger.Module
import interfaceInput.IInput

@Module
interface IIndexOfListModule {
    @Binds
    fun bindInput(impl: InputIndexOfList) : IInput<Int?>
}