package sk.figlar.quiz

data class Question(
    val id: Int,
    val questionText: String,
    val image: Int,
    val options: List<String>,
    val correctAnswer: Int,
)