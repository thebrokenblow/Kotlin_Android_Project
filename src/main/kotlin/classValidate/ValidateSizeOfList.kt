package classValidate

import interfaceValidate.IValidate
import javax.inject.Inject

open class ValidateSizeOfList @Inject constructor() : IValidate<Int?> {
    override fun validate(value: Int?): Boolean = value != null && value > 0
}
