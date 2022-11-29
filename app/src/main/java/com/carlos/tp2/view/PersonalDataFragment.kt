package com.carlos.tp2.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.carlos.tp2.R
import com.carlos.tp2.data.LongConverter
import com.carlos.tp2.database.MyDatabase
import com.carlos.tp2.databinding.FragmentPersonalDataBinding
import com.carlos.tp2.viewmodel.IdentityViewModel
import com.carlos.tp2.viewmodel.IdentityViewModelFactory

class PersonalDataFragment : Fragment() {
    private lateinit var binding: FragmentPersonalDataBinding
    private lateinit var viewModel: IdentityViewModel
    private lateinit var viewModelFactory: IdentityViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_personal_data, container, false)
        val args = PersonalDataFragmentArgs.fromBundle(requireArguments())
        val context = requireNotNull(this.activity).applicationContext
        val userId = args.userID
        viewModelFactory = IdentityViewModelFactory(MyDatabase.getInstance(context).userDao, userId)
        viewModel = ViewModelProvider(this, viewModelFactory)[IdentityViewModel::class.java]
        binding.viewModel = viewModel
        binding.apply {
            evBirthday.hint = getString(R.string.birthdayDate)
            btValidate.text = getString(R.string.validate)
        }
        viewModel.navigateToOtherActivity.observe(viewLifecycleOwner) { user ->
            user?.let {
                val message = viewModel.user.value?.gender + " " +
                        LongConverter.longToString(viewModel.user.value?.birthdayDate?:0)
                Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
                viewModel.doneValidateNavigating()
            }
        }
        return binding.root
    }
}

