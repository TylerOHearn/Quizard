package com.tylersapps.quizard

class QuestionUtil {
    companion object {


        fun getQuestions(): ArrayList<Question> {
            val questionsList = ArrayList<Question>()

            val que1 = Question(
                1, "What country does this flag belong to?",
                R.drawable.ic_flag_of_argentina,
                "Argentina", "Australia", "Armenia", "Austria",
                1
            )
            questionsList.add(que1)

            val que2 = Question(
                2, "What country does this flag belong to?",
                R.drawable.ic_flag_of_australia,
                "Argentina", "Australia", "Armenia", "Austria",
                2
            )
            questionsList.add(que2)

            val que3 = Question(
                3, "What country does this flag belong to?",
                R.drawable.ic_flag_of_belgium,
                "Belgium", "Bulgaria", "Bolivia", "Peru",
                1
            )
            questionsList.add(que3)

            val que4 = Question(
                4, "What country does this flag belong to?",
                R.drawable.ic_flag_of_brazil,
                "Bolivia", "Bulgaria", "Barbados", "Brazil",
                4
            )
            questionsList.add(que4)

            val que5 = Question(
                5, "What country does this flag belong to?",
                R.drawable.ic_flag_of_denmark,
                "Botswana", "Colombia", "Denmark", "Austria",
                3
            )
            questionsList.add(que5)

            val que6 = Question(
                6, "What country does this flag belong to?",
                R.drawable.ic_flag_of_fiji,
                "El Salvador", "Fiji", "Zimbabwe", "Mexico",
                2
            )
            questionsList.add(que6)

            val que7 = Question(
                7, "What country does this flag belong to?",
                R.drawable.ic_flag_of_germany,
                "Mozambique", "Singapore", "Germany", "Spain",
                3
            )
            questionsList.add(que7)

            val que8 = Question(
                8, "What country does this flag belong to?",
                R.drawable.ic_flag_of_india,
                "Germany", "Mexico", "South Africa", "India",
                4
            )
            questionsList.add(que8)

            val que9 = Question(
                9, "What country does this flag belong to?",
                R.drawable.ic_flag_of_kuwait,
                "Kuwait", "Uruguay", "Laos", "Japan",
                1
            )
            questionsList.add(que9)

            val que10 = Question(
                10, "What country does this flag belong to?",
                R.drawable.ic_flag_of_new_zealand,
                "Australia", "New Zealand", "Austria", "Sweden",
                2
            )
            questionsList.add(que10)

            return questionsList
        }
    }
}