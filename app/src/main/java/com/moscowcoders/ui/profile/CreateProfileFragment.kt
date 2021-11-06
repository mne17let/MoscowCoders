package com.moscowcoders.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.moscowcoders.R
import com.moscowcoders.data.models.people.StudentModel
import com.moscowcoders.data.room.StudentApplication
import com.moscowcoders.data.room.StudentViewModel
import com.moscowcoders.data.room.StudentViewModelFactory

class CreateProfileFragment: Fragment(R.layout.fragment_create_profile) {

    private val viewModel: StudentViewModel by activityViewModels{
        StudentViewModelFactory(
            (activity?.application as StudentApplication).database
                .studentDao()
        )

    }
    lateinit var student:StudentModel

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

    private fun isStudentValid(): Boolean{
        return viewModel.isStudentValid(

           nameEditText.text.toString(),
            lastNameEditText.text.toString(),
            genderEditText.text.toString(),
            studyGroupEditText.text.toString(),
            studyYearEditText.text.toString(),
            facultyEditText.text.toString(),
            bioOrFavSportsEditText.text.toString())

    }
    private fun addNewStudent() {
        if (isStudentValid()) {
            viewModel.addNewItem(
            nameEditText.text.toString(),
            lastNameEditText.text.toString(),
            genderEditText.text.toString(),
            studyGroupEditText.text.toString(),
            studyYearEditText.text.toString(),
            facultyEditText.text.toString(),
            bioOrFavSportsEditText.text.toString())

        }

    }
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

        val listOfGenders = resources.getStringArray(R.array.genders)
        val genderAdapter =  ArrayAdapter(requireContext(), R.layout.item_edittext_menu_gender, listOfGenders)

        genderEditText.setAdapter(genderAdapter)
        addNewStudent()
        setViews()

    }

    private fun setViews(){
        setGenderEditText()
        setStudyYearEditText()
        setFacultyEditText()
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

        facultyEditText.setAdapter(facultiesAdapter)
    }

}