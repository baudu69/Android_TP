package com.carlos.tp2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.carlos.tp2.R
import com.carlos.tp2.database.MyDatabase
import com.carlos.tp2.databinding.FragmentIdentityBinding
import com.carlos.tp2.viewmodel.IdentityViewModel
import com.carlos.tp2.viewmodel.IdentityViewModelFactory

class IdentityFragment : Fragment() {
    private lateinit var binding: FragmentIdentityBinding
    private lateinit var viewModel: IdentityViewModel
    private lateinit var viewModelFactory: IdentityViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_identity, container, false)
        val dao = MyDatabase.getInstance(requireNotNull(this.activity).application).userDao
        viewModelFactory = IdentityViewModelFactory(dao)
        viewModel = ViewModelProvider(this, viewModelFactory)[IdentityViewModel::class.java]
        binding.viewModel = viewModel
        binding.apply {
            tvTitle.text = getString(R.string.title)
            tiFirstname.hint = getString(R.string.firstname)
            tiLastname.hint = getString(R.string.lastname)
            btValidate.text = getString(R.string.validate)
        }

        viewModel.navigateToPersonalDataFragment.observe(viewLifecycleOwner) { userID ->
            userID?.let {
                this.findNavController().navigate(
                    IdentityFragmentDirections
                        .actionIdentityFragmentToPersonalDataFragment(userID)
                )
                viewModel.doneNavigating()
            }
        }


        return binding.root
    }
}
