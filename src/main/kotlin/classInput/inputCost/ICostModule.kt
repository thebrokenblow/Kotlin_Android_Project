package classInput.inputCost

import classValidate.ValidateCost
import dagger.Binds
import dagger.Module
import interfaceInput.IInput
import interfaceValidate.IValidate

@Module
interface ICostModule {
    @Binds
    fun bindValidate(impl: ValidateCost) : IValidate<Double?>
    @Binds
    fun bindInput(impl: InputCost) : IInput<Double>
}