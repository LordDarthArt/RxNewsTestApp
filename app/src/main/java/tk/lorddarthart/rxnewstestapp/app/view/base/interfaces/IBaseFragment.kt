package tk.lorddarthart.rxnewstestapp.app.view.base.interfaces

interface IBaseFragment {

    fun initialization()

    fun hangObservers()
    fun initListeners()
    fun start()
}