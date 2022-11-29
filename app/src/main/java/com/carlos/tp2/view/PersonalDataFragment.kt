package com.carlos.tp2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseMethod
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.carlos.tp2.R
import com.carlos.tp2.data.LongConverter
import com.carlos.tp2.data.User
import com.carlos.tp2.database.MyDatabase
import com.carlos.tp2.databinding.FragmentPersonalDataBinding
import com.carlos.tp2.viewmodel.IdentityViewModel
import com.carlos.tp2.viewmodel.IdentityViewModelFactory
import com.carlos.tp2.viewmodel.PersonalDataViewModel
import com.carlos.tp2.viewmodel.PersonalDataViewModelFactory
import java.text.SimpleDateFormat
import java.util.*



interface PersonalDateEventListener {
    fun onGender(gender: String)
}

class PersonalDataFragment : Fragment() { //, PersonalDateEventListener {
    private lateinit var binding: FragmentPersonalDataBinding
    private lateinit var viewModel: IdentityViewModel
    private lateinit var viewModelFactory: IdentityViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_personal_data, container, false)
// binding.eventListener = this
        val args = PersonalDataFragmentArgs.fromBundle(requireArguments())
        val context = requireNotNull(this.activity).applicationContext
        viewModelFactory = IdentityViewModelFactory(MyDatabase.getInstance(context).userDao, requireActivity().application)
        viewModel = ViewModelProvider(this, viewModelFactory)[IdentityViewModel::class.java]
        //viewModel = ViewModelProviders.of(this, viewModelFactory)
        // .get(PersonalDataViewModel::class.java)
        // depreciate
        binding.viewModel = viewModel
        binding.apply {
            evBirthday.hint = getString(R.string.birthdayDate)
            btValidate.text = getString(R.string.validate)
        }
        binding.btValidate.setOnClickListener {
            validate(it)
        }
        return binding.root
    }
    // override fun onGender(gender: String) {
// viewModel.onGender(gender)
// }
    private fun validate(view: View) {
        val message = viewModel.user.value?.gender + " " +
                LongConverter.longToString(viewModel.user.value?.birthdayDate)
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }
}

