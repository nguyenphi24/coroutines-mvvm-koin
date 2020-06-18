package vn.phitn.app.coroutines

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    // Instantiate viewModel with Koin
    private val viewModel: MainViewModel by viewModel()
    private lateinit var catAdapter: CatAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        catAdapter = CatAdapter()
        catsRecyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = catAdapter
        }
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.catsList.observe(this, Observer { newCatList ->
            catAdapter.updateData(newCatList)
        })
        viewModel.showLoading.observe(this, Observer { status ->
            mainProgressBar.visibility = if (status) View.VISIBLE else View.GONE
        })
        viewModel.showError.observe(this, Observer { showError ->
            Toast.makeText(this@MainActivity, showError, Toast.LENGTH_SHORT).show()
        })
        viewModel.loadCats()
    }
}