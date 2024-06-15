package com.dicoding.tutorinedutech.ui.auth.register.tutor

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dicoding.tutorinedutech.databinding.FragmentLoadingTestGradientBinding
import jp.wasabeef.blurry.Blurry

class LoadingTestGradient : Fragment() {

    private var _binding: FragmentLoadingTestGradientBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoadingTestGradientBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            ivGradient1.post {
                run {
                    Blurry.with(requireContext()).radius(25).sampling(15).capture(ivGradient1)
                        .into(ivGradient1)
                }
            }
            ivGradient2.post {
                run {
                    Blurry.with(requireContext()).radius(25).sampling(15).capture(ivGradient2)
                        .into(ivGradient2)
                }
            }
            ivGradient3.post {
                run {
                    Blurry.with(requireContext()).radius(25).sampling(15).capture(ivGradient3)
                        .into(ivGradient3)
                }
            }
        }
        Handler(Looper.getMainLooper()).postDelayed(
            {
                findNavController().navigate(LoadingTestGradientDirections.actionLoadingTestGradientToRegisterTutorResult())
            },
            2000
        )

    }
}