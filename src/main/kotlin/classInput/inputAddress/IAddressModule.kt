package classInput.inputAddress

import classValidate.ValidateAddress
import dagger.Binds
import dagger.Module
import interfaceInput.IInput
import interfaceValidate.IValidate

@Module
interface IAddressModule {
    @Binds
    fun bindValidate(impl: ValidateAddress) : IValidate<String?>
    @Binds
    fun bindInput(impl: InputAddress) : IInput<String>
}