package com.dicoding.tutorinedutech.ui.tutor.profile

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dicoding.tutorinedutech.R
import com.dicoding.tutorinedutech.databinding.FragmentProfileTutorBinding
import com.dicoding.tutorinedutech.helper.ResultState
import com.dicoding.tutorinedutech.helper.ViewModelFactory
import com.dicoding.tutorinedutech.ui.auth.register.tutor.UploadKtpDirections
import com.dicoding.tutorinedutech.utils.Event
import com.dicoding.tutorinedutech.utils.reduceFileImage
import com.dicoding.tutorinedutech.utils.uriToFile
import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody


class ProfileTutor : Fragment() {
    private var _binding: FragmentProfileTutorBinding? = null
    private val binding get() = _binding!!
    private lateinit var profileTutorVM: ProfileTutorVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileTutorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory: ViewModelFactory = ViewModelFactory.getInstance(requireActivity())
        profileTutorVM = ViewModelProvider(this, factory)[ProfileTutorVM::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileTutorVM.fetchProfileData()

        binding.apply {

            profileTutorVM.getUserData().observe(viewLifecycleOwner) { tutor ->
                Glide.with(requireContext())
                    .load(tutor?.profilePicture)
                    .into(ivUserTutorProfile)
                tvUserNameProfile.text = tutor?.name
                tvEmailProfile.text = tutor?.email
                etUsername.setText(tutor?.username)
                etName.setText(tutor?.name)
                etEmail.setText(tutor?.email)
                etGender.setText(tutor?.gender)
                etDomisili.setText(tutor?.domicile)
                etBahasa.setText(tutor?.languages)
                etMetodeBelajar.setText(tutor?.learningMethod)
                etEducationLevel.setText(tutor?.educationLevel)
                etTeachingApproach.setText(tutor?.teachingApproach)
                etNoTelp.setText(tutor?.phoneNumber)
                etRekening.setText(tutor?.accountNumber)
                tvCv.text = if (tutor?.cv.isNullOrBlank()) "Belum Diupload" else "Terupload"
            }

            tbProfileTutor.apply {
                navigationIcon =
                    AppCompatResources.getDrawable(requireContext(), R.drawable.backward)
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
            }

            tvPassword.setOnClickListener {
                findNavController().navigate(ProfileTutorDirections.actionProfileTutorToUpdatePassword())
            }

            btnSimpan.setOnClickListener {
                updateUserProfile()
            }

            btnChangeCv.setOnClickListener {
                launcherIntentDocument.launch(arrayOf("application/pdf"))
            }

            btnChangeProfileImage.setOnClickListener {
                launcherIntentImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }

            btnSimpanFile.setOnClickListener {
                updateUserImageProfile()
                updateUserCv()

            }

        }
    }

    private val launcherIntentDocument =
        registerForActivityResult(ActivityResultContracts.OpenDocument()) { uri: Uri? ->
            if (uri != null) {
                profileTutorVM.setCvUri(uri)
                Log.d("Uri", uri.toString())
            } else {
                setSnackBar(Event("Tidak ada File yang dipilih"))
            }
        }

    private val launcherIntentImage =
        registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
            if (uri != null) {
                profileTutorVM.setProfileUri(uri)
            } else {
                setSnackBar(Event("Tidak ada File yang dipilih"))
            }
        }

    private fun updateUserCv() {
        val pdfUri = profileTutorVM.cvUri.value
        pdfUri?.let {
            val pdfFile = uriToFile(it, requireContext(), ".pdf")
            val requestPdfFile = pdfFile.asRequestBody("application/pdf".toMediaType())
            val pdf = MultipartBody.Part.createFormData(
                "cv", pdfFile.name, requestPdfFile
            )
            binding.tvCv.text = pdfFile.name
            profileTutorVM.updateFile(pdf, null, "cv").observe(viewLifecycleOwner) {result->
                if (result != null) {
                    when (result) {
                        is ResultState.Loading -> {
                            binding.pbSimpanFile.visibility = View.VISIBLE
                            binding.btnSimpanFile.text = null
                        }

                        is ResultState.Error -> {
                            binding.pbSimpanFile.visibility = View.GONE
                            binding.btnSimpanFile.text = resources.getText(R.string.btn_simpan)
                            setSnackBar(Event(result.error))
                        }

                        is ResultState.Success -> {
                            binding.pbSimpanFile.visibility = View.GONE
                            binding.btnSimpanFile.text = resources.getText(R.string.btn_simpan)
                            setSnackBar(Event("CV Tutor berhasil dirubah"))
                        }
                    }
                }
            }
        }
    }


    private fun updateUserImageProfile() {
        val profileUri = profileTutorVM.profileUri.value
        profileUri?.let {
            val imageFile = uriToFile(it, requireContext(), ".jpg").reduceFileImage()
            val requestImageFile = imageFile.asRequestBody("image/jpeg".toMediaType())
            val profile = MultipartBody.Part.createFormData(
                "profilePicture", imageFile.name, requestImageFile
            )
            profileTutorVM.updateFile(null, profile, "profile").observe(viewLifecycleOwner) {result ->
                if (result != null) {
                    when (result) {
                        is ResultState.Loading -> {
                            binding.pbSimpanFile.visibility = View.VISIBLE
                            binding.btnSimpanFile.text = null
                        }

                        is ResultState.Error -> {
                            binding.pbSimpanFile.visibility = View.GONE
                            binding.btnSimpanFile.text = resources.getText(R.string.btn_simpan)
                            setSnackBar(Event(result.error))
                        }

                        is ResultState.Success -> {
                            binding.pbSimpanFile.visibility = View.GONE
                            binding.btnSimpanFile.text = resources.getText(R.string.btn_simpan)
                            setSnackBar(Event("Profile Tutor berhasil dirubah"))
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        profileTutorVM.setProfileUri(null)
        profileTutorVM.setCvUri(null)
    }

    private fun updateUserProfile() {
        binding.apply {
            profileTutorVM.updateProfileData(
                etEmail.text.toString(),
                etUsername.text.toString(),
                etName.text.toString(),
                etNoTelp.text.toString(),
                etEducationLevel.text.toString(),
                etGender.text.toString(),
                etDomisili.text.toString(),
                etRekening.text.toString(),
                etBahasa.text.toString(),
                etTeachingApproach.text.toString(),
                etMetodeBelajar.text.toString()
            ).observe(viewLifecycleOwner) { result ->
                if (result != null) {
                    when (result) {
                        is ResultState.Loading -> {
                            btnSimpan.text = null
                            pbLogin.visibility = View.VISIBLE
                        }

                        is ResultState.Error -> {
                            pbLogin.visibility = View.GONE
                            btnSimpan.text = resources.getString(R.string.btn_simpan)
                            setSnackBar(Event(result.error))
                        }

                        is ResultState.Success -> {
                            pbLogin.visibility = View.GONE
                            btnSimpan.text = resources.getString(R.string.btn_simpan)
                            setSnackBar(Event(result.data.message))
                        }
                    }
                }
            }
        }
    }

    private fun setSnackBar(e: Event<String>) {
        e.getContentIfNotHandled()?.let {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        }
    }
}