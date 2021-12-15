package classInput.inputDiscount

import classValidate.ValidateDiscount
import dagger.Binds
import dagger.Module
import interfaceInput.IInput
import interfaceValidate.IValidate

@Module
interface IDiscountModule {
    @Binds
    fun bindValidate(impl: ValidateDiscount) : IValidate<Int?>
    @Binds
    fun bindInput(impl: InputDiscount) : IInput<Int>
}