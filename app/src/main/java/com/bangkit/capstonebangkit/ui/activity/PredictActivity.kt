package com.bangkit.capstonebangkit.ui.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.net.Uri
import com.bangkit.capstonebangkit.models.Result
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bangkit.capstonebangkit.R
import com.bangkit.capstonebangkit.databinding.ActivityPredictBinding
import com.bangkit.capstonebangkit.models.ColorPicker
import com.bangkit.capstonebangkit.ui.adpater.ColorPickerAdapter
import com.bangkit.capstonebangkit.ui.viewModels.PredictViewModel
import com.bangkit.capstonebangkit.utils.ViewModelFactory
import com.bangkit.capstonebangkit.utils.createCustomTempFile
import com.bangkit.capstonebangkit.utils.reduceFileImage
import com.bangkit.capstonebangkit.utils.uriToFile
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class PredictActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPredictBinding

    private lateinit var currentPhotoPath: String
    private var getFile: File? = null

    private var dataColor = arrayListOf<ColorPicker>()

    private lateinit var colorAdapter: ColorPickerAdapter

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this, getString(R.string.didnt_get_permission), Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPredictBinding.inflate(layoutInflater)
        setContentView(binding.root)

        colorAdapter = ColorPickerAdapter()

        dataColor.add(ColorPicker(1, "#B68A65"))
        dataColor.add(ColorPicker(2, "#FFBB86FC"))
        dataColor.add(ColorPicker(3, "#C8BBCD"))
        dataColor.add(ColorPicker(4, "#FF03DAC5"))

        colorAdapter.setData(dataColor)

        binding.run {
            ivUpload.setOnClickListener { startGallery() }
            btnSubmit.setOnClickListener { uploadImage() }
            btnBack.setOnClickListener {
                finish()
            }

            with(rvColors) {
                setHasFixedSize(true)
                rvColors.adapter = colorAdapter
            }
        }

    }

    private fun uploadImage() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val predictViewModel: PredictViewModel by viewModels {
            factory
        }
        if (getFile != null) {
            val file = reduceFileImage(getFile as File)

            val rgb1 = "#B68A65".toRequestBody("text/plain".toMediaType())
            val rgb2 = "#FFBB86FC".toRequestBody("text/plain".toMediaType())
            val rgb3 = "#C8BBCD".toRequestBody("text/plain".toMediaType())
            val rgb4 = "#FF03DAC5".toRequestBody("text/plain".toMediaType())

            val requestImageFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "gambar",
                file.name,
                requestImageFile
            )
            predictViewModel.postPredict(imageMultipart, rgb1, rgb2, rgb3, rgb4)
                .observe(this@PredictActivity) { result ->
                    when (result) {
                        is Result.Success -> {
                            Toast.makeText(
                                this@PredictActivity,
                                result.data.season_from_color,
                                Toast.LENGTH_SHORT
                            ).show()
                            showLoading(false)
                            startActivity(Intent(this, ResultPredictActivity::class.java).apply {
                                putExtra(ResultPredictActivity.EXTRA_DETAIL, result.data)
                            })
                            Log.d("winsu", "${result.data}")
                            finish()

                        }

                        is Result.Loading -> showLoading(true)
                        is Result.Error -> {
                            Toast.makeText(this@PredictActivity, result.error, Toast.LENGTH_SHORT)
                                .show()
                            showLoading(false)
                        }
                    }
                }
        } else {
            Toast.makeText(
                this@PredictActivity,
                getString(R.string.input_image_first),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun startGallery() {
        val intent = Intent()
        intent.action = Intent.ACTION_GET_CONTENT
        intent.type = "image/*"
        val chooser = Intent.createChooser(intent, "Choose a Picture")
        launcherIntentGallery.launch(chooser)
    }

    private fun startTakePhoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.resolveActivity(packageManager)

        createCustomTempFile(application).also {
            val photoURI: Uri = FileProvider.getUriForFile(
                this@PredictActivity, "com.wisnu.capstonebangkit", it
            )
            currentPhotoPath = it.absolutePath
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
            launcherIntentCamera.launch(intent)
        }
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val selectedImg: Uri = result.data?.data as Uri

            val myFile = uriToFile(selectedImg, this@PredictActivity)

            getFile = myFile

            binding.ivUpload.setImageURI(selectedImg)
        }
    }

    private val launcherIntentCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val myFile = File(currentPhotoPath)
            getFile = myFile

            val result = BitmapFactory.decodeFile(getFile?.path)
            binding.ivUpload.setImageBitmap(result)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }
}