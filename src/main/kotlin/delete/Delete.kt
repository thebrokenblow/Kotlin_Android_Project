package delete

import model.OnlineStoreDataBase

open class Delete (
    private val indexForDelete: Int,
    private val listOfOnlineStoreDataBase: ArrayList<OnlineStoreDataBase>) {
    open fun delete() {
        listOfOnlineStoreDataBase.removeAt(indexForDelete)
    }
}