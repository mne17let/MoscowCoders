package com.moscowcoders.fragment_write_profile.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.moscowcoders.MainActivity
import com.moscowcoders.R
import com.moscowcoders.data.models.people.StudentModel
import com.moscowcoders.fragment_write_profile.helpers.SendProfileDataHelper_FB
import com.moscowcoders.fragment_write_profile.helpers.ProfileValidationHelper

class WriteProfileFragment: Fragment(R.layout.fragment_create_profile) {

    private val viewModel = ViewModelProvider(this).get(WriteProfileViewModel::class.java)

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
    private lateinit var buttonSendProfile: Button

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

        buttonSendProfile = view.findViewById(R.id.id_button_send_first_profile_data)

        setViews()
    }

    private fun setViews(){
        setNameEditText()
        setLastNameEditText()
        setGenderEditText()
        setStudyGroupEditText()
        setStudyYearEditText()
        setFacultyEditText()
        setSendProfileButton()
        // setBioOrFavSportsEditText()
    }

    private fun setNameEditText(){
        nameEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                nameTextInputLayout.error = ""
            }

        })
    }

    private fun setLastNameEditText(){
        lastNameEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                lastNameTextInputLayout.error = ""
            }

        })
    }

    private fun setStudyGroupEditText(){
        studyGroupEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                studyGroupTextInputLayout.error = ""
            }

        })
    }

    private fun setGenderEditText(){
        val listOfGenders = resources.getStringArray(R.array.genders)
        val genderAdapter =  ArrayAdapter(requireContext(), R.layout.item_for_edittexts_menu, listOfGenders)

        genderEditText.setAdapter(genderAdapter)

        genderEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                genderTextInputLayout.error = ""
            }

        })
    }

    private fun setStudyYearEditText(){
        val listOfStudyYears = listOf("1 курс", "2 курс", "3 курс", "4 курс", "5 курс", "1 курс магистратуры", "2 курс магистратуры")
        val studyYearsAdapter =  ArrayAdapter(requireContext(), R.layout.item_for_edittexts_menu, listOfStudyYears)

        studyYearEditText.setAdapter(studyYearsAdapter)

        studyYearEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                studyYearTextInputLayout.error = ""
            }

        })
    }

    private fun setFacultyEditText(){
        val listOfFaculties = listOf("ФИМ", "ФАиВТ", "ФГиГНиГ", "ФРНиГМ", "ФПСиЭСТТ", "ФХТиЭ", "ФКБТЭК", "ФЭиУ", "ФМЭБ", "ЮФ")
        val facultiesAdapter =  ArrayAdapter(requireContext(), R.layout.item_for_edittexts_menu, listOfFaculties)

        facultyEditText.setAdapter(facultiesAdapter)

        facultyEditText.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                facultyTextInputLayout.error = ""
            }

        })
    }

    private fun setSendProfileButton(){
        buttonSendProfile.setOnClickListener {

            if (!areAllEditTextNotEmpty()){
                return@setOnClickListener
            }

            val dataFromEditTexts = DataFromEditTexts(
                nameEditText.text.toString(),
                lastNameEditText.text.toString(),
                genderEditText.text.toString(),
                studyGroupEditText.text.toString(),
                studyYearEditText.text.toString(),
                facultyEditText.text.toString()
            )

            if (!notShowError(dataFromEditTexts)){
                return@setOnClickListener
            }

            val firebaseHelper = SendProfileDataHelper_FB()

            val currentUser = firebaseHelper.getCurrentUser()


            if(currentUser != null){
                val id = currentUser.uid
                val email = currentUser.email
                val fName: String = dataFromEditTexts.fName
                val lName: String = dataFromEditTexts.lName
                val gender: String = dataFromEditTexts.gender
                val studyGroup: String = dataFromEditTexts.studyGroup
                val studyYear: String = dataFromEditTexts.studyYear
                val faculty: String = dataFromEditTexts.faculty
                val newStudentModel = StudentModel(id, email, fName, lName, gender, studyGroup, studyYear, faculty)

                firebaseHelper.sendNewProfileSettings(newStudentModel, object :
                    SendProfileSettingsCallback {
                    override fun onSuccess(data: String) {
                        Toast.makeText(requireContext(), "$data", Toast.LENGTH_SHORT).show()

                        viewModel.saveProfileData(newStudentModel)

                        (activity as MainActivity).showSportObjectsListAfterLogIn()
                    }

                    override fun onError(error: String) {
                        Toast.makeText(requireContext(), "$error", Toast.LENGTH_SHORT).show()
                    }

                })
            } else {
                Toast.makeText(requireContext(), "Email текущего пользователя пуст", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }
    }

    private fun areAllEditTextNotEmpty(): Boolean {
        val listOfEditTexts = mutableListOf(nameEditText, lastNameEditText, genderEditText, studyGroupEditText, studyYearEditText, facultyEditText)

        var noEmptyEditText = true
        for (editText in listOfEditTexts){
            if (editText.text.isBlank()){
                val parent: TextInputLayout = editText.parent.parent as TextInputLayout
                parent.error = getString(R.string.empty_profile_setting_text)

                noEmptyEditText = false
            }
        }

        return noEmptyEditText
    }

    private fun notShowError(dataFromEditTexts: DataFromEditTexts): Boolean {
        val helper = ProfileValidationHelper(
            dataFromEditTexts.fName,
            dataFromEditTexts.lName,
            dataFromEditTexts.gender,
            dataFromEditTexts.studyGroup,
            dataFromEditTexts.studyYear,
            dataFromEditTexts.faculty
        )

        val isCorrectName = helper.checkName()
        val isCorrectLastName = helper.checkLastName()
        val isCorrectGender = helper.checkGender()
        val isCorrectStudyGroup = helper.checkStudyGroup()
        val isCorrectStudyYear = helper.checkStudyYear()
        val isCorrectFaculty = helper.checkFaculty()

        if (!isCorrectName){
            nameTextInputLayout.error = helper.getNameError()
        }

        if (!isCorrectLastName){
            lastNameTextInputLayout.error = helper.getLastNameError()
        }

        if (!isCorrectGender){
            genderTextInputLayout.error = helper.getGenderError()
        }

        if (!isCorrectStudyGroup){
            studyGroupTextInputLayout.error = helper.getStudyGroupError()
        }

        if (!isCorrectStudyYear){
            studyYearTextInputLayout.error = helper.getStudyYearError()
        }

        if (!isCorrectFaculty){
            facultyTextInputLayout.error = helper.getFacultyError()
        }

        val result: Boolean
        if (!isCorrectName || !isCorrectLastName || !isCorrectGender || !isCorrectStudyGroup || !isCorrectStudyYear || !isCorrectFaculty){
            result = false
        } else{
            result = true
            // Toast.makeText(requireContext(), "Ошибок нет", Toast.LENGTH_SHORT).show()
        }

        return result
    }

    interface SendProfileSettingsCallback{
        fun onSuccess(data: String)

        fun onError(error: String)
    }

    data class DataFromEditTexts(
        val fName: String,
        val lName: String,
        val gender: String,
        val studyGroup: String,
        val studyYear: String,
        val faculty: String,
        val bioOrFavouriteSports: String? = null
    )
}