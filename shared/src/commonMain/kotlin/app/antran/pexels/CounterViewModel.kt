package app.antran.pexels

import dev.icerock.moko.mvvm.livedata.LiveData
import dev.icerock.moko.mvvm.livedata.MutableLiveData
import dev.icerock.moko.mvvm.viewmodel.ViewModel

class CounterViewModel: ViewModel() {
    private var _count = MutableLiveData<Int>(0)
    var count: LiveData<Int> = _count

    var counter = Counter()

    fun increase() {
        _count.value = counter.increase()
    }

    fun decrease() {
        _count.value = counter.decrease()
    }
}
