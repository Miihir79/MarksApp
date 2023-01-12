package com.mihir.marksapp.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.mihir.marksapp.ui.screens.Quiz
import com.mihir.marksapp.databinding.ItemQuestionBinding
import com.mihir.marksapp.model.DataItem
import com.mihir.marksapp.ui.screens.QUESTION_ITEM
import com.mihir.marksapp.ui.screens.QUESTION_NUMBER
import com.mihir.marksapp.ui.screens.QUESTION_YEAR

class QuestionAdapter(private val items:List<DataItem>): RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {

    inner class ViewHolder(binding: ItemQuestionBinding):RecyclerView.ViewHolder(binding.root){
        val questionCard : CardView = binding.crdVQuestion
        val title: TextView = binding.heading
        val description:TextView  = binding.description
        val number: TextView = binding.number
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemQuestionBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){

            title.text  =  items[position].question.text

            description.text = items[position].exams[0] +" "+ items[position].previousYearPapers[0]

            if (position + 1 < 10 ){
                number.text = "0" + position.plus(1).toString()
            }else{
                number.text = position.plus(1).toString()
            }

            questionCard.setOnClickListener {
                val intent = Intent(questionCard.context, Quiz::class.java)
                intent.putExtra(QUESTION_ITEM,items[position])
                intent.putExtra(QUESTION_NUMBER,position+1)
                intent.putExtra(QUESTION_YEAR,description.text)
                questionCard.context.startActivity(intent)
            }
        }

    }

    override fun getItemCount() = items.size
}