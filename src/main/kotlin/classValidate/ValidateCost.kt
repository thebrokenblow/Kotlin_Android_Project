package classValidate

import interfaceValidate.IValidate
import javax.inject.Inject

open class ValidateCost @Inject constructor(): IValidate<Double?> {
    override fun validate(value : Double?): Boolean = value != null && value > 0.0
}