package com.bangkit.capstonebangkit.ui.activity

import android.content.Intent
import android.content.res.ColorStateList
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.bangkit.capstonebangkit.R
import com.bangkit.capstonebangkit.databinding.ActivityWelcomeBinding
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.CornerSize
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        val leftShapePathModel = ShapeAppearanceModel().toBuilder()
        //change style and size
        leftShapePathModel.setTopLeftCorner(
            CornerFamily.ROUNDED,
            CornerSize { return@CornerSize 40F })

        leftShapePathModel.setTopRightCorner(
            CornerFamily.ROUNDED,
            CornerSize { return@CornerSize 40F })

        val bg = MaterialShapeDrawable(leftShapePathModel.build())
        bg.fillColor = ColorStateList.valueOf(
            ContextCompat.getColor(applicationContext, R.color.white)
        )
        bg.elevation = 8F
        binding.cvBottom.background = bg
        binding.cvBottom.invalidate()

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity,AuthActivity::class.java).apply {
                putExtra("auth","login")
            })
            finish()
        }

        binding.btnRegis.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity,AuthActivity::class.java).apply {
                putExtra("auth","regis")
            })
            finish()
        }
    }
}