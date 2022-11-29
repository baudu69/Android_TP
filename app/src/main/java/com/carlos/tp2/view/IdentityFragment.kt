package com.carlos.tp2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.carlos.tp2.R
import com.carlos.tp2.data.User
import com.carlos.tp2.databinding.FragmentIdentityBinding
import com.carlos.tp2.viewmodel.IdentityViewModel
import com.carlos.tp2.viewmodel.IdentityViewModelFactory

class IdentityFragment : Fragment() {
    private lateinit var binding: FragmentIdentityBinding
    private lateinit var viewModel: IdentityViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_identity,
            container, false)
        viewModel = ViewModelProviders.of(this)[IdentityViewModel::class.java]
        binding.viewModel = viewModel
// viewModel.user.observe(this, Observer { user ->
// binding.user = user
// })
        binding.apply {
            tvTitle.text = getString(R.string.title)
            tiFirstname.hint = getString(R.string.firstname)
            tiLastname.hint = getString(R.string.lastname)
            btValidate.text = getString(R.string.validate)
        }
        binding.btValidate.setOnClickListener {
            validate(it)
        }
        return binding.root
    }
    private fun validate(view: View) {
        var t = 0

        view.findNavController().navigate(IdentityFragmentDirections.actionIdentityFragmentToPersonalDataFragment(viewModel.user.value?:User()))
    }
}
