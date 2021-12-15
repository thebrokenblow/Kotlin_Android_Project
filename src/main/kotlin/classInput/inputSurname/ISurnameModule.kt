package classInput.inputSurname

import classValidate.ValidateSurname
import dagger.Binds
import dagger.Module
import interfaceInput.IInput
import interfaceValidate.IValidate

@Module
interface ISurnameModule {
    @Binds
    fun bindValidate(impl: ValidateSurname) : IValidate<String?>
    @Binds
    fun bindInput(impl: InputSurname) : IInput<String>
}