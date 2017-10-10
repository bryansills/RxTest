package ninja.bryansills.rxtest

import io.reactivex.subjects.BehaviorSubject

class ViewModel(val dataRepo: DataRepo) {
    fun increment() {
        dataRepo.increment()
    }

    fun uiModel(): BehaviorSubject<Int> {
        return dataRepo.countObservable()
    }
}
