package ru.app.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.app.R
import ru.app.models.Movie
import ru.app.ui.DetailsActivity
import java.text.SimpleDateFormat

class MovieAdapter(private val context: Context) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val movieList: MutableList<Movie> = ArrayList()

    fun setData(newMovieList: List<Movie>) {
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_movie, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie = movieList[position]
        holder.movieTitle.text = movie.originalTitle.trim()
        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + movie.posterPath)

            .into(holder.movieCover)
        holder.movieDate.text = movie.releaseDate.trim()

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("desc", movie.overview)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = movieList.size


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val movieTitle: TextView = itemView.findViewById(R.id.tv_movieTitle)
        val movieCover: ImageView = itemView.findViewById(R.id.image_movieCover)
        val movieDate: TextView = itemView.findViewById(R.id.tv_movieDate)


    }


}