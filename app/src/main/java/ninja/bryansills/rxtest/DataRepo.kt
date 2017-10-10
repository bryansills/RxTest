package ninja.bryansills.rxtest

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class DataRepo(var count: Int) {

    val behaviorSubject = BehaviorSubject.create<Int>()

    fun increment() {
        behaviorSubject.onNext(++count)
    }

    fun countObservable(): BehaviorSubject<Int> {
        return behaviorSubject
    }
}
