package ninja.bryansills.rxtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers



class MainActivity : AppCompatActivity() {

    var subscription = CompositeDisposable()
    var viewModel = ViewModel(DataRepo(0))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.increment).setOnClickListener { viewModel.increment() }
    }

    public override fun onResume() {
        bindViewModel()
        super.onResume()
    }

    public override fun onPause() {
        unbindViewModel()
        super.onPause()
    }

    private fun bindViewModel() {
        subscription.add(viewModel.uiModel()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    value -> Log.d("BLARG", value.toString())
                }, {
                    error -> Log.d("BLARG", error.message)
                }))
    }

    private fun unbindViewModel() {
        // unsubscribing from all the subscriptions to ensure we don't have any memory leaks
        subscription.clear()
    }
}
