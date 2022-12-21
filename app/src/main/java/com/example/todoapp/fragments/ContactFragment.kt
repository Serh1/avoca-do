package com.example.todoapp.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.todoapp.R
private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"
class ContactFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        private val retrofit = Retrofit.Builder()
//            .addConverterFactory(ScalarsConverterFactory.create())
//            .baseUrl(BASE_URL)
//            .build()

        return inflater.inflate(R.layout.fragment_contact, container, false)
    }


}