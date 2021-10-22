package com.moscowcoders.ui.profile

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.moscowcoders.R

class CreateProfileFragment: Fragment(R.layout.fragment_create_profile) {

    private lateinit var nameTextInputLayout: TextInputLayout
    private lateinit var nameEditText: TextInputEditText
    private lateinit var lastNameTextInputLayout: TextInputLayout
    private lateinit var lastNameEditText: TextInputEditText
    private lateinit var genderTextInputLayout: TextInputLayout
    private lateinit var genderEditText: AutoCompleteTextView
    private lateinit var studyGroupTextInputLayout: TextInputLayout
    private lateinit var studyGroupEditText: TextInputEditText
    private lateinit var studyYearTextInputLayout: TextInputLayout
    private lateinit var studyYearEditText: AutoCompleteTextView
    private lateinit var facultyTextInputLayout: TextInputLayout
    private lateinit var facultyEditText: AutoCompleteTextView
    private lateinit var bioOrFavSportsTextInputLayout: TextInputLayout
    private lateinit var bioOrFavSportsEditText: TextInputEditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameTextInputLayout = view.findViewById(R.id.id_textinputlayout_firstname)
        nameEditText = view.findViewById(R.id.id_edittext_firstname)
        lastNameTextInputLayout = view.findViewById(R.id.id_textinputlayout_lastname)
        lastNameEditText = view.findViewById(R.id.id_edittext_lastname)
        genderTextInputLayout = view.findViewById(R.id.id_textinputlayout_gender)
        genderEditText = view.findViewById(R.id.id_edittext_gender)
        studyGroupTextInputLayout = view.findViewById(R.id.id_textinputlayout_study_group)
        studyGroupEditText = view.findViewById(R.id.id_edittext_study_group)
        studyYearTextInputLayout = view.findViewById(R.id.id_textinputlayout_study_year)
        studyYearEditText = view.findViewById(R.id.id_edittext_study_year)
        facultyTextInputLayout = view.findViewById(R.id.id_textinputlayout_faculty)
        facultyEditText = view.findViewById(R.id.id_edittext_faculty)
        bioOrFavSportsTextInputLayout = view.findViewById(R.id.id_textinputlayout_bio_or_fav_sports)
        bioOrFavSportsEditText = view.findViewById(R.id.id_edittext_bio_or_fav_sports)

        val listOfGenders = listOf("Мужчина", "Женщина")
        val genderAdapter =  ArrayAdapter(requireContext(), R.layout.item_edittext_menu_gender, listOfGenders)

        genderEditText.setAdapter(genderAdapter)

        setViews()

    }

    private fun setViews(){
        setGenderEditText()
        //setStudyYearEditText()
        //setFacultyEditText()
    }

    private fun setGenderEditText(){

    }

    private fun setStudyYearEditText(){
        val listOfStudyYears = listOf("1 курс", "2 курс", "3 курс", "4 курс", "5 курс", "1 курс магистратуры", "2 курс магистратуры")
        val studyYearsAdapter =  ArrayAdapter(requireContext(), R.layout.item_edittext_menu_gender, listOfStudyYears)

        studyYearEditText.setAdapter(studyYearsAdapter)
    }

    private fun setFacultyEditText(){
        val listOfFaculties = listOf("ФИМ", "ФАиВТ", "ФГиГНиГ", "ФРНиГМ", "ФПСиЭСТТ", "ФХТиЭ", "ФКБТЭК", "ФЭиУ", "ФМЭБ", "ЮФ")
        val facultiesAdapter =  ArrayAdapter(requireContext(), R.layout.item_edittext_menu_gender, listOfFaculties)

        studyYearEditText.setAdapter(facultiesAdapter)
    }

}