package com.lesnyg.mykotlinexam

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.lesnyg.mykotlinexam.bmi.BmiMainActivity
import com.lesnyg.mykotlinexam.databinding.ItemSubjectBinding
import com.lesnyg.mykotlinexam.mygallery.MyGalleryMainActivity
import com.lesnyg.mykotlinexam.stopwatch.StopWatchMainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = SubjectAdapter { subject ->
            val intent = Intent(this, subject.clazz)
            startActivity(intent)
        }
        recycler_view.adapter = adapter
        recycler_view.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                this,
                androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
            )
        )

        val subjects = arrayListOf<Subject>()
        subjects.add(Subject("BMI", BmiMainActivity::class.java))
        subjects.add(Subject("스탑워치", StopWatchMainActivity::class.java))
        subjects.add(Subject("전자액자(provider)", MyGalleryMainActivity::class.java))


        adapter.items = subjects
        adapter.notifyDataSetChanged()
    }

}

data class Subject(val title: String, val clazz: Class<out Activity>)

class SubjectAdapter(private val clickListener: (person: Subject) -> Unit) :
    androidx.recyclerview.widget.RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder>() {
    var items = arrayListOf<Subject>()

    class SubjectViewHolder(val binding: ItemSubjectBinding) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_subject, parent, false)
        val viewHolder = SubjectViewHolder(com.lesnyg.mykotlinexam.databinding.ItemSubjectBinding.bind(view))
        view.setOnClickListener {
            clickListener.invoke(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.binding.subject = items[position]
    }
}