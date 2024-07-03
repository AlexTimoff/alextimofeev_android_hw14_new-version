package com.example.alextimofeev_android_hw14

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.bumptech.glide.Glide
import com.example.alextimofeev_android_hw14.databinding.FragmentPersonProfileCardBinding
import kotlinx.coroutines.launch

class PersonProfileCardFragment : Fragment() {

    private val viewModel: PersonProfileCardViewModel by viewModels()
    private var _binding: FragmentPersonProfileCardBinding? = null
    private val binding get() = _binding!!

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentPersonProfileCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Первый старт
        viewModel.startProfileCard()

        //Изменения экрана при обновлении
        binding.buttonRefresh.setOnClickListener {
            viewModel.getDataFromRandomUser()
        }

            observeViewState()
            observePersonProfile()

    }

    private fun observeViewState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect{state ->
                    setSearchStatus(state)
                }
            }
        }
    }

    private fun observePersonProfile() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.profile.collect{profile ->
                    if (profile!=null){
                        showProfileCard(profile.results.first())
                    }
                }
            }
        }
    }


    private fun showProfileCard(person: Person) {
        //C использованием Glide
        Glide.with(this).load(person.picture.large).into(binding.imagePerson)

        // С использованием Coil
        //binding.imagePerson.load(person.picture.large)
        binding.title.text = person.name.title
        binding.firstName.text = person.name.first
        binding.lastName.text = person.name.last

        binding.birthDate.text = person.dob.date
        binding.age.text = "${person.dob.age}"
        binding.gender.text = person.gender

        binding.street.text = person.location.street.number + ", " + person.location.street.name
        binding.city.text = person.location.city
        binding.state.text = person.location.state
        binding.country.text = person.location.country

        binding.phoneNumber.text = person.phone
        binding.cellNumber.text = person.cell
    }

    private fun setSearchStatus(state: State) {
        when (state) {

            State.Load -> {
                with(binding) {
                    progress.visibility = View.VISIBLE
                    buttonRefresh.isEnabled = false

                }
            }

            is State.Normal -> {
                with(binding) {
                    progress.visibility = View.INVISIBLE
                    buttonRefresh.isEnabled = true
                }
            }

            is State.Error -> {
                with(binding) {
                    progress.visibility = View.INVISIBLE
                    buttonRefresh.isEnabled = true
                    clearProfile()
                }
            }
        }
    }

    private fun clearProfile(){
        val context = requireContext()
        binding.imagePerson.load(requireActivity().getDrawable(R.mipmap.ic_launcher))
        binding.title.text = "Mr"
        binding.firstName.text = "not found"
        binding.lastName.text = "not found"

        binding.birthDate.text = "not found"
        binding.age.text = "not found"
        binding.gender.text = "not found"

        binding.street.text = "not found"
        binding.city.text = "not found"
        binding.state.text = "not found"
        binding.country.text = "not found"

        binding.phoneNumber.text = "not found"
        binding.cellNumber.text = "not found"
    }


}


