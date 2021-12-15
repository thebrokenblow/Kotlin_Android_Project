package classInput.inputCost

import classInput.LoopInput
import dagger.Component

@Component(modules = [ICostModule::class])
interface IInputComponentCost {
    fun loopInput() : LoopInput<Double?>
}