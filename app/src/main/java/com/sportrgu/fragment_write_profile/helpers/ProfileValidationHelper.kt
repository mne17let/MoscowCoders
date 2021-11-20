package com.sportrgu.fragment_write_profile.helpers

data class ProfileValidationHelper(
    private val name: String,
    private val lastName: String,
    private val gender: String,
    private val studyGroup: String,
    private val studyYear: String,
    private val faculty: String
) {

    private val emptyError = "Заполните поле"
    private val firstLetterNotUpperCase = "Первая буква не заглавная"
    private val notFirstLetterIsUpperCase = "Не первая буква заглавная"
    private val haveSpace = "Не может содержать пробелы"
    private val lessThanTwoLetter = "Слишком коротко"

    fun checkName(): Boolean {
        val haveNotSpace = haveNoSpace(name)
        val necessaryCase = firstLetterIsUpperCaseOtherAreLowerCase(name)
        val notShort = moreThanOneLetter(name)

        return (haveNotSpace && necessaryCase && notShort)
    }

    fun checkLastName(): Boolean{
        val haveNotSpace = haveNoSpace(lastName)
        val necessaryCase = firstLetterIsUpperCaseOtherAreLowerCase(lastName)
        val notShort = moreThanOneLetter(lastName)

        return (haveNotSpace && necessaryCase && notShort)
    }

    fun checkGender(): Boolean{
        return gender.isNotBlank()
    }

    fun checkStudyGroup(): Boolean{
        return haveNoSpace(studyGroup)
    }

    fun checkStudyYear(): Boolean{
        return studyYear.isNotBlank()
    }

    fun checkFaculty(): Boolean{
        return faculty.isNotBlank()
    }

    private fun haveNoSpace(string: String): Boolean{
        var result: Boolean = true

        val stringArray: CharArray = string.toCharArray()
        for (i in stringArray){
            if (i == ' '){
                result = false
                break
            }
        }

        return result
    }

    private fun firstLetterIsUpperCaseOtherAreLowerCase(string: String): Boolean{
        var result: Boolean = true

        val stringArray = string.toCharArray()

        if (stringArray[0].isLowerCase()){
            result = false
        } else{
            for(i in 1 until stringArray.size){
                if (stringArray[i].isUpperCase()){
                    result = false
                    break
                }
            }
        }

        return result
    }

    private fun firstLetterIsUpperCase(string: String): Boolean{
        var result: Boolean = true

        val stringArray = string.toCharArray()

        if (stringArray[0].isLowerCase()){
            result = false
        }

        return result
    }

    private fun moreThanOneLetter(string: String): Boolean{
        return string.length > 1
    }

    fun getNameError(): String{
        val result = if(!haveNoSpace(name)){
            haveSpace
        } else if(!firstLetterIsUpperCaseOtherAreLowerCase(name)){
            if (!firstLetterIsUpperCase(name)){
                firstLetterNotUpperCase
            } else{
                notFirstLetterIsUpperCase
            }
        } else if(name.length < 2){
            lessThanTwoLetter
        } else{
            "Неизвестная ошибка"
        }

        return result
    }

    fun getLastNameError(): String{
        val result = if(!haveNoSpace(lastName)){
            haveSpace
        } else if(!firstLetterIsUpperCaseOtherAreLowerCase(lastName)){
            if (!firstLetterIsUpperCase(lastName)){
                firstLetterNotUpperCase
            } else{
                notFirstLetterIsUpperCase
            }
        } else if(lastName.length < 2){
            lessThanTwoLetter
        } else{
            "Неизвестная ошибка"
        }

        return result
    }

    fun getGenderError(): String{
        val result = if(gender.isBlank()){
            emptyError
        } else{
            "Неизвестная ошибка"
        }

        return result
    }

    fun getStudyGroupError(): String{
        val result = if(studyGroup.isBlank()){
            emptyError
        } else if(!haveNoSpace(studyGroup)){
            haveSpace
        } else{
            "Неизвестная ошибка"
        }

        return result
    }

    fun getStudyYearError(): String{
        val result = if(studyYear.isBlank()){
            emptyError
        } else{
            "Неизвестная ошибка"
        }

        return result
    }

    fun getFacultyError(): String{
        val result = if(faculty.isBlank()){
            emptyError
        } else{
            "Неизвестная ошибка"
        }

        return result
    }
}