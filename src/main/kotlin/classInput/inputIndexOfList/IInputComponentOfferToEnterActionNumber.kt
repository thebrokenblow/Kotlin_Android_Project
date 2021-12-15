package classInput.inputIndexOfList

import classInput.LoopInput
import dagger.Component

@Component(modules = [InputOfferToEnterActionNumberModule::class])
interface InputComponentOfferToEnterActionNumber {
    fun loopInput() : LoopInput<Int?>
}
