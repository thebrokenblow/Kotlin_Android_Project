package classInput.inputName

import classValidate.ValidateName
import dagger.Binds
import dagger.Module
import interfaceInput.IInput
import interfaceValidate.IValidate

@Module
interface INameModule {
    @Binds
    fun bindValidate(impl: ValidateName) : IValidate<String?>
    @Binds
    fun bindInput(impl: InputName) : IInput<String>
}