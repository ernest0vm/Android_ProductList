package mx.ernestovaldez.androidtest

import android.app.SearchManager
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import mx.ernestovaldez.androidtest.adapters.RecordListAdapter
import mx.ernestovaldez.androidtest.interfaces.ApiService
import mx.ernestovaldez.androidtest.interfaces.ResponseListener
import mx.ernestovaldez.androidtest.managers.ApiManager
import mx.ernestovaldez.androidtest.models.ApiResponse
import mx.ernestovaldez.androidtest.models.Records
import mx.ernestovaldez.androidtest.utils.RecentSuggestions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), ResponseListener<Any> {
    private val completeRecordList: MutableList<Records> = mutableListOf()
    private var currentPage = 1
    private var searchString = ""
    val responseListener = this

    companion object {
        lateinit var recentSuggestions: RecentSuggestions
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recentSuggestions = RecentSuggestions(applicationContext)

        previousBtn.isEnabled = false
        nextBtn.isEnabled = false

        previousBtn.setOnClickListener(View.OnClickListener {
            if (currentPage == 1) {
                return@OnClickListener
            }
            currentPage--
            fetchData(this)
        })

        nextBtn.setOnClickListener(View.OnClickListener {
            if (currentPage == 1000) {
                return@OnClickListener
            }
            currentPage++
            fetchData(this)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.queryHint = getString(R.string.search_hint)
        searchView.findViewById<AutoCompleteTextView>(R.id.search_src_text).threshold = 1

        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(R.id.item_label)
        val cursorAdapter = SimpleCursorAdapter(
            applicationContext,
            R.layout.search_item,
            null,
            from,
            to,
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        )

        var suggestions: MutableList<String> = mutableListOf()
        suggestions = recentSuggestions.recents

        searchView.suggestionsAdapter = cursorAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val cursor =
                    MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))

                hideKeyboard()
                searchString = query.toString().trim()
                makeText(applicationContext, getString(R.string.finding_label) + searchString + "...", Toast.LENGTH_SHORT).show()

                currentPage = 1
                fetchData(responseListener)

                if (!suggestions.contains(searchString)){
                    suggestions.add(searchString)
                    recentSuggestions.recents = suggestions

                    query?.let {
                        suggestions.forEachIndexed { index, suggestion ->
                            //if (suggestion.contains(query, true))
                            cursor.addRow(arrayOf(index, suggestion))
                        }
                    }
                }

                cursorAdapter.changeCursor(cursor)
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                val cursor =
                    MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))
                query?.let {
                    suggestions.forEachIndexed { index, suggestion ->
                        //if (suggestion.contains(query, true))
                        cursor.addRow(arrayOf(index, suggestion))
                    }
                }

                cursorAdapter.changeCursor(cursor)
                return true
            }
        })

        searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {
                hideKeyboard()
                val cursor = searchView.suggestionsAdapter.getItem(position) as Cursor
                val selection =
                    cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
                searchView.setQuery(selection, false)

                searchString = selection.toString().trim()
                makeText(applicationContext, getString(R.string.finding_label) + searchString + "...", Toast.LENGTH_SHORT).show()

                currentPage = 1
                fetchData(responseListener)
                return true
            }

        })

        super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onSuccess(responseObject: Any) {

        Log.d("onSuccess[Response]", "the request was successful")
        //makeText(this, "the request was successful", Toast.LENGTH_LONG).show()

        for (item in (responseObject as List<*>)) {
            val record: Records = item as Records
            completeRecordList.add(record)
        }
        ///create list adapter
        val mAdapter = RecordListAdapter(this, completeRecordList)
        itemList.layoutManager = LinearLayoutManager(this)
        itemList.setHasFixedSize(true)
        itemList.adapter = mAdapter

        previousBtn.isEnabled = true
        nextBtn.isEnabled = true
        tvPage.text = getString(R.string.page_label) + currentPage
    }

    override fun onError(error: String) {
        Log.e("onError[Response]", error)
        makeText(this, error, Toast.LENGTH_SHORT).show()

        previousBtn.isEnabled = false
        nextBtn.isEnabled = false
    }

    private fun fetchData(responseListener: ResponseListener<Any>) {

        completeRecordList.clear()
        val service = ApiManager().getApiService().create(ApiService::class.java)

        val call = service.getDataFromApi(
            true,
            searchString,
            currentPage,
            5
        )

        hideKeyboard()

        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                responseListener.onError("Something was wrong in the request: ${t.message}")
            }

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.code() == 200) {
                    val apiResponse = response.body() as ApiResponse
                    makeText(applicationContext, getString(R.string.finded_label)
                            + apiResponse.plpResults.plpState.totalNumRecs.toString()
                            + getString(R.string.products_label), Toast.LENGTH_SHORT).show()
                    responseListener.onSuccess(apiResponse.plpResults.records)
                } else {
                    responseListener.onError("Something was wrong in the request: ${response.code()}")
                }
            }

        })
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}
