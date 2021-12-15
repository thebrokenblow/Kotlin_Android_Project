package classInput.inputDiscount

import classInput.LoopInput
import dagger.Component

@Component(modules = [IDiscountModule::class])
interface IInputComponentDiscount {
    fun loopInput() : LoopInput<Int?>
}