package com.moscowcoders.authentication

class ValidationHelper {

    fun checkEmail(string: String): Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(string).matches()
    }

    fun checkPass(string: String): Boolean{
        return string.length > 5 && !checkSpacesInPass(string)
    }

    private fun checkSpacesInPass(string: String): Boolean{
        var haveSpace = false
        val passArray: CharArray = string.toCharArray()
        for (i in passArray){
            if (i == ' '){
                haveSpace = true
                break
            }
        }

        return haveSpace
    }

    fun getEmailError(string: String): String{
        val result = if (string.isBlank()){
            "Введите почту"
        } else if(!checkEmail(string)){
            "Некорректная почта"
        } else{
            ""
        }
        return result
    }

    fun getPassError(string: String): String{
        val result = if(!(string.length > 5)){
            "Пароль должен быть длиннее 5 символов"
        } else if(checkSpacesInPass(string)){
            "Пароль не должен содержать пробелы"
        } else{
            ""
        }
        return result
    }
}