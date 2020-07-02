package mx.ernestovaldez.androidtest.adapters

import mx.ernestovaldez.androidtest.models.Records
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.ernestovaldez.androidtest.R
import mx.ernestovaldez.androidtest.utils.fromUrl

class RecordListAdapter(context: Context, myDataSet: List<Records>) :
    RecyclerView.Adapter<RecordListAdapter.MyViewHolder>() {
    private val mDataSet: List<Records> = myDataSet
    private val context: Context = context

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvTitle: TextView = view.findViewById(R.id.tvTitle)
        var tvPrice: TextView = view.findViewById(R.id.tvPrice)
        var imgView: ImageView = view.findViewById(R.id.imgView)
        var ratingBar: RatingBar = view.findViewById(R.id.ratingBar)

        fun bind(image: String) {
            imgView.fromUrl(image)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder { // create a new view
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_component, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        var text = mDataSet[position].productDisplayName
        holder.tvTitle.text = text
        text = "$ ${mDataSet[position].listPrice}"
        holder.tvPrice.text = text

        holder.ratingBar.rating = mDataSet[position].productAvgRating.toFloat()

        val imageURL = mDataSet[position].smImage
        holder.bind(imageURL)
    }

    override fun getItemCount(): Int {
        return mDataSet.size
    }

}