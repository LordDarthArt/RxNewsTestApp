package tk.lorddarthart.rxnewstestapp.app.view.base.interfaces

interface IBaseActivity {
    fun initialization()

    fun hangObservers()
    fun initListeners()
    fun start()
}