package com.bangkit.capstonebangkit.ui.activity

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bangkit.capstonebangkit.R
import com.bangkit.capstonebangkit.databinding.ActivityPredictBinding
import com.bangkit.capstonebangkit.databinding.ActivityResultPredictBinding
import com.bangkit.capstonebangkit.models.ColorPicker
import com.bangkit.capstonebangkit.models.PredictResponse
import com.bangkit.capstonebangkit.ui.adpater.ColorPickerAdapter
import java.util.Locale

class ResultPredictActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultPredictBinding

    private var dataColor = arrayListOf<ColorPicker>()

    private lateinit var colorAdapter: ColorPickerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultPredictBinding.inflate(layoutInflater)
        setContentView(binding.root)

        colorAdapter = ColorPickerAdapter()

        dataColor.add(ColorPicker(1, "#B68A65"))
        dataColor.add(ColorPicker(2, "#FFBB86FC"))
        dataColor.add(ColorPicker(3, "#C8BBCD"))
        dataColor.add(ColorPicker(4, "#FF03DAC5"))

        colorAdapter.setData(dataColor)

        setupViews()
    }

    private fun setupViews() {
        val detail = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_DETAIL, PredictResponse::class.java)
        } else {
            intent.getParcelableExtra<PredictResponse>(EXTRA_DETAIL)
        }
        binding.apply {
            btnBack.setOnClickListener {
                startActivity(Intent(this@ResultPredictActivity, MainActivity::class.java))
                finish()
            }
            tvPredict.text = detail?.season_from_color

            tvDesc.text =
                if (detail?.tone_from_color?.lowercase(Locale.ROOT) == detail?.tone_from_image?.lowercase(
                        Locale.ROOT
                    )
                ) "The color that you pick is well suited, and it matches your skin tone well" else
                    "The color that you pick is well suited, unfortunately it matches your skin tone badly"

            btnSubmit.setOnClickListener {
                startActivity(Intent(this@ResultPredictActivity, MainActivity::class.java))
                finish()
            }

            with(rvColors) {
                setHasFixedSize(true)
                rvColors.adapter = colorAdapter
            }
        }

    }

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
}