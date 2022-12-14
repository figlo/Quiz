package sk.figlar.quiz

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): List<Question> {

        val questions: MutableList<Question> = mutableListOf()

        val question1 = Question(
            id = 1,
            text = "What country does this flag belong to?",
            image = R.drawable.ic_flag_of_argentina,
            options = listOf("Argentina", "Australia", "Armenia", "Austria"),
            correctAnswer = 0
        )
        questions.add(question1)

        val question2 = Question(
            id = 2,
            text = "What country does this flag belong to?",
            image = R.drawable.ic_flag_of_belgium,
            options = listOf("Bahrain", "Burundi", "Belgium", "Bulgaria"),
            correctAnswer = 2
        )
        questions.add(question2)

        val question3 = Question(
            id = 3,
            text = "What country does this flag belong to?",
            image = R.drawable.ic_flag_of_denmark,
            options = listOf("Norway", "Sweden", "Finland", "Denmark"),
            correctAnswer = 3
        )
        questions.add(question3)

        return questions
    }
}