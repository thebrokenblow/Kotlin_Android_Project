package classValidate

import interfaceValidate.IValidate
import valueOfExitingProgram
import valueToSearchOfRecords
import javax.inject.Inject

open class ValidateActionNumber @Inject constructor() : IValidate<Int> {
    override fun validate(value: Int?): Boolean = value != null && value in valueOfExitingProgram..valueToSearchOfRecords
}