package classInput.inputIndexOfList

import classInput.InputActionNumber
import classValidate.ValidateActionNumber
import dagger.Binds
import dagger.Module
import interfaceInput.IInput
import interfaceValidate.IValidate

@Module
interface InputOfferToEnterActionNumberModule {
    @Binds
    fun bindValidate(impl: ValidateActionNumber) : IValidate<Int?>
    @Binds
    fun bindInput(impl: InputActionNumber) : IInput<Int>
}