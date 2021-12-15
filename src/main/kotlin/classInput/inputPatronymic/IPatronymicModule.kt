package classInput.inputPatronymic

import classValidate.ValidatePatronymic
import dagger.Binds
import dagger.Module
import interfaceInput.IInput
import interfaceValidate.IValidate

@Module
interface IPatronymicModule {
    @Binds
    fun bindValidate(impl: ValidatePatronymic) : IValidate<String?>
    @Binds
    fun bindInput(impl: InputPatronymic) : IInput<String>
}