package com.mihir.marksapp.ui.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.core.content.ContextCompat
import com.mihir.marksapp.R
import com.mihir.marksapp.databinding.ActivityQuizBinding
import com.mihir.marksapp.model.DataItem
import com.mihir.marksapp.model.Option

const val QUESTION_ITEM = "QUESTION_ITEM"
const val QUESTION_NUMBER = "QUESTION_NUMBER"
const val QUESTION_YEAR = "QUESTION_YEAR"

class Quiz : AppCompatActivity() {

    private var correctId : Int? = null
    private val binding : ActivityQuizBinding by lazy { ActivityQuizBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.purple_500)
        supportActionBar?.hide()
        setContentView(binding.root)

        val question= intent.getParcelableExtra<DataItem>(QUESTION_ITEM)
        val number = intent.getIntExtra(QUESTION_NUMBER,0)
        val prevYear = intent.getStringExtra(QUESTION_YEAR)

        binding.prevYear.text = prevYear

        binding.questionNumber.text = "Q" + number.toString() + " (" + question?.type + ")"

        question?.question?.text?.let { binding.question.setDisplayText(it) }

        question?.solution?.text?.let { binding.solution.setDisplayText(it) }

        populateRadioGrp(question?.options)

        binding.radioGrp.setOnCheckedChangeListener { _, _ ->
            binding.checkAns.isEnabled = true
            binding.checkAns.setTextColor(ContextCompat.getColor(this, R.color.white))
        }

        binding.checkAns.setOnClickListener {
            val correct = correctId?.let { it1 -> binding.radioGrp.findViewById<RadioButton>(it1) }
            correct?.setBackgroundResource(R.drawable.radio_button_correct_bg)
            if(binding.radioGrp.checkedRadioButtonId != correctId){
                val incorrect = binding.radioGrp.findViewById<RadioButton>(binding.radioGrp.checkedRadioButtonId)
                incorrect.setBackgroundResource(R.drawable.radio_button_incorrect_bg)
            }else{
                binding.correctBanner.visibility = View.VISIBLE
                // removing the banner after 3 sec
                binding.correctBanner.postDelayed({
                    binding.correctBanner.visibility = View.GONE
                },3000)
            }

            binding.solution.visibility = View.VISIBLE
            binding.solutionTitle.visibility = View.VISIBLE
            binding.checkAns.text = getString(R.string.next)
        }

        binding.back.setOnClickListener {
            finish()
        }

    }

    private fun populateRadioGrp(options: List<Option>?) {
        options?.forEach {
            //val webViewBtn: MathView = LayoutInflater.from(this).inflate(R.layout.radio_btn_math_view,binding.root,false) as MathView
            //webViewBtn.setDisplayText(it.text)

            val radioButton: AppCompatRadioButton = LayoutInflater.from(this)
                .inflate(R.layout.radio_button_view, binding.root, false) as AppCompatRadioButton
            radioButton.text = it.text

            binding.radioGrp.addView(radioButton)

            if (it.isCorrect){  // storing the correct option's id
                correctId = radioButton.id
            }
        }
    }
}