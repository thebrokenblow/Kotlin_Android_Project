package model

import interfaceModel.*
import javax.inject.Inject

open class OnlineStoreDataBase @Inject constructor(
    override var name: String?,
    override var surname: String?,
    override var patronymic: String?,
    override var cost: Double?,
    override var discount: Int?,
    override var address: String?,
) : IHasName,
    IHasSurname,
    IHasPatronymic,
    IHasCost,
    IHasDiscount,
    IHasAddressOfOrder