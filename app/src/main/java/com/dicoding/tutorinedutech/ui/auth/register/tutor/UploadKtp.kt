package com.dicoding.tutorinedutech.ui.auth.register.tutor

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.dicoding.tutorinedutech.R
import com.dicoding.tutorinedutech.databinding.FragmentUploadKtpBinding
import com.dicoding.tutorinedutech.helper.ResultState
import com.dicoding.tutorinedutech.helper.ViewModelFactory
import com.dicoding.tutorinedutech.ui.main.CameraActivity
import com.dicoding.tutorinedutech.utils.Event
import com.dicoding.tutorinedutech.utils.reduceFileImage
import com.dicoding.tutorinedutech.utils.uriToFile
import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody

class UploadKtp : Fragment() {
    private var _binding: FragmentUploadKtpBinding? = null
    private val binding get() = _binding!!
    private lateinit var uploadKtpVM: UploadKtpVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUploadKtpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        uploadKtpVM = ViewModelProvider(this, factory)[UploadKtpVM::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }
        binding.apply {
            tbRegisterLearner.apply {
                navigationIcon =
                    AppCompatResources.getDrawable(requireContext(), R.drawable.backward)
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
            }

            uploadKtpVM.imageUri.observe(viewLifecycleOwner) {
                if (!it.path.isNullOrBlank()) {
                    imagePreview.setImageURI(it)
                }
            }

            btnSelectImage.setOnClickListener {
                startGallery()
            }

            btnCaptureImage.setOnClickListener {
                startCameraX()
            }

            btnFinish.setOnClickListener {
                finishRegisterTutor()
            }
        }
    }

    private fun finishRegisterTutor() {
        val imageUri = uploadKtpVM.imageUri.value
        imageUri?.let {
            val imageFile = uriToFile(it, requireContext()).reduceFileImage()

            uploadKtpVM.getRegisterTutorData().observe(viewLifecycleOwner) { tutor ->
                val accountNumber = tutor!!.accountNumber!!.toRequestBody(TEXT_PLAIN.toMediaType())
                val domicile = tutor.domicile!!.toRequestBody(TEXT_PLAIN.toMediaType())
                val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
                val educationLevel = tutor.educationLevel!!.toRequestBody(TEXT_PLAIN.toMediaType())
                val email = tutor.email!!.toRequestBody(TEXT_PLAIN.toMediaType())
                val gender = tutor.gender!!.toRequestBody(TEXT_PLAIN.toMediaType())
                val ktp = MultipartBody.Part.createFormData(
                    "ktp", imageFile.name, requestImageFile
                )
                val languages = tutor.languages!!.toRequestBody(TEXT_PLAIN.toMediaType())
                val learningMethod = tutor.learningMethod!!.toRequestBody(TEXT_PLAIN.toMediaType())
                val name = tutor.name!!.toRequestBody(TEXT_PLAIN.toMediaType())
                val password = tutor.password!!.toRequestBody(TEXT_PLAIN.toMediaType())
                val phoneNumber = tutor.phoneNumber!!.toRequestBody(TEXT_PLAIN.toMediaType())
                val teachingApproach =
                    tutor.teachingApproach!!.toRequestBody(TEXT_PLAIN.toMediaType())
                val username = tutor.username!!.toRequestBody(TEXT_PLAIN.toMediaType())

                uploadKtpVM.uploadRegisterDataTutor(
                    accountNumber,
                    domicile,
                    educationLevel,
                    email,
                    gender,
                    ktp,
                    languages,
                    learningMethod,
                    name,
                    password,
                    phoneNumber,
                    teachingApproach,
                    username
                ).observe(viewLifecycleOwner) { result ->
                    if (result != null) {
                        when (result) {
                            is ResultState.Loading -> {
                                binding.pbUpload.visibility = View.VISIBLE
                                binding.btnFinish.text = null
                            }

                            is ResultState.Error -> {
                                binding.pbUpload.visibility = View.GONE
                                binding.btnFinish.text = resources.getText(R.string.btn_finish)
                                setSnackBar(Event(result.error))
                            }

                            is ResultState.Success -> {
                                binding.pbUpload.visibility = View.GONE
                                binding.btnFinish.text = resources.getText(R.string.btn_finish)
                                setSnackBar(Event("Akun Tutor berhasil dibuat"))
                                findNavController().navigate(UploadKtpDirections.actionUploadKtpToLogin2())
                            }
                        }
                    }
                }

            }

        } ?: setSnackBar(Event("Masukkan gambar terlebiih dahulu"))
    }

    private val launcherIntentGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            uploadKtpVM.setImageUri(uri)
        } else {
            setSnackBar(Event("No media selected"))
        }
    }

    private val launcherIntentCameraX = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == CameraActivity.CAMERAX_RESULT) {
            it.data?.getStringExtra(CameraActivity.EXTRA_CAMERAX_IMAGE)?.toUri()
                ?.let { uri -> uploadKtpVM.setImageUri(uri) }
        }
    }

    private fun setSnackBar(e: Event<String>) {
        e.getContentIfNotHandled()?.let {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun allPermissionsGranted() = ContextCompat.checkSelfPermission(
        requireContext(), REQUIRED_PERMISSION
    ) == PackageManager.PERMISSION_GRANTED

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            setSnackBar(Event("Permission request granted"))
        } else {
            setSnackBar(Event("Permission request denied"))
        }
    }

    private fun startGallery() {
        launcherIntentGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private fun startCameraX() {
        val intent = Intent(requireContext(), CameraActivity::class.java)
        launcherIntentCameraX.launch(intent)
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
        private const val TEXT_PLAIN = "text/plain"
    }
}