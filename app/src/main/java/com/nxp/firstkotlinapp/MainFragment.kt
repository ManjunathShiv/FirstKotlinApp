package com.nxp.firstkotlinapp


import android.app.ActionBar
import android.app.Activity
import android.app.Application
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelProviders.*
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var detailButton: Button
/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class MainFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var userVM : UserViewModel? = ViewModelProviders.of(activity!!).get(UserViewModel :: class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.actionBar?.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.title = "First View"
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        detailButton= view.findViewById(R.id.btn_detail)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.actionBar?.hide()
        detailButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToDetailFragment()

            val username = view?.findViewById(R.id.username) as? EditText
            val userString = username?.text.toString()

            val password = view?.findViewById(R.id.password) as? EditText
            val pwdString = password?.text.toString()


            print("---------$userString----------, $pwdString ----------")

            val user : UserEntity = UserEntity(1,userString, pwdString)

            userVM?.insert(user)
            val allUsers : LiveData<List<UserEntity>>? = userVM?.fetchUsers()
            println("All Users : $allUsers")

            action.arg1 = userString
            action.arg2 = pwdString
            Navigation.findNavController(it).navigate(action)

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
